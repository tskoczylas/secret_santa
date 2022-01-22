package com.tomsproject.secret_santa.services;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import com.tomsproject.secret_santa.dto.AdminDto;
import com.tomsproject.secret_santa.dto.GameDto;
import com.tomsproject.secret_santa.dto.SantaUserDto;
import org.json.JSONArray;
import org.json.JSONObject;
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

    void sendLink(List<SantaUserDto> userDtoList, GameDto gameDto, AdminDto adminDto) throws  MailjetException {



        ClientOptions options = ClientOptions.builder()
                .apiKey(apiKey)
                .apiSecretKey(secretKey)
                .build();

        MailjetClient client = new MailjetClient(options);

        for (SantaUserDto users : userDtoList) {
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

        ;

    }
}
