import { Card, CardContent, CardHeader,CardMedia,IconButton, Typography } from "@mui/material";
import DeleteSharpIcon from '@mui/icons-material/DeleteSharp';

function MovieCard(props) {
  return (
    <Card>
      <CardHeader
        action={
          <IconButton onClick={()=>{console.log("dd")}}>
             <DeleteSharpIcon/>
          </IconButton>
        }
        title={props.movie.title}
        subheader={props.movie.voteAverage}
      />
      <CardMedia
        component="img"
        height="200"
        image={props.movie.imgPath}
        alt="Paella dish"
      />
      <CardContent>
          <Typography varian = 'body3' color="textSecoundary">
              {props.movie.desc}

          </Typography>
      </CardContent>

    </Card>
  );
}

export default MovieCard;
