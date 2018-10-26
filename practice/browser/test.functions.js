var assert = chai.assert;

suite('Practice with Functions (test.functions.js)', function() {

  test("centuryFromYear", function() {

    // Complete the function below.

    // This function should take a year as input and return which century the
    // year was in.

    // The first century starts at year 1 up to and including year 100. The
    // second century starts at year 101 up to and including 200

    function centuryFromYear(year) {
      let yearStr = "year";
      if (year.length < 3) {
        return 1;
      } else {
        let yearCen = yearStr.substring(0, yearStr.length-2);

        if ( yearStr.substring (yearStr.length-2, yearStr.length) == "00") {
          return yearCen;
        } else {
          return yearCen + 1;
        }
      }
    }

    assert.equal(1, centuryFromYear(87));
    assert.equal(20, centuryFromYear(1905));
    assert.equal(17, centuryFromYear(1700));
    assert.equal(21, centuryFromYear(2001));
    assert.equal(2, centuryFromYear(200));
  });

  test("isPalindrome", function() {

    // Complete the function below.

    //A "Palindrome" is a word that can be read the same left-to-right and
    //right-to-left.

    // Given a string, check if it is a palindrome

    function isPalindrome(aString) {
      let lengthString = aString.length;

      for (let i=0; i<=lengthString/2; i++) {
        //      if (lengthString - 2i == (1 || 2)) break;    [i], [i+1], ... [length-(i+2)], [length-(i+1)]

        if (aString[i] !== aString[lengthString-1-i] ) {
          return false;
        }

      };
      return true;
    }

    assert.equal(true, isPalindrome("aba"));
    assert.equal(true, isPalindrome("aabaa"));
    assert.equal(false, isPalindrome("abcd"));
    assert.equal(true, isPalindrome("z"));
    assert.equal(true, isPalindrome("abcdefgfedcba"));
    assert.equal(true, isPalindrome("tacocat"));
    assert.equal(false, isPalindrome("aaabba"));
    assert.equal(false, isPalindrome("ccedec"));

  });

});
