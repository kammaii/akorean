# What is Javascript?

Javascript is an interpreted, dynamically typed high level programming language.

Two popular Javascript Interpreters are the Chrome Browser (which uses the
Javascript V8 engine), and Node.js.

# Symbols (keywords)

Every programming language is made up of words that have special meanings.
These words are called "Symbols" (or "keywords").

Some examples of Javascript symbols/keywords are: `if`, `function`, `while`, and
`var`.

# Variables and Types

Variables are containers that store values.

In programming languages, it's useful to think of different "types" of
containers for values.

Javascript provides several "Types" of variables including Strings, Numbers,
Booleans, Arrays, and Objects.

Numbers, Strings, and Booleans represent simple values. They are considered
"Primitive" types.

Everything else (besides Numbers, Strings, and Booleans) in Javascript is an
"Object".

Primitive variables are containers of "immutable" values.

The word "immutable" means that the value will never change.

Objects are containers of primitive variables. The values of Objects can change
over time.

Javascript is a "Dynamically Typed" language. This means that Javascript tries
to determine the "type" of a variable depending on the value you assign. The
variable's type can change after it's been defined.

Java, C and C++ are examples of "Statically Typed" Languages. In these
Languages, each variable must be defined as a specific type. The type
can't change after the variable is defined.

Here's an example of how the type of a variable can change:

```
> var ten = 10;
> console.log(typeof(ten));
'number'

> ten = '10';
> console.log(typeof(ten))
'string'

> ten = [];
> console.log(typeof(ten))
'object'
```

## Undefined, Null, and NaN

When writing a program, you often need to show that a variable doesn't have any
value.

In Javascript, `undefined`, and `null` are both used to show that a variable
does not contain a value.

Note that `undefined` and `null` are different than "empty".

`NaN` stands for "Not a Number". It is used when a variable of type "Number" is
assigned something that is not a number.

Here are some examples to show `null`, `undefined`, and `NaN`

```
// before a variable is defined, type will be `undefined`
> console.log(typeof(ten));
'undefined'

// if you try to access the value of a variable before it is defined, an error
// will be thrown
> console.log(ten);
Uncaught ReferenceError: ten is not defined

// after the variable is defined, but before a value is assigned, it will still
// be undefined.
> var ten;
> console.log(typeof(ten));
'undefined'
> console.log(ten);
undefined

// after null is assigned, the type is 'object', and the value is 'null';
var ten = null;
> console.log(typeof(ten));
'Object'
> console.log(ten);
null

> ten = parseInt('abc')
> console.log(ten)
NaN
> console.log(typeof(ten));
'number' // Strange, huh?!
```

# Boolean and Comparing Values

A Boolean Type of variable can either be `true` or `false`.

The `==` symbol is used to compare 2 things to see if they are equal to one
another.

The `===` symbol is used to compare 2 things to see if the two things are the
EXACT same thing.

The `!==` is the opposite of `==`

The `!===` is the opposite of `===`

Also, the `!` symbol can be used to change any boolean value.

For example:
```
> !true == false
true
> !(1 == 1)
false
```

Using these two symbols to compare numbers works well:

```
> 1 == 1
true
> 1 === 1
true

> var v1 = 1;
> var v2 = 1;
> v1 == v2;
true
> v1 === v2;
true
```

Using these two symbols to compare strings works well:

```
> "Hello" == "Hello"
true
> "Hello" === "Hello"
true

> var v1 = "Hello"
> var v2 = "Hello"
> v1 == v2
true
> v1 === v2
true
```

Undefined is equal to null!

```
> undefined == null
true
```

But, when you use these two symbols to compare anything else, it gets very
tricky!

```
> [1] == [1]
false // these are two different objects, so this returns false?!

> var v1 = [1];
> var v2 = v1;
> v1 == v2;
true // since these two variables contain the exact same array,
     // this returns true
```

```
> {name: "Danny"} == {name: "Danny"}
false
> var v1 = {name: "Danny"};
> var v2 = v1;
> v2 == v1
true
> v2 === v1
true
```

What does it mean for two Arrays to be equal? For example,
sometimes, you might want to say that `[1, 2, 3]` and `[3, 2, 1]` are equal.

Instead of using `==`, and `===`, It's better to define your own functions to
compare two things.

Here's an example of creating a function to compare 2 arrays:

```
function arrayEq(arr1, arr2) {

  // if these two variables refer to the EXACT same array, return true
  if(arr1 === arr2) {
    return true;
  }

  // if one array is longer than the other, we know they are not equal
  if(arr1.length != arr2.length) {
    return false;
  }

  // result is initialized to true
  // If we find any value in arr1 is not equal in arr2 then we return false

  var result = true;

  // now we know that both arrays are the same length
  for(var i=0; i<=arr1.length; i++){
    if(arr1[i] != arr2[i] ) {
      result = false;
    }
  }

  return result;
}
```

# Functions and methods

Functions take inputs and produce an output. When you are defining a
new function, the inputs to the function are called "arguments".

The `return` keyword to return an output.

Here's a simple function that adds two numbers. This is an example of a
"pure" function because it doesn't do anything else except return an output.

```
function add(a, b) {
  return a + b;
}
```

After you define a function. You can call the function, like this:

```
> console.log(add(1, 2));
3
```

When you call a function, the inputs are called "parameters".

Functions that are not "pure" are called "methods".

Here's an example of a method that does something else in addition to returning
a value.

```
function addComponent(componentType) {
  document.createElement(componentType);
  return true;
}
```

The `addComponent` method creates a document object model element (DOMElement)
and then returns `true`. This is not a pure function. The `addComponent` method
will return `true`, however, it is doing more than just returning a value.

```
> result = addComponent("div");
> console.log(result);
true
```
Sometimes methods are useful. But you should always try to write pure functions
if possible. This will make your code much easier to understand. And it will be
easier to build more complicated programs.
