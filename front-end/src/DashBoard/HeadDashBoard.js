import React from 'react';
import { Link } from 'react-router-dom';
import routes from '../Config/Routes';

function HeadDashBoard({ message }) {
    function handleMultipleClicks(e) {
        // let clicks = sessionStorage.getItem(credentials.USER_REQ_CLICKS)
        // if (clicks === null) {
        //     sessionStorage.setItem(credentials.USER_REQ_CLICKS, 1)
        // } else if (clicks === 3) {
        //     setTimeout(() => {
        //         sessionStorage.setItem(credentials.USER_REQ_CLICKS, 0)
        //     }, [5000])
        // } else {
        //     sessionStorage.setItem(credentials.USER_REQ_CLICKS, clicks++)
        // }
    }

    return (
        <div>
            <div
                style={{
                    backgroundColor: 'cornflowerblue',
                    height: '2cm',
                    textAlign: 'center'
                }}
            >
                <div style={{ padding: '.5cm' }}>
                    <ul className="nav">
                        <li className="nav-item">
                            {/* <Link
                                className="nav-link"
                                style={{ color: 'aliceblue' }}
                                to={routes.SIGN_UP_ROUTE}
                            >
                                Sign-up
                            </Link> */}
                        </li>
                        <li className="nav-item">
                            {/* <Link
                                className="nav-link"
                                href="/login"
                                style={{ color: 'aliceblue' }}
                                onClick={handleMultipleClicks}
                                to={routes.LOGIN_IN_ROUTE}
                            >
                                Login
                            </Link> */}
                        </li>
                        <li className="nav-item">
                            {/* <Link
                                className="nav-link"
                                href="/APISource"
                                style={{ color: 'aliceblue' }}
                                to={routes.HOME_PAGE_ROUTE}
                            >
                                Home Page
                            </Link> */}
                        </li>

                        <li>
                            <p
                                style={{
                                    paddingLeft: '15cm',
                                    paddingBottom: '1cm'
                                }}
                            >
                                Covid Tracker Notification Services {message}
                            </p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default HeadDashBoard;
