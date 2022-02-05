
//do not change a array position !!!
import {timeOutLastResponseDate, timeOutStartDate} from "../pages/admin/newGame/SecondStep";
import {maxUserMessageLength} from "../pages/admin/newGame/NewGame";

//Seb - > nazwy ponizej do przemyslenia - krotkie, zwiezle
export const adminLeftMenu = ['New exchange', 'Current exchanges','Expired exchanges'];

export const adminRightMenu = [ 'Account', 'Log out'];

export const adminStepperData=[{label:"Add participants",error:"At least 2 participants"},
    {label: "Select time and date",error:"Select date"},
    {label:"Write a message",error: "Write message(Max "+maxUserMessageLength+" digits)" }]



export   const textTemplate=

{
    main_page:{
        h1:"Welcome to JustSecretSanta App",
        h6:"The Secret Santa gift exchange has never been so simple!",
        prim_1:"Simple",
        item_1:"You just need to sign in and create an email list to randomly assign participants.",
        prim_2:"Fast",
        item_2:"The system will automatically send questionnaire to each person, asking for short description of their ideal present.",
        prim_3:"Efficient",
        item_3:"You will be able to set a deadline by which the gift exchange will start for all participants that have provided necessary information.",
        prim_4:"Anonymous",
        item_4:"The identity of the gift giver will remain a secret to everybody, including you. Enjoy!",
        signin:"Sign in",
        login:"Log in",
        created_by:" Project created by " +
            "Tomasz Skoczylas, a self-taught Java/JavaStrict Developer."


    },

    account:{
        change_button:"Change",
        change_password:"Change password"
    },





    game:{

        game_start_date:"Invitation sent",
        last_response_date:"Drawn",
        admin_message:"Message",
        userCompletedFalse:"No",
        userCompletedTrue:"Yes",
        userGameTop:"Users",
        game_name:"Name",
        isCompleted:"Complete",

        userGame:{firstName: "Name",
                    lastName:"Surname",
                    email:"Email",
        isCompleted:"Complete"
        },





    },


  login: {
    login_head: "Welcome in JustSecretSanta",
    login: "Log in",
    user_name : "Username",
    password : "Password",
    sigin_tex : "Don't have an account yet?",
    sigin_link: "Sign in",
    password_fail:"Password or login is incorrect"
  },
  signin: {
      signin_button:"Sign in",
    email: "Your email address",
    repet_email:"Repeat email address",
    name:"Name",
    surname:"Surname",
    signin:"Sign in",
    terms:"I accept terms and conditions",
      password: "Password",
      re_password:"Re-enter password",
      password_error:"At least 8 characters, one lowercase, one uppercase, and one special sign",
      re_password_error:"The passwords are not the same",
      names_error:"Can not be empty",
      mail_error:"Wrong email adress",

      response_valid:"Mail to activate your account has been sent",
      response_bad:"There was a problem with your request",
      response_email_bad:"Email already exists",
      confirm:{success: "Your account has been activated",
      fault:"You have activated your account already or we don't have token which you provided",
          button:"Move to login page"
      }
  },
  "logout":{
    "message": "You have logged out or your session has expired",
    "button": "Back to login page"
  },

    token:{
        founded:{
            welcome_text:"Welcome in JustSecretSanta",
            users_completed:"Participants completed:",
            label_mail:"Your email",
            label_token:"Token",
            label_name:"Name",
            label_lastName:"Surname",
            gift_too_long:"Description is too long (max 100 digits)",
            label_git:"Short suggestion about your ideal gift",
            dialog_sure:"Are you sure?",
            dialog_back:"No, move back",
            aler_save:"Your data has been saved",
            button_save:"Save data",
            yes_button:"Yes"



        }
    },

  newGame:

      {
             steps_completed:"You have created a gift exchange! Would you like to: ",
             completed_reset:"Make changes",
            completed_submit:"Create new gift exchange",


          firstStep:{
            remove_user:"Remove",
            addUser:"Add user",
            id_field:"Id",
            user_email:"Email"},
    secondStep:{
          send_invitation:"Set invitation date",
          last_response_date:"Last response date (at least " +timeOutLastResponseDate + "h from now)",
            start_date_error:"You must select a date" ,
        last_resposne_date_error:"You must select a date (at least "+timeOutLastResponseDate+" h from now)" ,
        switch_text_field:"Send it now",

        send_invitation_info: "Select a date when we will send an invitation to participate in gift exchange. It must be at least one hour from now",
        last_response_info: "Select a date until which participants must respond to take part in the gift exchange. It must be at least 24 hours from now"
        },
     thirdStep:{
            text_field_label:"Invitation message to participants (up to "+maxUserMessageLength+" digits)",
            typography_top:"Write an invitation message to participants",
            game_name:"Name your gift exchange",
         game_name_top:"Give a personal name for the gift exhange",
         name_error:"This field can not be empty"

     },
   responseHandler:{
                 to_many_request:"You have reached maximum requests limit",
                created:"The gift exchange has been successfully created",
                not_valid_request:"You submitted invalid data",
                error_request:"There has been a server error. Try submitting again",
                button:{
                     error:"Try again",
                    move_exist:"Existing exchanges",

                }

   },



  },

  admin_welcome:{"welcome":"Welcome!!! Would you like you:"}


}