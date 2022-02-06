import {TextField} from "@mui/material";
import Button from "@mui/material/Button";
import {useNavigate} from "react-router-dom";
import {textTemplate} from "../../data/textTemplate";
import {Box} from "@mui/system";
import Grid from "@mui/material/Grid";
import {useContext} from "react";
import {AdminContex} from "./AdminAuth";

export default function Account() {
    let navigate = useNavigate();
    const adminData = useContext(AdminContex)

    return (
        <Box sx={{ml: 6, mr: 6, mt: 3}}>
            <Grid container alignItems="center" alignContent="center" spacing={2}>

                <Grid item xs={12}>
                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.email}
                        // error={emailError}
                        //   helperText={emailError? textTemplate.signin.mail_error:
                        ///     ""}
                               focused
                               defaultValue={adminData.data.email}
                               type="email"
                               name="email"
                               id="email"
                               margin="dense"
                               fullWidth

                    />
                </Grid>

                <Grid item xs={8}>
                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.name}
                               focused
                               defaultValue={adminData.data.firstName}
                               type="text"
                               name="name"
                               id="name"
                               margin="dense"
                               fullWidth

                    />
                </Grid>
                <Grid item xs={4}>
                    <Button size="small" variant="outlined">{textTemplate.account.change_button}</Button>
                </Grid>
                <Grid item xs={8}>
                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.surname}
                        // error={emailError}
                        //   helperText={emailError? textTemplate.signin.mail_error:
                        ///     ""}
                               focused
                               defaultValue={adminData.data.secondName}
                               type="text"
                               name="lastName"
                               id="lastName"
                               margin="dense"
                               fullWidth

                    />
                </Grid>
                <Grid item xs={4}>
                    <Button size="small" variant="outlined">{textTemplate.account.change_button}</Button>
                </Grid>

                <Grid alignItems="center" item xs={12} sm={6}>
                    <Button size="small" variant="outlined">{textTemplate.account.change_password}</Button>


                </Grid>


            </Grid>
        </Box>

    )
}


