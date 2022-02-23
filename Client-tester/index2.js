let stompClient =null;

const connect = () =>{
    console.log('connecting');
    const Stomp = require("stompjs");
    var SockJS = require("sockjs-client");
    SockJS = new SockJS("http://localhost:8003/register-socket");
    stompClient = Stomp.over(SockJS);
    
    
    stompClient.connect({}, onConnected, onError);

};

const sendChatRegistry = () => {
    stompClient.send('/app/chat/registry',{}, JSON.stringify({
        userName : 'ss9', 
        email : 'ss9@gmail.com', 
        clientIp:'IP',
        city:'Pune',
        continent:'ASIA',
        country:'INDIA',
        latitude:'LAT',
        longitude:'LONG',
        pincode:'411060',
        state:'MAHARASHTRA',
        spring_boot_app_Ip:'localhost'}));
}


const sendMessage = () => {
    stompClient.send('/app/chat/simple-text',{},JSON.stringify({id:null,chatId:null,senderId: 'aaryan_11_',recipientId:'sanket_12',senderName:'aaryan',recipientName:'sanket',content:null,uniConnectionId : "aaryan@112@gmail.com"}));
};

const sentTestMessage = () =>{
    stompClient.send("/app/test",{},JSON.stringify({"id":12,"chatId":"335","senderId":"aaryan_11_","recipientId":"sanket_12","senderName":"aaryan","recipientName":"sanket","blob":null}));
}

const onConnected = () => {
    console.log("connected");
    
    stompClient.subscribe(
         '/queue/'+'ss9@gmail.comss9_textMessage',
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
    sendChatRegistry();
},3000);

