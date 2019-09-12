package acmicpc3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class next_permutation {
	static StringBuilder sb = new StringBuilder();
	static void nextPermutation(int n, int[] arr) {
		int i = n-2;
		for(;i>=0;i--)
			if(arr[i+1] > arr[i]) break;
		if(i!=-1) {
			int j=n-1;
			for(;j>i;j--)
				if(arr[j] > arr[i]) break;
			swap(arr,i,j);
		}
		i=i+1;
		for(int j=n-1;i<j;i++,j--) 
			swap(arr,i,j);
	}
	static void swap(int[] arr, int i, int j) {
		arr[i] ^= arr[j];
		arr[j] ^= arr[i];
		arr[i] ^= arr[j];
	}
	static void solve(int n, int[] arr) {
		//Arrays.sort(arr);
		for(int i=0;i<10;i++) {
			nextPermutation(n,arr);
			System.out.println(Arrays.toString(arr));
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0;i<n;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		solve(n,arr);
		System.out.print(sb);
	}
}
