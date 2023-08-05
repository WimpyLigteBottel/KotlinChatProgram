import "./Userpage.css";
import { useEffect, useState } from "react";
import Select from "react-select";
import { getUsers } from "../core/UserService";
import { getRooms } from "../core/RoomService";
import {
  login,
  getAllJoinedRooms,
  getRoomMessages,
  sendUserMessage,
} from "./UsepageJs";

function Userpage(data) {
  const [sendMessage, setSendMessage] = useState("");
  const [username, setUsername] = useState("");
  const [currentRoom, setCurrentRoom] = useState("");
  const [roomOptions, setRoomOptions] = useState([]);
  const [receivedMessages, setReceivedMessages] = useState([
    { username: "", message: "" },
  ]);

  useEffect(() => {
    console.log(receivedMessages);
  }, [username, sendMessage, receivedMessages, roomOptions]);

  return (
    <div>
      <input
        id="username"
        className="css-input-username"
        defaultValue={username}
        type="text"
        onChange={async (event) => {
          setUsername(event.target.value);
          let isLoggedIn = await login(event.target.value);

          if (isLoggedIn) {
            getAllJoinedRooms(event.target.value).then((roomName) => {
              setRoomOptions(roomName);
            });
          }
        }}
      />
      <button className="coolButton" onClick={() => {}}>
        Login
      </button>
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
          await sendUserMessage(username, currentRoom, sendMessage);
          setReceivedMessages(await getRoomMessages(currentRoom));
        }}
      >
        Send
      </button>
    </div>
  );
}

export default Userpage;
