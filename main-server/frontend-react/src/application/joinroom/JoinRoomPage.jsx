import "./JoinRoomPage.css";
import Select from "react-select";
import { useEffect, useState } from "react";

import {
  getAllJoinedRooms,
  getRoomDetails,
  getRoomMessages,
} from "./JoinRoomPageJS";

function JoinRoomPage(data) {
  const [roomOptions, setRoomOptions] = useState([]);
  const [currentRoom, setCurrentRoom] = useState({
    id: "",
    value: "",
  });

  useEffect(() => {
    if (data.user == null || data.user.id === "") {
      return;
    }

    getAllJoinedRooms(data.user.id).then((result) => {
      if (result != []) {
        setRoomOptions(result);
      }
    });
  }, [currentRoom, data.user]);

  return (
    <div>
      <Select
        options={roomOptions}
        isSearchable={false}
        defaultValue={roomOptions[0]}
        onChange={(event) => {
          setCurrentRoom({
            id: event.roomId,
            name: event.value,
          });
        }}
      />

      <button
        className="coolButton"
        onClick={async () => {
          data.callbackSetParentRoom(await getRoomDetails(currentRoom.id));
        }}
      >
        Join
      </button>
    </div>
  );
}

export default JoinRoomPage;
