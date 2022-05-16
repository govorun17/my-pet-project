'use strict';


let messageForm = document.querySelector('#messageForm');
let messageInput = document.querySelector('#message');
let messageArea = document.querySelector('#messageArea');
let connectingElement = document.querySelector('#connecting');

let stompClient = null;
let username = null;

// Connect to WebSocket Server.
connect();

function connect() {
    username = document.querySelector('#username').innerText.trim();

    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/publicChatRoom', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({name: username, messageType: 'JOIN'})
    )

    connectingElement.style.display = "none";
    messageForm.style.display = "block";
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function onMessageReceived(payload) {
    console.log("onMessageReceived")
    let message = JSON.parse(payload.body);

    let messageElement = document.createElement('li');

    if(message.messageType === 'JOIN') {
        messageElement.classList.add('event-message');
        message.message = message.name + ' joined!';
    } else if (message.messageType === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.message = message.name + ' left!';
    } else {
        messageElement.classList.add('chat-message');
        let usernameElement = document.createElement('strong');
        usernameElement.classList.add('nickname');
        let usernameText = document.createTextNode(message.name);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    let textElement = document.createElement('span');
    let messageText = document.createTextNode(message.message);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


messageForm.addEventListener('submit', sendMessage, true);

function sendMessage(event) {
    let messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        let chatMessage = {
            name: username,
            message: messageInput.value,
            messageType: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}