const mongoose = require('mongoose');
const student = mongoose.Schema({
    firstName : String,
    lastName : String,
    rollNo : String
});


//const Student = mongoose.model('Student',student);

const testDomain = mongoose.Schema({
    id : String,
    name : String
});

const TestDomain = mongoose.model('TestDomain',testDomain,'testDomain');

// TestDomain.insertMany({id : 12,name : 'aary'});

// const user = new TestDomain({id : 14, name : 'aaryansri'});
// user.save((err,user)=>{
//     if(err) throw err;
//     console.log(user);
// });

module.exports = {

    fetchData : function(){
        var userList = TestDomain.find({},(err,users)=>{
            if(err) throw err;
            console.log(users);
        });
       
    }


};