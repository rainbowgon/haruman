package ssafy.haruman.domain.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChallengeGroup {

    GROUP_0("0원", 0),
    GROUP_2000("2000원 이하", 2000),
    GROUP_4000("4000원 이하", 4000),
    GROUP_6000("6000원 이하", 6000),
    GROUP_8000("8000원 이하", 8000),
    GROUP_10000("10000원 이하", 10000),
    CHALLENGE_FAILED("챌린지 실패", Integer.MAX_VALUE),
    INVALID("이상값", -1);

    private final String groupKey;
    private final Integer amountRangeMax;

    public static ChallengeGroup getGroup(Integer usedAmount) {
        for (ChallengeGroup group : ChallengeGroup.values()) {
            if (usedAmount <= group.amountRangeMax) {
                return group;
            }
        }
        return INVALID;
    }

}
