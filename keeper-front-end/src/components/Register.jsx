import React, {useState} from "react";
import Header from "./Header";
import {useHistory} from "react-router-dom";
import axios from "axios";
import cookies from "js-cookie";
function Register() {

    let history = useHistory();
    if (cookies.get("access_token") != null ) {
        history.push("/");
    }
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    function handleUserName(event) {
        const newUsername = event.target.value;
        setUsername(newUsername);
    }
    function handleEmail(event) {
        const newEmail = event.target.value;
        setEmail(newEmail);
    }    
    function handlePassword(event) {
        const newPassword = event.target.value;
        setPassword(newPassword);
    }

    //go to the login page
    function handleClick() {
        history.push("/login");
    }

    function handleSubmit(event) {
        event.preventDefault();
        axios.post("http://10.200.24.235:3000/register", {username: username, email: email, password: password})
        .then((res) => {
            if (res.data.errorCode === 0) {
                axios.post("http://10.200.24.235:3000/login", {userEmail: email, password: password})
                .then((res2) => {
                    if (res2.data.error === 0) {
                        cookies.set("access_token", res2.data.access_token);
                        cookies.set("fresh_token", res2.data.fresh_token);
                        cookies.set("useremail", email);
                        cookies.set("password", password);
                        history.push("/");  
                    } else if (res.data.error === 1){
                        //hanlde password incorrect
                    } else {
                        //email not registered
                    } 
                });
            } else if (res.data.errorCode === 3) {
                //email already registered
            }
        });
    }
    return (
    <div>
        <Header />
        <div>
            <form className="login">
            <input type="text" placeholder="username" onChange={handleUserName}/>
            <input type="email" placeholder="Email" onChange={handleEmail}/>
            <input type="password" placeholder="password"onChange={handlePassword}/>
            <button type="submit" onClick={handleSubmit}>Register</button>
            <button onClick={handleClick} className="no-account">has accout? go to login</button>
            </form>
        </div>
    </div>)
};
export default Register;