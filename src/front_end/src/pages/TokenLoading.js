import Avatar from "@mui/material/Avatar";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import { useContext } from "react";
import { createTheme, ThemeProvider } from "@mui/material";

import LoadingForm from "../components/LoadingForm";
import santa from "../jpg/santa.jpg";
import { TokenContex } from "./TokenMain";
import Copyright from "../components/Copyright";
import TokenFounded from "./TokenFounded";
import FillToken from "./FillToken";

export default function TokenLoading() {
  const userData = useContext(TokenContex);


function LookForToken(){
  if(userData.userid===0&&userData.userCreate===true)  return(<LoadingForm/>) 
  else if (userData.userCreate===false) return(<FillToken/>)
  else return (<TokenFounded/>)
}

  const theme = createTheme({
    palette: {
      primary: {
        main: "#d92121",
      },

      common: { white: "040202" },
    },
  });

  return (
    <ThemeProvider theme={theme}>

    <Container component="main" maxWidth="xs">
      <CssBaseline />

      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >

<Grid item sm={12} xs={12} align="center">
    <Avatar
      sx={{ width: 60, height: 60 }}
      alt="santa"
      src={santa}
    ></Avatar>
  </Grid>


        <LookForToken></LookForToken>
        
      </Box>
      <Copyright sx={{ mt: 1 }} />
    </Container>
    </ThemeProvider>
  );
}
