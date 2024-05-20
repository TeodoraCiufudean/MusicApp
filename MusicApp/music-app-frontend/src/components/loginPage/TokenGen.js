import React from 'react';
import './TokenGen.css'

function TokenGen({ token, onClose }) {
  return (
    <div className="tokenBox">
      <div className="tokenContent">
        <h2>Token Generated</h2>
        <p>Token: {token}</p>
        <button onClick={onClose}>Got It</button>
      </div>
    </div>
  );
}

export default TokenGen;
