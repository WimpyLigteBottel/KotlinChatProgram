import { getRooms, getRoomById } from "../core/RoomService";
import {} from "../core/UserService";

/*
This should get all the rooms that the user has joined
*/

export async function getAllJoinedRooms(userId) {
  let data = await getRooms();

  let filterData = data.filter((x) =>
    x.users.map((x) => x.id).includes(userId)
  );

  return filterData.map((x) => {
    return {
      roomId: x.id,
      value: x.name,
      label: x.name,
    };
  });
}

/*
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
export async function getRoomDetails(roomId) {
  return await getRoomById(roomId);
}
