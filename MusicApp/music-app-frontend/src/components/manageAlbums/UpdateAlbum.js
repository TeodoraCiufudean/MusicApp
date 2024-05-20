import React, { useState } from 'react';
import './UpdateAlbum.css';

function InsertAlbum() {
  const [album, setAlbum] = useState({
    albumID : ' ',
    albumTitle: '',
    artist: '',
    year: '',
    genre: '',
    record: ''
  });
  const [token, setToken] = useState('');
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
    const { name, value } = e.target;
    setAlbum((prevAlbum) => ({
      ...prevAlbum,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setError('');

    try {
    const sanitizedToken = encodeURIComponent(token);
      const response = await fetch('http://localhost:8080/albums/updateAlbum', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${sanitizedToken}`
        },
        body: JSON.stringify(album)
      });

      if (response.ok) {
        setMessage('Album updated successfully');
        setAlbum({
          albumID: ' ',
          albumTitle: '',
          artist: '',
          year: '',
          genre: '',
          record: ''
        });
      } else {
        setError('Failed to update album');
      }
    } catch (error) {
      console.error('Failed to update album:', error);
      setError('Failed to update album: ' + error.message);
    }
  };

  return (
    <div className="updateAlbumContainer">
      <div className="albumForm">
        <h2>Update Album</h2>
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
                value={album.albumID}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label htmlFor="albumTitle">Title:</label>
              <input
                type="text"
                id="albumTitle"
                name="albumTitle"
                value={album.albumTitle}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label htmlFor="artist">Artist:</label>
              <input
                type="text"
                id="artist"
                name="artist"
                value={album.artist}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label htmlFor="year">Year:</label>
              <input
                type="number"
                id="year"
                name="year"
                value={album.year}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label htmlFor="genre">Genre:</label>
              <input
                type="text"
                id="genre"
                name="genre"
                value={album.genre}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label htmlFor="record">Record:</label>
              <input
                type="text"
                id="record"
                name="record"
                value={album.record}
                onChange={handleChange}
                required
              />
            </div>
            <button type="submit">Update Album</button>
            {message && <p className="message">{message}</p>}
            {error && <p className="error">{error}</p>}
          </form>
        )}
      </div>
    </div>
  );
}

export default InsertAlbum;
