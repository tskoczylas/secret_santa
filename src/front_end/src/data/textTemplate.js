
//do not change a array position !!!
import {timeOutLastResponseDate, timeOutStartDate} from "../pages/admin/newGame/SecondStep";
import {maxUserMessageLength} from "../pages/admin/newGame/NewGame";

export const adminLeftMenu = ['Games', 'Start New Game',"Main Page"];
export const adminRightMenu = [ 'Account', 'Logout'];

export const adminStepperData=[{label:"Add users",error:"At least 2 users"},
    {label: "Select time and date",error:"Select dates"},
    {label:"Write a massage",error: "Write message(Max "+maxUserMessageLength+" digits)" }]



export   const textTemplate=

{
  "login": {
    "login_head": "Welcome in Santa Secret App",
    "login": "Log in",
    "user_name" : "Username",
    "password" : "Password",
    "sigin_tex" : "Don't have account yet?",
    "sigin_link": "Sign In",
    "password_fail":"Password or login is incorrect"
  },
  "signin": {
    "email": "You email",
    "repet_email":"Repeat email ",
    "name":"Name",
    "surname":"Surname",
    "signin":"Sign in",
    "terms":"Accepting terms and conditions"
  },
  "logout":{
    "message": "You have been logout or your session timeout.",
    "button": "Back to login page"
  },

  newGame:

      {
             steps_completed:"You have completed a game creation. Would you like to: ",
             completed_reset:"Make changes",
            completed_submit:"Create game",


          firstStep:{
            remove_user:"Remove",
            addUser:"Add user",
            id_field:"Id",
            user_email:"Email"},
    secondStep:{
          send_invitation:"Set invitation date",
          last_response_date:"Last response date(at least " +timeOutLastResponseDate + "h from now)",
            start_date_error:"You must select a date " ,
        last_resposne_date_error:"You must select a date(at least "+timeOutLastResponseDate+" h from now)" ,
        switch_text_field:"Send it now",

        send_invitation_info: "Select a date when we will send a invitation to guest of your game. It must be at least one your from now ",
        last_response_info: "last_response_info to wirite"
        },
     thirdStep:{
            text_field_label:"Invitation message to users (up to "+maxUserMessageLength+" digits) ",
            typography_top:"Write an invitation message to users"


     }

  },

  "admin_welcome":{"welcome":"Welcome !!! Would you like you:"}


}