import React, {useEffect, useState} from "react";
import {useParams} from "react-router";
import santaUserDefault from "../../data/DefaultData";
import LookForToken from "./TokenIf";

export const TokenContex = React.createContext();

export default function TokenMain() {
    const [getTokenData, setTokenData] = useState(santaUserDefault);

    const {token} = useParams();


    useEffect(() => {
        fetch("/api/santaToken/" + token)
            .then((res) => res.json())
            .then((data) => {
                console.log(data)
                setTokenData(data);
            })

            .catch((err) => {
                console.log(err)

            })
    }, [])

    return (

        <TokenContex.Provider value={getTokenData}>

            <LookForToken/>
        </TokenContex.Provider>


    );
}
