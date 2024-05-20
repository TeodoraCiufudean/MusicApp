import React, { useState, useEffect } from 'react';
import './ShowAlbums.css';

function ShowAlbums() {
  const [albums, setAlbums] = useState([]);
  const [loading, setLoading] = useState(false);
  const [token, setToken] = useState('');
  const [error, setError] = useState('');

  const fetchAlbums = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await fetch('http://localhost:8080/albums/getAllAlbums', {
        headers: {
          Authorization: `Bearer ${token}`, // Include the token in the request headers
          'Content-Type': 'application/json'
        }
      });

      if (!response.ok) {
        // Handle unauthorized access or other errors
        console.error('Failed to fetch albums:', response.statusText);
        setError('Failed to fetch albums: ' + response.statusText);
        setLoading(false);
        return;
      }

      const data = await response.json();
      setAlbums(data);
      setLoading(false);
    } catch (error) {
      console.error('Failed to fetch albums:', error);
      setError('Failed to fetch albums: ' + error.message);
      setLoading(false);
    }
  };

  const handleTokenSubmit = (event) => {
    event.preventDefault();
    fetchAlbums();
  };

  return (
    <div className="showAllAlbumsContainer">
      <div className="albumsBox">
        <h2>All Albums</h2>
        <form onSubmit={handleTokenSubmit}>
          <input
            type="text"
            placeholder="Enter your token"
            value={token}
            onChange={(e) => setToken(e.target.value)}
          />
          <button type="submit">Validate Token</button>
        </form>
        {error && <p className="error">{error}</p>}
        {loading ? (
          <p>Loading...</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Title</th>
                <th>Artist</th>
                <th>Year</th>
                <th>Genre</th>
                <th>Record</th>
                {/* Add more table headers if needed */}
              </tr>
            </thead>
            <tbody>
              {albums.map((album) => (
                <tr key={album.albumID}>
                  <td>{album.albumTitle}</td>
                  <td>{album.artist}</td>
                  <td>{album.year}</td>
                  <td>{album.genre}</td>
                  <td>{album.record}</td>
                  {/* Add more table cells if needed */}
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

export default ShowAlbums;
