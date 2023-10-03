package ssafy.haruman.global.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ssafy.haruman.global.gpt.dto.request.GPTCompletionChatRequest;
import ssafy.haruman.global.gpt.dto.response.CompletionChatResponse;
import ssafy.haruman.global.gpt.service.GPTChatRestService;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private final GPTChatRestService gptChatRestService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        GPTCompletionChatRequest gptCompletionChatRequest = new GPTCompletionChatRequest(
                "gpt-3.5-turbo", "user" ,"" +
                "My저금통 나의 소비내역을 바탕으로 알아서 저축해주는 똑똑한 통장 \\n" +
                " 데일리 워킹 적금 걸음 수에 따라 우대금리 혜택을 받을 수 있는 적금 상품 \\n" +
                " 우리 으쓱(ESG) 적금 대중교통 이용 및 환경보호 실천운동 달성 시 우대혜택 \\n" +
                " N일 적금 31일, 100일, 200일동안 감정을 기록하면 우대금리를 받을 수 있는 심플하고 재미있는 적금\\n" +
                " 우리사랑 나누미 적금 만기시 기부하면 우대금리가 플러스 되는 적금\\n" +
                " KB맑은하늘적금 맑은하늘을 위한 생활 속 작은 실천에 대해 우대금리를 제공하고, 대중교통/자전거상해 관련 무료 보험서비스(최대 2억원 보장)를 제공하는 친환경 특화 상품\\n" +
                " KB맑은바다적금 해양쓰레기 줄이기 활동에 동참할 경우 친환경 실천 우대이율을 제공하고,맑은바다의 소중함에 대한 공감대를 형성하는 친환경 특화 상품\\n" +
                " 온국민 건강적금-골든라이프 시니어 고객의 건강관리와 금융 혜택을 결합한 앱테크형 상품으로, 저소득층 대상 특별 우대이율을 제공하는 적금\\n" +
                " KB반려행복적금 반려동물 케어·입양·정보 등록이 한 고객 참여형 스마트폰 전용 적금\\n" +
                " KB두근두근여행적금 여행준비의 혜택과 설렘이 있는 적금으로, 노랑풍선 최대 3만원 & 4% 할인쿠폰 제공\\n" +
                " SK LPG 쏠쏠한 행복 적금 SK LPG 충전소 우수 이용 고객에게 우대금리를 제공하는 제휴 적금 상품\\n" +
                " 쏠편한 작심3일 적금 일주일에 3일만 마음먹고 목돈 만드는 좋은 습관! 소액으로 시작하는 6개월 단기 적금!\\n" +
                " 아름다운 용기 적금 [1회용 컵 보증금 제도] 사전홍보 및 친환경 실천 공감대 형성, 고령자를 우대하는 ESG상품\\n" +
                " 신한 KIA타이거즈 적금 2023년 KBO 프로야구 구단 별 성적에 따라 우대금리를 제공하는 적립식 상품\\n" +
                " 신한 플랫폼 적금(강원특별자치도 출범기념) 강원특별자치도 출범을 기념하여 강원도(강원특별자치도) 거주 고객에게 우대금리를 제공하는 자유 적금\\n" +
                " 신한 청년 드림(DREAM) 적금(인천시) 인천시가 선정한 청년근로자의 장기근속 및 자산형성을 위한 3년제 적금!\\n" +
                " Hey Young 머니박스 예비자금의 입출금이 자유로우며 하루만 맡겨도 금리혜택을 제공하는 20대(만18세~만29세) 전용 상품\\n" +
                " 한달愛(애) 저금통 매일 절약한 금액/자투리 금액을 계좌에 모아 그 적립금을 매월 돌려받을 수 있는 온라인(인터넷/스마트 뱅킹) 전용 소액 입출금 상품입니다\\n" +
                " 신한 위드펫(With Pet) 적금 한지붕 내 가족을 위한 맞춤 선택!\\n" +
                " 신한 헬스플러스 적금 스마트폰을 활용하여 건강관리 목표 달성시 우대이자율을 제공하는 적금\\n" +
                " 신한 미션플러스 적금 여행, 금연, 쇼핑, 유학, 연수, 출산/육아, 다이어트, 셀프미션, 전자제품 구입, 금주 등 고객님의 미션을 선택하여 설계할 수 있는 생활 밀착형 적금\\n" +
                " KB 영화사랑 적금 “영화”를 테마로 한 각종 우대이율을 제공하는 정액적립식 예금\\n" +
                " 우리직장인재테크통장(금리우대형) 급여이체를 하는 직장인들에게 금리우대, 수수료 면제 혜택 제공\\n" +
                " 스마트 금연적금 담뱃값도 아끼고 매일매일 금연실천도 하는 건강한 절약적금~~!! \\n" +
                " 펫사랑 적금 펫코노미 시대(반려인 1,500만명) 반려동물을 위한 목돈마련 저축상품 \\n" +
                " 도전365 적금 하나머니 앱 연동 자신의 걸음수에 따라 우대금리 혜택을 받는 적금 \\n" +
                " 키워봐요 적금 6개월 동안, 총 25번 매주마다 저금 성공하면 최고 금리 도전 성공!\\n" +
                " 26주 적금 with 카카오웹툰 적금 계좌를 개설하고 26주 동안 납입금을 매주 자동 이체한 고객은 최대 연 7%에 달하는 카카오뱅크 적용 금리에 더해 총 7회에 걸쳐 최대 2만원 상당의 카카오웹툰 캐시를 지급\\n" +
                " 26주 적금 with GS25 카카오뱅크의 26주적금을 통해 GS25 편의점과 GS더프레시 등 GS리테일 매장에서 이용할 수 있는 포인트를 제공하는 상품\\n" +
                " My저금통 나의 소비내역을 바탕으로 알아서 저축해주는 똑똑한 통장\\n" +
                " 데일리 워킹 적금 걸음 수에 따라 우대금리 혜택을 받을 수 있는 적금 상품",1000);

        CompletionChatResponse response = gptChatRestService.completionChat(gptCompletionChatRequest);
        System.out.printf(response.getMessages().get(0).getMessage());

    }
}