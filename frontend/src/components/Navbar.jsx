import React, {useState} from 'react';
import logo from '../assets/logo.png';
import { NavLink } from 'react-router-dom';
import './Navbar.css';

function Navbar() {

    return (
        <>
            <nav className='navbar'>
                <div className='navbar-container-1'>
                    <img src = {logo} alt='logo' className='logo-container'/>
                </div>
                <div className='navbar-container-2'>
                    <p><NavLink to="/">Home</NavLink></p>
                    <p><NavLink to="/record">Triangle Record</NavLink></p>
                </div>
            </nav>
        </>
    )
}

export default Navbar