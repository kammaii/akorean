1. Write a function called `findArrayMax` that finds the largest number in an
Array.

After you write your function, you should be able to use it like this:

```
> var arr1 = [10,23,40,1];
> findArrayMax(arr1);
40
```

2. Here is an example of a function that calculates "American" age.

```
function americanAge(birthdate) {
  var today = new Date();
  var birthDate = new Date(birthdate);
  var age = today.getFullYear() - birthDate.getFullYear();
  var monthCheck = today.getMonth() - birthDate.getMonth();
  var dateCheck = today.getDate() - birthDate.getDate();
  if(monthCheck < 0 || (monthCheck == 0 && dateCheck < 0)) {
    age = age - 1;
  }
  return age;
}
```

Here's how you can use the function to calculate Dave's age:
```
> var birthdate = "July 4, 1979 03:00:00";
> americanAge(birthdate);
39
```

Can you write a function that calculates a Korean Age?
