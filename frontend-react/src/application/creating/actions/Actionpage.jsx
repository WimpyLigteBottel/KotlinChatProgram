//functions

import { useEffect, useState } from "react";
import {
  createSingleUser,
  createSingleRoom,
  joinSpecificRoom,
} from "./ActionpageJs";
//Pages

//CSS
import "./Actionpage.css";

function Actionpage(data) {
  const [username, setUsername] = useState("");
  const [userId, setUserId] = useState("");
  const [roomName, setRoomName] = useState("");
  const [roomId, setRoomId] = useState("");

  return (
    <div>
      {createUser()}

      <br />
      <br />
      {room()}
      <br />
      <br />
      {joinRoom()}
    </div>
  );

  function createUser() {
    return (
      <div>
        <input
          id="username"
          className="css-input-username"
          defaultValue={username}
          placeholder="insert user name"
          type="text"
          onChange={async (event) => {
            setUsername(event.target.value);
          }}
        />
        <button
          className="coolButton"
          onClick={async () => {
            setUserId(await createSingleUser(username));
          }}
        >
          Create
        </button>
      </div>
    );
  }

  function room() {
    return (
      <div>
        <input
          id="username"
          className="css-input-username"
          placeholder="insert room name"
          defaultValue={roomName}
          type="text"
          onChange={async (event) => {
            setRoomName(event.target.value);
          }}
        />
        <button
          className="coolButton"
          onClick={async () => {
            setRoomId(await createSingleRoom(userId, roomName));
          }}
        >
          Create
        </button>
      </div>
    );
  }

  function joinRoom() {
    return (
      <div>
        <input
          id="userId"
          className="css-input-username"
          placeholder="insert userId"
          defaultValue={userId}
          type="text"
          onChange={async (event) => {
            setUserId(event.target.value);
          }}
        />
        <input
          id="roomId"
          className="css-input-username"
          placeholder="insert roomId"
          defaultValue={roomId}
          type="text"
          onChange={async (event) => {
            setRoomId(event.target.value);
          }}
        />
        <button
          className="coolButton"
          onClick={async () => {
            await joinSpecificRoom(userId, roomId);
          }}
        >
          Join!
        </button>
      </div>
    );
  }
}

export default Actionpage;
