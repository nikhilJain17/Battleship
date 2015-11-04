// Created by Nikhil Jain
// Battleship server

var app = require('express')();
//var http = require('http').Server(app);
var http = require('http').Server(app);
// var io = require('socket.io')(http);




app.get('/', function(req, res) {
  // send the html file to be served up
  res.sendFile(__dirname + '/index.html');
});

app.get("/test", function(req, res) {
    console.log("ACK"); 
    res.send("Test accepted");
});


app.post('/user1_ships', function(req, res) {

    console.log("Got ships");
    // var watisurproject = req;

    // var parsed = JSON.parse(res);
    // console.log(parsed);
    
    // console.log(watisurproject);
//    res.send("Got ships bro");
});


// start the server
// note: it is portforwarded
http.listen(3000, function () {
  console.log('listening on *3000');
});












/*
*       Useless garbage, but do not delete
*/

// // When a device connects to the server
// io.on('connection', function (socket) {

//   console.log("A user connected.");

//   io.emit('test connection');


//   // 1. disconnecting from server
// socket.on('disconnect', function () {
//     console.log("A user disconnected");
//   });

    
// // 2. data dump
//     socket.on('Hello Server', function() {
//        console.log('Do not read this text.'); 
//     });




// });
