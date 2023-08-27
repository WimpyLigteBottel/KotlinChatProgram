import {getRoomById} from "../../core/RoomService";

export async function getRoomMessages(roomId) {
  let room = await getRoomById(roomId);

  return room.messages
      .map((message) => `${message.user.name}: ${message.message}`)
      .join("\n");
}