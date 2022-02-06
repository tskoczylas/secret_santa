import axios from "axios";
import React from "react";
import {useEffect, useState} from "react";
import Admin from "./Admin";

import SmallTemplate from "../SmallTemplate";
import {defaultAdmin} from "../../data/DefaultData";
import LogIn from "../LogIn";

export const AdminContex = React.createContext(defaultAdmin());

export default function AdminAuth() {


    const [getData, setData] = useState(defaultAdmin);
    const [errorResponse, setErrorResponse] = useState(false)
    const user = localStorage.getItem("adminName");
    const token = JSON.parse(localStorage.getItem("token"));

    useEffect(() => {
        axios
            .get("/api/getAdmin/" + user, {
                headers: {
                    Authorization: "Bearer " + token.access_token,
                },
            })
            .then((res) => {
                setData(res);
            })
            .catch((err) => {
                setErrorResponse(true);
            });
    }, []);


    if (getData === 0) return <SmallTemplate maxWidth="sm" loading/>;
    else if (errorResponse) return <LogIn/>
    else
        return (

            <AdminContex.Provider value={getData}>
                <Admin data={getData}/>
            </AdminContex.Provider>
        );
}

