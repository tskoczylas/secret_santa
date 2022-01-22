import Typography from "@mui/material/Typography";
import {textTemplate} from "../../data/textTemplate";
import {Button} from "@mui/material";
import {useContext} from "react";
import {AdminContex} from "./AdminAuth";

export default function Welcome(props){

    const adminData=useContext(AdminContex)

    return(
        <Typography variant="h6" align="center">
            {textTemplate.admin_welcome.welcome}
            {adminData.data.adminId}
        </Typography>


    )

}