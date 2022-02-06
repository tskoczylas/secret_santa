import SmallTemplate from "../SmallTemplate";
import {createContext, useContext, useEffect, useState} from "react";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import {useNavigate} from "react-router-dom";
import Logout from "../Logout";
import {AdminContex} from "./AdminAuth";
import {adminLeftMenu, adminRightMenu} from "../../data/textTemplate";
import Welcome from "./Welcome";
import NewGame from "./newGame/NewGame";
import Account from "./Account";
import {useParams} from "react-router";
import Lottery from "./games/Lottery";

export const ButtonContex = createContext("Welcome")

export default function Admin(props) {

    let navigate = useNavigate;

    const adminData = useContext(AdminContex)


    const [anchorElNav, setAnchorElNav] = useState(null);
    const [anchorElUser, setAnchorElUser] = useState(null);
    const [currentUserButton, setCurrentUserButton] = useState(null);
    const [getLogout, setLogout] = useState(false)


    let {page} = useParams()

    useEffect(() => {
        if (page === "new_game") {
            setCurrentUserButton(adminLeftMenu[1])
            forward()
        }

    }, [])

    const handleOpenNavMenu = (event) => {

        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event) => {
        setAnchorElUser(event.currentTarget);

    }

    if (getLogout === true) {
        localStorage.clear()

        return <Logout/>
    }


    const forward = () => {
        if (currentUserButton == null || currentUserButton === adminLeftMenu[0]) {
            return <NewGame navigate={(value) => {
                setCurrentUserButton(value)
            }}/>
        } else if (currentUserButton === adminLeftMenu[2])
            return <Lottery button={currentUserButton} expire={true}/>
        else if (currentUserButton === adminLeftMenu[1])
            return <Lottery button={currentUserButton} expire={false}/>

        else if (currentUserButton === adminRightMenu[0])
            return <Account/>
        else if (currentUserButton === adminRightMenu[1]) {
            setLogout(true)
        }
    }


    const handleCloseNavMenu = (event) => {
        setAnchorElNav(null);

        setCurrentUserButton(event)

    };


    function stringAvatar(name) {
        return {

            children: `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`,
        };
    }

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };


    return (

        <ButtonContex.Provider value={currentUserButton}>
            <SmallTemplate maxWidth="sm">
                <AppBar position="static" sx={{
                    border: '2px groove red',
                    borderRadius: "5px", mt: 2, maxHeight: 36
                    , width: 11 / 12
                }}>

                    <Toolbar sx={{margin: -2, alignItems: "center", justifyContent: "center"}}>


                        <Box sx={{flexGrow: 1, display: {xs: 'flex', md: 'none'}}}>
                            <IconButton
                                size="small"
                                aria-label="account of current user"
                                aria-controls="menu-appbar"
                                aria-haspopup="false"
                                onClick={handleOpenNavMenu}
                                color="inherit"
                            >
                                <MenuIcon/>
                            </IconButton>
                            <Menu
                                id="menu-appbar"

                                anchorEl={anchorElNav}
                                anchorOrigin={{
                                    vertical: 'bottom',
                                    horizontal: 'left',
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'left',
                                }}
                                open={Boolean(anchorElNav)}
                                onClose={handleCloseNavMenu}
                                sx={{
                                    display: {xs: 'block', md: 'none'},
                                }}
                            >
                                {adminLeftMenu.map((page) => (
                                    <MenuItem id={page} key={page} onClick={() => {
                                        handleCloseNavMenu(page)
                                    }}>
                                        <Typography textAlign="center">{page}</Typography>
                                    </MenuItem>
                                ))}
                            </Menu>
                        </Box>

                        <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}>
                            {adminLeftMenu.map((page) => (

                                <Button
                                    key={page}
                                    onClick={() => handleCloseNavMenu(page)}
                                    sx={{my: 0, fontWeight: "bold", color: 'white', display: 'block'}}
                                >
                                    {page}
                                </Button>
                            ))}
                        </Box>

                        <Box sx={{flexGrow: 0}}>
                            <Tooltip title="Account">
                                <IconButton onClick={handleOpenUserMenu} sx={{p: 0}}>
                                    <Avatar variant="rounded" sx={{
                                        height: 24,
                                        width: 24,
                                        boxShadow: 2
                                    }} {...stringAvatar(adminData.data.firstName + " " + adminData.data.secondName)} />

                                </IconButton>
                            </Tooltip>
                            <Menu
                                sx={{mt: '20px'}}
                                id="menu-appbar"
                                anchorEl={anchorElUser}
                                anchorOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                open={Boolean(anchorElUser)}
                                onClose={handleCloseUserMenu}
                            >


                                {adminRightMenu.map((setting) => (
                                    <MenuItem key={setting} divider
                                              onClick={() => handleCloseNavMenu(setting)}>
                                        <Typography sx={{fontSize: 14}} textAlign="center">{setting}</Typography>
                                    </MenuItem>

                                ))}
                            </Menu>
                        </Box>
                    </Toolbar>

                </AppBar>

                <Box sx={{flexGrow: 0}}>


                    {forward()}

                </Box>

            </SmallTemplate>
        </ButtonContex.Provider>

    )
}

