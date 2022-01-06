import SmallTemplate from "./SmallTemplate";
import {Button, Checkbox, FormControlLabel, FormGroup, Grid, TextField} from "@mui/material";
import {textTemplate} from "../data/textTemplate";
import Typography from "@mui/material/Typography";
import {Test} from "../test";

export default function SignIn (){

    return(
        <SmallTemplate>

            <Typography variant="h6" marginTop={2}
            marginBottom={2}>
                {textTemplate.signin.signin}
            </Typography>
            <Grid container spacing={3} justifyContent="center">
                <Grid item sm={7} xs={7}>
                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.email}
                               focused
                               name={textTemplate.signin.email}
                               id="email"
                    />

                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.repet_email}
                               focused
                               name={textTemplate.signin.repet_email}
                               id="email_re"
                               type="email"
                               margin="dense"
                    />
                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.name}
                               focused
                               name={textTemplate.signin.name}
                               id="name"
                               type="text"
                               margin="normal"
                    />
                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.surname}
                               focused
                               name={textTemplate.signin.surname}
                               id="surname"
                               type="email"
                               margin="normal"
                    />

                    <FormGroup>
                        <FormControlLabel control={<Checkbox defaultChecked />} label={textTemplate.signin.terms} id="checkbox" />
                    </FormGroup>


                    <Button type="outlined">
            Sign In
        </Button>


                </Grid>



            </Grid>
        </SmallTemplate>
    )
}