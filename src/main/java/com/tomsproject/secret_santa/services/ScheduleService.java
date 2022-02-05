package com.tomsproject.secret_santa.services;


public interface ScheduleService {

    void findCompletedUsersAdminAndRunLottery();

    void sendStartMessage();
}
