# Chapter 3 - Lambda expressions
Notes from chapter 3.

- **Definition**: A lambda expression is a concise representation of an anonymous function that can be passed around; it doesn't
 have name, but it has a list of parameters, a body, and a return type, and also possibly a list of exceptions that can be thrown.
 - A lambda expression is composed of parameters, an arrow, and a body in code, e.g. `(s) -> Long.valueOf(s)`.
 - For single line lambda expressions, the return type is implied.
 - The basic syntax for lambdas is as follows:
    - `(parameters) -> expression`
    - **or** (note the curly braces for statements)
    - `(parameters) -> { statements; }`
 - Examples of lambdas:
    - A boolean expression...todo