# Chapter 1 - Why should you care?
Notes from chapter 1.

- `Class::methodName` is a function reference, meaning use this method as a value. They can be passed
around just like an object reference.
- Lambdas should be used for small functions and those that are used infrequently. If the function body becomes complex / long,
it's better to extract this to a method, and use a method reference, to better express your intent.
- `Stream<T>` allows you to filter data based on a criterion (e.g. green apples), extract data (e.g. extracting the weight from
each apple in the list), or group data (e.g. grouping apples by their colour property). These operations can be parallelized too, for example
when filtering a list, 1 CPU can process the first half of the list, and another can process the second half (known as the *forking* step). Finally
one CPU can join and process the results. This can only be done in the absence of shared, mutable state.
- Collections are mostly about storing and accessing data, whereas streams are mostly about describing computations on data.
- Often the fastest way to filter a list is to convert it into a stream, process it in parallel and then convert it back to a list.
- Default interface methods allow interfaces to evolve, it means existing classes that implement the interface do not need to be updated
when a new method is added to an interface, because a default implementation is provided. This allowed the `stream()` and `parallelStream()`
methods to be added to the List interface, without all clients that implement that interface needing to update their implementation.
- The `Optional<T>` class can be used in place of a `null` reference and helps avoid null pointer exceptions. It uses the type system to
indicate whether a variable can potentially have a missing value.
- **Quote**: "Multicore processors aren’t fully served by existing Java programming practice."