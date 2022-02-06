//do not change a array position !!!
import {timeOutLastResponseDate} from "../pages/admin/newGame/SecondStep";
import {maxUserMessageLength} from "../pages/admin/newGame/NewGame";

//Seb - > nazwy ponizej do przemyslenia - krotkie, zwiezle
export const adminLeftMenu = ['New Game', 'Exist Game', "Expire Game"];

export const adminRightMenu = ['Account', 'Logout'];

export const adminStepperData = [{label: "Add users", error: "At least 2 users"},
    {label: "Select time and date", error: "Select dates"},
    {label: "Write a massage", error: "Write message(Max " + maxUserMessageLength + " digits)"}]


export const textTemplate =

    {
        main_page: {
            h1: "Welcome to JustSecretSanta App",
            h6: "The Secret Santa lottery has never been so simple!",
            prim_1: "Simple",
            item_1: "You just need to sign in and create an email list to randomly assign players.",
            prim_2: "Fast",
            item_2: "The system will automatically send questionare to each person, asking for short description of the ideal present.",
            prim_3: "Efficient",
            item_3: "You will be able to set a deadline by which the lottery will start for all participants that has provided necessary information.",
            prim_4: "Anonymous",
            item_4: "The identity of the gift giver will remain a secret to everybody including you. Enjoy!",
            signin: "Sign in",
            login: "Log in",
            created_by: " This is a project witch has been created by" +
                "Tomasz Skoczylas, a self-taught Java/JavaStrict Developer."


        },

        account: {
            change_button: "Change",
            change_password: "Change password"
        },


        game: {

            game_start_date: "Invitation sent",
            last_response_date: "Drawn",
            admin_message: "Message",
            userCompletedFalse: "No",
            userCompletedTrue: "Yes",
            userGameTop: "Users",
            game_name: "Name",
            isCompleted: "Complete",

            userGame: {
                firstName: "Name",
                lastName: "Surname",
                email: "Email",
                isCompleted: "Complete"
            },


        },


        login: {
            login_head: "Welcome in Santa Secret App",
            login: "Log in",
            user_name: "Username",
            password: "Password",
            sigin_tex: "Don't have account yet?",
            sigin_link: "Sign In",
            password_fail: "Password or login is incorrect"
        },
        signin: {
            signin_button: "Sign In",
            email: "You email",
            repet_email: "Repeat email ",
            name: "Name",
            surname: "Surname",
            signin: "Sign in",
            terms: "Accepting terms and conditions",
            password: "Password",
            re_password: "Re enter password",
            password_error: "At least 8 in length, one lowercase , one uppercase, one special",
            re_password_error: "The password are not the same",
            names_error: "Can not be empty",
            mail_error: "Wrong email",

            response_valid: "Mail to activate your account has been sent.",
            response_bad: "It has been problem with your request",
            response_email_bad: "Email already exist",
            confirm: {
                success: "Your account has been activated",
                fault: "You have activated your account already or we don't have token witch you provided ",
                button: "Move to login page"
            }
        },
        "logout": {
            "message": "You have been logout or your session timeout.",
            "button": "Back to login page"
        },

        token: {
            founded: {
                welcome_text: "Welcome in JustSecretSanta",
                users_completed: "Users completed:",
                label_mail: "Your email",
                label_token: "Token",
                label_name: "Name",
                label_lastName: "Surname",
                gift_too_long: "To long (max 100 digits)",
                label_git: "Short suggestion about gift",
                dialog_sure: "Are you sure ?",
                dialog_back: "No, Move back ",
                aler_save: "Your data has been saved ",
                button_save: "Save data",
                yes_button: "Yes"


            }
        },

        newGame:

            {
                steps_completed: "You have completed a game creation. Would you like to: ",
                completed_reset: "Make changes",
                completed_submit: "Create game",


                firstStep: {
                    remove_user: "Remove",
                    addUser: "Add user",
                    id_field: "Id",
                    user_email: "Email"
                },
                secondStep: {
                    send_invitation: "Set invitation date",
                    last_response_date: "Last response date(at least " + timeOutLastResponseDate + "h from now)",
                    start_date_error: "You must select a date ",
                    last_resposne_date_error: "You must select a date(at least " + timeOutLastResponseDate + " h from now)",
                    switch_text_field: "Send it now",

                    send_invitation_info: "Select a date when we will send a invitation to guest of your game. It must be at least one your from now ",
                    last_response_info: "last_response_info to wirite"
                },
                thirdStep: {
                    text_field_label: "Invitation message to users (up to " + maxUserMessageLength + " digits) ",
                    typography_top: "Write an invitation message to users",
                    game_name: "Name a game",
                    game_name_top: "Give a personal name for the game",
                    name_error: "This field can not be empty"

                },
                responseHandler: {
                    to_many_request: "You reach maximum request limit",
                    created: "The game has been successfully created",
                    not_valid_request: "You submitted a wrong data",
                    error_request: "It has been server error. Try submit again",
                    button: {
                        error: "Try again",
                        move_exist: "Existing games",

                    }

                },


            },

        admin_welcome: {"welcome": "Welcome !!! Would you like you:"}


    }