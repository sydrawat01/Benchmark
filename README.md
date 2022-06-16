# Assignment 3 - Benchmarking

Your task for this assignment is in three parts.

## Part 1

You are to implement three (3) methods (_repeat_, _getClock_, and _toMillisecs_) of a class called Timer.
Please see the skeleton class that I created in the repository.
_Timer_ is invoked from a class called _Benchmark_Timer_ which implements the _Benchmark_ interface. The APIs of these class are as follows:

```java
public interface Benchmark<T> {
    default double run(T t, int m) {
        return runFromSupplier(() -> t, m);
    }
    double runFromSupplier(Supplier<T> supplier, int m);
}
```


[_Supplier_ is a Java function type which supplies values of type _T_ using the method: _get()_.]

```java
public class Benchmark_Timer<T> implements Benchmark<T>
```

```java
public Benchmark_Timer(String description, UnaryOperator<T> fPre, Consumer<T> fRun, Consumer<T> fPost)
```

[_Consumer<T>_ is a Java function type which consumes a type _T_ with the method: _accept(t)_.
_UnaryOperator<T>_ is essentially an alias of _Function<T, T>_ which defines _apply(t)_ which takes a _T_ and returns a _T_.]

```java
public Benchmark_Timer(String description, UnaryOperator<T> fPre, Consumer<T> fRun)
```

```java
public Benchmark_Timer(String description, Consumer<T> fRun, Consumer<T> fPost)
```

```java
public Benchmark_Timer(String description, Consumer<T> f)
```

```java
public class Timer {
    ... // see below for methods to be implemented...
}
```

```java
public <T, U> double repeat(int n, Supplier<T> supplier, Function<T, U> function, UnaryOperator<T> preFunction, Consumer<U> postFunction) {
    // TO BE IMPLEMENTED
}
```

[_Function<T, U>_ defines a method U _apply(t: T)_, which takes a value of _T_ and returns a value of _U_.]

```java
private static long getClock() {
    // TO BE IMPLEMENTED
}
```

```java
private static double toMillisecs(long ticks) {
    // TO BE IMPLEMENTED
}
```

The function to be timed, hereinafter the "target" function, is the _Consumer_ function _fRun_ (or just _f_) passed in to one or other of the constructors.
For example, you might create a function which sorts an array with _n_ elements.

The generic type _T_ is that of the input to the target function.

The first parameter to the first run method signature is the parameter that will, in turn, be passed to target function.
In the second signature, _supplier_ will be invoked each time to get a t which is passed to the other run method.

The second parameter to the _run_ function (_m_) is the number of times the target function will be called.

The return value from _run_ is the average number of milliseconds taken for each run of the target function.

Don't forget to check your implementation by running the unit tests in _BenchmarkTest_ and _TimerTest_.
If you have trouble with the exact timings in the unit tests, it's quite OK (in this assignment only) to change parameters until the tests run. Different machine architectures will result in different behavior.

## Part 2

Implement _InsertionSort_ (in the _InsertionSort_ class) by simply looking up the insertion code used by _Arrays.sort_. 
If you have the _instrument = true_ setting in _test/resources/config.ini_,
then you will need to use the _helper_ methods for comparing and swapping (so that they properly count the number of swaps/compares).
The easiest is to use the _helper.swapStableConditional_ method, continuing if it returns true, otherwise breaking the loop.
Alternatively, if you are not using instrumenting, then you can write (or copy) your own compare/swap code.
Either way, you must run the unit tests in _InsertionSortTest_.

## Part 3

Implement a main program (or you could do it via your own unit tests) to actually run the following benchmarks:
measure the running times of this sort, using four different initial array ordering situations: random, ordered, partially-ordered and reverse-ordered.
I suggest that your arrays to be sorted are of type _Integer_. Use the doubling method for choosing _n_ and test for at least five values of _n_.
Draw any conclusions from your observations regarding the order of growth.

As usual, the submission will be your entire project (_clean, i.e. without the target and project folders_).
There are stubs and unit tests in the repository.

Report on your observations and show screenshots of the runs and also the unit tests.
Please note that you may have to adjust the required execution time for the insertion sort unit test(s) because your computer may not run at the same speed as mine.

Further notes: you should use the _System.nanoTime_ method to get the clock time.
This isn't guaranteed to be accurate which is one of the reasons you should run the experiment several times for each value of _n_.
Also, for each invocation of _run_, run the given target function ten times to get the system "warmed up" before you start the timing properly.

The _Sort_ interface takes care of copying the array when the _sort(array)_ signature is called.
It returns a new array as a result. The original array is unchanged.
Therefore, you do not need to worry about the insertion-based sorts getting quicker because of the arrays getting more sorted (they don't).

If you need clarification, ask on Slack.

### Author

[Siddharth Rawat - 002963295](mailto:rawat.sid@northeastern.edu)