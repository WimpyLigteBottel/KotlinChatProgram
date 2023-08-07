import { sendMessage } from "../core/UserService";
import { getRoomById } from "../core/RoomService";
/*
This should get all the rooms that the user has joined

{
    "id": "70980fac-84d0-4fe4-96f5-85be16849642",
    "name": "The awesome room name!",
    "owner": {
        "id": "2fefc072-6be4-4149-91ff-45f1319752b0",
        "name": "bob"
    },
    "users": [
        {
            "id": "2fefc072-6be4-4149-91ff-45f1319752b0",
            "name": "bob"
        }
    ],
    "messages": []
}


*/
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
