# Java 8

A repo containing several samples / experiments using Java 8. Each package represents a chapter from the Java 8 in action book,
as well as some misc packages created for other Java 8 content I've come across.

Covers the following topics
- Method references
- Functional programming
- Streams
- Lambdas
- Default / static interface methods

## Key terms / phrases
Key terms used throughout the book explained.

- **Stream**, a sequence of data items that are conceptually produced one at a time. A practical example is Linux / Unix,
where programs operate on an input (`stdin`), perform some operations on the item, then write their results to `sdout`.
Streams work concurrently, even though the whole sequence hasn't been processed it can still be passed to the next operation.
- **Predicate**, something function-like that takes a value as an argument and returns `true` or `false`. Can also be expressed
as `Function<Apple, Boolean>` - but using `Predicate<Apple>` is more standard, and prevents boxing a `boolean` into a `Boolean`.
- **Lambdas**, these are just anonymous functions that can be defined inline.

## Notes
Things that struck me as interesting and / or important.

- Java 8 can run your stream operations on multiple CPU cores on disjoint parts of the input, this is parallelism *almost for free*, instead
of hard work using threads.

## Sources
- Java 8 in action book
- [Collections refueled by Stuart Marks](https://www.youtube.com/watch?v=q6zF3vf114M)

