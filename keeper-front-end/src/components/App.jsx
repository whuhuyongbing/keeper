/* eslint-disable no-unused-expressions */
import React from "react";
import Main from "./Main";
import Login from "./Login";
import {
    BrowserRouter as Router,
    Switch,
    Route,
  } from "react-router-dom";
import Register from "./Register";
function App() {
    return (
    <Router>
        <Switch>
        <Route  path="/login">
            <Login />
        </Route>
        <Route path="/register">
            <Register />
            </Route>
        <Route path="/">
        <Main />
        </Route>
        </Switch>
    </Router>
    );
};

export default App;
