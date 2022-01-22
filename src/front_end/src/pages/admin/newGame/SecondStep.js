import * as React from 'react';
import TextField from '@mui/material/TextField';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DateTimePicker from '@mui/lab/DateTimePicker';
import {useEffect, useState} from "react";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import {textTemplate} from "../../../data/textTemplate";
import Button from "@mui/material/Button";
import {now} from "moment";
import {add, compareAsc, isAfter} from "date-fns";
import {Icon, Switch} from "@mui/material";
import HelpOutlineIcon from '@mui/icons-material/HelpOutline';
import {Box} from "@mui/system";



export const timeOutStartDate = 1
export const timeOutLastResponseDate = 24

const startDate =  add(new Date(),{hours:timeOutStartDate})
const lastResponseDate =  add(new Date(),{hours:timeOutLastResponseDate})


export const isNotNullAndDateIsAfterNow = (date) =>{
    if(date===null ) return true
    else return isAfter(date,date)

}





export default function SecondStep() {

     let sendNowStorage= localStorage.getItem("sendNow")
     let startDateStorage =  localStorage.getItem("startDate");
     let lastResponseDateStorage =  localStorage.getItem("lastResponseDate");

     const  defaultSwitchStatus= () =>{
        if(sendNowStorage===null){ return true}
        else {return  JSON.parse(sendNowStorage).data}

    }
     const  defaultStartDate= () =>{
        if(sendNowStorage!==null){ return new Date(startDateStorage)}
        else {return startDate}

    }
     const  defaultLastResponseDate= () =>{
        if(sendNowStorage!==null){ return  new Date(lastResponseDateStorage)}
        else {return   lastResponseDate}

    }


    const [startDateState, setStartDateState] = useState(defaultStartDate);
    const [lastResponseDateState, setLastResponseDateState] = useState(defaultLastResponseDate);
    const [switchChangeState,setSwitchChangeState] = useState(defaultSwitchStatus)

    const setSendNowStorage = (value) =>{
        if(value === true || value ===false){
        localStorage.setItem("sendNow",JSON.stringify({data:value}))}
    }
    function saveToStorageLS(newValue) {
        localStorage.setItem("lastResponseDate", newValue)
    }

    function saveToStorageStart(newValue) {
        localStorage.setItem("startDate", newValue)

    }





    useEffect(()=>{
        if(startDateStorage===null){
        saveToStorageLS(startDate)}
        if(lastResponseDateStorage===null){
        saveToStorageStart(lastResponseDate)}
        if(sendNowStorage===null){
        setSendNowStorage(true)}

    },[])



    const errorStartHandler =() =>{
      return   isNotNullAndDateIsAfterNow(startDateState)

    }

    const errorResponseHandler =() =>{
        if( isNotNullAndDateIsAfterNow(lastResponseDateState)) return true
        else return !isAfter(lastResponseDateState, startDateState);


    }



    const handleButtonChange =
        (event)=>{
        setSendNowStorage(event.target.checked)
        setSwitchChangeState(event.target.checked)}




    return (
        <Grid container
              mt={2}
              spacing={1}
              justifyContent="center"
              alignItems="center"
                direction="column">

                <Grid item  >
                    <Box sx={{ display: 'flex', alignItems: 'flex-end', marginInline:2 }}>
                        <Typography sx={{mr:2}}>
                            {textTemplate.newGame.secondStep.send_invitation}
                        </Typography>

                        <HelpOutlineIcon onClick={()=>{alert(textTemplate.newGame.secondStep.send_invitation_info)}} sx={{ ml:2, my: 0.5 }} fontSize="small">add_circle</HelpOutlineIcon>
                    </Box>

                </Grid>
            <Grid item >
                <Box sx={{display: 'flex', flexDirection: "row" ,alignItems: 'center'}}>

                <Typography variant="body2">
                    {textTemplate.newGame.secondStep.switch_text_field}
                </Typography>

                    <Switch
                        checked={switchChangeState}
                         onChange={handleButtonChange}
                            inputProps={{ 'aria-label': 'controlled' }}
                    />
                </Box>

            </Grid>
            <Grid item xs={12} sm={6}  >
        <LocalizationProvider dateAdapter={AdapterDateFns}>
            <DateTimePicker
                disabled={switchChangeState}
                minDateTime={startDate}

                renderInput={(props) =>
                    <TextField
                        sx={{width:270}}
                        helperText={errorStartHandler()?textTemplate.newGame.secondStep.start_date_error:""}
                        error={errorStartHandler()}

                        size = "small"{...props} />}


                value={startDateState}
                onChange={(newValue) => {
                    setStartDateState(newValue)
                   saveToStorageStart(newValue);
                }}
            />
        </LocalizationProvider>
            </Grid>

            <Grid  item >
                <Box sx={{ display: 'flex', alignItems: 'flex-end', marginInline:2 }}>
                    <Typography>
                        {textTemplate.newGame.secondStep.last_response_date}
                    </Typography>
                    <HelpOutlineIcon onClick={()=>{alert(textTemplate.newGame.secondStep.last_response_info)}} sx={{ ml:2, my: 0.5 }} fontSize="small">add_circle</HelpOutlineIcon>
                </Box>



            </Grid>

            <Grid  item  >
                <LocalizationProvider dateAdapter={AdapterDateFns}>

                    <DateTimePicker
                       minDateTime={lastResponseDate}
                        renderInput={(props) =>
                            <TextField
                                sx={{width:270}}
                            helperText={errorResponseHandler()?
                                textTemplate.newGame.secondStep.last_resposne_date_error:""}
                            error={errorResponseHandler()}  size = "small"{...props} />}
                       value={lastResponseDateState}
                        onChange={(newValue) => {
                            saveToStorageLS(newValue);

                            setLastResponseDateState(newValue);
                        }}
                    />
                </LocalizationProvider>
            </Grid>

        </Grid>
    )


}