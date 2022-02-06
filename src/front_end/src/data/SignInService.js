import axios from "axios";

export default function saveAdmin(data, progress) {


    return (


        axios
            .post("/api/admin/create",
                data
            ).then(response => {

            return response
        })
            .catch(err => {
                    return err
                }
            ))

}

export function convFormToObj(currentTarget) {
    let formData = new FormData(currentTarget)
    return {
        email: formData.get("email"),
        reEmail: formData.get("emailRe"),
        password: formData.get("password"),
        re_password: formData.get("re_password"),
        firstName: formData.get("name"),
        secondName: formData.get("surname"),
        checkbox: formData.get("checkbox")
    }
}

export const defaultSignInData = {
    email: "not_valid",
    reEmail: "",
    password: "",
    re_password: "",
    firstName: "",
    secondName: "",
    checkbox: "",
}




