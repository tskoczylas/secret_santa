import SmallTemplate from "../SmallTemplate";
import {Button, Checkbox, FormControlLabel, FormGroup, Grid, TextField} from "@mui/material";
import {textTemplate} from "../../data/textTemplate";
import Typography from "@mui/material/Typography";
import {Test} from "../../test";
import {Box} from "@mui/system";
import {useState} from "react";

import {nameErrorHandler, passwordErrorHandler, rePasswordErrorHandler, validateEmail} from "../../data/Validation";
import SignInResponseHandler from "./SignInResponseHandler";
import saveAdmin, {convFormToObj, defaultSignInData} from "../../data/SignInService";

export const  passwordRexVal = "^(?:(?:(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]))|(?:(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\\]))|(?:(?=.*[0-9])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\\]))|(?:(?=.*[0-9])(?=.*[a-z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\\]))).{8,32}$";


export default function SignIn (){

    const [formData,setFormData] = useState({});
    const[passwordError,setPasswordError]=useState(false);
    const[rePasswordError,setRePasswordError]=useState(false);
    const[nameError,setNameError]=useState(false);
    const[lastNamesError,setLastNamesError]=useState(false);
    const[emailError,setEmailError]=useState(false);
    const[buttonHandler,setButtonHandler]=useState(false);

    const[creationStatus,setCreationStatus]=useState("inactive")


     function handleOnChange(event) {
     setFormData(event.currentTarget)
    }


    function handleErrors(formObjData) {
        setPasswordError(passwordErrorHandler(formObjData.password))
        setRePasswordError(rePasswordErrorHandler(formObjData.re_password, formObjData.password))
        setNameError(nameErrorHandler(formObjData.firstName))
        setLastNamesError(nameErrorHandler(formObjData.secondName))
        setEmailError(!validateEmail(formObjData.email))

    }
     function  submitValidation(){
      return   [passwordError,rePasswordError,nameError,lastNamesError,emailError].
         every((element)=>{return element===false})
     }

    function handleSubmit(event) {
         event.preventDefault()
        let formObjData;
        try {formObjData = convFormToObj(formData)}
        catch (error){formObjData = defaultSignInData}

        handleErrors(formObjData);

        if(submitValidation() && formObjData.email!=="not_valid")
        {
            setCreationStatus(saveAdmin(formObjData) )
        }


    }

    return(
        <SmallTemplate maxWidth="xs">

            <Typography variant="h6" marginTop={2}
            marginBottom={2}>
                {textTemplate.signin.signin}
            </Typography>

            <Box sx={{width:"60%",justifyContent:"center",display:"grid", gap:2}}  component="form"  onChange={handleOnChange} container >

                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.email}
                               error={emailError}
                               helperText={emailError? textTemplate.signin.mail_error:
                                   ""}
                               focused
                               type="email"
                               name="email"
                               id="email"
                               margin="dense"
                               fullWidth

                    />


                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.name}
                               focused
                               margin="dense"
                               fullWidth
                               onSelect={()=>console.log("sss")}
                               error={nameError}
                               helperText={nameError? textTemplate.signin.names_error:
                                   ""}
                               name="name"
                               id="name"
                               type="text"

                    />
                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.surname}
                               focused
                               error={lastNamesError}
                               helperText={lastNamesError? textTemplate.signin.names_error:
                                   ""}
                               name="surname"
                               id="surname"
                               type="text"
                               margin="dense"
                               fullWidth

                    />
                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.password}
                               focused
                               error={passwordError}
                               helperText={passwordError? textTemplate.signin.password_error:
                                   ""}
                               id="email_re"
                               name="password"
                               type="password"
                               margin="dense"
                               fullWidth
                    />
                    <TextField size="small" variant="outlined"
                               label={textTemplate.signin.re_password}
                               focused
                               helperText={rePasswordError? textTemplate.signin.re_password_error:
                                   ""}
                               id="email_re"
                               error={rePasswordError}
                               name="re_password"
                               type="password"
                               margin="dense"
                               fullWidth
                    />

                    <FormGroup>
                        <FormControlLabel control={<Checkbox defaultChecked />} label={textTemplate.signin.terms} name="checkbox" id="checkbox" />
                    </FormGroup>


                    <Button disabled={buttonHandler}  onClick={handleSubmit} type="outlined">
                        {textTemplate.signin.signin_button}
        </Button>

                <SignInResponseHandler button={(isActive)=>setButtonHandler(isActive)} data={creationStatus} />



            </Box>
        </SmallTemplate>
    )
}