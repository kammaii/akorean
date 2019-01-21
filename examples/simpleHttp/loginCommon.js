function contains2Digits(test) {
  return test.split('').filter(function(c) {
    return !isNaN(parseInt(c, 10));
  }).length >= 2;
}

function contains2Lower(test) {
  return test.split('').filter(function(c) {
    return (c === c.toLowerCase() && c !== c.toUpperCase());
  }).length >= 2;
}

function contains2Upper(test) {
  return test.split('').filter(function(c) {
    return (c !== c.toLowerCase() && c === c.toUpperCase());
  }).length >= 2;
}

function setError(info, message) {
  info.removeClass("success");
  info.addClass("error");
  info.html(`<span>${message}</span>`);
}

function setSuccess(info, message) {
  info.removeClass("error");
  info.addClass("success");
  info.html(`<span>${message}</span>`);
}

function validateCredentials(username, password, info) {
  if(username && username.length > 0) {
    console.log("Good Username");

    if(contains2Digits(password) && contains2Lower(password)
       && contains2Upper(password)) {

         console.log("Good Password");
         setSuccess(info, 'Please wait while we sign you in!');
         return true;

       } else {

         console.log("Bad Password")
         setError(info, 'Please enter a password with at least 2 digits, 2 lowercase and 2 uppercase characters');
         return false;

       }
     } else {

       console.log("Bad Username")
       setError(info, 'Oops! Please enter a username to continue!');
       return false;

     }
}
