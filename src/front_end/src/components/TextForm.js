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
import {Box} from "@mui/system";
import {useContext, useState} from "react";
import {TokenContex} from "../pages/token/TokenMain";
import {textTemplate} from "../data/textTemplate";

export default function TextForm(props) {
    const [getButtonState, setButtonState] = useState(true);
    const [getBackDropState, setBackDropState] = useState(false);
    const [getApiData, setApiData] = useState([]);
    const [getButtonSubmmit, setButtonSubmmit] = useState(false);
    const [getGiftDescError, setGiftDescError] = useState(false)


    const userData = useContext(TokenContex);

    const handleOnChange = (event) => {
        // event.preventDefoult();

        const data = new FormData(event.currentTarget);

        const currentData = {
            userid: userData.userid,
            email: userData.email,
            firstName: data.get("firstName"),
            lastName: data.get("lastName"),
            giftDesc: data.get("giftDesc"),
            adminId: userData.adminId
        };


        const isComplete =
            currentData.firstName === "" ||
            currentData.lastName === "" ||
            currentData.giftDesc === "" ||
            currentData.giftDesc.length > 30

        const apiCall = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(currentData),
        };

        setApiData(apiCall);
        setGiftDescError(currentData.giftDesc.length > 30)
        setButtonState(isComplete === true);
    };

    return (
        <Box component="form" onChange={handleOnChange} sx={{marginLeft: 4, marginRight: 3, mt: 3}}>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                    <TextField
                        disabled
                        value={userData.email}
                        label={textTemplate.token.founded.label_mail}
                        fullWidth
                        autoFocus
                        id="email"
                        name="email"
                    />
                </Grid>

                <Grid item xs={12} sm={6}>
                    <TextField
                        disabled
                        autoFocus
                        value={userData.userid}
                        label={textTemplate.token.founded.label_token}
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
                        label={textTemplate.token.founded.label_name}
                        autoFocus
                    />
                </Grid>

                <Grid item xs={12} sm={6}>
                    <TextField
                        disabled={userData.userComplete}
                        fullWidth
                        id="lastName"
                        defaultValue={userData.lastName}
                        label={textTemplate.token.founded.label_lastName}
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
                        helperText={getGiftDescError === true ? textTemplate.token.founded.gift_too_long : ""}


                        name="giftDesc"
                        label={textTemplate.token.founded.label_git}
                        id="giftDesc"
                    />
                </Grid>


                <Dialog
                    open={getBackDropState}
                    // onClose={getBackDropState}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">{textTemplate.token.founded.dialog_sure}</DialogTitle>

                    <DialogActions>
                        <Button
                            onClick={() => {
                                setButtonSubmmit(true);
                                setBackDropState(false);

                                fetch("/api/user/save/", getApiData)
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
                            {textTemplate.token.founded.yes_button}
                        </Button>

                        <Button
                            onClick={() => {
                                setBackDropState(false);
                            }}
                            autoFocus
                        >
                            {textTemplate.token.founded.dialog_back}
                        </Button>
                    </DialogActions>
                </Dialog>

                <Grid item xs={12} sm={12}>
                    {userData.userComplete === true ? (
                        <Alert>{textTemplate.token.founded.aler_save}</Alert>
                    ) : (
                        <Button
                            disabled={getButtonState}
                            onClick={() => {
                                setBackDropState(true);
                            }}
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                        >
                            {textTemplate.token.founded.button_save}
                        </Button>
                    )}

                    <Backdrop
                        sx={{color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1}}
                        open={getButtonSubmmit}
                    >
                        <CircularProgress color="inherit"/>
                    </Backdrop>
                </Grid>
            </Grid>
        </Box>
    );
}
