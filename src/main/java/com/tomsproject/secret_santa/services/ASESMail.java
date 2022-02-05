package com.tomsproject.secret_santa.services;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

import com.tomsproject.secret_santa.entity.AdminEntity;
import com.tomsproject.secret_santa.entity.GameEntity;
import com.tomsproject.secret_santa.entity.SantaUserEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ASESMail {

    @Value("${aws.accessKeyId}")
   private String accessKeyId;

    @Value("${host.name}")
    private String hostName;

    @Value("${aws.secretKey}")
    private  String secretKey;

    BasicAWSCredentials basicAWSCredentials;
    @Value("${mail.from}")
    private  String mailFrom;


    @PostConstruct
     void initializeCredentials() {
         basicAWSCredentials =
                new BasicAWSCredentials(
                        accessKeyId, secretKey);
    }


    GameEntity sendLink(GameEntity gameDto) {

        for (SantaUserEntity user : gameDto.getUserList()) {
            final String TO = user.getEmail();
            final String templateId = "santa_user";


            SendTemplatedEmailRequest template = new SendTemplatedEmailRequest();
            template.setDestination(new Destination().withToAddresses(TO));
            template.setTemplate(templateId);
            template.setSource(mailFrom);

            template.setTemplateData("{\"name\":\"" + gameDto.getAdminDto().getFirstName() + "\"," +
                    "\"lastName\":\"" + gameDto.getAdminDto().getSecondName() + "\"," +
                    "\"message\":\"" + gameDto.getUserText() + "\"," +
                    "\"link\":\""+hostName + "/token/" + user.getUserid() + "\"," +
                    "\"date\":\"" + gameDto.getLastResponseDate().format(DateTimeFormatter.ISO_DATE_TIME) + "\"}"
            );
            SendTemplatedEmailResult sendTemplatedEmailResult = getSendTemplatedEmailResult(template);

            user.setStartMessageSentId(sendTemplatedEmailResult.getMessageId());
            gameDto.setFirstMessageSent(true);
        }
        return gameDto;

    }

    List<SantaUserEntity> sendLotteryResult(List<SantaUserEntity> santaUserEntity) {

        for (SantaUserEntity user : santaUserEntity) {

            final String TO = user.getEmail();
            final String templateId = "santa_user_lottery";

         SendTemplatedEmailRequest template = new SendTemplatedEmailRequest();
            template.setDestination(new Destination().withToAddresses(TO));
            template.setTemplate(templateId);
            template.setSource(mailFrom);



            template.setTemplateData("{\"name\":\"" + user.getSantaUsersPairDto().getSantaUserEntitySecond().getFirstName() + "\"," +
                    "\"lastName\":\"" + user.getSantaUsersPairDto().getSantaUserEntitySecond().getLastName() + "\"," +
                    "\"message\":\"" + user.getSantaUsersPairDto().getSantaUserEntitySecond().getGiftDesc() + "\"}" );



            SendTemplatedEmailResult sendTemplatedEmailResult = getSendTemplatedEmailResult(template);
          user.setLastMessageSentId(sendTemplatedEmailResult.getMessageId());

        }
        return santaUserEntity;

    }

    public String  sendAccountCreateConfEmail(AdminEntity admin ) {



            final String FROM = "mail@justsecretsanta.com";
            final String TO = admin.getEmail();
            final String templateId = "santa_user_creation";

            SendTemplatedEmailRequest template = new SendTemplatedEmailRequest();
            template.setDestination(new Destination().withToAddresses(TO));
            template.setTemplate(templateId);
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






