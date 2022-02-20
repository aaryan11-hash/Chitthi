const mongoose = require('mongoose');
const chatRoomService = require('../Service/ChatRoomService');

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

        return count;
    },

    findChatMessages : async function(senderId , recipientId){

        var chatMessageId = await chatRoomService.getChatId(senderId,recipientId);
        
        var chatMessages = await ChatMessage.find({chatId : chatMessageId},(err,messages)=>{
            if(err)
                throw err;
            
                return messages;
        });
        //todo enum changes to be made
        if(chatMessage.length > 0)
            this.updateSatus(senderId,recipientId,'DELIVERED');
        
        return chatMessages;
    },


    findById : async function(id){

        var chatMessages = await ChatMessage.find({id : id},(err,data)=>{

            if(err)
                throw err;
            
            else if(data !== undefined)
                this.updateSatus(id,undefined,undefined,'DELIVERED');
            
            return chatMessages;
        });

        return chatMessages;
    },

    updateSatus : async function(id,senderId, recipientId, messageSatus){
        
        if(id == undefined){
            ChatMessage.where({senderId : senderId, recipientId : recipientId})
                   .update({status : messageSatus});
        }else{
            ChatMessage.where({id : id})
            .update({status : messageSatus});
        }
    }


};
