const net = require('net');
const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const server = net.createServer(function(socket) {
  console.log('client connected');
  socket.on('end', function() {
    console.log('client disconnected');
  });

  socket.on('data', function(data) {
    //console.log(data.toString());
    let response = JSON.parse(data);
    console.log(`${response.from} just said: ${response.messages}`);
  });
});

server.on('error', function(err) {
  throw err;
});

let prompt = ">>";
let username = null;
let port = 8124;
let messages = [];

let listenCmd = function(cmdArr, nextFn) {
  server.listen(port, function() {
    console.log(`${username} is listening!`);
    console.log(server.address());
  })
}

let tellCmd = function(cmdArr, cmd, nextFn) {
  if(cmdArr.length < 3 ) {
    console.log("Tell command should look like 'tell <ipAddress> <msg>'");
    nextFn();
  } else {

    let ipAdd = cmdArr[1];

    const client = net.createConnection({
      port: port,
      host: ipAdd
    }, function() {

      let cmdMsg = cmd.replace("tell ", "");
      cmdMsg = cmdMsg.replace(username, "");

      messages.push(`${cmdMsg}`);
      let lastMsg = messages[messages.length - 1];

      let packet = {
        "from": username,
        "messages": lastMsg
      }

      let packetStr = JSON.stringify(packet);

      console.log('connected to server!');
      client.write(packetStr);
      client.end();
      nextFn();
    });

    client.on('end', function() {
      console.log('disconnected from server');
    });
  }
}

let exitCmd = function() {
  rl.close();
}

let runApp = function() {
  rl.question(prompt, function(cmd) {

    let cmdArr = cmd.split(" ");

    if(cmdArr[0] == 'exit') {
      exitCmd();
    } else if(cmdArr[0] == 'tell') {
      tellCmd(cmdArr, cmd, runApp);
    } else if(cmdArr[0] == 'listen') {
      listenCmd(cmdArr, runApp);
    } else {
      console.log("Hmm, I didn't recognize that command.");
      runApp();
    }
  })
}

let simpleLogin = function(usernameArg) {
  console.log(`Welcome: ${usernameArg}`+ '\n');
  username = usernameArg
  runApp();
}

let promptForLogin = function(prompt) {
  rl.question(prompt, simpleLogin);
}

promptForLogin("What is your username?");
