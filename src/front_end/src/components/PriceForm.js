import FormControlLabel from "@mui/material/FormControlLabel";
import {
    FormControl,
    FormLabel,
    Radio,
    RadioGroup,
} from "@mui/material";
import {TokenContex} from "../pages/token/TokenMain";
import {useContext} from "react";


export default function PriceForm(props) {

    const userData = useContext(TokenContex)


    return (
        <FormControl disabled={userData.userComplete} component="fieldset">
            <FormLabel component="legend">Wybierz kwotę do jakiej chciałbyś prezent:</FormLabel>
            <RadioGroup row aria-label="gift" name="gift"
                        defaultValue={userData.admin.secondChosenPrice}

            >
                <FormControlLabel
                    value={userData.admin.firstChosenPrice}
                    control={<Radio/>}
                    label={userData.admin.firstChosenPrice + " zł"}
                />
                <FormControlLabel
                    value={userData.admin.secondChosenPrice}
                    control={<Radio/>}
                    label={userData.admin.secondChosenPrice + " zł"}/>
                <FormControlLabel
                    value={userData.admin.thirdChosenPrice}
                    control={<Radio/>}
                    label={userData.admin.thirdChosenPrice + " zł"}
                />
            </RadioGroup>
        </FormControl>
    )
}
