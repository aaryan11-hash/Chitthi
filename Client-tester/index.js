// const PORT = 5000;

// const app = (require('express'))();

// app.use('/test',require('./middleController'));

// app.listen(PORT,()=>console.log('Server created'));

// //new (require('./eurekaRegistry'))().registerWithEureka('CLIENT-TESTER-SERVICE',PORT);

// const domain = require('./Schema');

// const data = domain.fetchData();

// console.log(data);


let stompClient =null;

const connect = () =>{
    console.log('connecting');
    const Stomp = require("stompjs");
    var SockJS = require("sockjs-client");
    SockJS = new SockJS("http://localhost:8002/register-socket");
    stompClient = Stomp.over(SockJS);
    
    
    stompClient.connect({}, onConnected, onError);

};

const sendChatRegistry = () => {
    stompClient.send('/app/chat/registry',{}, JSON.stringify({userName : 'ss8', email : 'ss8@gmail.com', clientIp:'IP',city:'Pune',continent:'ASIA',country:'INDIA',latitude:'LAT',longitude:'LONG',pincode:'411060',state:'MAHARASHTRA',spring_boot_app_IP:'localhost'}));
}

const sendMessage = () => {
    stompClient.send('/app/chat/simple-text',{},JSON.stringify({id:null,chatId:null,senderId: 'ss8@gmail.com',recipientId:'ss9@gmail.com',senderName:'ss8',recipientName:'ss9',content:'hello there!!!!',uniConnectionId : "ss9@gmail.comss9"}));
};

const sentTestMessage = () =>{
    stompClient.send("/app/test",{},JSON.stringify({"id":12,"chatId":"335","senderId":"aaryan_11_","recipientId":"sanket_12","senderName":"aaryan","recipientName":"sanket","blob":null}));
}

const onConnected = () => {
    console.log("connected");
    
    stompClient.subscribe(
         '/queue/'+'aaryan@112@gmail.com_chatMessage',
        onMessageReceived
      );

    sendChatRegistry();
    sendMessage();

};

const onError = (err) => {
    console.log(err);
  };

  const onMessageReceived = (msg) => {
   
      console.log("Received a new message from " + msg);
    
    
  };

connect();
setTimeout(()=>{
        
    //sentTestMessage();
    
},3000);

