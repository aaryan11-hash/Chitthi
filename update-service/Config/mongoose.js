const mongoose = require('mongoose');

mongoose.connect('mongodb+srv://aaryan1101:aaryan1101@cluster0.ddchx.mongodb.net/chitthi?retryWrites=true&w=majority&ssl=true',{ useNewUrlParser: true , collection : 'testDomain'});

mongoose.connection.once('open',()=> console.log('Mongoose connection eshtablished'))
                   .on('closed',()=>console.log('Mongoose connection terminated'));

module.exports = mongoose.connection;