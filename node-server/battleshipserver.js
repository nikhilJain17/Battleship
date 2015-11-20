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
    console.log("Got ships from user 1");

    // retrieve the datum from the post request
    req.on('data', function(chunk) {

      console.log("Received some $hips:");
      console.log(chunk.toString());
      
      var shipStr = chunk.toString();
      userOneShips = shipStr.split(",");
      
      console.log("Ships left for User 1: " + userOneShips.length);
      
      for (var i = 0; i < userOneShips.length; i++) {
        console.log(userOneShips[i]);
      }

    });

    // console.log(req.url);
    // console.log(req.params);
    // console.log(req.useragent);

    // console.log(watisurproject);
//    res.send("Got ships bro");
});

// user 2 sends ships
app.post('/user2_ships', function(req, res) {

    res.send('Hello from the ships of planet earth');
    console.log("Got ships from user 2");

    // retrieve the datum from the post request
    req.on('data', function(chunk) {

      console.log("Received some $hips:");
      console.log(chunk.toString());
      
      var shipStr = chunk.toString();
      userTwoShips = shipStr.split(",");
      
      console.log("Ships left for User 2: " + userTwoShips.length);
      
      for (var i = 0; i < userTwoShips.length; i++) {
        console.log(userTwoShips[i]);
      }

    });

    // console.log(req.url);
    // console.log(req.params);
    // console.log(req.useragent);

    // console.log(watisurproject);
//    res.send("Got ships bro");
});

var playerOneWon = false;
var playerTwoWon = false;

// user 1 sends the attacks they want
// @TODO Change this to attack userTwoShips
app.post('/attackOnTitan1', function(req, res) {

    // res.send('Got some attacks from user 1');
    console.log('got attacks FR0M u$3r 1');

    if (!playerOneWon && !playerTwoWon) {
      var sendResponse = "MISS";
    }
    else if (playerOneWon && !playerTwoWon) {
      var sendResponse = "WINNER Player 1";
    }
    else if (!playerOneWon && playerTwoWon) {
      var sendResponse = "WINNER Player 2";
    }

    // retrieve the datum
    req.on('data', function(chunk) {
        console.log(chunk.toString());

         // is the game over?
        if (playerOneWon) {
          sendResponse = "WINNER Player 1";
          console.log("Player 1 won already");
        }

        else if (playerTwoWon) {
          sendResponse = "WINNER Player 2";
          console.log("Player 2 won already");
        }

        // game is not over
        else {

        var sentAttack = chunk.toString();

        var hitOrMiss = false;
        // check if there was a hit
        for (var i = 0; i < userTwoShips.length; i++) {
          
          // ITS A HIT
          if (sentAttack == userTwoShips[i]) {
            
            hitOrMiss = true;
            
            sendResponse = "HIT!";
            // console.log(sendResponse);

            // remove that ship coordinate from the array
            // TODO Remove from USERTWOARRAY!!!
            var hitShip = userTwoShips.splice(i, 1);
            console.log("removed this cord from user 2: " + hitShip);

            // display what ships are left
            console.log("Cords left " + userTwoShips.length);
      
            if ((userTwoShips.length - 1) == 0) {
              // DING DING DING WE HAVE A WINNER
              console.log("DING DING DING WE HAVE A WINNER: USER 1");
              sendResponse = "WINNER Player 1";
              playerOneWon = true;
            }

            for (var i = 0; i < userTwoShips.length; i++) {
              console.log(userTwoShips[i]);
            }

          } // end of hit

          // NO HIT
          else {
            // sendResponse = "MISS!";
            // console.log(sendResponse);
          }

        } // end of for loop checking for hit or miss

      } // end of else (game is not over)

      console.log(sendResponse);
      res.send(sendResponse);

    }); // end of req.on(data)


});



// Get attacks FROM user 2
app.post('/attackOnTitan2', function(req, res) {

    // res.send('Got some attacks from user 1');
    console.log('got attacks FROM u$3r TWO');

    if (!playerOneWon && !playerTwoWon) {
      var sendResponse = "MISS";
    }
    else if (playerOneWon && !playerTwoWon) {
      var sendResponse = "WINNER Player 1";
    }
    else if (!playerOneWon && playerTwoWon) {
      var sendResponse = "WINNER Player 2";
    }

    // retrieve the datum
    req.on('data', function(chunk) {
        console.log(chunk.toString());

        // is the game over?
        if (playerOneWon) {
          sendResponse = "WINNER Player 1";
        }

        else if (playerTwoWon) {
          sendResponse = "WINNER Player 2";
        }

        // game is not over
        else {

        var sentAttack = chunk.toString();

        var hitOrMiss = false;
        // check if there was a hit
        for (var i = 0; i < userOneShips.length; i++) {
          
          // ITS A HIT
          if (sentAttack == userOneShips[i]) {
            
            hitOrMiss = true;
            
            sendResponse = "HIT!";
            // console.log(sendResponse);

            // remove that ship coordinate from the array
            // TODO Remove from USERTWOARRAY!!!
            var hitShip = userOneShips.splice(i, 1);
            console.log("removed this cord from user 1's ships: " + hitShip);

            // display what ships are left
            console.log("Cords left " + userOneShips.length);

            // are there zero ships left?
            if (userOneShips == 0) {
              // DING DING DING WE HAVE A WINNER
              console.log("DING DING DING WE HAVE A WINNER: USER 2");
              sendResponse = "WINNER Player 2";
              playerTwoWon = true;
            }
      
            for (var i = 0; i < userOneShips.length; i++) {
              console.log(userOneShips[i]);
            }

          } // end of hit

          // NO HIT
          else {
            // sendResponse = "MISS!";
            // console.log(sendResponse);
          }

        } // end of for loop checking for hit or miss

      } // end of else (game is not over)

      console.log(sendResponse);
      res.send(sendResponse);

    }); // end of req.on(data)


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
