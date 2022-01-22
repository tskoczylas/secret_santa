package com.tomsproject.secret_santa.services;

import org.springframework.scheduling.annotation.Scheduled;

public interface SheduleService {
  //  @Scheduled(fixedDelay = 1000)
    void usersCompletedSendSmsAndGenerateToken();

    void findCompletedUsersAdminAndRunLottery();

    String generateUniqeToken(int tokenBand);

    void sendStartMessage();
}
