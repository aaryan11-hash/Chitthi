import React, { useState, useEffect, useRef } from 'react';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import JsonPTransport from 'sockjs-client/lib/transport/jsonp-polling';
import credentials from '../Config/Credentials';

let stompClient = null;
const apiKey = 'b34ccfe2df0b178dc3aae99716ccb395b7f9def3e6f6757e97a7d711';
export default function ChatView() {
    const [userDetails, setUserDetails] = useState(
        JSON.parse(sessionStorage.getItem(credentials.USER_CREDENTIALS))
    );
    const [userIdentifier, setUserIdentifier] = useState(
        JSON.parse(sessionStorage.getItem(credentials.USER_CREDENTIALS)).email +
            JSON.parse(sessionStorage.getItem(credentials.USER_CREDENTIALS))
                .userName
    );

    useEffect(() => {
        console.log('USER IDENTIFIER ==========>' + userIdentifier);
        fetchHostMachineData();
        connect();
    }, []);

    async function fetchHostMachineData() {
        fetch(`https://api.ipdata.co?api-key=${apiKey}`, {
            method: 'GET'
        })
            .then((data) => data.json())
            .then((res) => {
                console.log('RESPONSE OF IPDATA =====>', res);
                setUserDetails({
                    ...userDetails,
                    clientIp: res.ip,
                    city: res.city,
                    continent: res.continent_name,
                    country: res.country_name,
                    latitude: res.latitude,
                    longitude: res.longitude,
                    pincode: res.postal,
                    state: res.region
                });
            });
    }

    async function connect() {
        console.log('connecting');
        const sockJS = new SockJS('http://localhost:8002/register-socket');
        stompClient = Stomp.over(sockJS);

        stompClient.connect({}, onConnected, onError);
    }

    async function onConnected() {
        console.log('connected');

        stompClient.subscribe(
            `/queue/${userIdentifier}_instanceMessage`,
            onInstanceDataRecevived
        );
        stompClient.subscribe(
            `/queue/${userIdentifier}_textMessage`,
            onTextMessageReceived
        );
        stompClient.subscribe(
            `/queue/${userIdentifier}_blobMessage`,
            onBlobMessageReceived
        );
    }

    async function reqInstanceMessage() {
        stompClient.send(
            '/app/chat/registry',
            {},
            JSON.stringify({
                userName: userDetails.userName,
                email: userDetails.email,
                clientIp: userDetails.clientIp,
                city: userDetails.city,
                continent: userDetails.continent,
                country: userDetails.country,
                latitude: userDetails.latitude,
                longitude: userDetails.longitude,
                state: userDetails.state,
                pincode: userDetails.pincode
            })
        );
    }

    async function sendTextMessage() {
        stompClient.send(
            '/app/chat/simple-text',
            {},
            JSON.stringify({
                id: null,
                chatId: null,
                senderId: 'aaryan_11_',
                recipientId: 'sanket_12',
                senderName: 'aaryan',
                recipientName: 'sanket',
                content: null,
                uniConnectionId: userIdentifier
            })
        );
    }

    async function sentBlobMessage() {
        stompClient.send(
            '/app/test',
            {},
            JSON.stringify({
                id: 12,
                chatId: '335',
                senderId: 'aaryan_11_',
                recipientId: 'sanket_12',
                senderName: 'aaryan',
                recipientName: 'sanket',
                blob: null
            })
        );
    }

    async function onError(err) {
        console.log(err);
    }

    async function onInstanceDataRecevived() {
        //todo
        //this functionality is to allow scalability inside the backend infrastructure
        //tries to solve the generic problem of isolation of connections caused by websockets
    }

    async function onBlobMessageReceived(blob) {
        //todo
        //to work out a way to proccess this blob and then show it to the receiver.
    }

    async function onTextMessageReceived(msg) {
        //Convert the message to a JSON, and then refresh only the chat window component to
        //show the new message

        console.log('Received a new message from ', JSON.parse(msg.body));
    }

    return (
        <div>
            ChatView
            <button onClick={reqInstanceMessage}> send</button>
        </div>
    );
}
