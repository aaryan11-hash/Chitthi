const PORT = 8003;

new (require('./Config/EurekaConfig')().registerWithEureka('UPDATE-SERVICE',PORT));

const app = (require('express'))();

const chatRestController = require('./Controller/ChatRestController');


app.use('/messages',chatRestController);
app.listen(PORT,()=>console.log('Server listening on PORT ',PORT));