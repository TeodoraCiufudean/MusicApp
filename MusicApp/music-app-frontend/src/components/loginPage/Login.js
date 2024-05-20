import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import TokenGen from './TokenGen'; // Import the TokenPopup component
import './Login.css';

function Login() {
  const [emailAddress, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [token, setToken] = useState('');
  const [showTokenPopup, setShowTokenPopup] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/v1/auth/authenticate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ emailAddress, password }),
      });
      const data = await response.json();
      if (response.ok) {
        console.log('Login successful:', data);
        setToken(data.token);
        setShowTokenPopup(true);
        //localStorage.setItem('token', data.token);
      } else {
        console.error('Login failed:', data.message);
      }
    } catch (error) {
      console.error('Login failed:', error);
    }
  };

  const handleTokenPopupClose = () => {
    setShowTokenPopup(false);
    navigate("/manage");
  };

  return (
    <div className="loginWrapper">
      <div className="loginContainer">
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              value={emailAddress}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div>
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit">Login</button>
        </form>
      </div>
      {showTokenPopup && <TokenGen token={token} onClose={handleTokenPopupClose} />}
    </div>
  );
}

export default Login;

