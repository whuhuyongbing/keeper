import React, {useState} from "react";
import Header from "./Header";
import axios from "axios";
import cookies from "js-cookie";

import {useHistory} from "react-router-dom"


function Login() {
    let history = useHistory();
    if (cookies.get("access_token") != null) {
        history.push("/");
    }
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    function handleEmail(event) {
        const newEmail = event.target.value;
        setEmail(newEmail);
    }
    function handlePassword(event) {
        const newPassword = event.target.value;
        setPassword(newPassword);
    }
    function handleClick() {
        history.push("/register");
    }
    function handleLogin(event) {
        event.preventDefault();
        axios.post("http://10.200.24.235:3000/login", {userEmail: email, password: password})
        .then((res) => {
            if (res.data.error === 0) {
                cookies.set("access_token", res.data.access_token);
                cookies.set("fresh_token", res.data.fresh_token);
                cookies.set("useremail", email);
                cookies.set("password", password); 
                history.push("/login"); 
            } else if (res.data.error === 1){
                //hanlde password incorrect
            } else {
                //email not registered
            } 
        });
    }
    return (
    <div>
        <Header />
        <div>
            <form className="login">
            <input type="email" placeholder="Email" onChange={handleEmail} value={email}/>
            <input type="password" placeholder="password" onChange={handlePassword} value={password}/>
            <button type="submit" onClick={handleLogin}>login</button>
            <button onClick={handleClick} className="no-account">No accout? go to register</button>
            </form>

        </div>
    </div>);
};

export default Login;