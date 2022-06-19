package edu.neu.coe.info6205.sort.elementary;


import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

import static edu.neu.coe.info6205.sort.elementary.InsertionSort.sort;


public class InsertionSortBenchmarkTest {

    @BeforeClass
    public static void setupClass() {
        try {
            config = Config.load(InsertionSortBenchmarkTest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertionSortBenchmark() {
        String description = "Insertion sort";
        Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper);
    }

    public void runBenchmark(String description, GenericSort<Integer> sort, Helper<Integer> helper) {
        helper.init(N);
        Supplier<Integer[]> supplier = () -> helper.random(Integer.class, Random::nextInt);
        final Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                description + " for " + N + " Integers",
                (xs) -> Arrays.copyOf(xs, xs.length),
                (xs) -> sort.sort(xs, 0, xs.length),
                null
        );
        logger.info(Utilities.formatDecimal3Places(benchmark.runFromSupplier(supplier, 100)) + " ms");
    }

    final static LazyLogger logger = new LazyLogger(InsertionSortBenchmarkTest.class);

    public static final int N = 2000;

    private static Config config;
}
