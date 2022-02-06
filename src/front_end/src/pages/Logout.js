import SmallTemplate from "./SmallTemplate";
import Typography from "@mui/material/Typography";
import {textTemplate} from "../data/textTemplate";
import Button from "@mui/material/Button";
import {Link, useNavigate} from "react-router-dom";

export default function Logout() {

    let navigate = useNavigate;

    return (
        <SmallTemplate maxWidth="sm">
            <Typography>
                {textTemplate.logout.message}
            </Typography>

            <Link to={"/login"}>
                <Button>
                    {textTemplate.logout.button}
                </Button>
            </Link>

        </SmallTemplate>
    )

}