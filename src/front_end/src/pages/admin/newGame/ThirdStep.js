import Grid from "@mui/material/Grid";
import {TextField} from "@mui/material";
import {adminStepperData, textTemplate} from "../../../data/textTemplate";
import {Box} from "@mui/system";
import Typography from "@mui/material/Typography";
import {useContext, useState} from "react";
import {thirdStepValidation} from "./NewGame";
import {AdminContex} from "../AdminAuth";

export default function ThirdStep(){

  const  [message,setMessage] =
      useState(localStorage.getItem("userText"));
    const  [name,setName] =
        useState(localStorage.getItem("gameName"));

    function handleTextChange(event) {
        event.preventDefault()
        const formData  = new FormData(event.currentTarget);

       const textUser = formData.get("userText")
        setMessage(textUser)
        localStorage.setItem("userText",textUser)

        const gameName = formData.get("name")
        setName(gameName)
        localStorage.setItem("gameName",gameName)

    }








    return(
        <Box onChange={handleTextChange} component="form" autoComplete="off" noValidate sx={{width:"90%",
            m:2,
            display:"grid",
            gridAutoFlow: 'row',
            gap:2,
            textAlign:"center",
            alignContent:"center",
            alignItems:"center"}}>


        <Typography sx={{fontWeight:"bold"}} variant="body2">
                    {textTemplate.newGame.thirdStep.typography_top}
                </Typography>



                    <TextField

                        error={!thirdStepValidation(message)}
                        helperText={!thirdStepValidation(message)?
                            adminStepperData[2].error:""
                    }
                        name="userText"
                        id="userText"
                        size="small"
                        defaultValue={localStorage.getItem("userText")}
                        label={textTemplate.newGame.thirdStep.text_field_label}
                        fullWidth
                        multiline

                        id="outlined-uncontrolled"

                        rows={4}>


                    </TextField>


                        <Typography sx={{fontWeight:"bold"}} variant="body2">
                            {textTemplate.newGame.thirdStep.game_name_top}
                        </Typography>




                    <TextField
                        name="name"
                        id="name"
                        error={!thirdStepValidation(name)}
                        helperText={!thirdStepValidation(name)?
                            textTemplate.newGame.thirdStep.name_error:""
                        }
                        size="small"
                        defaultValue={localStorage.getItem("gameName")}
                        label={textTemplate.newGame.thirdStep.game_name}
                        fullWidth


                        id="outlined-uncontrolled"

                        rows={1}>


                    </TextField>

                </Box>




    )
}