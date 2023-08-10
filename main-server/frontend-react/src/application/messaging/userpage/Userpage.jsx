//functions
import { useEffect, useState } from "react";

//Pages
import LoginPage from "../login/LoginPage";
import JoinRoomPage from "../joinroom/JoinRoomPage";
import MessagesPage from "../messages/MessagesPage";

//CSS
import "./Userpage.css";

function Userpage(data) {
  const [user, setUser] = useState(null);
  const [currentRoom, setCurrentRoom] = useState(null);

  return (
    <div>
      <LoginPage
        callbackSetParentUser={(user) => {
          setUser(user);
        }}
      />

      <br />
      <br />
      <JoinRoomPage
        user={user}
        callbackSetParentRoom={(room) => {
          setCurrentRoom(room);
        }}
      />

      <br />
      <br />

      <MessagesPage user={user} currentRoom={currentRoom} />
    </div>
  );
}

export default Userpage;
