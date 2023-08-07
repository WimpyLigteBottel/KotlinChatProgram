import "./LoginPage.css";
import { useState } from "react";
import { login } from "./LoginPageJS";

function LoginPage(data) {
  const [username, setUsername] = useState("");

  return (
    <div>
      <input
        id="username"
        className="css-input-username"
        defaultValue={username}
        type="text"
        onChange={async (event) => {
          setUsername(event.target.value);
        }}
      />
      <button
        className="coolButton"
        onClick={async () => {
          data.callbackSetParentUser(await login(username));
        }}
      >
        Login
      </button>
    </div>
  );
}

export default LoginPage;
