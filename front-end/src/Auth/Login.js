import React, { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import HeadDashBoard from '../DashBoard/HeadDashBoard';
import routes from '../Config/Routes';
import credentials from '../Config/Credentials';

function Login() {
    const navigateToChatView = useNavigate();
    const [successLogin, setSuccessLogin] = useState(
        'PLEASE ENTER LOGIN CREDENTIALS'
    );

    const userName = useRef();
    const password = useRef();
    const email = useRef();

    const [validUser, setValidUser] = useState(false);

    function authorizeUserLogin(e) {
        const loginUserCred = {
            username: userName.current.value,
            password: password.current.value
        };

        fetch(routes.LOGIN, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginUserCred)
        })
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    setSuccessLogin('USER NOT IN OUR DATABASE!! TRY AGAIN');
                    setValidUser(false);
                    userName.current.value = null;
                    password.current.value = null;
                    email.current.value = null;
                    return undefined;
                }
            })
            .then((result) => {
                if (result !== undefined) {
                    console.log('User logged in!!');
                    console.log(result);
                    sessionStorage.setItem(
                        credentials.USER_CREDENTIALS,
                        JSON.stringify({
                            userName: userName.current.value,
                            password: password.current.value,
                            email: email.current.value,
                            ...result
                        })
                    );
                    setValidUser(true);
                    setSuccessLogin(
                        'Successful login! Taking you to chat View'
                    );
                    setTimeout(() => {
                        navigateToChatView(routes.CHAT_VIEW);
                    }, 2000);
                }
            });
    }

    return (
        <div>
            <div>
                {!validUser ? <HeadDashBoard /> : null}
                {successLogin}
                {!validUser ? (
                    <div>
                        <div
                            style={{ backgroundColor: 'white', height: '40cm' }}
                        >
                            <p
                                style={{
                                    fontFamily: 'sans-serif',
                                    paddingLeft: '40%',
                                    paddingTop: '3.5cm',
                                    fontSize: 'larger'
                                }}
                            ></p>

                            <div
                                className="container"
                                style={{
                                    maxWidth: '18cm',
                                    backgroundColor: 'cornflowerblue',
                                    maxHeight: '20cm',
                                    borderRadius: '25px',
                                    boxShadow: '0px 0px 15px rgba(0,0,0,0.22)'
                                }}
                            >
                                <div
                                    className="container"
                                    style={{
                                        maxWidth: '15cm',
                                        paddingRight: '1cm',
                                        paddingTop: '1.5cm'
                                    }}
                                >
                                    <div className="form-group">
                                        <label for="userName">UserName</label>

                                        <input
                                            ref={userName}
                                            id="userName"
                                            class="form-control"
                                            aria-describedby="emailHelp"
                                            type="text"
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label for="password">Password</label>

                                        <input
                                            ref={password}
                                            type="text"
                                            id="password"
                                            aria-describedby="emailHelp"
                                            class="form-control"
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label for="email">Email</label>

                                        <input
                                            ref={email}
                                            id="email"
                                            class="form-control"
                                            aria-describedby="emailHelp"
                                            type="text"
                                        />
                                    </div>

                                    <input
                                        onClick={authorizeUserLogin}
                                        type="submit"
                                        value="register"
                                        class="btn btn-dark"
                                        style={{
                                            marginLeft: '5.5cm',
                                            width: '3cm'
                                        }}
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                ) : null}
            </div>
        </div>
    );
}

export default Login;
