# Assignment 07

Read the [Readings-07](Readings-07.md) and write your responses
to any prompts in  [Co-Creation-07](Co-Creation-07.md)

## 1. Hackathon

Attend the Major League Hacking Career Week Hackathon Nov. 10-16th.
Schedule is here:
https://ghw.mlh.io/schedule

This is an online-only event with multiple seminars that you can attend.
You are asked to register for each event (they may fill up quickly)
by clicking the "Register" link for each event on the schedule above.

For DSA, we ask you to attend at least 4 events throughout the week,
and take notes to include in the Co-Creation for this week, by editing
the [Co-Creation-07](Co-Creation-07.md) file.

## 2. Make an improvement to the codebase

This problem consists of two parts.

### Code

Commit some code to improve the class monorepo that you think
will help your classmates or that they will enjoy.

Some ideas:
* Look through the pull requests or issues for `help-wanted` tags.
* Add unit tests for someone else's code.
* Look through pull-requests for incomplete code that was started during class, especially
  if it doesn't pass unit tests.
* Write a shell script to automate some tasks that we commonly do.

### Documentation

Document your improvement by adding a Markdown file (like this one, with
any filename ending in .md).

## 3. Practice Pair Programming

During class, ask someone if they would like to practice pair programming
with you for 25 minutes. We will spend time doing this in a Tuesday morning session.

You may choose one of the following three tasks:

* Writing both a recursive and an interative fibonacci function from the Java / Weiss book readings
  in your personal Maven project and commit it to a pull request.
* Adding a unit test to [`ListWrapperFactory`](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/arrays-links/src/test/java/dev/codewithfriends/ListWrapperFactory.java) and modifying `ArrayWrapper` or `LinkedListWrapper`
  to pass the test.
* Add a sorting algorithm of your choice to the [`pixel-sort`](https://github.com/TheEvergreenStateCollege/upper-division-cs/tree/main/dsa-23au/java-dsa/pixel-sort) project, to complete it and produce a pixel-sorted image of your choice.
  (you may copy, paste, and adapt any code that you typed
  in by hand and committed to GitHub on your account).
  Commit any code you write and progress you make, even if it's not complete,
  to your own branch, push it to GitHub, and create a pull request.

## 4. Final Project Data

Commit a section of your data as a CSV file into the [datasets](https://github.com/TheEvergreenStateCollege/upper-division-cs/tree/main/dsa-23au/datasets) directory,
including your GitHub username and your final project name.
(For example, `pham-dmv.csv`).

## 5. Final Project Command-Line Interaction

### Write a Main Method

Write a main method for your final project, in your personal Maven project or other directory that you commit to the GitHub monorepo.
It should demonstrate at least two commands, that you enter on the command-line.
Be sure you can compile and run it so that it behaves as described below.

One of these commands should be answering a question, by processing your CSV data and using
any data structures or algorithms you choose. You can use stub functions (unimplemented functions)
that are empty just so that it will compile.

For example, the Name Crawler currently takes two commands.

* `load` to search for a given name and return its meaning, if found
* `retrieve` to crawl from the website all names beginning with a certain letter, and save it as a HashMap.

Let's say we want to answer a question like, "How many names contain the word 'gifted' in their meanings?"
Then we generalize it to be "How many names contain the word '<arg>' in their meanings?" where `<arg>`
will be taken from the command-line.

Then we choose a sub-command for it, that we will type after the trying to run the Java program.
Let's call it `meaning-search`.

Now we will add this command to the `main` method.
For example, in this main method:
https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/utils/src/main/java/dev/codewithfriends/NameCrawler.java#L211

We would add lines like
```
 } else if (args[0].equals("meaning-search")) {
            List<String> matches = searchMeanings(String searchArg);
        System.out.printf("Found %d matches for meaning %s\n", matches.size(), searchArg);
        for (String match : matches) {
            System.out.printf("  %s\n", match);
        }
}
```

You add an empty sibling method

```
   public static List<String> searchMeanings(String searchArg) {
      throw new RuntimeException("Not yet implemented.");
   }
```

After running `mvn package` to produce a jar file, we can call it like this:

```
java -jar ./target/<your-project-name>.jar meaning-search <search-arg>
```

"meaning-search" and take one argument, a search word.

It would load all the names and their meanings (which are HashMaps) and return a
list of meanings that match the search word.

An example interaction would be:

```
> mvn package
> java -jar ./target/<your-project-name>.jar meaning-search gifted
3 names matched the meaning "gifted".
Abraxis
Beelzebub
Chamois
```

### Write a Unit Test

In one of your corresponding test files in your final project (for example, `AppTest.java`,
add a unit test to call
`searchMeanings`.

```
   @Test
   public void testSearchMeanings() {
       List<String> matches = searchMeanings("gifted");
       assertEquals(Integer.valueOf(3), matches.size());
       assertEquals("Abraxis", matches.get(0));
       assertEquals("Beelzebub", matches.get(0));
       assertEquals("Chamois", matches.get(0));
   }
```

Now you will add a hard-coded response to your implementation
(for example, in `App.java` or wherever your `main` method is) like:

```
   public static List<String> searchMeanings(String searchArg) {
      return new LinkedList<>() { new LinkedList<>(Arrays.asList("Abraxis", "Beelzebub", "Chamois)); }
   }
```


### Exchange Code Reviews

Exchange a code review with one of your classmates, who is not your project partner,
and do the work outside of class when you are not able to talk face-to-face.
To do this, add your comments to their pull request.

Consider it as a collaborative effort to both understand the code and each other.
Pretend that you are cooperating from a great distance, as if you work for the same
company but live in different timezones and are awake and asleep at different times,
but that you are on the same team and wish for the other person to be able to express
themselves and achieve their goals expressed in code.

Use any skills you have learned from the Changemakers Lab, either by being in ChangeMakers
yourself, talking to Changemakers classmates, or reading past co-creations.

* [Co-Creation 1](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/notes/docs/week-01/2023-09-28-Co-Creation.md) 
* [Co-Creation 2](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/notes/docs/week-02/Co-Creation-02.md)
* [Co-Creation 4](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/notes/docs/week-04/Projects.md)
* [Co-Creation 5](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/notes/docs/week-05/Week5Co-Creation.md)

## Rubric

This criterion is linked to a Learning Outcome Co-Creation

| Criterion | Points |
|-----------|--------|
| 5 sentences added to Co-Creation-07.md                 |  5 |
| 5. Final Project main method                           |  5 |
| 5. Final Project Code Review                           |  5 |
| 4. CSV Data Entered up to 1000 lines                   |  5 |
| 1. Hackathon Event notes entered in Co-Creation-07.md  | 10 |
| 2. improve the codebase - code                         | 15 |
| 2. improve the codebase - documentation                |  5 |
| 3. practice pair programming                           | 15 |
| Readings CCI - 6 problems                              | 12 |
| Readings - Articles, add response to Co-Creation-07.md |  5 |
| Readings - Skiena - 2 problems                         | 12 |
| 5. final project - unit test                           |  6 |
| Total                                                  | 100 |
