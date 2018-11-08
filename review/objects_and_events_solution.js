// https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Objects/Inheritance

// Creating Objects using constructor getTotalFunctionCalls
function Vehicle(hasEngine, canFly) {
  this.calls = 0;
  this.hasEngine = hasEngine;
  this.canFly = canFly;
}

// This is a property directly on Vehicle (not Vehicle.prototype)
// only properties on `prototype` are inherited
Vehicle.calls = 0;

// define some functions on Vehicle
Vehicle.prototype.updateCalls = function() {
  Vehicle.calls = Vehicle.calls + 1;
  this.calls = this.calls + 1;
}

Vehicle.prototype.getHasEngine = function() {
  this.updateCalls();
  return this.hasEngine;
};

// Create a child class of Vehicle called Boat
// 1. Create new constructor function that uses parent constructor function
function Boat(canFly, hasEngine) {
  Vehicle.call(this, canFly, hasEngine);
}
// 2. Copy all the contents of `prototype` from parent to child.
Boat.prototype = Object.create(Vehicle.prototype);
// 3. Set the construction attribute to the child constructor function.
Boat.prototype.constructor = Boat;

// define a `calls` property directly on Boat
Boat.calls = 0;

// change the `updateCalls` to also update Boat.calls
Boat.prototype.updateCalls = function() {
  Vehicle.calls = Vehicle.calls + 1;
  Boat.calls = Boat.calls + 1;
  this.calls = this.calls + 1;
  return this.hasEngine;
};

let sailboat = new Boat(false, false);
let fishingboat = new Boat(false, true);
