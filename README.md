# JustSecretSanta 

<img src="/src/front_end/src/jpg/christmas-shopping.svg" align="right"
     alt="Christmas Skoping" width="120" height="178">
     
     
Full stack SpringBoot/React app:   
https://justsecretsanta.com
     
 App has been written by **Tomasz Skoczylas**   who is 
 self-though developer looking for the first job in the industry 
 
 ## Apps features:

-Login/Sign in with transactional confirmation email for the game creator - admin
- New game - creating a list of participants  emails:
  * Admin can set a start and last response date, short messages to users taking part in the game.
  * The system will automatically send emails with links with questioners to each person, asking for a short description of the present.
  * After the last response date, the system will run a lottery  [Loterry Java Class](/src/main/java/com/tomsproject/secret_santa/util/DrawnPairs.java ) 

 

 
 ## Code is written:
 1. Back end:
 *  In Java:
    -  SpringBoot: 
    - Spring Data JSP:  [Dtos Classes](/src/main/java/com/tomsproject/secret_santa/dto), 
                         [Repo Classes](/src/main/java/com/tomsproject/secret_santa/repo), 
    - SpringBoot Rest, [Controller Classes](/src/main/java/com/tomsproject/secret_santa/controller),
    - Spring Security : JWS Token Authentication Loterry [Seciuirty Services Classes](/src/main/java/com/tomsproject/secret_santa/security/ )
    - [Services](src/main/java/com/tomsproject/secret_santa/services) inluding:
    - AWS Simple Email Service 
    - Services provading logic for Controllers
    - Shedule Spring service 
    
    - [Model maper](src/main/java/com/tomsproject/secret_santa/mapper) 
 
 2.Front end:   
 * In JavaStript:
     - React.js 
     - Material Ui
     - React Router
     - Date Fns
     - Axios
     
3. App has been deployed using #Maven and #Docker on #AWS Elastic Beanstalk     
     
