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

const sendMessage = () => {
    stompClient.send('/app/chat/simple-text',{},JSON.stringify({"id":null,"chatId":null,"senderId":"aaryan_11_","recipientId":"sanket_12","senderName":"aaryan","recipientName":"sanket","content":null}));
};

const sentTestMessage = () =>{
    stompClient.send("/app/test",{},JSON.stringify({"id":12,"chatId":"335","senderId":"aaryan_11_","recipientId":"sanket_12","senderName":"aaryan","recipientName":"sanket","blob":null}));
}

const onConnected = () => {
    console.log("connected");
    
    stompClient.subscribe(
         '/topic/simpleText',
        onMessageReceived
      );

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
    sendMessage();
},3000);

