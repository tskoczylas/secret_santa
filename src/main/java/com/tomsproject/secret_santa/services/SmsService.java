package com.tomsproject.secret_santa.services;

import ClickSend.Api.SmsApi;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;

import java.util.List;

public interface SmsService {
    SmsApi getSmsApi();

    boolean sendLotteryFinalSms(List<SantaUsersPairDto> santaUsersPairDtoSource);

    boolean sendRequestSMS(SantaUserDto santaUserDto);
}
