import SmallTemplate from "../SmallTemplate";
import {createContext, useContext, useEffect, useState} from "react";
import {getAdmin, itHasTokenAndUser} from "../../data/DataService";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import santa from "../../jpg/santaAatar.png"
import santaUserDefault, {defaultAdmin} from "../../data/DefaultData";
import {Link, Navigate, useNavigate} from "react-router-dom";
import Logout from "../Logout";
import {AdminContex} from "./AdminAuth";
import {adminLeftMenu, adminRightMenu} from "../../data/textTemplate";
import Games from "./Games";
import Welcome from "./Welcome";
import NewGame from "./newGame/NewGame";
import Account from "./Account";

export const ButtonContex = createContext("Welcome")

export  default function Admin(props){

    let navigate = useNavigate;

    const adminData=useContext(AdminContex)


        const [anchorElNav, setAnchorElNav] = useState(null);
        const [anchorElUser, setAnchorElUser] = useState(null);
        const [currentUserButton,setCurrentUserButton] = useState(null);
        const [getLogout, setLogout] = useState(false)


        const handleOpenNavMenu = (event) => {

            setAnchorElNav(event.currentTarget);
        };
        const handleOpenUserMenu = (event) => {
            setAnchorElUser(event.currentTarget);

            }

        if(getLogout===true){
            localStorage.clear()

            return <Logout/>
        }



        const forward = () =>{
            if(currentUserButton==null||currentUserButton===adminLeftMenu[2])
                return <Welcome/>
            else if(currentUserButton===adminLeftMenu[0])
                return <Games/>
            else if(currentUserButton===adminLeftMenu[1]){
                return <NewGame/>
            }
            else if(currentUserButton===adminRightMenu[0])
                return <Account/>
            else if(currentUserButton===adminRightMenu[1]){
                setLogout(true)
            }
        }


        const handleCloseNavMenu = (event) => {
            setAnchorElNav(null);

            setCurrentUserButton(event)






        };

        const handleCloseUserMenu = () => {
            setAnchorElUser(null);
        };





        return(

            <ButtonContex.Provider value={currentUserButton}>
        <SmallTemplate maxWidth="sm">
            <AppBar position="static" sx={{border: '2px groove red',
                borderRadius: "5px", mt:2,maxHeight:36
                ,width:11/12}}>

                    <Toolbar  sx={{ margin:-2,alignItems:"center",justifyContent:"center"}}>


                        <Box   sx={{  flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
                            <IconButton
                                size="small"
                                aria-label="account of current user"
                                aria-controls="menu-appbar"
                                aria-haspopup="false"
                                onClick={handleOpenNavMenu}
                                color="inherit"
                            >
                                <MenuIcon />
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
                                    display: { xs: 'block', md: 'none' },
                                }}
                            >
                                {adminLeftMenu.map((page) => (
                                    <MenuItem id={page} key={page} onClick={()=>{handleCloseNavMenu(page)}}>
                                        <Typography  textAlign="center">{page}</Typography>
                                    </MenuItem>
                                ))}
                            </Menu>
                        </Box>

                        <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                            {adminLeftMenu.map((page) => (

                                <Button
                                    key={page}
                                    onClick={()=>handleCloseNavMenu(page)}
                                    sx={{ my: 0, color: 'white', display: 'blovk' }}
                                >
                                    {page}
                                </Button>
                            ))}
                        </Box>

                        <Box  sx={{ flexGrow: 0 }}>
                            <Tooltip title="Account">
                                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0}}>
                                    <Avatar sx={{height:24,width:26}}  alt="Remy Sharp" src={santa} />
                                </IconButton>
                            </Tooltip>
                            <Menu
                                sx={{ mt: '20px' }}
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
                                <Typography>
                                    {props.data.data.login }
                                </Typography>
                                {adminRightMenu.map((setting) => (
                                    <MenuItem  key={setting} divider
                                              onClick={()=>handleCloseNavMenu(setting)}>
                                        <Typography textAlign="center">{setting}</Typography>
                                    </MenuItem>

                                ))}
                            </Menu>
                        </Box>
                    </Toolbar>

            </AppBar>

            <Box  sx={{ flexGrow: 0 }}>


                {forward()}

            </Box>

        </SmallTemplate >
                </ButtonContex.Provider>

    ) }

