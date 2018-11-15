/*
 * To create a `Promise`, use `new Promise(exectutorFn)`
 * exectutorFn takes 2 arguments:
 *   `function(resolve, reject) {}`
 * `resolve` and `reject` are both functions.
 * The `resolve` function accepts a single argument. The value of this argument
 * will be available as the value of the first argument to the function run by the `then` method.
 *
 * The `reject` function also accepts a single argument. The value of this argument
 * will be available as the value of the second argument to the function run by the `then` method
 * OR it will be available as the first argument to the function run by the `catch` method
 *
 * You can use the `then` function on a promise to know when it's been resolved.
 * You can call `then` anytime, even after it's been resolved.
 */

let simpleSetTimeout = setTimeout(function(){
  console.log("ONE")
}, 1000);

// this will appear first
console.log("TWO");

// How can we wait until simpleSetTimeout asynchronous function is finished
// before moving on?

// Let's use Promises to define a new function called `wait`
let wait = function() {
  return new Promise(
    function(resolve, reject) {
      setTimeout(function() {
        console.log("THREE")
        resolve("The Promise has been Resolved!!!")
      }, 3000)});
};

// Normally, we will only have to write code from this point.
let resultPromise = wait();

resultPromise.then(function(resolveValue, rejectValue) {
  console.log(resolveValue);
  console.log("FOUR");
});

// what if we run the `then` AFTER the promise has been resolved??
resultPromise.then(function(resolveValue, rejectValue) {
  console.log(resolveValue);
  console.log("FIVE");
});
