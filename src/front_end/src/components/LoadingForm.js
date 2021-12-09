import { Grid, LinearProgress } from "@mui/material";

export default function LoadingForm() {
  return (
    <Grid container spacing={2}>
      <Grid item sm={12}>
        <LinearProgress />
      </Grid>

      <Grid item xs={12} sm={6}>
        <LinearProgress />
      </Grid>

      <Grid item xs={12} sm={6}>
        <LinearProgress />
      </Grid>
      <Grid item xs={12} sm={6}>
        <LinearProgress />
      </Grid>
      <Grid item xs={12} sm={6}>
        <LinearProgress />
      </Grid>
      <Grid item xs={12} sm={6}>
        <LinearProgress />
      </Grid>

      <Grid item xs={12}>
        <LinearProgress />
      </Grid>

      <Grid item xs={12}>
        <LinearProgress />
      </Grid>
    </Grid>
  );
}
