# Overview

Let's build a chat program!

Let's try to build a program that we can use to send chat messages back and
forth to one another.

The purpose of building this program is to learn about the following:

- Program (console) input and output
- File input and output
- Communicating over a network (Network input and output, Socket input and output)
- Learn more about Events and Callbacks
- Learn more about higher order functions (functions that create functions)
- Learn about json? (maybe we can implement a very simple json protocol)

# Design

Our chat program will allow 2 or more users (user1 and user2) to send messages back and forth to each other.

Our chat program will have a very simple "Login".

When user1 starts the program, it will prompt for a "username".

User1 will enter a username and then the program will create a file with the
same name as the username.

This username file will be used to store messages sent to user1.

Next, user2 will start the program and enter a username. The program will create
a file with the same name as the username of user2.

user1 will type a message to user2. The program will store the message inside user2's file.

user2 will read the new message from the file.

# TODO

- Able to send messages longer than one word
- why does listen show the contents of the file twice?
- only show the latest message when listening
