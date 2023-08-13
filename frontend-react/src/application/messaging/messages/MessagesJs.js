import { sendMessage } from "../../core/UserService";
import { getRoomById } from "../../core/RoomService";



export async function getRoomMessages(roomId) {
  let room = await getRoomById(roomId);

  return formatMessages(room.messages);
}

export function formatMessages(messages) {
  return messages
    .map((message) => `${message.user.name}: ${message.message}`)
    .join("\n");
}

export async function sendUserMessage(userId, roomId, message) {
  sendMessage(userId, roomId, message);
}
