// Hyper Text Transport Protocol
// html = hyper text markup language
const http = require('http');
const fs = require('fs');

const hostname = '127.0.0.1';
// common http port : 80
const port = 3000;

let serveFile = (pathToFile, contentType, httpResponse) => {
  fs.readFile(pathToFile, function(err, data) {
    //response.writeHead(200, {'Content-Type': 'text/html'});
    httpResponse.statusCode = 200;
    httpResponse.setHeader('Content-Type', contentType);
    httpResponse.write(data);
    httpResponse.end();
  });
}

let userAccounts = {
  'upgradingdave': {
    username: 'upgradingdave',
    password: 'secret'
  },
  'danny': {
    username: 'danny',
    password: 'secret2'
  }
};

let handleRequest = function(httpRequest, httpResponse) {
  try {
  //console.log(httpResponse);

  // here's where we define the routes
  // Browsers will send GET or POST requests
  // If they are GET requests, all the info is in the url like this:
  //  /login?username=upgradingdave&password=secret
  console.log(httpRequest.url);
  console.log(httpRequest.method);

  if(httpRequest.url == '/login.js'){

    serveFile('login.js', 'text/javascript', httpResponse);

  } else if(httpRequest.url.match(/\/login.*/)) {

    console.log('This is a LOGIN request!');
    // get paramaters out of GET login url
    let loginRE = /login\?username=([^&]*)&password=(.*)/
    let paramResult = httpRequest.url.match(loginRE);
    let username = paramResult[1];
    let password = paramResult[2];

    if(userAccounts[username]) {
      let passwordCheck = userAccounts[username].password;
      if(passwordCheck === password) {
        // Authenticated!!
        serveFile('home.html', 'text/html', httpResponse);
      } else {
        // Wrong password!!
      }
    } else {
      // user doesn't exist!!
    }
  // Any request for any ".js" file should return the correct js file.

  } else {
    serveFile('login.html', 'text/html', httpResponse);
    //httpResponse.statusCode = 200;

    // Very simple string response
    //httpResponse.setHeader('Content-Type', 'text/html');

    // everything after the headers is the "Response Body"
    /*let html = "<html><head><title>Jquery</title></head>" +
              "<body>"+
              "<div>Hello Jquery</div>"+
              "<form method=\"POST\" action=\"/login\">"+
              "<div>" +
             "<label for=\"username\"/>Username</label>"+
             "<input id=\"username\" name=\"username\" type=\"text\"></input>" +
             "</div>" +
             "<div>" +
             "<label for=\"password\"/>Password</label>"+
             "<input name=\"password\" type=\"password\"></input>" +
             "</div>" +
             "<a href=\"#\" id=\"submitBtn\" class=\"btn\" value=\"Submit\">Submit</a>" +
             "</form>"+
             "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js\"></script>"+
             "<script src=\"http://127.0.0.1:3000/app.js\">" +
             "</script>"+
             "</body>";*/

    //httpResponse.end(html);
  }
} catch(err) {
  console.log(err);
}

}

const server = http.createServer(handleRequest);

server.listen(port, hostname, function() {
  console.log(`Server running at http://${hostname}:${port}/`);
});
