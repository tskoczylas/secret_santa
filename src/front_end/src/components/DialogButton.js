import {Button, Dialog, DialogActions, DialogTitle} from "@mui/material";
import {useEffect} from "react";

export default function DialogButton(props) {

    const [getOpen, setOpen] = false;
    useEffect(() => {
        setOpen(true)
        console.log(props)

    })
    return (
        <Dialog
            open={getOpen}
            onClose={setOpen(false)}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
        >

            <DialogTitle id="alert-dialog-title">{"Jestes pewien?"}</DialogTitle>

            <DialogActions>
                <Button onClick="">Tak</Button>
                <Button onClick="" autoFocus>
                    Nie, chcę wrócić
                </Button>
            </DialogActions>
        </Dialog>
    );
}
