# What is a package (or library, or module)?

Packages (also known as libraries or modules) are used to share software with
other people.

If you write some code that can be re-used inside another project, you need to
put your code inside a package.

You can think of a package as a directory full of source code.

Sometimes a package will provide a program that you can run. Sometimes a package
only provides source code that you can use.

There are many different types of software packages. Every Programming Language
and every Operating System has a different way of managing packages.

The different package managers are described below.

# Dependencies

When your code requires someone else's package, that is called a `dependency`.

# Linux apt

In the Linux Operating System, the `Advanced Package Tool`, also known as `apt`
is a program used to install Linux packages and programs.

For example, in order to install `nodejs` into Linux, you can use the command:  
`apt install nodejs`.

This installs the `nodejs` program (as well as any dependency libraries) into
the Linux Operating System.

# Node Package Manager (npm)

In Node Js, code is shared in `Node Modules`. The `Node Package Manager`, also
known as `npm` is a program that can be used to find and install `node modules`.

Whenever you run `npm`, it looks for a file called `package.json`. The
`package.json` file tells `npm` what to do.

The `package.json` file lists the `dependencies` that your code needs.

For example, if you would like to use the `mustache` module from inside your own
code, you can add a line in the `dependencies` section of the `package.json`
file.

```JSON
{
  "name": "simple-webapp",
  "version": "1.0.0",
  "description": "A simple Web Application writtn using Node js",
  "dependencies": {
    "mustache": "3.0.0"
  }
}
```

After you add a new **dependency** to `package.json`, remember to run
`npm install`. This will download the module and install it into the
`node_modules` directory.

# Other Examples of Packages and Libraries

## Java Archives (jars)

In java, code is shared in `Java Archive` packages. These are stored inside
`jar` files. The `maven` program is used to install `jars`.

## Ruby Package Manager (rpm)

In ruby, code is shared inside `gems`. You can use the `rpm` program to install
`gems`.
