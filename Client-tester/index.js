

let stompClient =null;

const connect = () =>{
    console.log('connecting')
    const Stomp = require("stompjs");
    var SockJS = require("sockjs-client");
    SockJS = new SockJS("http://localhost:8001/register-socket");
    stompClient = Stomp.over(SockJS);
    
    
    stompClient.connect({}, onConnected, onError);

    setTimeout(()=>{
        
        sendMessage();
        
    },3000);
       
    
    
};

const sendMessage = () => {
    stompClient.send("/app/chat/blob",{},JSON.stringify({"id":12,"chatId":"335","senderId":"aaryan_11_","recipientId":"sanket_12","senderName":"aaryan","recipientName":"sanket","content":null}));
};

const onConnected = () => {
    console.log("connected");
    stompClient.subscribe(
         "/queue/messages",
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