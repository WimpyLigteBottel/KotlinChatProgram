import "./Messages.css";
import {useEffect, useState} from "react";

import {getRoomMessages, sendUserMessage} from "./MessagesJs";
import {useInterval} from "../../core/UtilsJs";


function MessagesPage({user, currentRoom}) {
    const [receivedMessages, setReceivedMessages] = useState("");
    const [message, setMessage] = useState("");


    //2 (techinally on start this goes off)
    useInterval(async () => {
        refresh()
        // put your interval code here.
    }, 5000);

    // 1
    useEffect(() => {
        refresh();
    }, [currentRoom]);

    // Whenever refresh happens then scroll to the bottom, only works after render update
    useEffect(() => {
        scrollToBottomOfTextArea();
    }, [receivedMessages]);

    return (
        <div>
            <textarea id="textarea-input" className="css-input-area" defaultValue={receivedMessages}/>
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

    function scrollToBottomOfTextArea() {
        let textarea = document.getElementById('textarea-input');
        textarea.scrollTop = textarea.scrollHeight;
    }
}

export default MessagesPage;
