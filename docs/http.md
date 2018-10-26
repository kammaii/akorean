# Inter process communication.

Many times 2 processes need to communicate. There are infinite different ways that two processes can communicate.

A very simple way would be one program writes to a file and
the reads what the program wrote.

Writing and reading to a file is not very well defined.

Protocols are formats that 2 programs can agree upon in order to communicate.

# Hyper Text Transport Protocol (http)

In http, a client (web browser) sends a request.

The server (which is another process, could be on same computer or different computer) will listen for requests and for each request will
send a response.

# Hyper Text Transport Protocol Secure (https)


## Two most popular commands:

GET

POST

# Ports

By default, Port 80 is used for http.
Port 443 is used for https.
