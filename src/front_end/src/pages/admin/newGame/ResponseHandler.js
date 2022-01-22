
import {addNewUser} from "../../../data/AdminFirstStepService";
import {useContext, useEffect, useState} from "react";
import {AdminContex} from "../AdminAuth";
import {Alert, AlertTitle} from "@mui/lab";
import {Button, CircularProgress, Link} from "@mui/material";
import {adminLeftMenu, textTemplate} from "../../../data/textTemplate";
import {useNavigate} from "react-router-dom";
import {Box} from "@mui/system";

export default function ResponseHandler({navigate,data}){



    const [status,setStatus] = useState()
useEffect(()=>{

    if(data!==undefined){

        data.then((res)=>{
            setStatus(res)
        })
    } },[data])

    const adminDataContex=useContext(AdminContex)




    function alertMoveToGames(alertText,page,buttonText,severity) {
        return (

            <Alert
                action={
                    <Button size="small" variant="outlined"

                            onClick={() => {
                                navigate(adminLeftMenu[page])
                            }} >{buttonText}</Button>
                }
                severity={severity}> {alertText}


            </Alert>


        );
    }

    if(status===undefined)  return ('')

    else if(status.status==="PENDING") return <CircularProgress />
    else if(status.data==="TOO_MANY_REQUESTS") return alertMoveToGames(textTemplate.newGame.responseHandler.to_many_request,0,
        textTemplate.newGame.responseHandler.button.move_exist,"warning")

   else if(status.data==="NOT_ACCEPTABLE") return  alertMoveToGames(textTemplate.newGame.responseHandler.not_valid_request,0,
        textTemplate.newGame.responseHandler.button.move_exist,"warning")

    else if(status.data==="CREATED") return alertMoveToGames(textTemplate.newGame.responseHandler.created,0,
        textTemplate.newGame.responseHandler.button.move_exist,"success")
    else if(status.data==="NOT_FOUND")return  alertMoveToGames(textTemplate.newGame.responseHandler.not_valid_request,0,
        textTemplate.newGame.responseHandler.button.move_exist,"warning")
    else return   alertMoveToGames(textTemplate.newGame.responseHandler.error_request,1,
            textTemplate.newGame.responseHandler.button.error,"error")


    /*
        else if (props.status.result.status==="pending") return (<CircularProgress />)
        else if(props.status.result.status===200) return (<Alert severity="warning">This is a warning alert — check it out!</Alert>)
           else return ''

    */




}