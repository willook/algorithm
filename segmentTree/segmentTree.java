package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2042_segment {
	static class SegmentTree{
		long[] arr;
		long[] tree;
		
		SegmentTree(long[] arr){
			this.arr = arr;
			this.tree = new long[arr.length*4];
		}
		long init(int node, int i, int j) {
			if(i==j) {
				tree[node] = arr[i];
				return tree[node]; 
			}
			return tree[node] = init(2*node, i, (i+j)/2)
					   + init(2*node+1, (i+j)/2+1, j);
			// tree[node];
		}
		long sum(int node, int i, int j, int l, int r) {
			if(j<l || r<i) return 0;
			
			if(l<=i && j<=r) return tree[node];
			return sum(2*node, i, (i+j)/2, l, r)
				 + sum(2*node+1, (i+j)/2+1, j, l, r);
		}
		long sum(int l, int r) {
			return sum(1,0,arr.length-1,l,r);
		}
		void update(int i, long value) {
			long diff = value - arr[i];
			arr[i] = value;
			//System.out.println("diff: " +diff);
			update(1, 0, arr.length-1, i, diff);
		}
		void update(int node, int i, int j,int l, long diff) {
			if(l<i || j<l) return;
			tree[node] += diff;
			//System.out.println(i+" "+j+" "+l);
			if(i!=j) {
				update(2*node, i, (i+j)/2, l, diff);
				update(2*node+1, (i+j)/2+1, j, l, diff);
			}
		}
		
	}
	static String solve(int n, int m, int k, long[] arr, long[][] query) {
		SegmentTree tree = new SegmentTree(arr);
		tree.init(1, 0, n-1);
		//System.out.println(Arrays.toString(tree.tree));
		
		long[] ret = new long[k];
		int j=0;
		//tree.pprint();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<m+k;i++) {
			if(query[i][0] == 1) {
				tree.update((int)query[i][1]-1, query[i][2]);
				//tree.change((int)query[i][1]-1, query[i][2]);
				//tree.pprint();
			}
			if(query[i][0] == 2) {
				//System.out.println(Arrays.toString(tree.tree));
				
				//long sum(int node, int i, int j, int l, int r) {
				ret[j] = tree.sum(1,0,n-1,(int)query[i][1]-1, (int)query[i][2]-1);
				sb.append(ret[j]).append('\n');
				++j;
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		long[] arr = new long[n];
		for(int i=0;i<n;i++)
			arr[i] = Integer.parseInt(br.readLine());
		//long[] arr2 = arr.clone();
		long[][] query = new long[m+k][3];
		for(int i=0;i<m+k;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				query[i][j] = Long.parseLong(st.nextToken());
			}
		}
		String ret = solve(n,m,k,arr,query);
		//long[] ret2 = solve2(n,m,k,arr2,query);
		
		System.out.println(ret);
		br.close();
	}
}
