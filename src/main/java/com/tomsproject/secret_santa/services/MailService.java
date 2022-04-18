package com.tomsproject.secret_santa.services;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import com.tomsproject.secret_santa.entity.Admin;
import com.tomsproject.secret_santa.entity.GameEntity;
import com.tomsproject.secret_santa.entity.SantaUserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class MailService {

    @Value("${mail.api.key}")
    String apiKey;

    @Value("${mail.secret.key}")
    String secretKey;

    void sendLink(List<SantaUserEntity> userDtoList, GameEntity gameDto, Admin adminDto) throws  MailjetException {



        ClientOptions options = ClientOptions.builder()
                .apiKey(apiKey)
                .apiSecretKey(secretKey)
                .build();

        MailjetClient client = new MailjetClient(options);

        for (SantaUserEntity users : userDtoList) {
            TransactionalEmail message1 = TransactionalEmail
                    .builder()
                    .templateLanguage(true)
                    .to(new SendContact(users.getEmail()))
                    .from(new SendContact("mail@justsecretsanta.com", "JustSecretSanta"))
                    .subject("You have been invited to participate in game")
                    .templateID(3486814L)
                    .variables(Map.of("name", adminDto.getFirstName() + " " +
                                    adminDto.getSecondName(), "message", gameDto.getUserText(),
                            "link", "https://justsecretsanta.com/token/" + users.getUserid()))
                    .build();

            SendEmailsRequest request = SendEmailsRequest
                    .builder()

                    .message(message1) // you can add up to 50 messages per request
                    .build();
            SendEmailsResponse response = request.sendWith(client);

        }


    }
}
