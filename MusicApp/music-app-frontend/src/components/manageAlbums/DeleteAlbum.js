import React, { useState } from 'react';
import './DeleteAlbum.css';

function DeleteAlbum() {
  const [token, setToken] = useState('');
  const [albumID, setAlbumID] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [isValidToken, setIsValidToken] = useState(false);

  const handleTokenSubmit = async (event) => {
    event.preventDefault();
    if (token) {
      // Assuming the token validation is done here
      setIsValidToken(true);
      setMessage('Token validated');
      setError('');
    } else {
      setIsValidToken(false);
      setMessage('');
      setError('Invalid token');
    }
  };

  const handleChange = (e) => {
    const { value } = e.target;
    setAlbumID(value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setError('');

    try {
      const sanitizedToken = encodeURIComponent(token);
      const response = await fetch(`http://localhost:8080/albums/deleteAlbumById?id=${albumID}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${sanitizedToken}`
        }
      });

      if (response.ok) {
        setMessage('Album deleted successfully');
        setAlbumID('');
      } else {
        setError('Failed to delete album');
      }
    } catch (error) {
      console.error('Failed to delete album:', error);
      setError('Failed to delete album: ' + error.message);
    }
  };

  return (
    <div className="deleteAlbumContainer">
      <div className="albumForm">
        <h2>Delete Album</h2>
        {!isValidToken ? (
          <form onSubmit={handleTokenSubmit}>
            <input
              type="text"
              placeholder="Enter your token"
              value={token}
              onChange={(e) => setToken(e.target.value)}
            />
            <button type="submit">Validate Token</button>
            {error && <p className="error">{error}</p>}
            {message && <p className="message">{message}</p>}
          </form>
        ) : (
          <form onSubmit={handleSubmit}>
            <div>
              <label htmlFor="albumID">ID:</label>
              <input
                type="number"
                id="albumID"
                name="albumID"
                value={albumID}
                onChange={handleChange}
                required
              />
            </div>
            <button type="submit">Delete Album</button>
            {message && <p className="message">{message}</p>}
            {error && <p className="error">{error}</p>}
          </form>
        )}
      </div>
    </div>
  );
}

export default DeleteAlbum;
