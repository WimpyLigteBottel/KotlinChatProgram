import axios from "axios";

const baseUrl = `http://localhost:9090/v1`;

/*
Below is the example object of user
{
    "id": 2,
    "name": "abc"
}
*/

export async function createUser(username) {
  let data = axios
    .post(`${baseUrl}/users`, { name: username })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error(error);
    });

  return data;
}

export async function getUsers() {
  let data = axios
    .get(`${baseUrl}/users`)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error(error);
    });

  return data;
}

export async function getUserById(userId) {
  let data = axios
    .get(`${baseUrl}/users?id=${encodeURIComponent(userId)}`)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error(error);
    });

  return data;
}

export function sendMessage(userid, roomid, message) {
  let request = {
    roomId: `${roomid}`,
    userId: `${userid}`,
    message: `${message}`,
  };

  let data = axios
    .post(`${baseUrl}/message`, request)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error(error);
    });
  return data;
}
