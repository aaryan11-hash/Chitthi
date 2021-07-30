const mongoose = require('mongoose');


const chatRoom = mongoose.Schema({
    id : String,
    chatId : String,
    senderId : String,
    recipientId : String
});

const ChatRoom = mongoose.model('ChatRoom',chatRoom,'room');

module.exports = {

    fetchData : async function(){

        var chatRoomList = await ChatRoom.find({},(err,rooms)=>{
            
            if(err)
                throw err;

            return rooms;  
        });

        return chatRoomList;
    },

    getChatRoomModel : function(){
        return ChatRoom;
    }
    
}
