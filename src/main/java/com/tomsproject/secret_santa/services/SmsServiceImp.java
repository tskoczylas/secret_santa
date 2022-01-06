package com.tomsproject.secret_santa.services;
import java.util.ArrayList;
import java.util.List;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import com.tomsproject.secret_santa.services.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImp implements  SmsService {
    @Value("${sms.client.login}")
    String smsClientLogin;

    @Value("${sms.client.api}")
    String smsClientApi;









    @Override
    public SmsApi getSmsApi() {
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername(smsClientLogin);
        defaultClient.setPassword(smsClientApi);
        SmsApi apiInstance = new SmsApi(defaultClient);
        return apiInstance;
    }


    @Override
    public boolean sendLotteryFinalSms(List<SantaUsersPairDto> santaUsersPairDtoSource) {
        SmsApi apiInstance = getSmsApi();
        List<SmsMessage> smsMessageList = new ArrayList<>();



        santaUsersPairDtoSource.forEach(santaUsersPairDto
                ->{
            SmsMessage smsMessage = new SmsMessage();
            smsMessage.body("Loteria Świąteczna. Twój wylosowany użytkownik to: " + santaUsersPairDto.getSantaUserDtoSecond().getFirstName() + " "+
                    santaUsersPairDto.getSantaUserDtoSecond().getLastName() + " Prezent kupujemy do " + santaUsersPairDto.getSantaUserDtoFirst().getAdminDto().getMedianeUserChosenPrice()  + " zł" +". Wskazówka: "
                    +santaUsersPairDto.getSantaUserDtoSecond().getGiftDesc());
            smsMessage.to(santaUsersPairDto.getSantaUserDtoFirst().getPhoneNumber());
            smsMessage.source("Swięty Mikołaj");

            smsMessageList.add(smsMessage);

        });




        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            return apiInstance.smsSendPost(smsMessages).contains("\"status\":\"SUCCESS\"");
        } catch (ApiException e) {
            return false;

        }
    }


    @Override
    public boolean sendRequestSMS(SantaUserDto santaUserDto) {
        SmsApi apiInstance = getSmsApi();
        List<SmsMessage> smsMessageList = new ArrayList<>();



        SmsMessage smsMessage = new SmsMessage();
            smsMessage.body("Witaj Loterii Świątecznej! Zostałeś zaproszony przez użytkownika " + santaUserDto.getAdminDto().getFirstName() + " " +
                    santaUserDto.getAdminDto().getSecondName() +". http://tomasz.swietymikolaj.link/"+ santaUserDto.getToken() +" . Kliknij i zarejstruj się.");
            smsMessage.to(santaUserDto.getPhoneNumber());
            smsMessage.source(santaUserDto.getAdminDto().getSmsSource());

            smsMessageList.add(smsMessage);



        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            return   apiInstance.smsSendPost(smsMessages).contains("\"status\":\"SUCCESS\"");



        } catch (ApiException e) {
            return false;

        }



    }
}

