const student = require('mongoose').Schema({
    firstName : String,
    lastName : String,
    rollNo : String
});


const Student = require('mongoose').model('Student',student);

module.exports = Student;