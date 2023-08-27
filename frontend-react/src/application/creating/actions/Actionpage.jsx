//functions

import {useEffect, useState} from "react";
import {
    createSingleRoom,
    createSingleUser,
    getRoomSelection,
    getUserSelection,
    joinSpecificRoom,
} from "./ActionpageJs";
//Pages
import Select from "react-select";
//CSS
import "./Actionpage.css";

function Actionpage(data) {
    const [roomName, setRoomName] = useState("");
    const [roomSelection, setRoomSelection] = useState([]);
    const [userSelection, setUserSelection] = useState([]);
    const [roomId, setRoomId] = useState("");
    const [userId, setUserId] = useState("");
    const [user, setUser] = useState({id: "", username: ""});

    useEffect(() => {
        getRoomSelection().then(data => {
            setRoomSelection(data)
        })
        getUserSelection().then(data => {
            setUserSelection(data)
        })
    }, [userId,roomId])

    useEffect(() => {
        console.log(user)
    }, [user])


    return (
        <div className="side-by-side-action-page">
            {createUser()}
            <br/>
            <br/>


            <div>
                User selected:
                <Select
                    options={userSelection}
                    isSearchable={false}
                    defaultValue={userSelection[0]}
                    onChange={async (event) => {
                        setUserId(event.id)
                    }}
                />
            </div>
            <br/>
            <br/>
            {room()}
            <br/>
            <br/>
            {joinRoom()}
        </div>
    );

    function createUser() {
        return (
            <div className="side-by-side">
                <input
                    id="username"
                    className="action-page-input"
                    defaultValue={user.username}
                    placeholder="insert user name"
                    type="text"
                    onChange={async (event) => {
                        setUser({...user, username: event.target.value})
                    }}
                />
                <button
                    className="coolButton"
                    onClick={async () => {
                        let userId = await createSingleUser(user.username);
                        setUser({...user, id: userId})
                    }}
                >
                    Create
                </button>
            </div>
        );
    }

    function room() {
        return (
            <div className="side-by-side">
                <input
                    id="username"
                    className="action-page-input"
                    placeholder="insert room name"
                    defaultValue={roomName}
                    type="text"
                    onChange={async (event) => {
                        setRoomName(event.target.value);
                    }}
                />
                <button
                    className="coolButton"
                    onClick={async () => {
                        setRoomId(await createSingleRoom(userId, roomName));
                    }}
                >
                    Create
                </button>
            </div>
        );
    }

    function joinRoom() {
        return (
            <div className="side-by-side">
                Selected join room:
                <Select
                    options={roomSelection}
                    isSearchable={false}
                    defaultValue={roomSelection[0]}
                    onChange={async (event) => {
                        setRoomId(event.id)
                    }}
                />
                <button
                    className="coolButton"
                    onClick={async () => {
                        await joinSpecificRoom(userId, roomId);
                    }}
                >
                    Join!
                </button>
            </div>
        );
    }
}

export default Actionpage;
