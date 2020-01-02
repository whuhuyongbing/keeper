import React from "react";
import {useHistory} from "react-router-dom";

function User(props) {
    let history = useHistory();

    function handleClick() {
        console.log("click");
        history.push("/login");

    }

 return <button onClick={handleClick} className="welcome">{props.login?  "welcome " : "login" }</button>
};

export default User;