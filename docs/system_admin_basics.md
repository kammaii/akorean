# What is a programming language?

Computers are essentially made up of a CPU, Random access memory
(RAM), and Input output devices (I/O Devices) such as networking
devices and disk memory.

The CPU (central processing unit) only understands binary code (1's and
0's). This is also called "Machine Code".

Writing programs using 1's and 0's would be very tedious, so people
created `Assembly Code`. Assembly Code is plain text and can be read
by humans but is still very difficult to write.

High Level Programming Languages were created to make it easier for
people to write programs. There are hundreds of high level programming
languages.

Some of the most popular are: C, C++, C#, Ruby, Java, Javascript, PHP
and Python.

All of these High Level Programming Languages needs to be converted
into Assembly Code and then into Machine Code. There are 2 popular
ways to do this:

1. Compiler
2. Interpreter

Some examples of Compiled languages are C, C++ and Java. Some examples
of interpreted languages are Python, Ruby and Javascript.

Compiling and Interpreting are very similar, but slightly different.

When compiling, there are 3 main steps:

1. Write High Level Code
2. Run compiler program to convert to machine code.
3. Machine code is executed by CPU

When interpreting, there are only 2 steps:

1. Write High Level Code
2. Interpreter translates code to machine language and executes in the CPU

Compiled code is slightly faster. But Interpreted code has faster
feedback for developers

# What is an Operating System?

An operating system is software that makes it easier for humans to
control computer hardware.

Most computers need the same types of things. We need files and folders. We need
the ability to run `processes`. We need the ability to display things to our
Monitor, and we need the ability to secure files.

Operating Systems provide all of these common things. For example, operating
systems provide ideas like `processes`, `file systems`, and `User Accounts`.

Your operating system is the software that communicates directly with your
computer's hardware.

Examples of Operating systems: Windows, Linux, OS x (Mac), Android, iOS

# Shell Console

A shell is a program that allows you to send commands to the operating system.
The operating system then handles running the command on hardware
(processor, and memory, etc)

Another word for `console` is `terminal`.

Some examples of shell programs include `DOS`, `PowerShell`, and `Bash`.

## Bash shell

See `docs/bash.md`.

## Javascript

Javascript is an interpreted language. Therefore, we need an
interpreter to run Javascript Code.

A web browser (like Chrome) is a javascript interpreter.

Node.js is another javascript interpreter.

# What is a Process?

CPU's can really only do one thing at a time. In order to make it easier to send code to a CPU, people created Operating Systems

Operating Systems (like Windows, Mac OSX, and Linux) provide a lot of
programs to help you send code to the CPU.

One of the ideas inside Operating Systems is a `process`.

A `process` is a program that is managed by the Operating System to
appear as if it runs continuously.

You can see all the processes currently running using the `ps` command:

	ps -ef

Another useful command is `grep`. `grep` stands for Global Regular
Expression Print. You can think of it like a way to search thru a lot
of text.

For example, to see all the `node.js` processes running, you can use this command:

	ps -ef | grep node

The `|` symbol is called a `pipe`. This is used to connect commands
together. In the command above, the pipe sends the output from the
`ps` command into the input of the `grep` command.

After you find a process id using the `ps` command, you can use the
`kill` command to stop a process:

	kill 76328

# Packages and libraries

Whenever people want to share code, they create a package or library. Each
operating system and every programming language has a different way to packages
code.

See `docs/packages.md` for more information.

# User Accounts

When multiple people use a computer, it's nice to be able to know who is running
which programs.

Most Operating Systems provide Security in the form of User Accounts, Groups,
Authentication, and Authorization.

## Authentication

"Who is currently running this process?"

Authentication is the process of identifying a person using a computer.

## Authorization

"Are you allowed to do run this process?"

Authorization is the process of knowing whether someone is allowed to run
a process.
