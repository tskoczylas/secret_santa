
import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import santaUserDefault from "../data/DefaultData";
import TokenLoading from "./TokenLoading";

export const TokenContex = React.createContext();

export default function TokenMain() {
  const [getTokenData, setTokenData] = useState(santaUserDefault);

  const { token } = useParams();




 
  useEffect(() => {
    fetch("/api/santaToken/" + token)
      .then((res) => res.json())
      .then((data) => {
        setTokenData(data);
      })

      .catch((err) => {
        console.log(err)
        
      })},[])

  return (

    <TokenContex.Provider value={getTokenData}>

    <TokenLoading></TokenLoading>
    </TokenContex.Provider>


  );
}
