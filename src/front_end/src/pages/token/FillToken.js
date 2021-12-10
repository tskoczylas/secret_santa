import {
  Button,
  Grid,
  TextField,
  Typography,
} from "@mui/material";


export default  function FillToken() {
  return (
    <Grid container
          direction="column"
          alignItems="center" justifyContent="center" spacing={3}>
      <Grid item sm={12} >
        <Typography align="center" component="h1" variant="h5">
          Zapisz sie do naszej zabawy!
        </Typography>
      </Grid>

      <Grid item sm={6} xs={6}>
        <TextField focused size="small"  label="Wpisz swoj token" id="tokenId" variant="outlined">
            Wpisz swoj token
        </TextField>
      </Grid>

      <Grid item sm={6} xs={6}>

        <Button
        variant="outlined"
        color="secondary"

        >
            Szukaj
        </Button>
      </Grid>
    </Grid>
  );
}
