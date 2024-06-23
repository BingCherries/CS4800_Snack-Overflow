import React, { useState } from "react";
import {HiOutlineBars3} from "react-icons/hi2";
import {
  Box,
  Drawer,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import Divider from "@mui/material/Divider";
import List from "@mui/material/List";
import HomeIcon from '@mui/icons-material/Home';
import InfoIcon from '@mui/icons-material/Info';
import LoginIcon from '@mui/icons-material/Login';
import PhoneRoundedIcon from '@mui/icons-material/HowToReg';
import {Link, useMatch, useResolvedPath} from 'react-router-dom';
import { NavLink } from 'react-router-dom';

export default function Navbar(){
  const [openMenu, setOpenMenu] = useState(false);
  const menuOptions = [
    {
      text: "Home",
      icon: <HomeIcon />,
    },
    {
      text: "About",
      icon: <InfoIcon />,
    },
    {
      text: "Login",
      icon: <LoginIcon />,
    },
    {
      text: "Signup",
      icon: <PhoneRoundedIcon />,
    },
  ];
  return (
    <nav className="navbar-color">
      <div className="nav-logo-container">
        <h3>SAFEPAWS</h3>
      </div>
      <div className="navbar-links-container">
  <NavLink exact to="/" className="nav-link" activeClassName="active">Home</NavLink>
  <NavLink to="/about" className="nav-link" activeClassName="active">About us</NavLink>
  <NavLink to="/log_ingredients" className="nav-link" activeClassName="active">Log Ingredients</NavLink>
  <NavLink to="/record_reactions" className="nav-link" activeClassName="active">Record Reactions</NavLink>
  <NavLink to="/login" className="nav-link" activeClassName="active">Log in</NavLink>
  <NavLink to="/signup" ><button className="primary-button">Sign up</button></NavLink> 
</div>
      <div className="navbar-menu-container">
        <HiOutlineBars3 onClick={() => setOpenMenu(true)} />
      </div>
      <Drawer open={openMenu} onClose={() => setOpenMenu(false)} anchor="right">
        <Box
          sx={{ width: 250 }}
          role="presentation"
          onClick={() => setOpenMenu(false)}
          onKeyDown={() => setOpenMenu(false)}
        >
          <List>
            {menuOptions.map((item) => (
              <ListItem key={item.text} disablePadding>
                <ListItemButton>
                  <ListItemIcon>{item.icon}</ListItemIcon>
                  <ListItemText primary={item.text} />
                </ListItemButton>
              </ListItem>
            ))}
          </List>
          <Divider />
        </Box>
      </Drawer>
    </nav>
    );
  };

   
