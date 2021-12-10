import {  Grid, Typography } from "@mui/material";
import ProgressBar from "../../components/ProgressBar";
import TextForm from "../../components/TextForm";


export default function TokenFounded() {
  return (
    <Grid container spacing={2}>
      <Grid item sm={12}>
        <Typography align="center" component="h1" variant="h5">
          Zapisz sie do naszej zabawy!
        </Typography>
      </Grid>

      <Grid item xs={12} sm={6}>
        <Typography component="h2" align="center" variant="body2">
          Do losowania pozostalo
        </Typography>
      </Grid>
      <Grid item xs={12} sm={6}>
        <ProgressBar></ProgressBar>
      </Grid>

      <TextForm></TextForm>
    </Grid>
  );
}
