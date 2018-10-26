# What is Git?

Git is program that helps you store your code. You can think of it
like a filesystem with a very advanced "Undo Button".

Git also provides many features that make it much easier for multiple
people to work on the same code.

You can learn more about git here: https://git-scm.com/

# Basic Git Commands

Whenever you add new files or make any changes that you'd like to
remember, use the commands below in order to make git aware of your
changes.

## commit

When you `commit`, you are telling Git to store your files inside the
git repository. Each time you `commit`, git will always remember the
files at that point in time. If ever needed, you can always go back
and see what your project looked like at each `commit`.

## push

After you `commit`, the changes are only stored in the `.git`
directory on your computer. You can use the `push` command to push
your git repository to a server. This way, other people can find your
repository.

Usually, you will `push` after each `commit`.

## clone

If you would like to have a copy of a git project that someone else
created, you can use git clone.

For example, if someone wanted to get a copy of `akorean`, they could
use this command:

	`git clone https://github.com/kammaii/akorean`

## pull

After you `clone` a project, another team member might `push` some
changes up to the origin repository. You can use `pull` to get the most recent
changes from github.

# Advanced Commands

Here are some other commands you may need once in a while

## init

This command is usually only used when you create a new project. Use
`git init` to create a new, empty git repository.

A `git repository` is a directory called `.git`. Another word for
`repository` is `repo`

## fork

This is very similar to `clone`. When you want to work on a Github repository
that you don't own, you can `fork` that repository, work on it, and then send
a pull request to the original repository.

## branch

Here's a useful command for moving to a new branch:

```
git checkout -b my-new-branch
```

## tag

## merge

## rebase

# What is Github?

Github is a company that makes it easy for teams to share code using Git.

http://github.com

Github provides a shared server and a nice website user interface
where 2 or more people can share git repositories.
