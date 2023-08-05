import "./LoginPage.css";
import { useEffect, useState } from "react";
import { login } from "./LoginPageJS";

function LoginPage(data) {
  const [username, setUsername] = useState("");
  const [user, setUser] = useState(null);

  useEffect(() => {
    data.callbackSetParentUser(user);
  }, [user]);

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
          setUser(await login(username));
        }}
      >
        Login
      </button>
    </div>
  );
}

export default LoginPage;
