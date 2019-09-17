package acmicpc3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p10830 {
	static long MAX_VALUE = 1_000;
	static StringBuilder sb = new StringBuilder();
	public static int[][] matMul(int[][] A, int[][] B) {

        int nA = A.length;
        int mA = A[0].length;
        int mB = B[0].length;


        int[][] C = new int[nA][mB];
        
        for (int i = 0; i < nA; i++)
            for (int j = 0; j < mB; j++) 
                for (int k = 0; k < mA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                    C[i][j] %= MAX_VALUE;
                }
        return C;
    }
	
	static void solve(int n, long b, int[][] A) {
		
		
		int[][] I = new int[n][n];
		for(int i=0;i<n;i++)
			I[i][i] = 1;
		
		
		for(int k=0;k<64;k++) {
			if((b >> k ) % 2 == 1)
				I = matMul(I,A);
			
			A = matMul(A,A);
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++)
				sb.append(I[i][j]).append(' ');
			sb.append('\n');
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		int[][] A = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		solve(n,b,A);
		System.out.println(sb);
		
	}

}
