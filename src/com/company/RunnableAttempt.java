package com.company;

public class RunnableAttempt {

    public static void main(String[] args) {

        var divisionAttempt = new Attempt(() -> {
            System.out.println("trying to divide 5 by 0");
            var result = 5 / 0;
        }).onSuccess(() -> System.out.println("Everything went cool"))
          .onFailure(() -> System.out.println("blowed up"));

        divisionAttempt.evaluate();
    }

    private static class Attempt {

        private final Runnable runnable;

        private Runnable onSuccess;
        private Runnable onFailure;

        private Attempt(Runnable runnable) {
            this.runnable = runnable;
        }

        public void evaluate() {
            try {
                runnable.run();
                onSuccess.run();
            } catch (Exception e) {
                onFailure.run();
            }
        }

        public Attempt onSuccess(Runnable onSuccess) {
            this.onSuccess = onSuccess;
            return this;
         }

        public Attempt onFailure(Runnable onFailure) {
            this.onFailure = onFailure;
            return this;
        }

    }
}
