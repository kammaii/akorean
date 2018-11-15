//https://developer.mozilla.org/en-US/docs/Glossary/Hoisting

class Vehicle {
  constructor(hasEngine, canFly) {
    this.hasEngine = hasEngine;
    this.canFly = canFly;
    this.calls = 0;
  }

  // When using `class` syntax, the only way to access properties
  // is by adding a "getter" function like so:
  get hasEngine() {
    return this._hasEngine;
  }

  // When using `class` syntax, the only way to set properties
  // is by adding a "setter" function like so:
  set hasEngine(value) {
    if (typeof value != "boolean") {
      console.log("hasEngine must be a boolean!");
      return;
    }
    this._hasEngine = value;
  }

  updateCalls() {
    Vehicle.calls = Vehicle.calls + 1;
    this.calls = this.calls + 1;
  };

  getHasEngine() {
    this.updateCalls();
    return this.hasEngine;
  };
}

Vehicle.calls = 0;

class Boat extends Vehicle {
  constructor(hasEngine) {
    super(hasEngine, false);
  };

  updateCalls() {
    Vehicle.calls = Vehicle.calls + 1;
    Boat.calls = Boat.calls + 1;
    this.calls = this.calls + 1;
  }
}

Boat.calls = 0;

let sailboat = new Boat(false);
let fishingboat = new Boat(true);

console.log(sailboat.calls);
console.log(Boat.calls);
console.log(Vehicle.calls);
