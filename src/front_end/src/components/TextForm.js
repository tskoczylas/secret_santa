import {
  Alert,
  Backdrop,
  Button,
  CircularProgress,
  Dialog,
  DialogActions,
  DialogTitle,
  Grid,
  TextField,
} from "@mui/material";
import { Box } from "@mui/system";
import { useContext, useState } from "react";
import { TokenContex } from "../pages/TokenMain";
import PriceForm from "./PriceForm";

export default function TextForm(props) {
  const [getButtonState, setButtonState] = useState(true);
  const [getBackDropState, setBackDropState] = useState(false);
  const [getApiData, setApiData] = useState([]);
  const [getButtonSubmmit, setButtonSubmmit] = useState(false);
  const[getGiftDescError,setGiftDescError] = useState(false)


  const userData = useContext(TokenContex);

  const handleOnChange = (event) => {
    // event.preventDefoult();

    const data = new FormData(event.currentTarget);

    const currentData = {
      userid: userData.userid,
      phoneNumber: userData.phoneNumber,
      token: userData.token,
      firstName: data.get("firstName"),
      lastName: data.get("lastName"),
      giftDesc: data.get("giftDesc"),
      admin: userData.admin,
      userComplete: true,
      chosenPrice: data.get("gift"),
    };

  
    const isComplete =
      currentData.firstName === "" ||
      currentData.lastName === "" ||
      currentData.giftDesc === ""||
      currentData.giftDesc.length>30

    const apiCall = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(currentData),
    };

    setApiData(apiCall);
    setGiftDescError(currentData.giftDesc.length>30)
    setButtonState(isComplete === true);
  };

  return (
    <Box component="form" onChange={handleOnChange} sx={{ mt: 3 }}>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
          <TextField
            disabled
            value={userData.phoneNumber}
            label="Numer telefonu"
            fullWidth
            autoFocus
            id="phoneNumber"
            name="phoneNumber"
          />
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
            disabled
            autoFocus
            value={userData.token}
            label="Token"
            fullWidth
            id="token"
            name="token"
          />
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
            disabled={userData.userComplete}
            autoComplete="off"
            name="firstName"
            defaultValue={userData.firstName}
            fullWidth
            id="firstName"
            label="Imię"
            autoFocus
          />
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
            disabled={userData.userComplete}
            fullWidth
            id="lastName"
            defaultValue={userData.lastName}
            label="Nazwisko"
            name="lastName"
            autoComplete="off"
          />
        </Grid>

        <Grid item xs={12} sm={12}>
          <TextField
            required
            disabled={userData.userComplete}
            fullWidth
            multiline
            defaultValue={userData.giftDesc}
            maxRows={2}
            error={getGiftDescError}
              helperText={getGiftDescError===true?"Zbyt długi opis":""}
            

        
            name="giftDesc"
            label="Krótka sugestja co do prezentu(30 znaków)"
            id="giftDesc"
          />
        </Grid>

        <Grid item xs={12} sm={12}>
          <PriceForm></PriceForm>
        </Grid>
        <Dialog
          open={getBackDropState}
          // onClose={getBackDropState}
          aria-labelledby="alert-dialog-title"
          aria-describedby="alert-dialog-description"
        >
          <DialogTitle id="alert-dialog-title">{"Jestes pewien?"}</DialogTitle>

          <DialogActions>
            <Button
              onClick={() => {
                setButtonSubmmit(true);
                setBackDropState(false);

                fetch("/api/user/", getApiData)
                  .then((response) => response.json())
                  .then((data) => {
                    if (data.userid !== 0) {
                      window.location.reload(false);

                     
                    }
                  })

                  .catch((err) => {
                  });
              }}
            >
              Tak
            </Button>

            <Button
              onClick={() => {
                setBackDropState(false);
              }}
              autoFocus
            >
              Nie, chcę wrócić!
            </Button>
          </DialogActions>
        </Dialog>

        <Grid item xs={12} sm={12}>
          {userData.userComplete === true ? (
            <Alert> Twoje dane zostały zapisane</Alert>
          ) : (
            <Button
              disabled={getButtonState}
              onClick={() => {
                setBackDropState(true);
              }}
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Zapisz się
            </Button>
          )}

          <Backdrop
            sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
            open={getButtonSubmmit}
          >
            <CircularProgress color="inherit" />
          </Backdrop>
        </Grid>
      </Grid>
    </Box>
  );
}
