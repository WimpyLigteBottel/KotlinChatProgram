import "./JoinRoomPage.css";
import Select from "react-select";
import { useEffect, useState } from "react";

import { getAllJoinedRooms, getRoomDetails } from "./JoinRoomPageJS";

function JoinRoomPage(data) {
  const [roomOptions, setRoomOptions] = useState([]);
  const [currentRoom, setCurrentRoom] = useState({
    id: "",
    value: "",
  });

  async function updateRoomOptions(data) {
    if (data.user == null || data.user.id === "") {
      return;
    }
    let rooms = await getAllJoinedRooms(data.user.id);
    setRoomOptions(rooms);
  }

  useEffect(() => {
    updateRoomOptions(data);
  }, [currentRoom, data.user]);

  return (
    <div className="join-room-div">
      <Select
        options={roomOptions}
        isSearchable={false}
        defaultValue={roomOptions[0]}
        onChange={async (event) => {
          let details = await getRoomDetails(event.roomId);
          setCurrentRoom(details);
          data.callbackSetParentRoom(details);
        }}
      />
    </div>
  );
}

export default JoinRoomPage;
