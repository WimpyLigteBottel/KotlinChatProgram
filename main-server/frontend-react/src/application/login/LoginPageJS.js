import { getRooms } from "../core/RoomService";
import { getUsers, getUserById, sendMessage } from "../core/UserService";

/*
This will try to login in to the user

*/
export async function login(username) {
  let data = await getUsers();

  let userFound = data.map((x) => x.name).includes(username);

  if (!userFound) {
    return {id: ""};
  }

  //Find the first user
  return data.filter((x) => x.name == username)[0];
}
