import React from "react";
import HighlightIcon from "@material-ui/icons/Highlight";
import User from "./User";
import cookies from "js-cookie";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useHistory
} from "react-router-dom";

function Header() {
  let history = useHistory();

  function handleClick() {
      if (cookies.get("access_token") != null) {
          history.push("/login");
      }
        
  }
  return (
    <header>
      <span>
        <HighlightIcon />
        Keeper
      </span>
      <User login={cookies.get("access_token") != null} onClick={handleClick}/>
    </header>
  );
}

export default Header;
