import Typography from "@mui/material/Typography";
import SmallTemplate from "../SmallTemplate";
import Grid from "@mui/material/Grid";
import {Button} from "@mui/material";
import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import { useNavigate} from "react-router-dom";
import christmas_letter from "../../jpg/christmas-invitation.svg"
import christmas_wishes from "../../jpg/christmas-wishes.svg"
import christmas_shopping from "../../jpg/christmas-shopping.svg"
import christmas_socks from "../../jpg/christmas-socks.svg"
import {textTemplate} from "../../data/textTemplate";

export default function MainPage(){
    let navigate = useNavigate()


    return(

            <SmallTemplate  maxWidth="xs">
                <Grid   mt={1}  alignItems="center" alignContent="center"  textAlign="center" container spacing={3}>
                    <Grid item xs={12} sm={12}>
            <Typography variant="h6" sx={{fontWeight:"bold"}}>
                {textTemplate.main_page.h1}
            </Typography>

                        <Typography sx={{mt:3, fontSize:14}} >
                            {textTemplate.main_page.h6}
                        </Typography>



                    </Grid>
                    <Grid item>
                        <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
                            <ListItem alignItems="flex-start">
                                <ListItemAvatar>
                                    <Avatar alt="christmas_letter" src={christmas_letter} />
                                </ListItemAvatar>
                                <ListItemText
                                    primary= {textTemplate.main_page.prim_1}

                                    secondary={
                                        <React.Fragment>
                                            <Typography
                                                sx={{ display: 'inline' }}
                                                component="span"
                                                variant="body2"
                                                color="text.primary"
                                            >
                                                {textTemplate.main_page.item_1}


                                            </Typography>
                                        </React.Fragment>
                                    }
                                />
                            </ListItem>
                            <Divider variant="inset" component="li" />
                            <ListItem alignItems="flex-start">
                                <ListItemAvatar>
                                    <Avatar alt="christmas_shopping" src={christmas_shopping} />
                                </ListItemAvatar>
                                <ListItemText
                                    primary= {textTemplate.main_page.prim_2}

                                    secondary={
                                        <React.Fragment>
                                            <Typography
                                                sx={{ display: 'inline' }}
                                                component="span"
                                                variant="body2"
                                                color="text.primary"
                                            >
                                                {textTemplate.main_page.item_2}


                                            </Typography>
                                        </React.Fragment>
                                    }
                                />
                            </ListItem>
                            <Divider variant="inset" component="li" />
                            <ListItem alignItems="flex-start">
                                <ListItemAvatar>
                                    <Avatar alt="christmas_socks" src={christmas_socks} />
                                </ListItemAvatar>
                                <ListItemText
                                    primary={textTemplate.main_page.prim_3}
                                    secondary={
                                        <React.Fragment>
                                            <Typography
                                                sx={{ display: 'inline' }}
                                                component="span"
                                                variant="body2"
                                                color="text.primary"
                                            >
                                                {textTemplate.main_page.item_3}

                                            </Typography>
                                        </React.Fragment>
                                    }
                                />
                            </ListItem>
                            <Divider variant="inset" component="li" />
                            <ListItem alignItems="flex-start">
                                <ListItemAvatar>
                                    <Avatar alt="christmas_wishes" src={christmas_wishes} />
                                </ListItemAvatar>
                                <ListItemText
                                    primary={textTemplate.main_page.prim_4}
                                    secondary={
                                        <React.Fragment>
                                            <Typography
                                                sx={{ display: 'inline' }}
                                                component="span"
                                                variant="body2"
                                                color="text.primary"
                                            >
                                                {textTemplate.main_page.item_4}


                                            </Typography>
                                        </React.Fragment>
                                    }
                                />
                            </ListItem>
                        </List>

                    </Grid>
                    <Grid item xs={6} sm={6}>

                        <Button
                            size="small"
                        onClick={()=>navigate("/signin")}
                        variant="outlined"

                            >
                            {textTemplate.main_page.signin}
                        </Button>


                    </Grid>

                <Grid item xs={6} sm={6}>
                    <Button
                        size="small"
                        onClick={()=>navigate("/login")}

                        variant="outlined"

                    >
                        {textTemplate.main_page.login}
                    </Button>

                </Grid>

                    <Grid item xs={12} sm={12} >
                        <Typography sx={{fontSize:12}}  >
                            {textTemplate.main_page.created_by}
                        </Typography>

                    </Grid>

                </Grid>
            </SmallTemplate>

    )
}