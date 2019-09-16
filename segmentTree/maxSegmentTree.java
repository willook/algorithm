package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p10868 {
	static class SegmentTree{
		int[] tree;
		
		SegmentTree(int[] arr){
			int n = arr.length;
			this.tree = new int[4*n];
			init(1,0,n-1, arr);
		}
		int init(int node, int i, int j, int[] arr) {
			if(i==j) {
				tree[node] = arr[i];
				return tree[node]; 
			}
			return tree[node] = Math.min(init(2*node, i, (i+j)/2, arr),
					   					 init(2*node+1, (i+j)/2+1, j, arr));
		}
		
		int getMin(int node, int i, int j, int l, int r) {
			if(j<l || r<i) return Integer.MAX_VALUE;
			if(l<=i && j<=r) return tree[node];
			return Math.min(getMin(node*2, i, (i+j)/2, l ,r),
						   getMin(node*2+1, (i+j)/2+1, j, l ,r));
		}
	}
	static void solve(int n, int m, int[] arr, int[][] query) {
		
		SegmentTree tree = new SegmentTree(arr);
		for(int i=0;i<m;i++) {
			int ret = tree.getMin(1, 0, n-1, query[i][0], query[i][1]);
			sb.append(ret).append('\n');
		}
	}
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[][] query = new int[m][2];
		for(int i = 0;i<n;i++)
			arr[i] = Integer.parseInt(br.readLine());
		for(int i = 0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			query[i][0] = Integer.parseInt(st.nextToken())-1;
			query[i][1] = Integer.parseInt(st.nextToken())-1;			
		}
		sb = new StringBuilder();
		solve(n,m,arr,query);
		System.out.println(sb.toString());
	}
}
