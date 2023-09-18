package ssafy.haruman.domain.challenge.repository;

import java.time.LocalDateTime;

public interface ChallengeUserInfoMapping {

    String getNickname();

    String getSavedPath();

    LocalDateTime getStartTime();

    Integer getUsedAmount();

    LocalDateTime getCreatedAt();

}
