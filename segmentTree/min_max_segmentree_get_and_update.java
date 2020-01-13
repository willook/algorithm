

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2357 {
	static StringBuilder sb = new StringBuilder();
	//static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] maxTree;
	static int[] minTree;
	static void updateMin(int node, int s, int i, int j, int x) {
		if(j<s||i>s) return;
		minTree[node] = Math.min(minTree[node], x);
		if(i!=j) {
			updateMin(node*2+1, s, i, (i+j)/2, x);
			updateMin(node*2+2, s, (i+j)/2+1, j, x);
		}
	}
	static void updateMax(int node, int s, int i, int j, int x) {
		if(j<s||i>s) return;
		maxTree[node] = Math.max(maxTree[node], x);
		if(i!=j) {
			updateMax(node*2+1, s, i, (i+j)/2, x);
			updateMax(node*2+2, s, (i+j)/2+1, j, x);
		}
	}
	static int getMin(int node, int s, int e, int i, int j) {
		if(j<s || i>e) return Integer.MAX_VALUE;
		if(s<=i && j<=e) return minTree[node];
		return Math.min(getMin(2*node+1, s, e, i ,(i+j)/2),
						getMin(2*node+2, s, e, (i+j)/2+1 ,j));
	}
	static int getMax(int node, int s, int e, int i, int j) {
		if(j<s||i>e) return Integer.MIN_VALUE;
		if(s<=i && j<=e) return maxTree[node];
		return Math.max(getMax(2*node+1, s, e, i ,(i+j)/2),
						getMax(2*node+2, s, e, (i+j)/2+1 ,j));
	}
	
	static void solve(int n, int m, int[] arr, Query[] queries) {
		minTree = new int[4*n];
		Arrays.fill(minTree, Integer.MAX_VALUE);
		maxTree = new int[4*n];
		Arrays.fill(maxTree, Integer.MIN_VALUE);
		
		for(int i=0;i<n;i++) {
			updateMin(0,i,0,n-1,arr[i]);
			updateMax(0,i,0,n-1,arr[i]);
		}
		//System.out.println(Arrays.toString(minTree));
		for(int i=0;i<m;i++) {
			int min = getMin(0,queries[i].s,queries[i].e,0,n-1);
			int max = getMax(0,queries[i].s,queries[i].e,0,n-1);
			sb.append(min).append(' ');
			sb.append(max).append('\n');
		}
	}
	static class Query{
		int s,e;
		Query(int s, int e){
			this.s = s;
			this.e = e;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for(int i = 0;i<n;i++)
			arr[i] = Integer.parseInt(br.readLine());
		Query[] queries = new Query[m];
		for(int i = 0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			queries[i] = new Query(s,e);
		}
		//System.out.println("start");
		solve(n,m,arr,queries);
		System.out.print(sb);
	}
}
