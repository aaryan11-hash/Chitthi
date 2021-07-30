const router = require('express').Router();

const chatMessageDAO = require('../Domain/ChatMessage');


router.get('/:userId',async function(req,res){
    const params = await req.params;
    mongoose.save(new Student({firstName : 'Aaryan',lastName : 'Srivastava'}));
    console.log(params.userId);
    res.send('DONE')
});

router.get('/:senderId/:recipientId/count', async (req, res) =>{

    const count = await chatMessageDAO.countNewMessages(req.params.senderId,req.params.recipientId);

    return res.send(count);
});

router.get('/:senderId/:recipientId', async (req, res)=>{

    const chatMessages = await chatMessageDAO.findChatMessages(req.params.senderId,req.params.recipientId);

    res.send(chatMessages);
});

router.get('/:id', async (req, res)=>{

    const chatmessages = await chatMessageDAO.findById(req.params.id);

    res.send(chatmessages);
});


module.exports = router;