import { createRoom, joinRoom } from "../../core/RoomService";
import { createUser } from "../../core/UserService";

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
