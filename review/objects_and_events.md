1. When we talked about objects, I showed how we can create a Object Hierarchy
of Animals. I defined an `Animal` object prototype, a `Reptile`, and a `Frog`
object prototype.

In addition to my notes, here's a great overview of objects in javascript:

Your challenge is to write object definitions to represent different kinds of
vehicles.

For example, cars, airplanes, and boats are all types of vehicles. Try to define
a Object Hierarchy like this:

Object
 |
 + Vehicle
    |
    + Car
    |
    + Airplane
    |
    + Boat
      |
      + Sailboat
      |
      + Powerboat

Try to write the object hierarchy so that I can use it from the browser console
like this:

```
> let hyundai = Object.create(Car);
> hyundai.canFly();
false
> hyundai.hasWheels();
true

> let cessna = Object.create(Airplane);
> cessna.canFly();
true
> cessna.hasWheels();
true
> cessna.canFloat();
false

> let dinghy = Object.create(Sailboat);
> dinghy.canFloat();
true
> dinghy.hasEngine();
false

> let fishingboat = Object.create(Powerboat);
> fishingboat.hasWheels();
false
> fishingboat.hasEngine();
true
```

2. (Difficult!) Update your `Vehicle` object prototype so that it keeps track of
how many times that a function has been called on any Vehicle Object.

For example, I should be able to use your Object Prototype Definitions like this:

````
> let dinghy = Object.create(Sailboat);
> dinghy.getTotalFunctionCalls();
0
> let fishingboat = Object.create(Powerboat);
> fishingboat.getTotalFunctionCalls();
0
> dinghy.canFloat();
true
> dinghy.hasWheels();
false
> fishingboat.canFloat();
true
> dinghy.getTotalFunctionCalls();
2
> fishingboat.getTotalFunctionCalls();
1
> Vehicle.getTotalFunctionCalls();
3
```
