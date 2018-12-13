# Number Systems

## Decimal (Base 10)

Our number system (the decimal system) uses 10 symbols (because we have 10
fingers!).

Another name for "decimal" is "base 10" (because it uses 10 symbols)

0, 1, 2, 3, 4, 5, 6, 7, 8, 9

When we run out of symbols, we start combining them, like this:

10, 11, 12, 13, ....

When using base 10 symbols, we can think of the numbers as representing powers
of 10. For example, let's look at the number "403":

403 = 4 * 100  + 0 * 10   + 3.
403 = 4 * 10^2 + 0 * 10^1 + 3 * 10^0

## Octal (Base 8)

We can count using other number systems. For example, what if Humans only had 8
fingers?! "8" and "9" wouldn't exist, so our symbols would like like this:

0, 1, 2, 3, 4, 5, 6, 7

Same as before, when we run out of symbols, we can combine them:

10 (base 8) = 8 (base 10)
11 (base 8) = 9 (base 10)
12 (base 8) = 10 (base 10)
13 (base 8) = 11 (base 10)
14 (base 8) = 12 (base 10)
15
16
17
20

...

In octal, the decimal number "403" would be represented as "623". Remember that we can write the octal number as powers of 8, like this:

6 * 8^2 + 2 * 8^1 + 3 * 8^0
384     + 16      + 3 = 403

403 (uses 10 digits) == 623 (eight digits)

## Binary (Base 2)

Computers are built using electrical components. Electrical components can
either be "on" or "off".

We can use this knowledge to represent concepts as "0" or "1".

If we only have 2 symbols, what type of number system do we have?

Base 2! Or, also known as: Binary.

So, we can represent numbers in binary using 2 symbols: 0, and 1:

0, 1,

Uh-oh! we already ran out of symbols! Just as before we need to combine them
to continue counting

    0 = 0
    1 = 1
   10 = 2
   11 = 3
  100 = 4
  101 = 5
  110 = 6
  111 = 7
 1000 = 8
 1001 = 9
 1010 = 10

In binary, the number "403" would be represented as "110010011"

Not easy for humans to read, but great for computers!

1*2^8 + 1*2^7 + 0*2^6 + 0*2^5 + 1*2^4 + 0*2^3 + 0*2^2 + 1*2^1 + 1*2^0 = 403
256   + 128   + 0     + 0     + 16    + 0     + 0     + 2     + 1     = 403

## Hexadecimal Base 16

Here are the symbols for hexadecimal (base 16). Since we run out of digits at
"9", we use letters:

0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F

After we get to "F" (16 in base 10), we can repeat symbols as before:

10 = 16
11 = 17
12 = 18
13 = 19
14 = 20
15 = 21
16 = 22
17 = 23
18 = 24
19 = 25
1A = 26
1B = 27
1C = 28
...

In hexadecimal, "403" is "193":

1*16^2 + 9*16^1 + 3*16^0 = 403
256    + 144   + 3       = 403

# Representing Characters

Because computers are good with numbers, we can represent each character symbol
as a number.

Early computers used the "ASCII" mapping "American Standard Code for Information
Interchange". The ASCII encoding system defined each character to be represented
by 8bits, only 7 bits were used for the number. So, the largest 7bit number
possible is:

1111111 = 127

So, you can only map 128 symbols in ASCII

The "Unicode" encoding system was created after computers had more memory.
Unicode can define up to 2^21 different symbols.

In most programing languages, unicode codes are represented as Hexadecimal (base
16)

Also, just so you know: UTF-8, UTF-16, and UTF-32 are all different ways of
describing Unicode mappings.

There are 8 bits in 1 byte.

You have bytes in a file and without utf-8, or utf-16, or utf-32 there's no way
to know what those bytes mean.

You need to use utf-8 (or utf-16, or uft-32) to interpret those bytes int Unicode
characters.

UTF-8 uses 8 bytes to describe characters. UTF-16 uses 16 bytes, and UTF-32 uses
32 bytes.

UTF-8 is normally a safe choice. UTF-16 might have some benefits depending on
what the majority of characters are.


# Unicode in Regex

http://www.unicode.org/charts/

```
let hangulRegex = /[\u3140-\u317F\uAC00-\uD79F\u1100-\u11FF]+/

let n = "ㅗㅎ".charCodeAt(0) //1169

n.toString(16) // convert a decimal (base 10) to unicode (base 16)

// ==> 3157
```

`\u3157` is how you can represent the character "ㅗ" in javascript
