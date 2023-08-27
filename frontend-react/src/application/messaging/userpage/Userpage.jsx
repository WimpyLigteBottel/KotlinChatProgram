//functions
import {useEffect, useState} from "react";

//Pages
import LoginPage from "../login/LoginPage";
import JoinRoomPage from "../joinroom/JoinRoomPage";
import MessagesPage from "../messages/MessagesPage";

//CSS
import "./Userpage.css";
import {useInterval} from "../../core/UtilsJs";
import {getRoomMessages} from "./UserpageJs";
import {sendMessage} from "../../core/UserService";

function Userpage(data) {
    const [user, setUser] = useState(null);
    const [currentRoom, setCurrentRoom] = useState(null);
    const [roomMessages, setRoomMessages] = useState("");

    async function refreshMessages() {
        if (currentRoom == null || currentRoom.id === "") {
            return;
        }
        let messages = await getRoomMessages(currentRoom.id);
        setRoomMessages(messages)
    }

    useInterval(async () => {
        await refreshMessages();
    }, 5000);

    useEffect(() => {
        refreshMessages();
    }, [currentRoom]);

    return (
        <div>
            <LoginPage
                callbackSetParentUser={setUser}
            />
            <br/>
            <br/>
            <JoinRoomPage
                user={user}
                callbackSetParentRoom={setCurrentRoom}
            />
            <br/>
            <br/>
            <MessagesPage
                          roomMessages={roomMessages}
                          refreshParent={()=>{
                              refreshMessages();
                          }}
                          sendMessage={(message)=>{
                              sendMessage(user.id, currentRoom.id, message)
                          }}
            />
        </div>
    );
}

export default Userpage;
