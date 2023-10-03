package ssafy.haruman.domain.deposit.service;


import ssafy.haruman.domain.deposit.dto.request.DepositCreateRequestDto;
import ssafy.haruman.domain.deposit.dto.request.DepositUpdateRequestDto;
import ssafy.haruman.domain.deposit.dto.response.DepositDetailResponseDto;
import ssafy.haruman.domain.deposit.dto.response.DepositSimpleResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;

import java.util.List;

public interface DepositService {

    DepositSimpleResponseDto createDeposit(Profile profile, DepositCreateRequestDto createDto);

    List<DepositSimpleResponseDto> createDepositList(Profile profile, List<DepositCreateRequestDto> createDtoList);

    DepositSimpleResponseDto updateDeposit(Profile profile, DepositUpdateRequestDto updateDto);

    void deleteDeposit(Profile profile, Long depositId);

    void deleteDepositAll(Profile profile);

    List<DepositSimpleResponseDto> selectDepositSimpleList(Profile profile);

    List<DepositDetailResponseDto> selectDepositDetailList(Profile profile);

}
