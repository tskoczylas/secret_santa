import * as React from "react";

import { Typography, Container, Grid , Card} from "@mui/material";
import { useEffect } from "react";
import { useState } from "react";
import MovieCard from "../components/MovieCards";

function MovieList() {
  const [movieListApi, setMovieListApi] = useState([]);
  useEffect(() => {
    fetch("/catalog/66/")
      .then((res) => res.json())
      .then((data) => setMovieListApi(data.moviesList))
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <Container>
      <Grid container spacing={3}>
        {movieListApi.map((mov) => {
          return (
            <Grid key={mov.movieId} item lg={4} xs={12} md={8}>
              {" "}
            {<MovieCard movie = {mov}/>}
            </Grid>
          );
        })}
      </Grid>
    </Container>
  );
}

export default MovieList;
