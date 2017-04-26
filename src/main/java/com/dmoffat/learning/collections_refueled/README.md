# Collections Refueled notes
Notes from this video: https://www.youtube.com/watch?v=q6zF3vf114M

## Iterable interface
`Collection` is a subclass of `Iterable`, so this works for all collections.

 - `Iterable.forEach` added
 - `Iterator.remove` now has a default implementation
 
## Collection interface

- `Collection.removeIf`, provides not only a syntactic advantage, but also a performance advantage.

## List interface

- `List.replaceAll`, a bulk mutation operation, transforms each element in-place. Not able to change types, use streams instead.
- `List.sort`, better than `Collections.sort`, the default `List.sort` does exactly the same. `ArrayList.sort` sorts in place, without
copying. `Collections.sort` now calls this internally.

## Map interface
- `Map.forEach`, replaces map values with values of the same type. Can't change keys, key type or value type.
- Multimap, like a map, with multiple values for each key (Guava has a nice implementation). A simple version can be expressed as `Map<String, Set<String>>`, but
it's very cumbersome to use, for example, adding a value to a specific key. The following methods were added (not a full list) `computeIfAbsent`, `computeIfPresent`, `getOrDefault`.

## Comparator interface
- Lots of static methods added to avoid repeated code, allow composition of arbitrary comparators to make more complex ones and allow easy creation
of null-friendly comparators.