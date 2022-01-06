import Avatar from "@mui/material/Avatar";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import {useContext} from "react";
import {CircularProgress, createTheme, ThemeProvider} from "@mui/material";

import LoadingForm from "../components/LoadingForm";
import santa from "../jpg/santa.jpg";
import {TokenContex} from "./token/TokenMain";
import Copyright from "../components/Copyright";
import TokenFounded from "./token/TokenFounded";
import FillToken from "./token/FillToken";

export default function SmallTemplate(props) {


    const theme = createTheme({
        palette: {
            primary: {
                main: "#d92121",
            },

            common: {white: "040202"},
        },
    });

    return (
        <ThemeProvider theme={theme}>

            <Container component="main" maxWidth={props.maxWidth}>
                <CssBaseline/>

                <Box
                    sx={{
                        marginTop: 8,


                        border: '2px groove red',
                       borderRadius: "10px",
                        display: "flex",
                        flexDirection: "column",
                       alignItems: "center",
                    }}
                >


                        <Avatar

                            sx={{width: 60, height: 60, marginTop:2}}
                            alt="santa"
                            src={santa}
                        ></Avatar>

                    {props.loading?<CircularProgress/>: "" }

                    {props.children}
                    <Copyright sx={{mt: 1,marginBottom:3}}/>

                </Box>
            </Container>
        </ThemeProvider>
    );
}
