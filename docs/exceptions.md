## Error Handling

Almost all programming languages have the concept of "Exceptions" and "Exception Handling".

Exceptions are events that happen outside the control of your program. For example, the network might lose connection, or the hard drive might be full when you try to write to a file.

Instead of your program crashing and exiting, it's possible to work
around these types of exceptions using `try` and `catch`.

When you're writing Javascript, you will often encounter a `Syntax` error. By
default, the javascript interpreter will "throw" an `Exception` such as a
`ReferenceError`.

In Javascript, `Exceptions` are `Objects` just like everything else.

When an exception is "thrown", 2 things can happen:

1. the program will stop running and the Error message and stack trace will be
written to standard output

2. If the exception is "caught" by your code, then the code inside the `catch`
block will run.

```
try {

  // You can surround a bunch of code that might throw an exception in a
  // try and catch. That way, any exception that might occur, such as a
  // network failure, or a problem reading or writing to files, or a
  // syntax error, will not cause the program to crash.

} catch (err) {
  // when an exception occurs, this code will run
} finally {
  // the `finally` block is used to run code regardless of whether a
  // exception happened or not.
}
```

There are several Error Types that come with Javascript. For example `ReferenceError`.

In addition to the standard errors, you can also create your own Error types.

Use the `throw` syntax to "throw" a custom Error

```
var myError = new Error("Watch out!");
throw myError;
```

For more information about throw, try, catch, and Exceptions, here are some links:

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/try...catch

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/throw

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Error
