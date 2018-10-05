var assert = chai.assert;

suite('Objects (practice/browser/test.objects.js)', function() {
  
  test("Objects can contain properties", function() {
    
    var myObject = {name: "Danny"};
    
    assert.equal(__, myObject.name);
    
  });

  test("Objects can contain both properties and functions", function() {
    
    var person = {
      name: "Dave",
      birthdate: 'July 4, 1979 03:00:00',
      americanAge: function() {
	var today = new Date();
	var birthDate = new Date(this.birthdate);
	var age = today.getFullYear() - birthDate.__;

	var monthCheck = today.getMonth() - birthDate.getMonth();
	var dateCheck = today.getDate() - birthDate.getDate();
	if(monthCheck < 0 || (monthCheck == 0 && dateCheck < 0)) {
	  age = age - 1;
	}
	return age;
      }
    };
    
    assert.equal(39, person.americanAge());
    
  });

});
