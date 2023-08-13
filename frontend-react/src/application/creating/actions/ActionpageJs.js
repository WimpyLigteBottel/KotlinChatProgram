import {createRoom, getRooms, joinRoom} from "../../core/RoomService";
import {createUser, getUsers} from "../../core/UserService";

export async function createSingleUser(name) {
  let userId = await createUser(name);
  console.log(`User has been created ${userId}`);

  return userId;
}

export async function createSingleRoom(userId, name) {
  let roomId = await createRoom(name, userId);
  console.log(`Room has been created ${roomId}: ${name}`);

  return roomId;
}

export async function joinSpecificRoom(userId, roomId) {
  await joinRoom(userId, roomId);
  console.log(`Room has been joined ${roomId} by ${userId}`);

  return roomId;
}



export async function getRoomSelection() {
  let data = await getRooms();

  return data.map((x) => {
    return {
      id: x.id,
      value: x.name,
      label: x.name,
    };
  });
}


export async function getUserSelection() {
  let data = await getUsers();

  return data.map((x) => {
    return {
      id: x.id,
      value: x.name,
      label: x.name,
    };
  });
}