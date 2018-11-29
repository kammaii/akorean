// the fs library provides input & output to files
const net = require('net');
// the readline library provides input & ouptut to console
const readline = require('readline');

/**
 * Before we can read or write data to the console, we
 * need to configure the `readline` variable so that
 * it will read and write to standard input and standard output
 **/
const rl = readline.createInterface({
  input: process.stdin, // standard input
  output: process.stdout // standard output
});

/**
 * `username` is a "globally" scoped variable. This means
 * we can see it from anywhere in our program
 **/
let username = null;

// Here are some more global variables to track the "state" of our program
let isRunning = true;
let prompt = ">> ";
let port = 8124;

let doExitCommand = function() {
  isRunning = false;
  rl.close(); // remember to close after writing
}

let messages = [];

// cmdArray ["tell" "ip address" "hello"]
let doTellCommand = function(cmdArray, nextFn) {

  // Is this a valid "tell" command or did the user make a mistake?
  if(cmdArray.length != 3) {
    console.log("Oops, the tell command should look like 'tell <ipAddress> <msg>' ")
    nextFn();
  } else {

    // get ip address
    let ipAddress = cmdArray[1];

    const client = net.createConnection({ port: port, host: ipAddress},
      function() {

      messages.push(`${cmdArray[2]}\r\n`);

      let packet = {"from": username,
                    "messages": messages}

      let packetStr = JSON.stringify(packet);

      // 'connect' listener
      console.log('connected to server!');
      client.write(packetStr);
      client.end();
      nextFn();

    });

    /*client.on('data', (data) => {
      console.log(data.toString());
    });*/

    client.on('end', () => {
      console.log('disconnected from server');
    });
  }
}

// Create a TCP Server
const server = net.createServer(function (socket) {
  console.log('client connected');
  socket.on('end', () => {
    console.log('client disconnected');
  });

  socket.on('data', (data) => {
    console.log(data.toString());
    let response = JSON.parse(data);
    console.log(`${response.from} just said: ${response.messages[0]} `);
  });
  //socket.write('hello\r\n');
  //socket.pipe(socket);
});

server.on('error', (err) => {
  throw err;
});

// The syntax is ["listen"]
let doListenCommand = function(cmdArray, nextFn) {
    server.listen(port, () => {
      console.log(`${username} is listening!`);
      console.log(server.address());
    });
}

/**
 * This function runs the Main Program Loop. It listens for input,
 * and then calls functions based on what the user has entered into
 * the console
 **/
let runApp = function() {
//  try {
    // prompt for next command
    rl.question(prompt, function(cmd) {

      // a command will be a string like "tell danny hi"
      // we need to break that string into an array of words
      // "to danny".split(" ") will produce an array like ["tell", "danny", "hi"]
      let cmdArray = cmd.split(" ");

      if(cmdArray[0] == 'exit') {
        doExitCommand();
        // don't call runApp() again, so the program will just exit
      } else if(cmdArray[0] == 'tell') {
        doTellCommand(cmdArray, runApp);
      } else if(cmdArray[0] == 'listen') {
        doListenCommand(cmdArray, runApp);
      } else {
        // whatever the user entered didn't match any commands
        console.log("Hmm, I didn't recognize that command?");
        runApp();
      }

    });
}

/**
 * This is a very simple "login" function.
 * When the program starts, it asks for a username and then
 * sets the global username variable
 **/
let simpleLogin = function(usernameArg) {
  console.log(`Welcome: ${usernameArg}`);
  username = usernameArg;
  runApp();
}

/**
 * Prompt the user to login.
 */
let promptForLogin = function(prompt) {
  rl.question(prompt, simpleLogin);
}

// start things with a very simple login
promptForLogin("What is your username? ");
