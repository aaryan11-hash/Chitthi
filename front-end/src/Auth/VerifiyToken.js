import React, { useRef } from 'react';

export default function VerifiyToken({ tokenAuth }) {
    const passToken = useRef();

    return (
        <div>
            <input ref={passToken} type="text" /> Enter the token sent to you on
            mail
            <button onClick={(e) => tokenAuth(passToken.current.value)}>
                Submit
            </button>
        </div>
    );
}
