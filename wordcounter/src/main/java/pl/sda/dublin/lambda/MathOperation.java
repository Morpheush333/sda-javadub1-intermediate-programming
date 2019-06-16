package pl.sda.dublin.lambda;

@FunctionalInterface
public interface MathOperation {
    int operation(int a, int b);


    default void foo() {
        System.out.println("Hello world");
    }
}
