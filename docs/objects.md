# Javascript Objects

Often, when programming, it's very useful to group together data and functions.

For example, it might be useful to define a "Person" object. A Person object
might have data such as "first name" and "last name". It also might have
functions such as a function to calculate age.

An "Object" is an idea in Programming Languages. A group of data and functions
is called an Object.

## Object Literals

In programming, the word "Literal" refers to the fact that you can represent
a complicated concept using only text.

Here's how you can represent a Javascript Object using plain text:

```
var babeRuth = {
  firstName: "Babe",
  lastName: "Ruth",
  birthdate: "February 6, 1895 05:00",
  americanAge: function() {
    let today = new Date();
    let birthDate = new Date(this.birthdate);
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

## Accessing Object Properties and functions

There are a few different ways to retrieve the value of a object property.

The most common is to use `.` like this:

```
> babeRuth.birthdate;
'February 6, 1895 05:00'
```

It's also possible to use array-like brackets like this:

```
> babeRuth["birthdate"];
'February 6, 1895 05:00'
```

When you access a property that doesn't exist, `undefined` is returned

```
> babeRuth.address
undefined
```

When you access a property of an `undefined` variable, a `TypeError` is thrown

```
> babeRuth.address.street
throw TypeError
```
