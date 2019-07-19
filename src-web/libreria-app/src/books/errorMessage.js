import React from 'react';

const ErrorMessage = ({ error }) => 
    <div className="message">
        <p>Ocurrió un problema :(</p>
        <p>{error.toString()}</p>
    </div>

export default ErrorMessage;