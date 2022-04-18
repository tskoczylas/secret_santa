package com.tomsproject.secret_santa.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CreateUserDto {


   private List<String> emailList;
   private LocalDateTime startDate;
   private LocalDateTime lastResponseDate;
   private String gameName;
   private Long adminId;
   private String userText;
   private boolean isStartNow;

    static final long lastResponseTimeout = 12;


    private boolean allFieldsNotNull() {
        return Arrays.stream(this.getClass().getDeclaredFields()).allMatch(field -> {
            try {
                return field.get(this) != null;
            } catch (IllegalAccessException e) {
                return false;
            }
        });
    }


   boolean isStartDateValid(){
    return isStartNow || startDate.isAfter(LocalDateTime.now());

    }

    boolean isLastResponseDateValid(){
        return lastResponseDate.isAfter(LocalDateTime.now().plusHours(lastResponseTimeout)) &&
                isStartNow
                ||
                lastResponseDate.isAfter(startDate.plusHours(lastResponseTimeout)) && !isStartNow;
    }





    boolean isValidEmail(String email){
         String regExp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                 + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.toLowerCase().matches(regExp);
    }


    public boolean isValidForCreate()  {
                if(!allFieldsNotNull()) return false;
                else if (adminId==0) return false;
        else if(! emailList.stream().allMatch(this::isValidEmail)) return false;
                else if(emailList.size()<=1) return false;
                else if(!isStartDateValid()) return false;
        else if(gameName.isEmpty()) return false;
                else if(!isLastResponseDateValid()) return false;
                else return !userText.isEmpty();


    }

    @Override
    public String toString() {
        return "CreateUser{" +
                "emailList=" + emailList +
                ", startDate=" + startDate +
                ", lastResponseDate=" + lastResponseDate +
                ", adminId='" + adminId + '\'' +
                ", userText='" + userText + '\'' +
                ", isStartNow=" + isStartNow +
                ", lastResponseTimeout=" + lastResponseTimeout
                + "    " + isValidForCreate() +
                '}'; }



}
