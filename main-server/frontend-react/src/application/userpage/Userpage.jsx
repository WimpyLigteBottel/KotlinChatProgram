//functions
import {
  login,
  getAllJoinedRooms,
  getRoomMessages,
  sendUserMessage,
} from "./UsepageJs";
import { useEffect, useState } from "react";

//Pages
import LoginPage from "../login/LoginPage";
import JoinRoomPage from "../joinroom/JoinRoomPage";
import MessagesPage from "../messages/MessagesPage";

//CSS
import "./Userpage.css";

function Userpage(data) {
  const [user, setUser] = useState({
    id: "",
    name: "",
  });

  const [currentRoom, setCurrentRoom] = useState({
    id: "",
    name: "",
    owner: {
      id: "",
      name: "",
    },
    users: [
      {
        id: "",
        name: "",
      },
    ],
    messages: [],
  });

  const [sendMessage, setSendMessage] = useState("");
  const [receivedMessages, setReceivedMessages] = useState([
    { username: "", message: "" },
  ]);

  useEffect(() => {
    console.log(`User has been set`, user);
    console.log(`CurrentRoom has been set`, currentRoom);

    if (currentRoom.id != "")
      setReceivedMessages(currentRoom.messages[0].message);
  }, [user, currentRoom]);

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

      <MessagesPage currentRoom={currentRoom} />

      <br />
      <br />

      <input
        id="messageSendBox"
        className="css-input"
        value={sendMessage}
        type="text"
        onChange={(event) => {
          setSendMessage(event.target.value.replace(",", ""));
        }}
      />
      <br />
      <button
        className="coolButton"
        onClick={async () => {
          await sendUserMessage(user.name, currentRoom, sendMessage);
          setReceivedMessages(await getRoomMessages(currentRoom));
        }}
      >
        Send
      </button>
    </div>
  );
}

export default Userpage;
