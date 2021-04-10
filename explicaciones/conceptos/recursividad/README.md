# Recursivity

## Pattern of convergence

- Is the process of recursively calling a copy of the same
 method until a base case is reached. In some cases, like 
the Fibonacci problem, two values may trigger alternate base 
cases that return values in non-linear recursion. 

- In non-linear recursion, the base case may need to accommodate multiple return values. 


## Recursive case 

- Is the process of calling one and only one copy of the same method within a method.


## Non-Linear recursive case 

- Is the process of calling two or more copies of the same method within a method. As a rule, the calls are separated by an operator in an algorithm.

## Linear recursive case 

- Is the process of backing into a problem by recursively calling a copy of the method until you arrive at a base case, and then returning the values up the chain to resolve a problem.


## Base case
- The last case processed by a recursive program, which may also be processed for the last couple values. This is true when resolving a Fibonacci sequence for the first two values of the sequence. They are the last values calculated when recursively resolving the problem.


## General case

- Is the alternative to the base case, and run when the base case criteria isn't met.
 It runs when the program needs to call yet another copy or set of copies of itself to resolve a problem. 

(si n>1 seria un caso base, para cuando queremos que acabe la recursion, pero el
 general seria decir cuando queremos que la recursion se inicie o continue. Entonces,
 lo contrario, si no se cumple el general, daria lugar al caso base, que es cuando se acaba la recursion)
