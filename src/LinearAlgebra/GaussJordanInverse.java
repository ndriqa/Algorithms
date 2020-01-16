package LinearAlgebra;
/**This class finds inverse matrix of matrix A using Gaussian - Jordan elimination
 *
 * @author ndricimrrahmani
 * */;



public class GaussJordanInverse {

    private double[][] matrixA;
    private double[][] elementA;
    private double[][] augmentedOriginal;
    private double[][] augmented;
    private int n;

    private double[][] inverseA;

    public GaussJordanInverse(double[][] a){    //initialization of
        matrixA = a;                            //matrix A
        n = matrixA.length;                     //size of matrix
        elementA = new double[n][n];            //elementary matrix that's used in augmented matrix
        for (int i = 0; i < n; i++) {           //diagonal elements of elementary matrix are set to 1
            elementA[i][i] = 1;
        }
        augmented = new double[n][n*2];         //to be used augmented matrix size
        augmentedOriginal = new double[n][n*2]; //original augmented matrix size
        inverseA = new double[n][n];            //inverse matrix size

        for (int i = 0; i < matrixA.length; i++) {                  //this initializes augmented matrices arrays with matrix A on the left
            for (int j = 0; j < matrixA[0].length; j++) {           //                                   and elementary matrix on the right
                augmented[i][j] = matrixA[i][j];
                augmented[i][j+ matrixA[0].length] = elementA[i][j];
                augmentedOriginal[i][j] = matrixA[i][j];
                augmentedOriginal[i][j+ matrixA[0].length] = elementA[i][j];
            }
        }
    }

    private void findInverse() {                                    //does exactly that
        for (int i = 0; i < n; i++) {                               //for every row
            double simplifyRatio = augmented[i][i];                 //get the first non zero double (from left to right)

            for (int j = i; j < 2 * n; j++) {
                augmented[i][j] = augmented[i][j]/simplifyRatio;    //divide every member of the row with the first non zero double
            }

            for (int j = 0; j < n; j++) {                           //for every row
                if (j!=i){                                          //except pivot row
                    double simplifyFactor = augmented[j][i];        //get the factor to multiply the selected row

                    for (int k = i; k < 2 * n; k++) {               //then subtract each member of the current row with the member in the same column in the pivot row
                        augmented[j][k] = augmented[j][k] - (simplifyFactor* augmented[i][k]);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseA[i][j] = augmented[i][j+n];                 //fill inverse matrix from found inverse in augmented matrix
            }
        }
    }

    private void showMatrices(){
        System.out.println("Matrix A");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(" | " + matrixA[i][j]);
            }
            System.out.println(" | ");
        }
        System.out.println("==================================================");
        System.out.println("Augmented matrix before before row operations");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                System.out.print(" | " + augmentedOriginal[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("==================================================");
        System.out.println("Augmented matrix before after row operations");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                System.out.print(" | " + augmented[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("==================================================");
        System.out.println("Inverse matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(" | " + inverseA[i][j]);
            }
            System.out.println(" |");
        }
    }

    public static void main(String[] args) {

        //use whichever you want, or try a new one with a different order
        double[][] matrix = {   {1.0, 3.0, 7.0},
                                {7.0, 4.0, 4.0},
                                {6.0, 4.0, 1.0}};

        double[][] matrix2 = {  {1.0, 0.0, 0.0},
                                {0.0, 1.0, 0.0},
                                {0.0, 0.0, 1.0}};

        double[][] matrix3 = {  {1.2, 3.5},
                                {4.3, 7.1}};

        GaussJordanInverse theInverse = new GaussJordanInverse(matrix);
        theInverse.findInverse();
        theInverse.showMatrices();
    }
}