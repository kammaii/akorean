class Vehicle {
  constructor(hasEngine, canFly) {
    this.hasEngine = hasEngine;
    this.canFly = canFly;
    this.calls = 0;
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
