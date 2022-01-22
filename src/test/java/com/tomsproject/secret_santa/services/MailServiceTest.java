package com.tomsproject.secret_santa.services;

import com.mailjet.client.errors.MailjetException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MailServiceTest {




    @Test
    void sendLink() throws  MailjetException {
        MailService mailService = new MailService();

    }
}