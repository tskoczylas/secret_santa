import SmallTemplate from "../SmallTemplate";
import Typography from "@mui/material/Typography";
import {useParams} from "react-router";
import {useState} from "react";
import axios from "axios";
import {textTemplate} from "../../data/textTemplate";
import {Button} from "@mui/material";
import {useNavigate} from "react-router-dom";

export default function SignInConformation (){

    let  {confirmToken } = useParams()

    const [responseText, setResponseText] = useState("")

    useState(()=>{
        try{
        axios.get("/api/admin/confirm/" + confirmToken).
        then((res)=>{
                if(res.status===200)setResponseText(textTemplate.signin.confirm.success)
        }).catch((error)=>{setResponseText(textTemplate.signin.confirm.fault)})
    }
        catch(error){setResponseText(textTemplate.signin.confirm.fault)}}
        ,confirmToken)

   let navigate  = useNavigate()
    return (
        <SmallTemplate maxWidth="xs">
    <Typography mt={2} mb={2} maxWidth="70%" variant="body2">
        {responseText}
    </Typography >
            <Button size="small"
                onClick = {()=>{navigate("/login")}}>{textTemplate.signin.confirm.button}</Button>
        </SmallTemplate>
        )
}