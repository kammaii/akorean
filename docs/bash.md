# What's a Shell?

Remember that computer hardware (memory, processors, monitors) can only
understand binary code (1's and 0's). Each piece of Hardware can usually only
do 1 thing at a time.

An operating system is a piece of software that makes it easier for humans to
control computer hardware. Operating Systems provide abstractions such as
`processes`, `file systems`, and `User Accounts`.

A **shell** is a program inside an operating system that allows you to send Commands
to the operating system. The Operating system translates commands into binary
in order to control the computer hardware.

The shell is commonly referred to as the `console` or `terminal` or
`command prompt`.

DOS, PowerShell, and Bash are examples of Shells.

# Bash Commands

* `CTRL-C` : if you ever get stuck, use `CTRL-C` to cancel. `CTRL-C` will also
  stop a running process.
* `cd` : change directory
* `ls` : list directory
   By default `ls` will not list visible files. To list all files, use `ls -a`.
* `man` : View help manual for any command
   For example, `man ls` will show a description of the `ls` command.
* `cat` : View contents of a file
* `grep` : Search for files. For example, if you want to find all files that
   contain the word "hello", you can use `grep . "hello"`
* `ps` : list all the processes currently running in your Operating System.
   By default `ps` will only list processes that you started. Use `ps -ef` to
   list all the processes.
* `kill` : stop a process.
* `touch` : create a new files
* `.`, `..` : these are special symbols. `.` represents the current directory.
  `..` represents the parent directory (go up one directory)

# Bash on Windows

Normally, you need a computer with Linux to use Bash. But, Windows 10 actually
provides a Bash Shell! The Windows 10 Operating system is capable of translating
Linux operating system calls into windows operating system calls.
