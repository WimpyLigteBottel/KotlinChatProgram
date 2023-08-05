import "./Messages.css";
import Select from "react-select";
import { useEffect, useState } from "react";

import {
  getAllJoinedRooms,
  getRoomDetails,
  getRoomMessages,
} from "./MessagesJs";

function MessagesPage(data) {

    /*
    [
        { username: "", message: "" },
      ]
    */

  const [receivedMessages, setReceivedMessages] = useState(data.currentRoom.messages);

  useEffect(() => {

  console.log(`This is receivedMessages `,data.currentRoom)

  }, [data.currentRoom]);

  return (
    <div>
      <textarea id="messagebox" defaultValue={receivedMessages} />
    </div>
  );
}

export default MessagesPage;
