# Objects and Prototypes

Javascript is a "Prototypical", "Prototype" language.  A Prototype is a
definition that describes the properties of an Object.  You can think of a
prototype as a Recipe.

Sometimes people might also refer to "Prototype" as "Class". When you create an
object from a Prototype, you can say it is an "Instance" of a Prototype (or
"Instance" of a Object Definition)

All Objects in Javascript inherit from the "Object" Prototype (or Class)

There are several different ways to Define Objects and Object Prototypes

Here we create an object using "Literal" syntax
```
var babeRuth = {
  firstName: "Babe",
  lastName: "Ruth",
  birthDate: "February 6, 1895 05:00",
  americanAge: function() {
    let today = new Date();
    let birthDate = new Date(this.birthDate);
    let age = today.getFullYear() - birthDate.getFullYear();
    let monthCheck = today.getMonth() - birthDate.getMonth();
    let dateCheck = today.getDate() - birthDate.getDate();
    if(monthCheck < 0 || (monthCheck == 0 && dateCheck < 0)) {
      age = age - 1;
    }
    return age;
  }
}
```

Object
 |
 +---- babeRuth

Here's an example of another way to create the same object as above.

Everything in Javascript derives from a Prototype called "Object"

A function that can be used to create an object is called a "Constructor"

In Javascript, there is a special symbol (keyword) called "new" that we can use
to call object constructor functions

This is how we can use the Object Constructor to create a new object This is
similar to using object literal above.

```
var babeRuth2 = new Object(); // We can also say Object.create(). We are creating an instance of the Object Prototype called "babeRuth2".
babeRuth2.firstName = "Babe";
babeRuth2.lastName = "Ruth";
babeRuth2.birthDate = "February 6, 1895 05:00";
babeRuth2.americanAge = function() {
  let today = new Date();
  let birthDate = new Date(this.birthDate);
  let age = today.getFullYear() - birthDate.getFullYear();
  let monthCheck = today.getMonth() - birthDate.getMonth();
  let dateCheck = today.getDate() - birthDate.getDate();
  if(monthCheck < 0 || (monthCheck == 0 && dateCheck < 0)) {
    age = age - 1;
  }
  return age;
};
```

So far, we have defined very "specific" objects.

What if we want to define a "Class" of objects. For example, let's try to define
a "Person" prototype  (also sometimes called a "Person" Object Class
Definition).

There are several ways to define an Object Prototype (also sometimes called
Object Class Definition)

Remember: Another way to think of a prototype is a "Recipe" for creating
specific objects

Here's one way to make a "Person" Prototype ("Recipe"). This is how we can
create a "Constructor Function".

After we have defined a Person using a "Constructor Function", we can use `new`
to create an "instance" of a Person Prototype

```
function Person(a, b, c) {
  this.firstName = a;
  this.lastName = b;
  this.birthDate = c;

  this.age = function() {
    let today = new Date();
    let birthDate = new Date(this.birthDate);
    let age = today.getFullYear() - birthDate.getFullYear();
    let monthCheck = today.getMonth() - birthDate.getMonth();
    let dateCheck = today.getDate() - birthDate.getDate();
    if(monthCheck < 0 || (monthCheck == 0 && dateCheck < 0)) {
      age = age - 1;
    }
    return age;
  }

  this.log = function() {
    console.log("FirstName: " + this.firstName + ", lastName: " + this.lastName + ", age: " + this.age());
  }
}
```

Now, we can use our Person Prototype (Recipe) to create specific People:

```
let dave = new Person("David", "Paroulek", "July 4, 1979 0300");
//dave.log();
```

`dave` is an instance of Person. Person is an instance of Object

Object
 |
 +--- Person
      |
      +--- dave

```
let babe = new Person("Babe", "Ruth", "February 6, 1895 05:00");
//babe.log();
```

We can can add more functions or properties to a Prototype anytime after it has
been defined:

```
Person.prototype.formatName = function() {
  console.log(this.lastName + ", " + this.firstName);
};
```

When you use `new` to create an object, everything is copied from the prototype
to the object.

When `dave` was created, `formatName` didn't exist.

So, at runtime, the javascript interpreter (the browser) first checks if there's
a `dave.formatName`, which is undefined.

Then it checks the parent of `dave` (which is `Person.prototype`) and finds a
`formatName()` function.

```
//dave.formatName();
```

This creates a hierarchy like this:

Object.prototype.formatName (not defined; returns "undefined")
|
+----Person.prototype.formatName (returns "firstName, LastName")
     |
     +----dave.formatName (not defined; returns "undefined")

We can add a function directly to an object:

```
dave.formatName = function() {
  console.log(this.firstName.substr(0,1) + ". " + this.lastName);
}
```

Now, the hierarchy looks like this:

Object.prototype.formatName (not defined; returns "undefined")
 |
 +----Person.prototype.formatName (returns "firstName, LastName")
      |
      +----dave.formatName (returns "D. Paroulek")

Now, formatName will be found on `dave`, so the `Person.prototype.formatName`
function won't be called

```
dave.formatName();
```

New instances of Person will inherit (copy) the new formatName method. let
hankAaron = new Person("Hank", "Aaron", "February 4, 1934 00:00");

