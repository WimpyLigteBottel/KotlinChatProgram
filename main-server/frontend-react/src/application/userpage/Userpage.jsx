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
import Select from "react-select";

//CSS
import "./Userpage.css";

function Userpage(data) {
  const [user, setUser] = useState({
    id: "",
    name: "",
  });

  const [sendMessage, setSendMessage] = useState("");
  const [currentRoom, setCurrentRoom] = useState("");
  const [roomOptions, setRoomOptions] = useState([]);
  const [receivedMessages, setReceivedMessages] = useState([
    { username: "", message: "" },
  ]);

  useEffect(() => {
                console.log(`User has been set`, user);

  }, [user]);

  return (
    <div>
      <LoginPage
        callbackSetParentUser ={(user) => {

          setUser(user);
        }}
      />

      <br />
      <br />

      <div>
        <Select
          options={roomOptions}
          isSearchable={false}
          defaultValue={roomOptions[0]}
          onChange={(event) => {
            setCurrentRoom(event.value);
          }}
        />

        <button
          className="coolButton"
          onClick={async () => {
            let messages = await getRoomMessages(currentRoom);
            setReceivedMessages(messages);
          }}
        >
          Join
        </button>
      </div>

      <br />
      <br />

      <textarea id="messagebox" defaultValue={receivedMessages} />

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
