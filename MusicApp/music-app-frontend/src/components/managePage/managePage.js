import React from 'react';
import './managePage.css';
import { useNavigate } from "react-router-dom";

function ManagePage() {

    const navigate = useNavigate()
    const gotToNewPage=()=>{
        navigate("/manageAlbums");
    }
  return (
    <div className="buttonContainer">
      <button className="manageAlbumsButton" onClick={() => gotToNewPage()}>Manage Albums</button>
      <button className="manageSongsButton">Manage Songs</button>
    </div>
  );
}

export default ManagePage;