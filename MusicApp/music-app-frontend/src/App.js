import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/firstPage/HomePage';
import Login from './components/loginPage/Login';
import Register from './components/RegisterPage/Register';
import ManagePage from './components/managePage/managePage';
import ManageAlbums from './components/manageAlbums/ManageAlbums';

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<HomePage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/manage" element={<ManagePage />} />
        <Route path="/manageAlbums" element={<ManageAlbums />} />
      </Routes>
    </Router>
  );
}

export default App;
