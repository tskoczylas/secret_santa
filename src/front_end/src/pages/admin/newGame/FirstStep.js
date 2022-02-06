import {DataGrid} from "@mui/x-data-grid";
import * as React from 'react';
import Button from "@mui/material/Button";
import {Grid, TextField} from "@mui/material";
import {textTemplate} from "../../../data/textTemplate";
import {useContext, useState} from "react";
import Box from "@mui/material/Box";
import {AdminContex} from "../AdminAuth";
import {validateEmail} from "../../../data/Validation";


export const emailList = () => {
    if (localStorage.getItem("emails") !== null) return JSON.parse(localStorage.getItem("emails")).email
    else return []
}

export default function FirstStep() {

    const emailStorage = localStorage.getItem("emails")


    const adminData = useContext(AdminContex)


    const [formOpen, setFormOpen] = useState(false);
    const [getEmail, setEmail] = useState();
    const [rowEmailList, setRowEmailList] = useState([]);
    const [rowSelectionHandler, setRowSelectionHandler] = useState([])
    const [emailListState, setEmailListState] = useState(emailList())


    function handleForm(event) {
        event.preventDefault()
        setEmail(event.target.value)
    }


    function saveEmail(emails) {
        localStorage.setItem("emails", JSON.stringify({email: emails}))
    }

    function saveUser() {
        let emails = emailListState
        if (getEmail !== "" && validateEmail(getEmail)) {
            emails.push(getEmail)
            saveEmail(emails);
            loadEmailList()


        }
    }


    function rows(list) {
        return (
            list.map((email, index) => {
                let i = index + 1;
                return {id: i++, email: email}
            }))
    }

    useState(() => {
        loadEmailList()
    }, [])

    function loadEmailList() {


        if (emailListState.length !== 0) {
            setFormOpen(true)
            setRowEmailList(rows(emailListState))
        } else

            setFormOpen(false)


    }


    function handleUserRemove() {

        rowSelectionHandler.forEach((id) => {

            let emailList = emailListState

            emailList.splice(id - 1, id)
            saveEmail(emailList)
            setEmailListState(emailList)
            loadEmailList()


        })


    }


    const columns = [
        {field: 'id', headerName: textTemplate.newGame.firstStep.id_field, width: 50, editable: false},
        {
            field: "email",
            headerName: textTemplate.newGame.firstStep.user_email,
            width: 250,
            editable: true,
        },


    ];


    return (
        <Grid container
              mt={2}
              spacing={2}
              direction="column"
              justifyContent="center"
              alignItems="center">

            <Grid item>


                <TextField

                    sx={{width: 250}}
                    onChange={handleForm}
                    error={!validateEmail(getEmail)}
                    size="small" type="email" id="email" name="email" label="User email">
                </TextField>
            </Grid>

            <Grid item>

                <Button

                    onClick={saveUser} disabled={!validateEmail(getEmail)}
                    variant="outlined"
                    size="small"
                >
                    {textTemplate.newGame.firstStep.addUser}

                </Button>


            </Grid>

            <Grid item>


                {formOpen ?
                    <Box height={400} width={300}>

                        <DataGrid
                            rows={rowEmailList}
                            onSelectionModelChange={(num) => {
                                setRowSelectionHandler(num)
                            }
                            }
                            columns={columns}
                            pageSize={5}
                            rowsPerPageOptions={[5]}
                            checkboxSelection
                            disableSelectionOnClick
                        />
                    </Box>

                    : ""}

            </Grid>

            <Grid xs={12} item>
                {rowSelectionHandler.length !== 0 ?
                    <Button variany="contained" label="Clear" onClick={handleUserRemove
                    }>
                        {textTemplate.newGame.firstStep.remove_user}

                    </Button> : ''}
            </Grid>
        </Grid>

    )
}
