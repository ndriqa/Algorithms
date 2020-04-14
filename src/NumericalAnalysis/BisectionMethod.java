package NumericalAnalysis;
/**This class uses bisection method to find the root of a function.
 *
 * @author ndriqa
 * */

public class BisectionMethod {
    private double endpointA, endpointB, A, B;
    private double tolerance;
    private int N;
    String theFunction;

    private Double root;
    /**
     * @param a start of interval.
     * @param b end of interval.
     * @param tol tolerance of error.
     * @param n number of iterations so method doesnt run forever.
     * */
    public BisectionMethod(double a, double b, double tol, int n){
        endpointA = a; A = endpointA;
        endpointB = b; B = endpointB;
        tolerance = tol;
        N = n;
        theFunction = "f(x) = x^2 - 3x - 4"; //doesn't change actual function, for appearance only!

        findRoot();
        System.out.println(this);
    }
    public BisectionMethod(int a, int b, double tol, int n){
        new BisectionMethod((double)a, (double)b, tol, n);
    }
    public void findRoot(){
        int i = 1;
        double FA = f(endpointA);
        double Fr;
        double tempRoot;


        while(i<=N){
            tempRoot = endpointA + (endpointB - endpointA)/2.0;
            Fr = f(tempRoot);

            if (Double.compare(Fr, 0) == 0 || ((endpointB - endpointA)/2.0) < tolerance){
                root = tempRoot;
                //System.out.println(root);
                break;
            }

            i = i+1;
            if(FA*Fr > 0){
                endpointA = tempRoot;
                FA = Fr;
            } else {
                endpointB = tempRoot;
            }
        }

        if(root == null){
            System.out.println("Method failed after " + N + " iterations");
        }
    }

    //f method f is a function
    public double f(double x){
        return x*x - 3*x - 4;
    }

    @Override
    public String toString(){
        String temp = "The root of the function: " + theFunction + "\n" +
                "between endpoints A = " + A + " and endpoint B = " + B + " is:" +
                " " + root;
        return temp;
    }

    public static void main(String[] args) {
        double endptA = 2.1;
        double endptB = 15.5;
        double tol = 0.000000001;
        int N = 50000;

        new BisectionMethod(endptA, endptB, tol, N);
    }
}
