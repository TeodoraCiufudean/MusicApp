import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import TokenGen from './TokenGenReg'; // Import the TokenPopup component
import './Register.css';

function Register() {
  // State variables for registration form fields
  const [user, setUser] = useState({
    userId : '',
    firstName: '',
    lastName: '',
    emailAddress: '',
    password: '',
    age: '',
    role: 'USER', // Default role
  });
  const [token, setToken] = useState('');
  const [showTokenPopup, setShowTokenPopup] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser((prevUser) => ({
      ...prevUser,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const response = await fetch('http://localhost:8080/api/v1/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
      });

      const data = await response.json();
      if (response.ok) {
        console.log('Registration successful:', data);
        setToken(data.token);
        setShowTokenPopup(true);
        // Clear form fields
        setUser({
          userId: '',
          firstName: '',
          lastName: '',
          emailAddress: '',
          password: '',
          age: '',
          role: 'USER',
        });
        setError('');
      } else {
        console.error('Registration failed:', data.message);
        setError(data.message || 'Registration failed');
      }
    } catch (error) {
      console.error('Registration failed:', error);
      setError('Registration failed. Please try again later.');
    }
  };

  const handleTokenPopupClose = () => {
    setShowTokenPopup(false);
    navigate("/manage");
  };


  return (
    <div className="registerWrapper">
      <div className="registerContainer">
        <h2>Register</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="firstName">First Name:</label>
            <input
              type="text"
              id="firstName"
              name="firstName"
              value={user.firstName}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="lastName">Last Name:</label>
            <input
              type="text"
              id="lastName"
              name="lastName"
              value={user.lastName}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="emailAddress">Email:</label>
            <input
              type="email"
              id="emailAddress"
              name="emailAddress"
              value={user.emailAddress}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              name="password"
              value={user.password}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="age">Age:</label>
            <input
              type="number"
              id="age"
              name="age"
              value={user.age}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="role">Role:</label>
            <select id="role" name="role" value={user.role} onChange={handleChange}>
              <option value="USER">User</option>
              <option value="ADMIN">Admin</option>
            </select>
          </div>
          <button type="submit">Register</button>
        </form>
        {error && <p className="error">{error}</p>}
      </div>

      {showTokenPopup && <TokenGen token={token} onClose={handleTokenPopupClose} />}
    </div>
  );
}

export default Register;
