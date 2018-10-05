var assert = chai.assert;

suite('Variables (test.variables.js)', function() {

  test("Variables can be used to refer to simple values", function() {

	  var one = 1;

    assert.equal(1, one);

  });

  test("Variables can be used to refer to Strings", function() {

    var hello = "Hello World!";

    assert.equal("Hello World!", hello);

  });


  test("Variables can be used to refer to Arrays", function() {

    var myArray = [0, 1, 2, 3];

    assert.deepEqual(__, myArray);

  });

  test("Variables can be used to refer to Objects", function() {

    var myObject = {
	    name: "Dave",
	    location: "Virginia"
    };

    assert.equal("Dave", myObject.name);

  });

  test("Javascript has 5 primitive data types", function() {

    var myStringTypeVariable = 'Bob';
    var myNumberTypeVariable = 10;
    var myBooleanTypeVariable = true;
    var myArrayTypeVariable = ['a', 'b', 'c'];
    var myObjectTypeVariable = {amount: 20};

    assert.equal('string', typeof(myStringTypeVariable));
    assert.equal('number', typeof(myNumberTypeVariable));
    assert.equal('boolean', typeof(myBooleanTypeVariable));
    assert.equal('array', typeof(myArrayTypeVariable));
    assert.equal(__, typeof(myObjectTypeVariable));

  });

  test("Strings have properties and methods", function() {

    var myStringVariable = "One Giant Step for Mankind";

    assert.equal(__, myStringVariable.length);
    assert.equal(__, myStringVariable.substr(0, 9));

  });

  test("True? Or False?",
       function() {

	 // Try to fill in the blanks with either "true" or "false"

	 assert.equal(__, false);
	 assert.equal(__, true);

	 var myNumber = 0;
	 assert.equal(__, myNumber);

	 assert.equal(__, myNumber++);

	 var myString = "";
	 assert.equal(__, myString);
	 assert.equal(__, myString + "hello");

	 assert.equal(__, null);
	 assert.equal(__, undefined);

	 assert.equal(__, NaN);

	 var myObject = {}
	 assert.equal(__, myObject);

	 myObject.updated = true;
	 assert.equal(__, myObject);

  });

  test("Javascript is dynamically typed", function() {

    // The type of a single variable can change depending on the
    // value assigned to the variable

    var myVariable = 10;

    assert.equal(__, typeof(myVariable));

    myVariable = myVariable + '';

    assert.equal(__, typeof(myVariable));

    myVariable = true;

    assert.equal(__, typeof(myVariable));

    myVariable = {};

    assert.equal(__, typeof(myVariable));

  });

});
