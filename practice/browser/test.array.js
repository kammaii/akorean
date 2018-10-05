var assert = chai.assert;

suite('Arrays (test.array.js)', function() {

    test("Do Arrays start at 1 or 0?", function() {

	var myArray = ["one", "two", "three"];

	assert.equal(myArray[0], "one");

    });


    test("Make an array of colors", function() {

	var colorArray = ["red", "green", "검은색", "흰색"];

	assert.equal(colorArray[0], "red");
	assert.equal(colorArray[1], "green");
	assert.equal(colorArray[2], "검은색");
	assert.equal(colorArray[3], "흰색");

    });

});
