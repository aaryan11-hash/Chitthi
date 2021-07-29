const router = require('express').Router();
const mClient = require('./mongooseConfig');
const Student = require('./Schema');

router.get('/:userId',async function(req,res){
    const params = await req.params;
    mongoose.save(new Student({firstName : 'Aaryan',lastName : 'Srivastava'}));
    console.log(params.userId);
    res.send('DONE')
})


module.exports = router;