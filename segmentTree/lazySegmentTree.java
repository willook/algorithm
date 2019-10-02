package acmicpc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p10999_lazy {
	static class SegmentTree{
		long[] arr;
		long[] lazy;
		long[] tree;
		int size;
		SegmentTree(long[] arr){
			this.arr = arr;
			this.size = arr.length;
			this.lazy = new long[size*4];
			this.tree = new long[size*4];
			
			init(1, 0, size-1);
		}
		private long init(int node, int i, int j) {
			if(i==j) {
				tree[node] = arr[i];
				return tree[node]; 
			}
			return tree[node] = init(2*node, i, (i+j)/2)
					   + init(2*node+1, (i+j)/2+1, j);
		}
		long sum(int node, int i, int j, int l, int r) {
			if(j<l || r<i) return 0;
			
			if(l<=i && j<=r) return tree[node] + (j-i+1)*lazy[node];
			
			if(lazy[node]!=0) {
				lazy[2*node] += lazy[node];
				lazy[2*node+1] += lazy[node];
				
				tree[node] += (j-i+1)*lazy[node];
				lazy[node] = 0;
			}
			
			return sum(2*node, i, (i+j)/2, l, r)
				 + sum(2*node+1, (i+j)/2+1, j, l, r);
		}
		void lazyUpdate(int i, int j, long diff) {
			lazyUpdate(1, 0, size-1, i,j, diff);
			
		}
		void lazyUpdate(int node, int i, int j,int l, int r, long diff) {
			if(r<i || j<l) return;
			
			if(l<=i && j<=r) {
				lazy[node] += diff;
				return;
			}
			
			tree[node] += (Math.min(r, j) - Math.max(l, i) + 1) * diff;
			
			if(i==j) return;
			
			lazyUpdate(2*node, i, (i+j)/2, l, r, diff);
			lazyUpdate(2*node+1, (i+j)/2+1, j, l, r, diff);
		}
	}
	static StringBuilder sb = new StringBuilder();
	static void solve(int n, int m, int k, long[] arr, int[] type, int[] start, int[] end, long[] value) {
		SegmentTree stree = new SegmentTree(arr);
		
		long[] ret = new long[k];
		int j=0;
		for(int i=0;i<m+k;i++) {
			
			if(type[i] == 1) {
				stree.lazyUpdate(start[i], end[i], value[i]);
			}
			if(type[i] == 2) {
				ret[j] = stree.sum(1,0,n-1,start[i], end[i]);
				sb.append(ret[j]).append('\n');
				++j;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		long[] arr = new long[n];
		for(int i=0;i<n;i++)
			arr[i] = Integer.parseInt(br.readLine());
		int[] type = new int[m+k];
		int[] start = new int[m+k];
		int[] end = new int[m+k];
		long[] value = new long[m+k];
		
		
		for(int i=0;i<m+k;i++) {
			st = new StringTokenizer(br.readLine());
			type[i] = Integer.parseInt(st.nextToken());
			start[i] = Integer.parseInt(st.nextToken())-1;
			end[i] = Integer.parseInt(st.nextToken())-1;
			if(type[i] == 1) value[i] = Long.parseLong(st.nextToken());
		}
		solve(n,m,k,arr,type, start, end, value);
		bw.write(sb.toString());
		bw.flush();
	}
}
