import {Alert} from "@mui/lab";
import {Button, LinearProgress} from "@mui/material";
import {textTemplate} from "../../data/textTemplate";
import {useEffect, useState} from "react";
import {trackPromise, usePromiseTracker} from "react-promise-tracker";
import {useNavigate} from "react-router-dom";

export default function SignInResponseHandler({button, data}) {


    const [response, setResponse] = useState("inactive")
    const {promiseInProgress} = usePromiseTracker();
    useEffect(() => {

        if (data !== "inactive") {

            const promise =
                data.then((res) => {
                    setResponse(res)


                }).catch((er) => {
                    setResponse(er)
                })

            trackPromise(promise)


        }


    }, [data])


    let navigate = useNavigate()


    function alert(severity, alertText) {

        return (

            <Alert
                action={
                    <Button size="small" variant="outlined"

                            onClick={() => {
                                navigate("/login")
                            }}>{textTemplate.signin.confirm.button}</Button>
                }
                severity={severity}> {alertText}

            </Alert>


        );
    }

    console.log(response)
    if (promiseInProgress === true || response === undefined) return <LinearProgress/>
    else if (response === "inactive") return ""
    else if (response.status === 200) {
        button(true)
        return alert("success", textTemplate.signin.response_valid)
    } else if (response.status === 203) return alert("warning", textTemplate.signin.mail_error)
    else if (response.status === 204) return alert("warning", textTemplate.signin.response_email_bad)

    else return alert("error", textTemplate.signin.response_bad)


    /*
        else if (props.status.result.status==="pending") return (<CircularProgress />)
        else if(props.status.result.status===200) return (<Alert severity="warning">This is a warning alert — check it out!</Alert>)
           else return ''

    */


}