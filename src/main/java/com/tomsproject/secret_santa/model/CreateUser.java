package com.tomsproject.secret_santa.model;


import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUser {


   private List<String> emailList;
   private LocalDateTime startDate;
   private LocalDateTime lastResponseDate;
   private String adminName;
   private String userText;
   private boolean isStartNow;
    final private   long lastResponseTimeout = 12;


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
      return startDate.isAfter(LocalDateTime.now());
    }

    boolean isLastResponseDateValid(){
        return lastResponseDate.isAfter(LocalDateTime.now().plusHours(lastResponseTimeout));
    }




     boolean isValidEmail(String email){
         String regExp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                 + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.toLowerCase().matches(regExp);
    }


    public boolean isValidForCreate()  {
                if(!allFieldsNotNull()) return false;
        else if(! emailList.stream().allMatch(this::isValidEmail)) return false;
                else if(emailList.size()<=1) return false;
                else if(!isStartNow&&!isStartDateValid()) return false;
                else if(!isLastResponseDateValid()) return false;
                else return !userText.isEmpty();


    }



}
