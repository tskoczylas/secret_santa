import SmallTemplate from "../SmallTemplate";
import {textTemplate} from "../../data/textTemplate";
import Typography from "@mui/material/Typography";
import {Button, Grid, TextField} from "@mui/material";
import TextForm from "../../components/TextForm";
import LoginIcon from '@mui/icons-material/Login';
import {Link} from "react-router-dom";
export default function LogIn(){

    return(
        <SmallTemplate>
            <Typography  marginTop={2} component={"h1"}  variant={"h5"} align={"center"}
            >
                {textTemplate.login.login_head}
            </Typography>

            <Typography  marginTop={2} marginBottom={3} component={"h1"}  variant={"h6"} align={"center"}
            >
                {textTemplate.login.login_h2}
            </Typography>
            <Grid container justifyContent="center"  spacing={3} >
                <Grid item alignItems={"center"}  xs={7} sm={7}>
                    <TextField id="username"
                               variant="outlined"
                               focused
                    name="username"
                               margin="dense"
                               label={textTemplate.login.user_name}
                    fullWidth>


                    </TextField>


                    <TextField id="username"
                               margin ="dense"
                               variant="outlined"
                               focused

                               type="button"
                               name="username"
                               label={textTemplate.login.password}
                               fullWidth>


                    </TextField>
            </Grid>



                <Grid  item xs={7} sm={7}>

        <Button    color="primary"
        endIcon={<LoginIcon/>}>
            {textTemplate.login.login}
        </Button>
                </Grid>
                <Grid item xs={7} sm={7}>
                    <Typography variant="body2">
                        {textTemplate.login.sigin_tex}
                    </Typography>

                    <Link to="/signin" >
                        <Typography variant="body2">
                            {textTemplate.login.sigin_link}
                        </Typography>
                    </Link>
                </Grid>
            </Grid>

        </SmallTemplate>
    )

}