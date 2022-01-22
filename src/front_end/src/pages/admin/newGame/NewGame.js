import Box from "@mui/material/Box";
import * as React from 'react';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepButton from '@mui/material/StepButton';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {adminLeftMenu, adminStepperData, textTemplate} from "../../../data/textTemplate";
import FirstStep, {emailList} from "./FirstStep";
import SecondStep  from "./SecondStep";
import ThirdStep from "./ThirdStep";
import {StepLabel} from "@mui/material";
import {addNewUser} from "../../../data/AdminFirstStepService";
import {useContext, useState} from "react";
import {AdminContex} from "../AdminAuth";
import {isAfter} from "date-fns";
import ResponseHandler from "./ResponseHandler";

export const maxUserMessageLength = 160
export const minUserMessageLength = 2


export const thirdStepValidation = (userMessage) =>{

    if(userMessage===null ) return false
        else if (userMessage===undefined) return false
    else return userMessage.length < maxUserMessageLength && userMessage.length>minUserMessageLength;
}

export default function NewGame({navigate}){


    const [completeButtonState,setCompleteButtonState] = useState(false);
    const [resetButtonState,setResetButtonState] = useState(false)
    const [responseStatus,setResponseStatus] = useState()

    let sendNowStorage= localStorage.getItem("sendNow")

    const  defaultSwitchStatus= () =>{
        if(sendNowStorage===null){ return true}
        else {return  JSON.parse(sendNowStorage).data}

    }

    const completeStep = (step) =>{
        const newCompleted = completed;
        newCompleted[step] = true;
        setCompleted(newCompleted)
    }

    const isCompletedEmptyOrUndefined = (step) =>{
      return  completed[step]===undefined || completed.length===0
    }

        const [activeStep, setActiveStep] = React.useState(0);
        const [completed, setCompleted] = React.useState([]);

        const isFirstStepCompleted = () =>{
            const emailStorage=emailList()
              if(emailStorage.length>1 ){
                  const step = 0;
                  if(isCompletedEmptyOrUndefined(step)){completeStep(step)}
                  return true}
              else return false


        }


    const isSecondStepCompleted = () =>{
        const startDateStorage=localStorage.getItem("startDate");
        const lastResponseDate=localStorage.getItem("lastResponseDate");
        const sendNowSwitch  = defaultSwitchStatus();
        if(
            ( ((startDateStorage!==null&&
            lastResponseDate!==null) ||

            (sendNowSwitch===true &&lastResponseDate!==null))
                && isAfter(new Date(lastResponseDate),
                new Date(startDateStorage)) )

    

        )
        {   const step = 1;
            if(isCompletedEmptyOrUndefined(step))completeStep(step)
            return true;
        }
        else return false

    }

    const isThirdStepCompleted = () =>{
        const userMessage=localStorage.getItem("userText");
        const gameName=localStorage.getItem("gameName");

        if(thirdStepValidation(userMessage)&&thirdStepValidation(gameName) )
        {   const step = 2;
            if(completeStep(step))completeStep(step)
            return true
        }
        else return false

    }

    const adminDataContex=useContext(AdminContex)






        const totalSteps = () => {
            return adminStepperData.length;
        };

        const completedSteps = () => {
            return Object.keys(completed).length;
        };

        const isLastStep = () => {
            return activeStep === totalSteps() - 1;
        };

        const allStepsCompleted = () => {
            return completedSteps() === totalSteps();
        };

        const handleNext = () => {
            if(activeStep===0)isFirstStepCompleted()
            if(activeStep===1)isSecondStepCompleted()
            if(activeStep===2)isThirdStepCompleted()
            const newActiveStep =
                isLastStep() && !allStepsCompleted()
                    ? // It's the last step, but not all steps have been completed,
                      // find the first step that has been completed
                    adminStepperData.findIndex((step, i) => !(i in completed))
                    : activeStep + 1;
            setActiveStep(newActiveStep);
        };

        const forwardToSteps = () => {

            if(activeStep===0) return <FirstStep  activeStep={activeStep}/>
            else if(activeStep===1) return <SecondStep/>
            else if(activeStep===2) return <ThirdStep/>
            else return <FirstStep/>
        }

        const handleBack = () => {
            setActiveStep((prevActiveStep) => prevActiveStep - 1);
        };

        const handleStep = (step) => () => {
            if(activeStep===0)isFirstStepCompleted()
            if(activeStep===1)isSecondStepCompleted()
            if(activeStep===2)isThirdStepCompleted()

            setActiveStep(step);
        };



        const handleReset = () => {
            setActiveStep(0);
            setCompleted([]);
        };

    function handleSubmit() {
        setCompleteButtonState(true)
        setResetButtonState(true)
        const response = addNewUser(adminDataContex.data.adminId);
        setResponseStatus(response)



    }
    const isStepFailed = (step) => {
if(activeStep===2){
    if(step===0&&!isFirstStepCompleted())return true
     if (step===1&&!isSecondStepCompleted()) return true
}



    };

    return(

        <Box sx={{  ml:2 ,mt:5,mb:4, width: '90%' ,flexGrow:1 }}>
            <Stepper nonLinear activeStep={activeStep}>
                {adminStepperData.map((label, index) => {
                    const labelProps = {};
                    if (isStepFailed(index)) {
                        labelProps.optional = (
                            <Typography key={label.error} variant="caption" color="error">
                                {label.error}
                            </Typography>
                        );

                        labelProps.error = true;
                    }
                    const isStepsComp = true;
                    return(
                    <Step key={index} completed={completed[index]}>
                        <StepLabel color="inherit" onClick={handleStep(index)} {...labelProps}>{label.label}</StepLabel>


                    </Step> )
                })}
            </Stepper>
            <div>
                {allStepsCompleted() ? (
                    <React.Fragment>
                        <Typography sx={{ mt: 3, mb: 2 }}>
                            {textTemplate.newGame.steps_completed}
                        </Typography>
                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                           <Button sx={{ml:8}} onClick={handleReset} disabled={completeButtonState}
                                >

                                {textTemplate.newGame.completed_reset}
                            </Button>

                            <Box sx={{ flex: '1 1 auto' }} />

                            <Button sx={{mr:6}} onClick={handleSubmit} disabled={resetButtonState}>

                                {textTemplate.newGame.completed_submit}
                            </Button>
                        </Box>
                        <Box sx={{ display: 'flex',  justifyContent: 'center', flexDirection: 'column', pt: 2 }}>
                            <ResponseHandler navigate={nav=>{navigate(nav)}} data={responseStatus}/>

                        </Box>
                    </React.Fragment>
                ) : (
                    <React.Fragment>
                        {forwardToSteps()}


                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                            <Button
                                color="inherit"
                                disabled={activeStep === 0}
                                onClick={handleBack}
                                sx={{ mr: 1 }}
                            >
                                Back
                            </Button>
                            <Box sx={{ flex: '1 1 auto' }} />
                            <Button onClick={handleNext} sx={{ mr: 1 }}>
                                Next
                            </Button>

                        </Box>
                    </React.Fragment>
                )}
            </div>
        </Box>
    );


}