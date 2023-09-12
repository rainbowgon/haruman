package ssafy.haruman.domain.challenge.service;

import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.profile.entity.Profile;

public interface ChallengeService {

    Challenge startChallenge(Profile profile);
}
