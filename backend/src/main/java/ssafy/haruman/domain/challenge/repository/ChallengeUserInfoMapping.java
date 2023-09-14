package ssafy.haruman.domain.challenge.repository;

import java.time.LocalDateTime;

public interface ChallengeUserInfoMapping {

    Long getChallengeId();

    Long getProfileId();

    LocalDateTime getStartTime();

    Integer getUsedAmount();

    LocalDateTime getCreatedAt();

}
