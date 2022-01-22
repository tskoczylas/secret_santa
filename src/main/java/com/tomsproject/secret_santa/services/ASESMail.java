package com.tomsproject.secret_santa.services;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.GameDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;

import com.tomsproject.secret_santa.model.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ASESMail {

    @Value("${aws.accessKeyId}")
    String accessKeyId;

    @Value("${host.name}")
    String hostName;

    @Value("${aws.secretKey}")
    String secretKey;
    BasicAWSCredentials basicAWSCredentials;

    @PostConstruct
     void initializeCredentials() {
         basicAWSCredentials =
                new BasicAWSCredentials(
                        accessKeyId, secretKey);
    }


    GameDto sendLink(GameDto gameDto) {

        for (SantaUserDto user : gameDto.getUserList()) {
            final String FROM = "mail@justsecretsanta.com";
            final String TO = user.getEmail();
            final String tmepleteId = "santa_user";


            SendTemplatedEmailRequest template = new SendTemplatedEmailRequest();
            template.setDestination(new Destination().withToAddresses(TO));
            template.setTemplate(tmepleteId);
            template.setSource(FROM);

            template.setTemplateData("{\"name\":\"" + gameDto.getAdminDto().getFirstName() + "\"," +
                    "\"lastName\":\"" + gameDto.getAdminDto().getSecondName() + "\"," +
                    "\"message\":\"" + gameDto.getUserText() + "\"," +
                    "\"link\":\""+hostName + "/token/" + user.getUserid() + "\"," +
                    "\"date\":\"" + gameDto.getLastResponseDate().format(DateTimeFormatter.ISO_DATE_TIME).toString() + "\"}"
            );
            SendTemplatedEmailResult sendTemplatedEmailResult = getSendTemplatedEmailResult(template);

            user.setStartMessageSentId(sendTemplatedEmailResult.getMessageId());
            gameDto.setFirstMessageSent(true);
        }
        return gameDto;

    }

    List<SantaUserDto> sendLotteryResult(List<SantaUserDto> santaUserDto ) {

        for (SantaUserDto user : santaUserDto) {

            final String FROM = "mail@justsecretsanta.com";
            final String TO = user.getEmail();
            final String tmepleteId = "santa_user_lottery";

         SendTemplatedEmailRequest template = new SendTemplatedEmailRequest();
            template.setDestination(new Destination().withToAddresses(TO));
            template.setTemplate(tmepleteId);
            template.setSource(FROM);



            template.setTemplateData("{\"name\":\"" + user.getSantaUsersPairDto().getSantaUserDtoSecond().getFirstName() + "\"," +
                    "\"lastName\":\"" + user.getSantaUsersPairDto().getSantaUserDtoSecond().getLastName() + "\"," +
                    "\"message\":\"" + user.getSantaUsersPairDto().getSantaUserDtoSecond().getGiftDesc() + "\"}" );



            SendTemplatedEmailResult sendTemplatedEmailResult = getSendTemplatedEmailResult(template);
          user.setLastMessageSentId(sendTemplatedEmailResult.getMessageId());

        }
        return santaUserDto;

    }

    public String  sendAccountCreateConfEmail(AdminDto admin ) {



            final String FROM = "mail@justsecretsanta.com";
            final String TO = admin.getEmail();
            final String tmepleteId = "santa_user_creation";

            SendTemplatedEmailRequest template = new SendTemplatedEmailRequest();
            template.setDestination(new Destination().withToAddresses(TO));
            template.setTemplate(tmepleteId);
            template.setSource(FROM);



            template.setTemplateData("{\"emails\":\"" + admin.getEmail() + "\"," +
                    "\"link\":\"" +hostName+"/admin/confirm/"+admin.getActivationToken() + "\"}");


            SendTemplatedEmailResult sendTemplatedEmailResult = getSendTemplatedEmailResult(template);

            return sendTemplatedEmailResult.getMessageId();



    }







    private SendTemplatedEmailResult getSendTemplatedEmailResult(SendTemplatedEmailRequest template) {
        AmazonSimpleEmailService client =
                AmazonSimpleEmailServiceClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                        .withRegion(Regions.EU_WEST_1).build();

        return client.sendTemplatedEmail(template);
    }




}






