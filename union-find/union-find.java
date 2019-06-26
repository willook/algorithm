package acmicpc;

import java.util.Arrays;
import java.util.Scanner;

public class p15955 {
	public static int find(int[] arr, int ch) {
		if (arr[ch] < 0) return ch;
		int pa = arr[ch];
		while(arr[pa] >= 0)
			pa = arr[pa];
		arr[ch] = pa;
		return pa;
	}
	public static void union(int[] arr, int v1, int v2) {
		int parent1 = find(arr, v1);
		int parent2 = find(arr, v2);
		
		if(parent1 == parent2) return;
		
		if(size(arr, parent1) < size(arr, parent2)) {
			parent1 ^= parent2;
			parent2 ^= parent1;
			parent1 ^= parent2;
		}
		arr[parent1] += arr[parent2];
		arr[parent2] = parent1;
	}
	public static boolean isSource(int[] arr, int v) {
		return arr[v] < 0;
	}
	public static int size(int[] arr, int v) {
		return -arr[find(arr, v)];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		int[] arr = new int[n];
		Arrays.fill(arr, -1);
		Scanner scan = new Scanner(System.in);
		while(true) {

			System.out.println(Arrays.toString(arr));
			int v1 = scan.nextInt();
			int v2 = scan.nextInt();
			//System.out.println(size(arr, v1) +" "+ size(arr, v2));
			union(arr,v1,v2);
			
		}
	}

}
