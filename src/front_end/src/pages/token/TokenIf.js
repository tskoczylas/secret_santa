import SmallTemplate from "../SmallTemplate";
import {TokenContex} from "./TokenMain";
import LoadingForm from "../../components/LoadingForm";
import FillToken from "./FillToken";
import TokenFounded from "./TokenFounded";
import {useContext} from "react";


function FoundToken(){

    const userData = useContext(TokenContex);

    if (userData.userid === 0 && userData.userCreate === true) return (<LoadingForm/>)
    else if (userData.userCreate === false) return (<FillToken/>)
    else return (<TokenFounded/>)

}

export default function LookForToken() {

    const userData = useContext(TokenContex);


    return (
        <SmallTemplate>


            <FoundToken/>

        </SmallTemplate>
    )
}