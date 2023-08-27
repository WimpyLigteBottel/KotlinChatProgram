import { getRooms, getRoomById } from "../../core/RoomService";

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
