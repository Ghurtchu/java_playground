package com.company;

public class CounterExample {

    public static void main(String[] args) {

        System.out.println(new Counter(0).inc().inc());

        System.out.println(new EnrichedCounter(0).inc(5).dec());
    }

    private static class Counter {
        private int value;

        public Counter(int value) {
            this.value = value;
        }

        public Counter inc() {
            return new Counter(value + 1);
        }

        public Counter dec() {
            return new Counter(value - 1);
        }

        public int get() {
            return value;
        }

        @Override
        public String toString() {
            return "Counter{" +
                    "value=" + value +
                    '}';
        }
    }

    private static class EnrichedCounter extends Counter {

        public EnrichedCounter(int value) {
            super(value);
        }

        public EnrichedCounter inc(int n) {
            if (n == 0)
                return this;
            else if (n > 0)
                return ((EnrichedCounter) this.inc()).inc(n - 1);
            else
                return this;
        }

        public EnrichedCounter dec(int n) {
            if (n == 0)
                return this;
            else if (n > 0)
                return ((EnrichedCounter) this.dec()).dec(n - 1);
            else
                return this;
        }
    }
}
