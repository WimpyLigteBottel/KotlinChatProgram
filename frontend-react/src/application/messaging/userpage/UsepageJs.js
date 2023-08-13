import { getRooms } from "../../core/RoomService";
import { getUsers, sendMessage } from "../../core/UserService";

export async function login(username) {
  let data = await getUsers();
  return data.map((x) => x.name).includes(username);
}

/*
{id: '83f2776a-1dd3-4e91-8e95-1554f8794d2d', name: 'The awesome room name!', owner: {…}, users: Array(1), messages: Array(0)
*/

export async function getAllJoinedRooms(username) {
  let data = await getRooms();

  let filterData = data.filter((x) =>
    x.users.map((x) => x.name).includes(username)
  );

  return filterData.map((x) => {
    return {
      value: x.name,
      label: x.name,
    };
  });
}

export async function getRoomMessages(roomName) {
  let data = await getRooms();

  let filterData = data.filter((x) => x.name == roomName)[0];

  let messages = filterData.messages.map(
    (message) => `${message.user.name}: ${message.message}`
  );

  return messages.join("\n");
}

export async function sendUserMessage(username, roomName, message) {
  let users = await getUsers();
  let userId = users
    .filter((x) => x.name == username)
    .filter((x) => x.id)[0].id;

  let rooms = await getRooms();

  let roomId = rooms.filter((x) => x.name == roomName)[0].id;

  sendMessage(userId, roomId, message);
}
