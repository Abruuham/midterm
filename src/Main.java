import java.text.DecimalFormat;
import java.util.Arrays;

public class Main {

    private static final double[][] A = {{3.2, 8.7, 5.9, 1.3},{2.4, 3.1, 1.1, 9.1},{9.7, 6.1, 0.3, 1}};
    private static final double[] B = {5, 12.9, 2};
    private static final double[] S = {3.7, 3.8, 1};
    private static double[] mx = new double[3];
    private static boolean done = false;
    private static int mSize = 3;
    private static final DecimalFormat formatter = new DecimalFormat("#.0000");


    public static void main(String[] args) {
        // subtract B - S
        subMatrix(B,S);
//        System.out.println(Arrays.toString(B));
        concatMatrix(A, B);
//        for(int i = 0; i < A.length; i++){
//            for(int j = 0; j < A.length; j++){
//                System.out.println(A[i][j]);
//            }
//        }
        solveRecursive(0);

        System.out.println(Arrays.toString(mx));

    }

    private static void concatMatrix(double[][] a, double[] b){

        for(int i = 0; i < a.length; i++){
            A[i][a[i].length-1] = b[i];
        }
    }
    private static double[] subMatrix(double[] mat, double[] mat2){
        double[] subtracted_matrix = new double[A.length];
        for(int i = 0; i < mat.length-1; i++){
            B[i] = Math.round((mat[i] - mat2[i])*100)/100;
        }
        return subtracted_matrix;
    }

    private static double solveRecursive(int i){
        if(i == mSize || done){
            done = true;
            return A[i-1][mSize];
        } else if(i < mSize){
            scaleRow(A[i], 1/A[i][i]);
            for(int j = i+1; j < mSize; j++){
                subtractScaled(A[j], A[i], A[j][i]);
            }
            double x = solveRecursive(++i);
            for(int k = i-2; k >= 0; k--){
                subtractScaled(A[k], A[i-1], A[k][i-1]);
            }
            mx[i-1] = Double.parseDouble(String.format("%,.4f", x));
        }
        if(0 <= i-2) return A[i-2][mSize];
        return 0.0;
    }

    private static void scaleRow(double[] a, double x){
        for(int i = 0; i< a.length; i++){
            a[i] = Double.parseDouble(String.format("%,.4f",a[i]*x));
        }
    }

    private static void subtractScaled(double[] a, double[] b, double x){
        for(int i = 0; i < a.length; i++){
            a[i] = a[i]-(b[i]*x);
        }
    }


}
