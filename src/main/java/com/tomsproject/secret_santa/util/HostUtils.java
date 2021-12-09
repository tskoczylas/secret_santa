package com.tomsproject.secret_santa.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class HostUtils {


    public static String generateToken(int band){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < band; i++) {
            int nextInt = random.nextInt(9);
            stringBuilder.append(nextInt);
        }
        return stringBuilder.toString();

    }

    public static   String getHost(){
        String hostAddress;
        try {
            hostAddress = NetworkInterface.
                    getNetworkInterfaces().
                    nextElement().getInetAddresses().
                    nextElement().getHostAddress();
        } catch (SocketException e) {
            hostAddress="unknown";
        }

        return hostAddress;
    }
}
