package ssafy.haruman.domain.deposit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.haruman.domain.deposit.entity.Deposit;
import ssafy.haruman.domain.deposit.dto.request.DepositCreateRequestDto;
import ssafy.haruman.domain.deposit.dto.request.DepositUpdateRequestDto;
import ssafy.haruman.domain.deposit.dto.response.DepositDetailResponseDto;
import ssafy.haruman.domain.deposit.dto.response.DepositSimpleResponseDto;
import ssafy.haruman.domain.deposit.repository.DepositRepository;
import ssafy.haruman.domain.profile.entity.Profile;


@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService{
    private final DepositRepository depositRepository;
    @Override
    @Transactional
    public DepositSimpleResponseDto createDeposit(Profile profile,
            DepositCreateRequestDto createDto) {

        Optional<Deposit> deposit =
                depositRepository.findByProfileAndName(profile,createDto.getName());

//        if(deposit.isPresent()){
//            throw DepositDuplicateException.EXCEPTION;
//        }

        Deposit createdDeposit = Deposit.builder()
                .profile(profile)
                .bank(createDto.getBank())
                .name(createDto.getName())
                .description(createDto.getDescription())
                .interestRate(createDto.getInterestRate())
                .build();

        return DepositSimpleResponseDto.from(depositRepository.save(createdDeposit));
    }

    @Override
    @Transactional
    public List<DepositSimpleResponseDto> createDepositList(Profile profile, List<DepositCreateRequestDto> createDtoList) {

        // 중복 체크 및 객체 생성을 위한 List
        List<Deposit> depositsToCreate = new ArrayList<>();

        List<Deposit> existingDeposit = depositRepository.findAllByProfile(profile);
        if(!existingDeposit.isEmpty()){
            depositRepository.deleteAll(existingDeposit);
        }

        for (DepositCreateRequestDto createDto : createDtoList) {

//            if(existingDeposit.isPresent()){
//                // 이미 존재하는 경우에 대한 처리
//                throw DepositDuplicateException.EXCEPTION;
//            }

            Deposit deposit = Deposit.builder()
                    .profile(profile)
                    .bank(createDto.getBank())
                    .name(createDto.getName())
                    .description(createDto.getDescription())
                    .interestRate(createDto.getInterestRate())
                    .build();

            depositsToCreate.add(deposit);
        }

        // 여러 개의 Deposit 객체 저장
        List<Deposit> createdDeposits = depositRepository.saveAll(depositsToCreate);

        // 생성된 Deposit 객체들을 ResponseDto로 변환하여 반환
        return createdDeposits.stream()
                .map(DepositSimpleResponseDto::from)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public DepositSimpleResponseDto updateDeposit(Profile profile,
            DepositUpdateRequestDto updateDto) {

        Deposit deposit = depositRepository.findById(updateDto.getDepositId()).get();

//        if (deposit.getIsDefault() == CustomStatus.DEFAULT || !deposit.getProfile().getId().equals(profile.getId())) {
//            throw DepositUnauthorizedException.EXCEPTION;
//        }

        deposit.updateDeposit(updateDto.getBank(), updateDto.getName(), updateDto.getDescription(), updateDto.getInterestRate());

        return DepositSimpleResponseDto.from(deposit);
    }

    @Override
    @Transactional
    public void deleteDeposit(Profile profile, Long depositId) {
        Deposit deposit = depositRepository.findById(depositId).get();

//        if (!deposit.getProfile().getId().equals(profile.getId())) {
//            throw DepositUnauthorizedException.EXCEPTION;
//        }
        depositRepository.delete(deposit);
    }
    @Override
    @Transactional
    public void deleteDepositAll(Profile profile) {
        List<Deposit> deposit = depositRepository.findAllByProfile(profile);

//        if (!deposit.getProfile().getId().equals(profile.getId())) {
//            throw DepositUnauthorizedException.EXCEPTION;
//        }
        depositRepository.deleteAll(deposit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepositSimpleResponseDto> selectDepositSimpleList(Profile profile) {
        List<Deposit> DepositSimpleList =
                depositRepository.findAllByProfile(profile);

        return DepositSimpleList.stream()
                .map(DepositSimpleResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepositDetailResponseDto> selectDepositDetailList(Profile profile) {
        List<Deposit> DepositDetailList =
                depositRepository.findAllByProfile(profile);

        return DepositDetailList.stream()
                .map(DepositDetailResponseDto::from)
                .collect(Collectors.toList());
    }
}
