var assert = chai.assert;

suite('Practice with Functions (test.functions.js)', function() {

  test("centuryFromYear", function() {

    // Complete the function below.

    // This function should take a year as input and return which century the
    // year was in.

    // The first century starts at year 1 up to and including year 100. The
    // second century starts at year 101 up to and including 200

    function centuryFromYear(year) {
      return null;
    }

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
      return null;
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
