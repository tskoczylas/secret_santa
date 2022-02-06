import Box from '@mui/material/Box';
import * as React from 'react';
import Collapse from '@mui/material/Collapse';
import IconButton from '@mui/material/IconButton';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import lotteryService, {getUserLottery} from "./LotteryService";
import {useContext, useEffect, useState} from "react";
import {AdminContex} from "../AdminAuth";
import {CircularProgress, LinearProgress, Tooltip} from "@mui/material";
import {textTemplate} from "../../../data/textTemplate";
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import CommentIcon from '@mui/icons-material/Comment';
import {format, formatDistanceToNow} from "date-fns";
import FaceRetouchingOffIcon from '@mui/icons-material/FaceRetouchingOff';
import CircularStatic from "../../../components/CircularProgressWithLabel";
import {createTheme, ThemeProvider} from '@mui/material/styles';


export default function Lottery(props) {


    const [adminLottery, setAdminLottery] = useState()
    const [rowsLottery, setRowsLottery] = useState();


    const adminDataContex = useContext(AdminContex)

    useEffect(() => {
        lotteryService(adminDataContex.data.adminId, props.expire).then(res => {
            setAdminLottery(res.data)
        })
    }, [props.expire])

    useEffect(() => {

        if (adminLottery !== undefined) {
            setRowsLottery(adminLottery.map((res) => {
                return createData(res.gameId, res.gameName, res.startDate, res.lastResponseDate, res.userText, res.percentageCompleteUsers)
            }))
        }


    }, [adminLottery])


    function createData(id, gameName, gameCreated, lotteryDate, adminMessage, percentageCompleteUsers) {
        return {
            id,
            gameName,
            gameCreated,
            lotteryDate,
            adminMessage,
            percentageCompleteUsers,
            users: undefined,
        };
    }


    const rows = rowsLottery


    function Row({row}) {


        const [gameIdSelected, setGameIdSelected] = useState();
        const [gameIdRow, setGameIdRow] = useState();

        const [open, setOpen] = React.useState(false);

        useEffect(() => {
            if (gameIdSelected !== undefined && open === true) {
                getUserLottery(gameIdSelected, props.expire).then(res => {
                    setGameIdRow((res.data))
                })
            }
        }, [gameIdSelected, open])


        return (
            <React.Fragment>

                < TableRow sx={{'& > *': {borderBottom: 'unset'}}}>
                    <TableCell>
                        <IconButton
                            aria-label="expand row"
                            size="small"
                            sx={{maxWidth: 2}}
                            onClick={() => {
                                setOpen(!open)
                                setGameIdSelected(row.id)
                            }}
                        >
                            {open ? <KeyboardArrowUpIcon/> : <KeyboardArrowDownIcon/>}
                        </IconButton>
                    </TableCell>
                    <TableCell sx={{fontWeight: "bold"}} align="center">{row.gameName}</TableCell>

                    <TableCell
                        onClick={() => alert(format(new Date(row.gameCreated), "Do  MMMM yyyy p ago",))}
                        align="center">{formatDistanceToNow(new Date(row.gameCreated), {addSuffix: true})}</TableCell>


                    <TableCell
                        onClick={() => alert(format(new Date(row.lotteryDate), "Do  MMMM yyyy p "))}

                        align="center">{row.completed ? <CheckCircleOutlineIcon sx={{color: "green"}}/>
                        : <CircularStatic progress={row.percentageCompleteUsers}/>}
                    </TableCell>

                    <TableCell
                        onClick={() => alert(row.adminMessage)}
                        align="center"><CommentIcon sx={{color: "blue"}}/></TableCell>
                </TableRow>
                <TableRow>
                    <TableCell style={{paddingBottom: 0, paddingTop: 0}} colSpan={6}>
                        <Collapse in={open} timeout="auto" unmountOnExit>

                            {gameIdRow !== undefined ?
                                <Box sx={{
                                    boxShadow: 3, fontWeight: 'bold', borderRadius: 2, mt: 2, mb: 2,
                                    borderColor: "black", bgcolor: "#FFF9F8"
                                }}>


                                    <Table size="small" aria-label="users">
                                        <TableHead>
                                            <TableRow>
                                                <TableCell>

                                                    {textTemplate.game.userGame.firstName}</TableCell>
                                                <TableCell
                                                    align="center">{textTemplate.game.userGame.lastName}</TableCell>
                                                <TableCell align="center">{textTemplate.game.userGame.email}</TableCell>
                                            </TableRow>
                                        </TableHead>
                                        <TableBody>
                                            {gameIdRow.map((users) => (

                                                < TableRow
                                                    key={users.userid}>
                                                    <Tooltip disableFocusListener disableTouchListener title="Add">

                                                        <TableCell align="center">{users.firstName === null ?
                                                            <FaceRetouchingOffIcon/> :
                                                            users.firstName
                                                        }</TableCell>
                                                    </Tooltip>
                                                    <TableCell align="center">{users.firstName === null ?
                                                        <FaceRetouchingOffIcon/> :
                                                        users.lastName
                                                    }</TableCell>
                                                    <TableCell align="center">
                                                        {users.email}
                                                    </TableCell>
                                                </TableRow>
                                            ))}
                                        </TableBody>
                                    </Table>

                                </Box>

                                :

                                <LinearProgress/>}

                        </Collapse>
                    </TableCell>
                </TableRow>
            </React.Fragment>
        );
    }


    const theme = createTheme({
        typography: {
            fontSize: 12
        },
        // MuiTableCell:{width:4},
        shape: {borderRadius: 2},
        spacing: 0,

        components: {
            Table: {
                styleOverrides: {
                    root: {
                        color: "red",
                        bgcolor: "red",
                        width: 2,
                    }
                }
            }

        }
    });


    return (
        <ThemeProvider theme={theme}>

            <TableContainer sx={{mt: 2, mb: 2}}

                            component={Box}
            >
                {rowsLottery !== undefined ?


                    <Table
                        sx={{width: {xs: 320, sm: 450}}}
                        padding="normal"
                        size="small" aria-label="collapsible table">
                        <TableHead>
                            <TableRow>
                                <TableCell sx={{fontWeight: "bold", maxWidth: 3}}
                                           align="left">{textTemplate.game.userGameTop}</TableCell>
                                <TableCell sx={{fontWeight: "bold"}}
                                           align="center">{textTemplate.game.game_name}</TableCell>

                                <TableCell sx={{fontWeight: "bold"}}
                                           align="center">{textTemplate.game.game_start_date}</TableCell>
                                <TableCell sx={{fontWeight: "bold", maxWidth: 5}}
                                           align="center">{textTemplate.game.isCompleted}</TableCell>
                                <TableCell sx={{fontWeight: "bold"}}
                                           align="right">{textTemplate.game.admin_message}</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {rows.map((row) => (

                                <Row key={row.name} row={row}/>

                            ))}
                        </TableBody>
                    </Table>
                    :
                    <CircularProgress sx={{mt: 3, mb: 3, alignItems: "center"}}/>
                }
            </TableContainer>

        </ThemeProvider>


    );

}