Object.prototype.formatName (not defined; returns "undefined")
 |
 +----Person.prototype.formatName (returns "firstName, LastName")
      |
      +----hankAaron.formatName (not defined; returns "undefined")

```
//hankAaron.formatName();
```

The example above showed how we can add a new method to an existing Prototype.

What happens if we try to re-define an existing method?

Beware! I think this is very confusing!

let's try to redefine the `log` function in the `Person.prototype`:

```
Person.prototype.log = function() {
  console.log("This has changed!!");
}
```

This won't have any effect on existing object instances?!!

```
dave.log()
```

What if we create a new object instance?

```
let cal = new Person("Ripken", "Cal", "August 24, 1960 00:00");
```

Notice how the `log` function on a new instance `cal` is still using the
definition from the original Person Object Constructor Function??!

```
//cal.log();
```

Note that we can call the Person.prototype.log() function directly like this if
we want:

```
//Person.prototype.log();
```

The reason we don't see "this has changed!" is because the `new` operator calls
the Person constructor function to copy  all the data and functions to the new
object. So we end up with a hierarchy like this:

Object.prototype.log (not defined; returns "undefined")
 |
 +----Person.prototype.log (returns "This has changed!!"); // this is never
      |                                                    // called because both
      |                                                    // `dave` and `cal`
      |                                                    //copy the `log`
      |                                                    //function from the
      |                                                    //Person constructor
      |                                                    //function.
      |
      +----dave.log (returns "firstName: David, lastName: Paroulek, age: 39")
      +----cal.log (returns "firstName: Cal, lastName: Ripken, age: 58")

So far, we've learned how to create an Object 3 ways:

1. Using Object Literal. Literal is plain text way to create Objects
2. Using Object.create(). We create an instance of Object, then assign
   additional properties and functions
3. Define a Object Constructor Function. We can use `new Person(...);`
    to call the Person Object Constructor Function to create a new Person
    Instance.

Here's another way to create an Object Prototype (sometimes called Object Class
Definition). This is a combination of #1 and #2.

We will create a Object Prototype "hierarchy" (or Object Class hierarchy) of
"Animals"

```
let Animal = {
  family: "Animal",
  name: "Animal",
  says: function () {
    return this.sound;
  },
  // this `log` function will produce different results based on the type of object
  // it is called on. This is known as "polymorphism"
  log: function() {
    console.log("The " + this.name + " says " + this.says())         
  }
}
```

In Javascript, properties and functions are copied from `Animal` to to `Mammal`.
We can say that `Mammal` "inherits" from `Animal`. Animal is the "parent" class and Mammal is the "child" class. Mammal is a "subclass" of "Animal".

```
let Mammal = Object.create(Animal);
Mammal.family = "Mammal";
```

Reptile inherits from the parent class "Animal". Reptile is a "subclass" of
"Animal".

```
let Reptile = Object.create(Animal);
Reptile.family = "Reptile";
```

Now we can create instances of Reptile

```
let frog = Object.create(Reptile);
frog.name = "frog";
frog.sound = "ribbit";

frog.log();
```

We can create instances of Mammal

```
let cat = Object.create(Mammal);
cat.name = "cat";
cat.sound = "meow";

//cat.log();
```

"Inheritance" - objects inherit properties from their parent.  

"Polymorphism" - same method name, but different behavior depending on the type
of object

Remember that everything in javascript is a based on a hierarchy of prototypes
The top of the hierarchy is `Object`

So, for example, we can add a method to all objects!

```
Object.prototype.hello = function() {
  console.log("Hello!!");
}
let myString = "Javascript is crazy!!!";
//myString.hello(); // all Strings have "hello" function!
//cat.hello(); // in fact, all objects, even Mammals, have "hello" function!
```

Why does `myString.hello` work??

Object.prototype.hello (prints "Hello!" to the console)
|
+----String.prototype.log (undefined, so check parent)
|    |
|    +----myString.hello (undefined, so check parent)
|
+----Person.prototype.hello(undefined, so check parent)
     |
     +----dave.hello (undefined, so check parent)

Sometimes, objects have a lot of data properties. It can be confusing to match
arguments to properties.

For example:
```
var Student = function(firstName, lastName, middleName, street, city, country,
   zipcode, company, telephoneNumber, email, username, password) {
  ...
}
```

One way to make this easier is to use a object of properties as an argument. A
lot of times, this parameter will be called "options", or "opts", or "props".

```
var Student = function(options) {
  this.firstName = options.firstName;
  this.lastName = options.lastName;
  this.email = options.email;
  //... many more properties
}
```

Now we only need to pass 1 argument to the Student Object Constructor Function instead of a lot of parameters

```
var tim = new Student({
  firstName: "Tim",
  lastName: "Smith",
  email: "tsmith@gmail.com"
});
```

Here's another way to get the value of a object property:

```
console.log(tim.email);
console.log(tim["email"]);
````

This can be useful when you don't know the properties until you want to use
them.

```
function printStudent(student, propNames) {
  if(!propNames || propNames.length <= 0) {
    return "";
  }

  let result = "Student: ";
  for(let i=0; i<propNames.length; i++) {
    if(student[propNames[i]] != undefined) {
      result += student[propNames[i]] + (i<propNames.length-1 ? ", " : "");
    }
  }
  return result;
}

console.log(printStudent(tim, ["firstName"]));
```
