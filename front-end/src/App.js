import './App.css';
import SignUp from './Auth/SignUp';
import ChatView from './Chat-View/ChatView';
import routes from '../src/Config/Routes';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './Auth/Login';

function App() {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path={routes.CHAT_VIEW} element={<ChatView />} />
                    <Route path={routes.SIGN_UP_VIEW} element={<SignUp />} />
                    <Route path={routes.LOGIN_VIEW} element={<Login />} />
                </Routes>
            </Router>
        </div>
    );
}

export default App;
