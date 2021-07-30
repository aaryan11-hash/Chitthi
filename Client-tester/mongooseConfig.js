const mongoose = require('mongoose');

mongoose.connect('mongodb+srv://aaryan1101:aaryan1101@cluster0.ddchx.mongodb.net/chitthi?retryWrites=true&w=majority&ssl=true',{ useNewUrlParser: true , collection : 'testDomain'});

mongoose.connection.once('open',() => console.log('Mongoose Connection has been made'))
                   .on('error',() => console.log('Mongoose Connection error'));


module.exports = mongoose.connection;