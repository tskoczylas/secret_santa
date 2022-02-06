import {Grid, Typography} from "@mui/material";
import ProgressBar from "../../components/ProgressBar";
import TextForm from "../../components/TextForm";
import {textTemplate} from "../../data/textTemplate";
import * as React from "react";
import {TokenContex} from "./TokenMain";

export default function TokenFounded() {
    const userData = React.useContext(TokenContex)


    return (
        <Grid container spacing={2} alignItems="center" alignContent="center" textAlign="center">
            <Grid mt={2} item xs={12} sm={12}>
                <Typography component="h1" variant="h5">
                    {textTemplate.token.founded.welcome_text}
                </Typography>
            </Grid>

            <Grid item xs={12} sm={6}>
                <Typography component="h2" variant="body2">
                    {textTemplate.token.founded.users_completed}
                </Typography>
            </Grid>
            <Grid item xs={12} sm={6}>
                <ProgressBar percentage={userData.percentageCompleteUsers}/>

            </Grid>

            <TextForm/>
        </Grid>
    );
}
