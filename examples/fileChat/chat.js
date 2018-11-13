/*******
/* Notes

input and output
input & output to file (`fs` module)
input & output from the console (`readline` module)
stdout & stdin
content-type == encoding
most popular encoding for text is called "utf"
all files contain binary 11010110100110101

In order to learn about input and output and the flow of a program,
we will implement a simple chat Program

javascript Event Loop.
*******/

// the fs library provides input & output to files
const fs = require('fs');
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
 * This function is a higher order function. It is a function
 * that returns a function. The function that it returns
 * is called everytime we write to a file.
 * The `nextFn` function is called after the file is written to
 **/
let afterFileWrite = function(nextFn) {
  return function(err) {
    if(err) {
      console.log(err.code);
    } else {
      nextFn();
    }
  }
}

/**
 * This function can be used to write a msg to a file
 **/
let writeToFile = function(fileName, msg, nextFn) {
  fs.appendFile(fileName, msg, "utf8", afterFileWrite(nextFn));
}

/**
 * `username` is a "globally" scoped variable. This means
 * we can see it from anywhere in our program
 **/
let username = null;

// Here are some more global variables to track the "state" of our program
let isRunning = true;
let to = null;
let prompt = ">> ";

let doExitCommand = function() {
  isRunning = false;
  rl.close(); // remember to close after writing
}

let doesFileExist = function(path) {
  let result = null;
  // fs.open is an `asynchronous` function
  // 1. we can find a synchronous version of fs.open
  // fs.openSync is a synchronous version of fs.open
  // 2. we can use "Promise".
 result = fs.openSync(path, 'r', function(err, fd){
    if(err) {
      console.log("file didnt' exist")
      return false;
    } else {
      console.log("file exists")
      return true;
    }
  })

  return result;
}

// cmdArray ["tell" "danny" "hello"]
let doTellCommand = function(cmdArray, nextFn) {
  // Is this a valid "tell" command or did the user make a mistake?
  if(cmdArray.length != 3) {
    console.log("Oops, the tell command should look like 'tell <username> <msg>' ")
    nextFn();

  } else {

    // If it's valid, get the username
    let username = cmdArray["1"];
    console.log("username is: " + username);
    // Is this a valid username?
    // check if there's a file that has same name
    let result = doesFileExist(username);
    console.log(`result: ${result}`);
    if(result) {
      writeToFile(username, cmdArray[2], nextFn);
    } else {
      console.log("Hmm, looks like that user is not signed in?");
      nextFn();
    }

  }

}

/**
 * This function runs the Main Program Loop. It listens for input,
 * and then calls functions based on what the user has entered into
 * the console
 **/
let runApp = function() {
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
 * creates a file named the same as the username
 * we also set the global username variable
 **/
let simpleLogin = function(usernameArg) {
  console.log(`Welcome: ${usernameArg}`);
  username = usernameArg;

  // create a file named 'username' and then, run the app
  writeToFile(usernameArg, "", runApp);
}

/**
 * Prompt the user to login.
 */
let promptForLogin = function(prompt) {
  rl.question(prompt, simpleLogin);
}

// start things with a very simple login
promptForLogin("What is your username? ");
