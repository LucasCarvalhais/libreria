import React from 'react';
export const ErrorMessage = ({ error }) => <div className="message">
    <p>Ocurrió un problema :(</p>
    <p>{error.toString()}</p>
</div>;
