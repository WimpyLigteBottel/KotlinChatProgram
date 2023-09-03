import "./LoginPage.css";
import { useState } from "react";
import { login } from "./LoginPageJS";

function LoginPage(data) {
  const [username, setUsername] = useState("");
  const [userId, setUserId] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <div className="login-area">
      <div className="smaller-text">ID: {userId}</div> <br />
      <br />
      <input
        id="username"
        className="css-input-username rounded font-bold pl-2"
        defaultValue={username}
        type="text"
        onChange={async (event) => {
          setUsername(event.target.value);
          setIsLoggedIn(false);
        }}
      />
      <br />
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
      <img
        className={isLoggedIn ? "" : "hidden"}
        src={isLoggedIn ? "checkmark.jpg" : ""}
        width="22"
        height="22"
      />
    </div>
  );
}

export default LoginPage;
