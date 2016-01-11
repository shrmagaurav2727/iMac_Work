package ShortPrograms;

import java.util.Scanner;

public class MatrixTwoD {

	public static void main(String[] args) {
		
		MatrixTwoD Matx = new MatrixTwoD();
		int Matrix1[][] = Matx.CreateMatrix(3, 3);
		int Matrix2[][] = Matx.CreateMatrix(3, 3);
		
		int sum[][] =Matx.Multiply(Matrix1, Matrix2);
		Matx.Print(sum);

	}
	
	public int[][] CreateMatrix(int row,  int col){
		int[][] Matrix = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				 Matrix[i][j]= 3;
			}
		} return Matrix;
	}
	
	
	public void Print(int[][] Matrix){
		int row = Matrix.length;
		int col = Matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				 System.out.print(Matrix[i][j]+ " ");
			}System.out.println();
		} 
	}
	
	
	public int[][] Add(int[][] a, int[][] b){
		int row = a.length;
		int col = a[2].length; 
		int[][] sum = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				 sum[i][j] = a[i][j] + b[i][j];
			}
		} return sum;
	}
	
	public int[][] Multiply(int[][] a, int[][] b){
		int rowInA = a.length;
		int colInA = a[0].length;
		int colInB = b[0].length;
		int[][] mul = new int[rowInA][colInA];
		for (int i = 0; i < rowInA; i++) {
			for (int j = 0; j < colInB; j++) {
				for (int z = 0; z < colInA; z++) {
					System.out.println(a[i][z]* b[z][j]);
					mul[i][j] = mul[i][j] + (a[i][z] * b[z][j]);
				}
			}
		} return mul;
	}

}
