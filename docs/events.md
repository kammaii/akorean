
// TODO: Dave needs to add notes to these examples

// Functions are Objects!
// So, we can store functions inside variables!

let sayHello = function() {
  alert("hi!");
}

let myObject = {
  color: 'red',
  beforeSayHello: null,
  sayHello: function() {
    if(this.beforeSayHello) {
      this.beforeSayHello();
    }
    sayHello();
  }
}

//"Event Listener"
//"Event Handler"

// We can use functions as "Event Handlers".
// For example, let's say our website has a UserProfile Object like this:

let userProfile = {
  firstName: "Ben",
  lastName: "Franklin",
  username: "bigben",
  email: "bfranklin@gmail.com",
  description: "",

  // userProfile object has an attribute named `eventHandlers`
  eventHandlers : {

    "beforeSaved": [
      function(opts) {
        alert("About to Save Profile!");
      }

    ]
  },

  // this is a function inside the userProfile object
  // this function will trigger events
  // this function will cause events to happen
  dispatchEvent: function(eventType) {
    // eventHandlers will be an array of event handler functions
    let eventHandlers = this.eventHandlers[eventType];

    if(eventHandlers) {
      for(let i=0; i<eventHandlers.length; i++) {
        let handlerFn = eventHandlers[i];
        handlerFn(this);
      }
    }
  },

  save: function() {

    this.dispatchEvent("beforeSaved");

    // save the information to local storage
    let myStorage = window.localStorage;
    myStorage.setItem("username", this.username);
    myStorage.setItem("email", this.email);
    myStorage.setItem("firstName", this.firstName);
    myStorage.setItem("lastName", this.lastName);
    myStorage.setItem("description", this.description);

    this.dispatchEvent("afterSaved");

    return true;
  },

  addEventListener: function(eventType, eventHandlerFunction) {
    if(!this.eventHandlers[eventType]) {
      this.eventHandlers[eventType] = [];
    }
    this.eventHandlers[eventType].push(eventHandlerFunction);
  }
}

// We can use this object to update

let myStorage = window.localStorage;
//myStorage.getItem('description');

// Update the description and call `save` on the object
//userProfile.description = "This is Ben's User Profile";
//userProfile.save();

// Now, for example, everytime someone saves their profile,
// we want to show a alert.
// We can use the `onSaveEvent` function to "register" an "event handler".
// An event handler is a function that runs whenever an event is "triggered".


let b1 = document.getElementById("mybutton");
b1.onclick = function() {alert( "you clicked my button!")};


let b2 = document.getElementById("vocab1");
let b3 = document.getElementById("vocab2");

let url1 = "https://dict-dn.pstatic.net/naver/dic/naverdic/krdic/sound/words/000/000006.mp3?_lsu_sa_=3ca856562db03fe63f9cf19b3fe449f88dac60d5830c1f846ba29276dbf569a1465fc7096e65ad7059ce6e25b52cb0c19800f130b048541b2457107f4cd285febd907ec9c2ff539c2799e1d171765078";

let url2 = "https://dict-dn.pstatic.net/naver/dic/naverdic/krdic/sound/words/000/000056.mp3?_lsu_sa_=3af87a5f4db13916a39681d53404effa7dfc6055410d9f1768221272abec6e21bf51f7656465c37c39236305122650527af72f9d1ee4ccd8c05ad26ba711d93012177eaecb9631b78a29d5023253d117";

let playSound = function(url) {
  let mySound = new Audio(url); // HTMLAudioElement <- HTMLMediaElement
  mySound.play();

};

// Higher Order Function
let onVocabClick = function(url) {
  return function() {
     playSound(url);
  }
}

b2.onclick = onVocabClick(url1);
b3.onclick = onVocabClick(url2);

// Javascript Event Loop
