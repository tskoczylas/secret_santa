import axios from "axios";
import {emailList} from "../pages/admin/newGame/FirstStep";


export const token = JSON.parse(localStorage.getItem("token"))


export const addNewUser = (adminId) => {


    const objNewUser = {
        emailList: emailList(),
        startDate: new Date(localStorage.getItem("startDate")),
        lastResponseDate: new Date(localStorage.getItem("lastResponseDate")),
        adminId: adminId,
        gameName: localStorage.getItem("gameName"),
        userText: localStorage.getItem("userText"),
        startNow: JSON.parse(localStorage.getItem("sendNow")).data


    }


    return (
        axios
            .post("/api/user/create",
                objNewUser,
                {
                    headers: {
                        Authorization: "Bearer " + token.access_token
                    }
                }).then(response => {
            return response
        })
            .catch(err => {
                return err
            })

    )
}

