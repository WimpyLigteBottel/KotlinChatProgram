import "./Messages.css";
import { useEffect, useState } from "react";

import { getRoomMessages, sendUserMessage } from "./MessagesJs";

function MessagesPage(data) {
  const [receivedMessages, setReceivedMessages] = useState("");
  const [message, setMessage] = useState("");

  async function refreshMessages(data) {
    let roomMessages = await getRoomMessages(data.currentRoom.id);
    setReceivedMessages(roomMessages);
  }

  useEffect(() => {
    if (data.currentRoom == null || data.currentRoom.id === "") {
      console.log("room is empty", data.currentRoom);
      return;
    }
    refreshMessages(data);
  }, [data.currentRoom, message, receivedMessages]);

  return (
    <div>
      <textarea className="css-input-area" defaultValue={receivedMessages} />
      <button className="coolButton" onClick={async () => {}}>
        Refresh
      </button>

      <br />
      <br />

      <input
        className="css-input"
        defaultValue={message}
        type="text"
        onChange={(event) => {
          setMessage(event.target.value);
        }}
      />
      <br />
      <button
        className="coolButton"
        onClick={async () => {
          await sendUserMessage(data.user.id, data.currentRoom.id, message);
          setMessage("");
          refreshMessages(data);
        }}
      >
        Send
      </button>
    </div>
  );
}

export default MessagesPage;
