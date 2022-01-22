import axios from "axios";
import {token} from "../../../data/AdminFirstStepService";

export default function lotteryService(id, complete ){


    const data = {id:id,
        complete:complete}

return(
    axios.post("/api/game/admin_games", data
    ,
        {headers: {
                Authorization:"Bearer " + token.access_token
            }
        }).then( res=> {
            console.log(res)
        return res
        }).catch(err=>console.log(err)) )

}

export  function getUserLottery(id,complete ){



    const data = {id:id,
        complete:complete}

    return(
        axios.post("/api/game/user_games", data
            ,
            {headers: {
                    Authorization:"Bearer " + token.access_token
                }
            }).then( res=> {

            return res
        }).catch(

         //   err=>console.log(err)

        ) )

}