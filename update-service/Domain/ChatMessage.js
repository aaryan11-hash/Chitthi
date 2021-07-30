const mongoose = require('mongoose');


const chatMessage = mongoose.Schema({
    id : String,
    chatId : String,
    senderId : String,
    recipientId : String,
    senderName : String,
    recipientName : String,
    content : String,
    timeStamp : Date,
    status : String /*todo change this to enum*/
});


const ChatMessage = mongoose.model('ChatMessage',chatMessage,'chat');

module.exports = {

    fetchData : async function(){
        var chatMessageList = await ChatMessage.find({},(err,chatMessage)=>{
            if(err)
                throw err;
            
            return chatMessage;
        });

        return chatMessageList;
    },

    countNewMessages : async function(senderId , recipientId , status){
        
        var count = await ChatMessage.count({senderId : senderId, recipientId : recipientId, status : status},(err,chatMessage)=>{
            if(err)
                throw err;
            
            return chatMessage;
        });

    }
}
