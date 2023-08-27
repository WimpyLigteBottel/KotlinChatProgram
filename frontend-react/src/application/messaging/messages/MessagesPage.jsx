import "./Messages.css";
import {useEffect, useState} from "react";


function MessagesPage({roomMessages, refreshParent, sendMessage}) {
    const [messageInput, setMessageInput] = useState("");

    useEffect(() => {
        let textarea = document.getElementById('textarea-input');
        textarea.scrollTop = textarea.scrollHeight;
    }, [roomMessages]);

    return (
        <div>
            <textarea id="textarea-input" className="css-input-area"
                      value={roomMessages} readOnly={true}
                      onChange={refreshParent()}/>
            <br/>
            <input
                className="css-input"
                value={messageInput}
                type="text"
                onChange={(event) => {
                    setMessageInput(event.target.value);
                }}
            />
            <br/>
            <button className="coolButton"
                    onClick={() => {
                        sendMessage(messageInput);
                        setMessageInput("")
                    }}
            >Send
            </button>
        </div>
    );
}

export default MessagesPage;
