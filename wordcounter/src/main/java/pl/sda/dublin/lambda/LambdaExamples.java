package pl.sda.dublin.lambda;

public class LambdaExamples {


    static void wykonajOperacje(int a, int b, MathOperation op) {

        System.out.println("wynik to: " + op.operation(a, b));
    }


    public static void main(String[] args) {

        wykonajOperacje(5, 2, (a, b) -> {

            System.out.println("tutaj robie dodwanie");



            return 2 * a + 2 * b;
        });


        wykonajOperacje(5, 2, (a, b) -> a - b);
        wykonajOperacje(5, 2, (a, b) -> a * b);
        wykonajOperacje(5, 2, (a, b) -> a / b);
    }
}
