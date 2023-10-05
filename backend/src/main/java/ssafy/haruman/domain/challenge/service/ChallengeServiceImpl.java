package ssafy.haruman.domain.challenge.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.challenge.dto.response.*;
import ssafy.haruman.domain.challenge.entity.*;
import ssafy.haruman.domain.challenge.repository.ChallengeRepository;
import ssafy.haruman.domain.challenge.repository.ChallengeUserInfoMapping;
import ssafy.haruman.domain.challenge.repository.ExpenseRepository;
import ssafy.haruman.domain.deposit.dto.request.DepositCreateRequestDto;
import ssafy.haruman.domain.deposit.dto.response.DepositSimpleResponseDto;
import ssafy.haruman.domain.deposit.service.DepositService;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.error.exception.ChallengeAlreadyExistsException;
import ssafy.haruman.global.error.exception.ChallengeWrongDataException;
import ssafy.haruman.global.gpt.dto.response.CompletionChatResponse;
import ssafy.haruman.global.gpt.service.GPTChatRestService;
import ssafy.haruman.global.gpt.vo.BankProduct;
import ssafy.haruman.global.service.S3FileService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ExpenseRepository expenseRepository;
    private final RedisTemplate<String, Float> floatRedisTemplate;
    private final S3FileService s3FileService;
    private final String FILE_NAME = "bank_products.json";
    private final GPTChatRestService gptChatRestService;
    private final DepositService depositService;
    private static StringBuilder sb = new StringBuilder();


    @Override
    @Transactional
    public DailyChallengeResponseDto startChallenge(Profile profile) {

        Optional<Challenge> challenge = challengeRepository.findFirstChallenge(profile.getId());
        if (challenge.isPresent() && challenge.get().getChallengeStatus().equals(ChallengeStatus.PROGRESS)) {
            throw ChallengeAlreadyExistsException.EXCEPTION;
        }

//        if (LocalTime.now().isBefore(LocalTime.of(5, 0))
//                || LocalTime.now().isAfter(LocalTime.of(12, 0))) {
//            throw ChallengeNotAvailableException.EXCEPTION;
//        }

        Challenge createdChallenge = Challenge.builder()
                .profile(profile)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0))
                .challengeStatus(ChallengeStatus.PROGRESS)
                .targetAmount(10000)
                .usedAmount(0)
                .leftoverAmount(10000)
                .isViewed(ViewStatus.NOT_VIEWED)
                .build();

        return DailyChallengeResponseDto.from(challengeRepository.save(createdChallenge),
                                              challengeRepository.countByStatus() - 1);
    }

    @Override
    @Transactional
    public DailyChallengeResponseDto selectDailyChallenge(Profile profile) {

        Optional<Challenge> firstChallenge = challengeRepository.findFirstChallenge(profile.getId());
        Integer participantCount = challengeRepository.countByStatus() - 1;

        if (firstChallenge.isPresent()) {
            Challenge challenge = firstChallenge.get();
            if (challenge.getChallengeStatus().equals(ChallengeStatus.PROGRESS)) {
                if (challenge.getIsViewed().equals(ViewStatus.NOT_VIEWED)) {
                    // 1. 챌린지 진행 중인 경우
                    return DailyChallengeResponseDto.from(challenge, participantCount);
                }
            } else {
                if (challenge.getIsViewed().equals(ViewStatus.NOT_VIEWED)) {
                    // 2. 챌린지 진행 중이 아니며, 이전 챌린지의 결과를 아직 확인하지 않은 경우
                    challenge.updateViewStatus(ViewStatus.VIEWED);
                    return DailyChallengeResponseDto.from(challenge, participantCount);
                } else {
                    // 3. 챌린지 진행 중이 아니며, 이전 챌린지의 결과를 확인한 경우
                    return DailyChallengeResponseDto.from(participantCount);
                }
            }
        } else { // 4. 아직 한 번도 챌린지를 진행한 적이 없는 경우
            return DailyChallengeResponseDto.from(participantCount);
        }

        throw ChallengeWrongDataException.EXCEPTION;
    }

    @Override
    public List<ChallengeUserListResponseDto> selectChallengeUserList() {

        List<ChallengeUserInfoMapping> challengeList = challengeRepository.findChallengeAndExpenseAndProfileByStatus();

        return challengeList.stream()
                .collect(Collectors.groupingBy(this::getGroupKey))
                .entrySet().stream()
                .map(entry -> ChallengeUserListResponseDto.from(entry.getKey(), convertToUserInfoDto(entry.getValue())))
                .collect(Collectors.toList());
    }

    @Override
    public AccumulatedAmountResponseDto selectAccumulatedAmount(Profile profile) {

        Integer accumulatedAmount = challengeRepository.sumByProfileAndStatus(profile.getId());

        return AccumulatedAmountResponseDto.builder().accumulatedAmount(accumulatedAmount).build();
    }

    @Override
    public List<ChallengeHistoryResponseDto> selectChallengeHistory(Profile profile, Date yearAndMonth) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String date = yearAndMonth == null ?
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM")) : dateFormat.format(yearAndMonth);

        List<Challenge> challengeList = challengeRepository.findAllByProfileAndDate(profile.getId(), date);

        return challengeList.stream()
                .map(ChallengeHistoryResponseDto::from)
                .collect(Collectors.toList());
    }

    private String getGroupKey(ChallengeUserInfoMapping challenge) {
        ChallengeGroup group = ChallengeGroup.getGroup(challenge.getUsedAmount());
        return group.getGroupKey();
    }

    private List<ChallengeUserInfoDto> convertToUserInfoDto(List<ChallengeUserInfoMapping> list) {
        return list.stream()
                .map(item -> {

                    String profileImageUrl = s3FileService.getS3Url(item.getProfileImagePath(), item.getProfileImageName());
                    return ChallengeUserInfoDto.from(item, profileImageUrl);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void endChallenge() throws IOException {

        List<Challenge> list = challengeRepository.findAllByStatus();
        // 챌린지가 없을 때 빈리스트가 반환되어 아무 결과 처리 없음
        for (Challenge challenge : list) {

            if (challenge.getTargetAmount() - challenge.getUsedAmount() < 0) {
                challenge.updateChallengeStatus(ChallengeStatus.FAIL);
            } else {
                challenge.updateChallengeStatus(ChallengeStatus.SUCCESS);
                depositRecommend(challenge);
            }
        }
    }

    @Override
    @Transactional
    public void testEndChallenge() throws IOException {

        List<Challenge> list = challengeRepository.findAllByStatus();
        // 챌린지가 없을 때 빈리스트가 반환되어 아무 결과 처리 없음
        for (Challenge challenge : list) {
            if (challenge.getTargetAmount() - challenge.getUsedAmount() < 0) {
                challenge.updateChallengeStatus(ChallengeStatus.FAIL);
            } else {
                challenge.updateChallengeStatus(ChallengeStatus.SUCCESS);
                depositRecommend(challenge);
            }
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 55 23 * * *")
    public void preRecommend() throws IOException {

        List<Challenge> list = challengeRepository.findAllByStatus();
        // 챌린지가 없을 때 빈리스트가 반환되어 아무 결과 처리 없음

        for (Challenge challenge : list) {
            if (challenge.getTargetAmount() - challenge.getUsedAmount() < 0) {
            } else {
                depositRecommend(challenge);
            }
        }
    }

    @Override
    @Transactional
    public void depositRecommend(Challenge challenge) throws IOException {

        ValueOperations<String, Float> valueOperations = floatRedisTemplate.opsForValue();
        List<BankProduct> bankProductList = gptChatRestService.parseJsonFileToBankProductList();
        StringBuilder Bank = gptChatRestService.sendBankProductListToGPT(bankProductList);

        sb.append("선호하는 카테고리 : ");

        valueOperations.set("cnt", (float) (valueOperations.get("cnt") + 1f));
        List<Expense> expenseList = expenseRepository.findAllByChallenge(challenge);
        List<DepositCreateRequestDto> dtoList = new ArrayList<>();
        // 카테고리별 소비 총액을 저장할 맵 생성
        Map<String, Float> categoryTotalSpent = new HashMap<>();

        // 전체 소비 총액을 저장할 변수
        float totalSpent = 0.0f;

        // Expense 항목을 반복하면서 카테고리별 소비 총액 계산
        for (Expense expense : expenseList) {
            String categoryName = expense.getCategory().getName();
            float payAmount = expense.getPayAmount();

            // 카테고리별 소비 총액 업데이트
            categoryTotalSpent.put(categoryName, categoryTotalSpent.getOrDefault(categoryName, 0.0f) + payAmount);

            // 전체 소비 총액 업데이트
            totalSpent += payAmount;

        }

        // 카테고리별 소비 비율을 계산하고 Redis에 저장
        for (Map.Entry<String, Float> entry : categoryTotalSpent.entrySet()) {
            String categoryName = entry.getKey();
            float categorySpent = entry.getValue();
            float categoryRatio = (categorySpent / totalSpent) * 100.0f;

            if (valueOperations.get(categoryName) == null) {
                valueOperations.set(categoryName, 0.0f);
            }
            float preCategoryRatio = valueOperations.get(categoryName);
            System.out.println("pre" + categoryName + preCategoryRatio);
            // Redis에 카테고리별 소비 비율 저장
            valueOperations.set(categoryName, ((preCategoryRatio * (valueOperations.get("cnt") - 1) + categoryRatio) / valueOperations.get("cnt")));
            if (valueOperations.get(categoryName) <= categoryRatio) {
                sb.append(categoryName).append(", ");
            }
        }

        sb.append("\n").append("내가 알려준 적금 중에서 적합한 적금 3개를 (bank, name, description, interestRate) 각각을 키로 JSON 배열형태로 알려줘(interestRate에 % 붙이지마)");
        CompletionChatResponse completionChatResponse = gptChatRestService.GPT(Bank.toString() + sb.toString());
        sb.setLength(0);
        System.out.println(sb.toString());
        for (int i = 0; i < completionChatResponse.getMessages().size(); i++) {
            String message = completionChatResponse.getMessages().get(i).getMessage();
            JsonArray jsonArray = JsonParser.parseString(message).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String bank = jsonObject.get("bank").getAsString();
                String name = jsonObject.get("name").getAsString();
                String description = jsonObject.get("description").getAsString();
                float interestRate = jsonObject.get("interestRate").getAsFloat();

                DepositCreateRequestDto dto = new DepositCreateRequestDto(bank, name, description, interestRate);
                dtoList.add(dto);
            }
        }
        List<DepositSimpleResponseDto> createdDeposit = depositService.createDepositList(challenge.getProfile(), dtoList);
    }
}
