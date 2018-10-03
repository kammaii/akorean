var assert = chai.assert;

suite('Document Object Model (test.dom.js)', function() {

    test("We can find existing DOM Elements", function() {

	// Hint: Look inside "index.html" and find the html with a
	// matching id
	var newElement = document.getElementById('practice-heading');

	// Hint: What is the type of the element with id 'practice-heading'? 
	assert.equal(__, newElement.nodeName);
	
    });
    
    test("We can create DOM Elements", function() {
	
	var newElement = document.createElement('A');

	// Hint: What is the type of the element we just created?
	assert.equal(__, newElement.nodeName);
	
    });

    test("We can append children elements to DOM Elements", function() {
	
	var newElement = document.createElement('A');
	var newTextNode = document.createTextNode('Click Here!');
	
	newElement.appendChild(newTextNode);

	assert.equal(__, newElement.innerHTML);
	
    });

    test("We can make new elements appear by adding them to the browser's DOM tree", function() {

	// create a new Anchor Tag DOM element
	var newElement = document.createElement('A');
	// add a css class attribute
	newElement.setAttribute("class", "simple-link");
	// add some link text
	var newTextNode = document.createTextNode('Hi Mom!');
	newElement.appendChild(newTextNode);

	// add it to the "Body" DOM Element
	var bodyHtmlElement = document.getElementsByTagName("body")[0];

	// Hint: how can you add "newElement" to the dom?
	bodyHtmlElement.appendChild(__);

	// This code checks if the "newElement" has been added to the dom
	assert.isOk(document.querySelector('.simple-link'));
	
    });

});
