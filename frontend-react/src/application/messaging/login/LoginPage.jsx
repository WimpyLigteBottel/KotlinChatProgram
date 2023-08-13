import "./LoginPage.css";
import { useState } from "react";
import { login } from "./LoginPageJS";

function LoginPage(data) {
  const [username, setUsername] = useState("");
  const [userId, setUserId] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <div>
      <span>{userId} </span>
      <input
        id="username"
        className="css-input-username"
        defaultValue={username}
        type="text"
        onChange={async (event) => {
          setUsername(event.target.value);
          setIsLoggedIn(false);
        }}
      />
      <button
        className="coolButton"
        onClick={async () => {
          let user = await login(username);
          if (user.id !== "") {
            setIsLoggedIn(true);
            setUserId(user.id);
          }

          data.callbackSetParentUser(user);
        }}
      >
        Login
      </button>

      <img src={isLoggedIn ? "checkmark.jpg" : ""} width="22" />
    </div>
  );
}

export default LoginPage;
