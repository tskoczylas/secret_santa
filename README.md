# JustSecretSanta :mrs_claus:

<img src="/src/front_end/src/jpg/christmas-shopping.svg" align="right"
     alt="Christmas Skoping" width="120" height="178">
     
     
Full stack SpringBoot/React app:   
https://justsecretsanta.com
     
 The JustSecretSanta App has been written by **Tomasz Skoczylas** who is a self-taught developer looking for their first job in the industry.
 
 ## App's features: 

-Login/Signin with transactional confirmation email for the game creator - admin
- New game - creating a list of participants  emails:
  * Admin can set a start and last response date, short messages to users taking part in the game. :hourglass:
  * The system will automatically send emails with links containing  questioners to each person, asking for a short description of the present. :gift:
  * After the last response date, the system will run a lottery :joystick:  [Lottery Java Class](/src/main/java/com/tomsproject/secret_santa/util/DrawnPairs.java )
  * Admin has an access to the list of participants and can check the completion of the questionnaire. :file_cabinet:
  
 ## Code is written:
 1. Back end - Java:
    - SpringBoot: 
      - Spring Data JSP:  [Dtos Classes](/src/main/java/com/tomsproject/secret_santa/dto), 
                         [Repo Classes](/src/main/java/com/tomsproject/secret_santa/repo), MySql DB                     
      - SpringBoot Rest, [Controller Classes](/src/main/java/com/tomsproject/secret_santa/controller),
      - Spring Security : JWS Token Authentication Loterry [Seciuirty Services Classes](/src/main/java/com/tomsproject/secret_santa/security/ )
      - [Services](/src/main/java/com/tomsproject/secret_santa/services) inluding:
         - AWS Simple Email Service 
         - Services providing with logic for Controllers
         - Shedule Spring service 
    - [Model maper](/src/main/java/com/tomsproject/secret_santa/mapper), [Test classes](/src/test/java/com/tomsproject/secret_sant)  using: Mockito, JUnit 5, Hamcrest 
 
 2. [Front end](/src/test/java/com/tomsproject/secret_santa) - JavaStript: 
- React.js 
- Material Ui
- React Router
- Date Fns
- Axios

3. The App has been deployed using #Maven and #Docker on #AWS Elastic Beanstalk 
## Things to do:
- [ ] Wirte rest of Java Test #1
- [ ] Wirte rest of JavaScript Test #2
- [ ] Account panel - password change feature      
