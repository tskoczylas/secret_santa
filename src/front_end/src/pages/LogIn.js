import SmallTemplate from "./SmallTemplate";
import { textTemplate } from "../data/textTemplate";
import Typography from "@mui/material/Typography";
import { Button, Grid, TextField } from "@mui/material";
import LoginIcon from "@mui/icons-material/Login";
import { Link, useNavigate } from "react-router-dom";
import {  useState } from "react";
import { login } from "../data/DataService";
import Box from "@mui/material/Box";
export default function LogIn() {
  const [getButtonState, setButtonState] = useState(false);
  const [getFormData, setFormData] = useState();
  let navigate = useNavigate();
  const [authError, setAuthError] = useState();

  const handleLogin = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    setFormData(data);
  };

  if (getButtonState === true) {
    setButtonState(false);
    login(getFormData.get("username"), getFormData.get("password")).then(
      (response) => {
        if (response === "success") {
          localStorage.setItem("adminName", getFormData.get("username"));
          navigate("/admin");
            window.location.reload(false)
        } else setAuthError(true);
      }
    );
  }

  return (
    <SmallTemplate maxWidth="xs">
      <Typography
        marginTop={2}
        component={"h1"}
        variant={"h5"}
        align={"center"}
      >
        {textTemplate.login.login_head}
      </Typography>

      <Typography
        marginTop={2}
        marginBottom={3}
        component={"h1"}
        variant={"h6"}
        align={"center"}
      >
        {textTemplate.login.login_h2}
      </Typography>
      <Box component="form" onChange={handleLogin}>
        <Grid container justifyContent="center" spacing={3}>
          <Grid item alignItems={"center"} xs={7} sm={7}>
            <TextField
              id="username"
              variant="outlined"
              error={authError === true}
              name="username"
              margin="dense"
              label={textTemplate.login.user_name}
              fullWidth
            ></TextField>

            <TextField
              id="password"
              margin="dense"
              variant="outlined"
              error={authError == true}
              type="password"
              name="password"
              label={textTemplate.login.password}
              fullWidth
            ></TextField>

            {authError ? (
              <Typography color="error" variant="body2">
                {textTemplate.login.password_fail}
              </Typography>
            ) : (
              ""
            )}
          </Grid>

          <Grid item xs={7} sm={7}>
            <Button
              onClick={() => {
                setButtonState(true);
              }}
              color="primary"
              endIcon={<LoginIcon />}
            >
              {textTemplate.login.login}
            </Button>
          </Grid>
          <Grid item xs={7} sm={7}>
            <Typography variant="body2">
              {textTemplate.login.sigin_tex}
            </Typography>

            <Link to="/signin">
              <Typography variant="body2">
                {textTemplate.login.sigin_link}
              </Typography>
            </Link>
          </Grid>
        </Grid>
      </Box>
    </SmallTemplate>
  );
}
