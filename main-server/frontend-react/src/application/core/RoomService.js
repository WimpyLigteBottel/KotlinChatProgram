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
