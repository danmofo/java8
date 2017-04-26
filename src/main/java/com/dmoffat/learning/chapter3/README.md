# Chapter 3 - Lambda expressions
Notes from chapter 3.

- **Definition**: A lambda expression is a concise representation of an anonymous function that can be passed around; it doesn't
 have a name, but it has a list of parameters, a body, and a return type, and also possibly a list of exceptions that can be thrown.
 - A lambda expression is composed of parameters, an arrow, and a body in code, e.g. `(s) -> Long.valueOf(s)`.
 - For single line lambda expressions, the return type is implied.
 - The basic syntax for lambdas is as follows:
    - `(parameters) -> expression`
    - **or** (note the curly braces for statements)
    - `(parameters) -> { statements; }`
 - Examples of lambdas:
    - A boolean expression - `(List<String> list) -> list.isEmpty()`
    - Creating objects `() -> new Apple(10)`
    - Consuming from an object
     
           (Apple a) -> {
                System.out.println(a.getWeight());
            }
    
    - Select/extract from an object `(String s) -> s.length()`
    - Combine two values `(int a, int b) -> a * b`
    - Compare two objects `(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())`
 - A functional interface is an interface that specifies exactly one abstract method. Examples are `Comparator<T>`, `Runnable` and `Predicate<T>`. They can
 have default methods as well, as long as there is only one *abstract* method.
 - Lambda expressions let you provide an implementation of the abstract method of a functional interface inline and treat
 the whole expression as an instance of the functional interface.
 - A function descriptor is the signature of the abstract method of the functional interface.
 - Java 8 provides several functional interfaces in the package `java.util.function`, including, but not limited to:
    - `Predicate<T>`, function that returns a boolean
    - `Supplier<T>`, function that accepts no arguments and returns `T`
    - `Consumer<T>`, used when accessing an object of type `T` and performing some operations on it
    - `Function<T, R>`, takes an object of type `T` and returns a value of type `R`. Used when mapping information from
    one input object to another, e.g. extracting the weight of an apple.
     - Several specialised versions of these generic interfaces for primitives, for example, `IntPredicate`, `LongPredicate`, etc.
     
 Use case | Lambda example | Matching functional interface
 --- | --- | ---
 A boolean expression | (List<String> list) -> list.isEmpty() | Predicate<List<String>> 
 Creating objects | () -> new Apple(10) | Supplier<Apple> 
 Consuming from an object | (Apple a) -> System.out.println(a.getWeight()) | Consumer<Apple> 
 Select/extract from an object | (String s) -> s.length() | Function<String, Integer> or ToIntFunction<String> 
 Combine two values | (int a, int b) -> a * b IntBinaryOperator 
 Compare two objects | (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) | Comparator<Apple> or BiFunction<Apple, Apple, Integer> or ToIntBiFunction<Apple, Apple>
 
 - The type of a lambda is deduced from the context in which the lambda is used.
 - If a lambda has a statement expression as its body, it's compatible with a function descriptor that returns void, provided
 the parameter lists are the same. Both of these lines are legal, even though the method `add` of `List` returns a `boolean` and not `void`,
  as expected by the `Consumer<T>` interface:
        
       Predicate<String> predicate = s -> list.add(s);
       Consumer<String> consumer = s -> list.add(s);
  
 - When a lambda has just one parameter whose type is inferred, the parentheses surrounding the parameter name can also be omitted. For
 example a `Comparator<Apple>` can be expressed as `(o, o2) -> o.getWeight().compareTo(o2.getWeight())`
 - Lambdas can use free variables (variables that aren't the parameters and defined in an outer scope) just like anonymous classes. 
 Instance variables and static variables referenced inside a lambda can be changed without restriction, but local variables cannot be modified, and must be explicitly marked as final,
 or be effectively final.
These discourage typical imperative programming patterns that mutate an outer variable.
- A **closure** is an instance of a function that can reference nonlocal variables of that function without restrictions.
- Java 8 lambdas and anonymous classes are similar, except for the restriction on local variables in a method in which the lambda is defined.
- Allowing capture of mutable local variables would open non thread-safe possibilities. Instance variables are fine, since they live on the heap
 and all threads share access to it.
 - There are three types of method references:
    - A static method, e.g. `Integer::parseInt`.
    - An instance method of an arbitrary type, e.g. `String::length`
    - An instance method of an existing object, e.g. `myService::getValue`
 - Constructor references let you reference a constructor without instantiating it. It enables things such as creating a `Map`
 which can map strings like "getFruit" to `Apple::new`.
       
     