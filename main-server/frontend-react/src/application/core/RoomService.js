import axios from "axios";

const baseUrl = `http://localhost:9090/v1`;

/*
Below is the example object of user
{
    "id": 2,
    "name": "abc"
}
*/

export async function getRooms() {
  let data = axios
    .get(`${baseUrl}/rooms`)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error(error);
    });

  return data;
}

/**
 * Example response
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
export async function getRoomById(roomId) {
  let data = axios
    .get(`${baseUrl}/rooms/${roomId}`) // TODO: encode the id incase theirs weird characters
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error(error);
    });

  return data;
}
