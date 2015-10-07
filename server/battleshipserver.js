// Created by Nikhil Jain
// Battleship server

var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function (req, res) {
  // send the html file to be served up
  res.sendFile(__dirname + '/index.html');
});

http.listen(3000, function () {
  console.log('listening on *3000');
});


// When a device connects to the server
io.on('connection', function (socket) {

  console.log("A user connected.");

  io.emit('test connection');


  /************************************************************
  *                                                           *
  *   All the received events that the client sends are here  *
  *                                                           *
  ************************************************************/

  // 1. disconnecting from server
socket.on('disconnect', function () {
    console.log("A user disconnected");
  });


// // 2. Confirming location of your ships
// socket.on('confirm positions', positions[], function() {
//
// })



// 3. Attack a square




});
