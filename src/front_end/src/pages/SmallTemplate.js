import Avatar from "@mui/material/Avatar";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import {CircularProgress, createTheme, ThemeProvider} from "@mui/material";

import santa from "../jpg/santa.jpg";
import Copyright from "../components/Copyright";

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
                        marginTop: 2,
                        mb:2,
                        border: '2px groove red',
                        borderRadius: "10px",
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                    }}
                >

                    <Avatar

                        sx={{width: 60, height: 60, marginTop: 2}}
                        alt="santa"
                        src={santa}
                    ></Avatar>

                    {props.loading ? <CircularProgress/> : ""}

                    {props.children}
                    <Copyright sx={{mt: 1, marginBottom: 2}}/>

                </Box>
            </Container>
        </ThemeProvider>
    );
}
