# Javascript Scope

Programs often re-uses the same variable names to describe
different concepts. Programming languages need to use `scope` to
avoid confusion.

"Scope" defines a context where a variable can be seen.

The most top-level Scope in Javascript is called the "Global" scope.
Variables defined inside the "Global" scope can be seen from
anywhere else in the program.

In the browser, the Global Scope is called "window". In Node.js, the
Global scope is called "global".

A new function scope is created when you define a function.

A new block scope is created when you define a new block (such as an
`if` statement or `for` loop);

Be careful when using `var` to define variables. `var` will
create a globally scoped variable inside block scopes!

So, it's good practice to always use `let` instead of `var`.

In addition to `let` and `var`, you can also define variables using
`const`. `const` will define a variable that can never be changed.

When variables are passed into functions, Javascript uses "pass by
value". You can also think "pass by copy". This means that the
function arguments get a copy of the value of the parameter passed.

Therefore, when a primitive variable is passed to a function, the
function argument gets a copy of the actual value.

However, when a object variable is passed to a function, the function
argument gets a copy of a reference to the object.

Here's a (simplified) diagram of how a computer might allocate memory
for the program below. Note how the value of z is a memory location.
and notice that the value of x is an actual value.

![Javascript Scope Diagram](https://upgradingdave.github.io/images/js_scope.png)

```
(01) var x = 0;
(02) let y = 0;     // in the global scope, var is the same as let
(03) let z = [0];
(04) if(x == 0) {
(05)   var i = 20;  // this creates a global `window.x` variable.
                    // we should use `let` here instead!
(06)   let j = 30;
    }

(07) function f1(a, b, c) {

(08)   a = a + 1;
(09)   x = x + 1;
(10)   b = b + 1;
(11)   c.push(1);
(12)   let d = 0;
(13)   d = d + 1;
(14)   var e = 0;  // this does not create a global `window.e`
                   //  variable.
(15)   e = e + 1;
     }

// Here are the values at this point:
// x = 0
// y = 0
// z = [0]
// i = 0
// a, b, c, d, and e are all 'undefined';

(16) f1(x,y,z);

// After calling f1:
// x = 1 because it's a global variable and it was changed inside f1
// y = 0 even though y is also a global variable, it was not directly
//       changed in f1. A copy of y was passed to b and b was
//       changed.
// z = [0,1] because it's an object. A copy of the object reference
//           was copied into c. c updated the value that the object
//           reference points to. Since z and c both point to the
//           same object, the array changed.
// i = 20
// a, b, d and e and c are all still undefined because they were all
// defined inside the f1 function's scope which is not visible to they
// global scope.
```
