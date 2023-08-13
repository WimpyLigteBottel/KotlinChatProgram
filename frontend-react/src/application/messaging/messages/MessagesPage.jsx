import "./Messages.css";
import {useEffect, useState} from "react";

import {getRoomMessages, sendUserMessage} from "./MessagesJs";

function MessagesPage({user, currentRoom}) {
    const [receivedMessages, setReceivedMessages] = useState("");
    const [message, setMessage] = useState("");

    useEffect(() => {
        refresh();
    }, [currentRoom]);

    return (
        <div>
            <textarea className="css-input-area" defaultValue={receivedMessages}/>
            <br/>

            <button
                className="coolButton"
                onClick={async () => {
                    await refresh();
                }}
            >
                Refresh
            </button>

            <br/>
            <br/>

            <input
                className="css-input"
                defaultValue={message}
                type="text"
                onChange={async (event) => {
                    setMessage(event.target.value);
                    await refresh();
                }}
            />
            <br/>
            <button
                className="coolButton"
                onClick={async () => {
                    await sendUserMessage(user.id, currentRoom.id, message);
                    await refresh();
                }}
            >
                Send
            </button>
        </div>
    );

    async function refresh() {
        if (currentRoom == null || currentRoom.id == "") {
            return;
        }

        let messages = await getRoomMessages(currentRoom.id);
        setReceivedMessages(messages);
    }
}

export default MessagesPage;
