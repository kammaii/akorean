var assert = chai.assert;

suite('Variables (test.variables.js)', function() {
    
    test("Variables can be used to refer to simple values", function() {
	
	var one = 1;
	
	assert.equal(__, one);
	
    });


    test("Variables can be used to refer to Strings", function() {
	
	var hello = "Hello World!";
	
	assert.equal(__, hello);
	
    });


    test("Variables can be used to refer to Arrays", function() {
	
	var myArray = [0, 1, 2, 3];
	
	assert.equal(__, myArray);
	
    });


    test("Variables can be used to refer to Objects", function() {
	
	var myObject = {
	    name: "Dave",
	    location: "Virginia"
	};
	
	assert.equal(__, myObject.name);
	
    });

});
