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

// The array that holds the location of the ships of user 1
var userOneShips;
// Same but for user 2
var userTwoShips;

// user 1 sends the ships
app.post('/user1_ships', function(req, res) {

    res.send('Hello from the ships of planet earth');
    console.log("Got ships");

    // retrieve the datum from the post request
    req.on('data', function(chunk) {

      console.log("Received some $hips:");
      console.log(chunk.toString());
      
      var shipStr = chunk.toString();
      userOneShips = shipStr.split();

    });

    // console.log(req.url);
    // console.log(req.params);
    // console.log(req.useragent);

    // console.log(watisurproject);
//    res.send("Got ships bro");
});


// user 1 sends the attacks they want
app.post('/attackOnTitan1', function(req, res) {

    res.send('Got some attacks from user 1');
    console.log('got attacks from u$3r 1');

    // retrieve the datum
    req.on('data', function(chunk) {
        console.log(chunk.toString());

    });


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
