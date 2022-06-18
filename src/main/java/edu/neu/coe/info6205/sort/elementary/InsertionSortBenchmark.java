package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.TimeLogger;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Supplier;



public class InsertionSortBenchmark {

    public InsertionSortBenchmark(int runs, int n ,int m, String type) {
        this.runs = runs;
//        this.supplier = new Source(n,m).randomInts(10, type);
        this.supplier = new Source(n).intsArr(type);
        this.n = n;
    }

    public void runBenchmarks(String type) {
        System.out.println("InsertionSort Benchmark: N=" + n);
        String description = "Insertion Sort";
        InsertionSort<Integer> sort = new InsertionSort<>();
        switch (type) {
            case "sorted" -> benchmarkInsertionSort(description, sort, timeLoggersOrdered, type);
            case "random" -> benchmarkInsertionSort(description, sort, timeLoggersRandom, type);
            case "partially" -> benchmarkInsertionSort(description, sort, timeLoggersPartiallyOrdered, type);
            case "reverse" -> benchmarkInsertionSort(description, sort, timeLoggersReverseOrdered, type);
            default -> {
            }
        }
    }

    private void benchmarkInsertionSort(String description, InsertionSort<Integer> insertionSort, final TimeLogger[] timeLoggers, String type) {
        final double time = new Benchmark_Timer<Integer[]>(
                description + " (" + type + ")",
                null,
                (x)->insertionSort.sort(x,0, x.length),
                null
        ).runFromSupplier(supplier, runs);
        for (TimeLogger timeLogger : timeLoggers) timeLogger.log(time, n);
        // END
    }

    public static void main(String[] args) {
        int runs = 10;
        for(int i=250; i<16000; i*=2) {
            new InsertionSortBenchmark(runs, i, i, "random").runBenchmarks("random");
            new InsertionSortBenchmark(runs, i, i, "reverse").runBenchmarks("reverse");
            new InsertionSortBenchmark(runs, i, i, "partially").runBenchmarks("partially");
            new InsertionSortBenchmark(runs, i, i, "sorted").runBenchmarks("sorted");
        }
    }

    private final static TimeLogger[] timeLoggersRandom = {
            new TimeLogger("Random Raw time per run (mSec): ", (time, n) -> time)
    };
    private final static TimeLogger[] timeLoggersOrdered = {
            new TimeLogger("Ordered Raw time per run (mSec): ", (time, n) -> time)
    };
    private final static TimeLogger[] timeLoggersPartiallyOrdered = {
            new TimeLogger("Partially Raw time per run (mSec): ", (time, n) -> time)
    };
    private final static TimeLogger[] timeLoggersReverseOrdered = {
            new TimeLogger("Reverse Raw time per run (mSec): ", (time, n) -> time)
    };

    private final int runs;
    private final Supplier<Integer[]> supplier;
    private final int n;
}
