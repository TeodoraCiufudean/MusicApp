import React, { useState } from 'react';
import ShowAlbums from './ShowAlbums'; // Import the ShowAlbums component
import InsertAlbum from './InsertAlbum';
import UpdateAlbum from './UpdateAlbum';
import DeleteAlbum from './DeleteAlbum';
import './ManageAlbums.css';
import { useNavigate } from 'react-router-dom';

function ManageAlbums() {
  const [displayComponent, setDisplayComponent] = useState(null);
  const navigate = useNavigate();

  const showAllAlbums = () => {
    setDisplayComponent('showAllAlbums');
  };

  const insertAlbum = () => {
    setDisplayComponent('insertAlbum');
  };

  const updateAlbum = () => {
    setDisplayComponent('updateAlbum');
  };

  const deleteAlbum = () => {
    setDisplayComponent('deleteAlbum');
  };

  return (
    <div className="manageAlbumsContainer">
      <div className="manageAlbumsButtons">
        <div className="manageAlbumsButtonsContainer">
          <button onClick={showAllAlbums}>Show All Albums</button>
          <button onClick={insertAlbum}>Insert Album</button>
          <button onClick={updateAlbum}>Edit Album</button>
          <button onClick={deleteAlbum}>Delete Album</button>
        </div>
      </div>
      <div className="albumList">
        {/* Conditionally render the ShowAlbums component */}
        {displayComponent === 'showAllAlbums' && <ShowAlbums />}
        {displayComponent === 'insertAlbum' && <InsertAlbum />}
        {displayComponent === 'updateAlbum' && <UpdateAlbum />}
        {displayComponent === 'deleteAlbum' && <DeleteAlbum />}
      </div>
    </div>
  );
}

export default ManageAlbums;
