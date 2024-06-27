import React, { useState } from "react";
import { NavLink } from 'react-router-dom';

export default function Navbar(){
  return (
    <nav className="navbar-color">
      <div className="nav-logo-container">
        <h3>SAFEPAWS</h3>
      </div>
      <div className="navbar-links-container">
        <NavLink exact to="/" className="nav-link" activeClassName="active">Home</NavLink>
        <NavLink to="/about" className="nav-link" activeClassName="active">About us</NavLink>
        <NavLink to="/record_reactions" className="nav-link" activeClassName="active">Record Reactions</NavLink>
        <NavLink to="/dashboard" className="nav-link" activeClassName="active">Dashboard</NavLink>
        <NavLink to="/login" className="nav-link" activeClassName="active">Log in</NavLink>
        <NavLink to="/signup" className="nav-link" activeClassName="active">Sign up</NavLink>
      </div>
    </nav>
  );
}