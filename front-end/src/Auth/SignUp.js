import React, { useRef, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import HeadDashBoard from '../DashBoard/HeadDashBoard';
import VerifiyToken from './VerifiyToken';
import routes from '../Config/Routes';
import credentials from '../Config/Credentials';
import ChatView from '../Chat-View/ChatView';

function SignUp() {
    const navigateToChatView = useNavigate();

    const [token, setToken] = useState({});
    const [tempUserData, setTempUserData] = useState({});
    const [tokenFetched, setTokenFetched] = useState(false);
    const [signUpSuccess, setSignUpSuccess] = useState(false);
    const [tokenVerified, setTokenVerified] = useState(false);

    const userName = useRef();
    const email = useRef();
    const password = useRef();

    function handleUserInput(e) {
        const tempUserName = userName.current.value;
        const tempEmail = email.current.value;
        const tempPassword = password.current.value;

        const postUser = {
            userName: tempUserName,
            email: tempEmail,
            password: tempPassword
        };

        setTempUserData(postUser);
        //add code to check validness of input data
        //then call save user function
        verifyUserDetail(postUser);
    }

    function verifyUserDetail(userData) {
        fetch(routes.SIGNUP, {
            method: 'POST',
            //mode: 'cors',
            //cache: 'no-cache',
            //credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            //redirect: 'follow',
            //referrerPolicy: 'no-referrer',
            body: JSON.stringify(userData)
        })
            .then((result) => result.json())
            .then((result) => {
                if (result.status === 200) {
                    console.log('User saved Successfully');
                }
            })
            .finally(() => {
                sessionStorage.setItem(
                    credentials.UNIQUE_ID,
                    email.current.value
                );
                userName.current.value = null;
                email.current.value = null;
                password.current.value = null;
                setTokenFetched(true);
            });
    }

    function tokenAuth(userInput) {
        console.log(userInput);

        setTokenFetched(true);
        setSignUpSuccess(true);

        fetch(`${routes.VERIFICATION}${userInput}`, {
            method: 'GET',
            //mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((response) => response.json())
            .then((result) => {
                console.log('result======>', result.message);
                if (result.message === 'OK::OK') {
                    setTempUserData({});
                    setTokenVerified(true);
                    setTokenFetched(false);
                    setSignUpSuccess(true);
                    console.log('User Token Verification Successful');
                    setTimeout(() => {
                        navigateToChatView(routes.LOGIN_VIEW, {
                            replace: true
                        });
                    }, 2000);
                } else {
                    //handle exception case
                    //todo
                    console.log('Token Not Same');
                }
            });
    }

    function testCompnentSwitch() {
        return <ChatView />;
    }

    return (
        <div>
            {signUpSuccess ? (
                <div>
                    Sign up successful, routing you to ChatView
                    {/* <Link to={routes.HOME_PAGE_ROUTE}>Home-page</Link> */}
                </div>
            ) : null}

            {tokenFetched === true ? (
                <VerifiyToken tokenAuth={tokenAuth} />
            ) : null}

            {tokenFetched === false && signUpSuccess === false ? (
                <div>
                    <HeadDashBoard message={'Sign-Up'} />

                    <br />
                    <br />
                    <br />
                    <br />
                    <br />

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
                                <label for="firstname">User Name</label>
                                <input
                                    ref={userName}
                                    type="text"
                                    id="firstname"
                                    className="form-control"
                                    aria-describedby="emailHelp"
                                />
                            </div>

                            <div class="form-group">
                                <label for="password">password</label>
                                <input
                                    ref={password}
                                    type="text"
                                    id="password"
                                    aria-describedby="emailHelp"
                                    class="form-control"
                                />
                            </div>

                            <div class="form-group">
                                <label for="email">E-mail</label>

                                <input
                                    ref={email}
                                    type="text"
                                    aria-describedby="emailHelp"
                                    className="form-control"
                                    id="email"
                                />

                                <small class="form-text text-muted">
                                    We'll never share your email with anyone
                                    else.
                                </small>
                            </div>

                            <input
                                type="submit"
                                onClick={handleUserInput}
                                value="register"
                                className="btn btn-dark"
                                style={{ marginLeft: '5.5cm', width: '3cm' }}
                            />
                        </div>
                    </div>
                </div>
            ) : null}
        </div>
    );
}

export default SignUp;
