import {passwordRexVal} from "../pages/SignIn/SignIn";

export const validateEmail = (email) => {
    return String(email)
        .toLowerCase()
        .match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
};

export function passwordErrorHandler(password) {
    return !String(password).match(passwordRexVal)
}

export function rePasswordErrorHandler(re_password, password) {
    return re_password !== password || re_password.length === 0
}

export function nameErrorHandler(name) {

    return String(name).length < 1 && String(name).length < 1
}