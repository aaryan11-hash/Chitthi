const ChatRoom = require('../Domain/ChatRoom');


module.exports = {

    getChatId : async function(senderId, recipientId){
        var chatRoomId = await ChatRoom.getChatRoomModel.find({senderId : senderId, recipientId : recipientId}, (err,room)=>{
            if(err)
                throw err;
            
            return room.id;
        });

        return chatRoomId;
    }
}