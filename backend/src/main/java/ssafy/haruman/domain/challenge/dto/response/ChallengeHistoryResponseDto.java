package ssafy.haruman.domain.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.challenge.entity.ChallengeStatus;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeHistoryResponseDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private ChallengeStatus status;

}
