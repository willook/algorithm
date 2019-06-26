package acmicpc;

import java.util.Arrays;
import java.util.Scanner;

public class p11657_edge {
	static long MAX_VALUE = Integer.MAX_VALUE; 
	static long[] solve(int V, int E, long[][] edges){
		long[] ret = new long[V];
		Arrays.fill(ret, MAX_VALUE);
		ret[0] = 0;
		for(int idx=0;idx<V;idx++) {
			for(int i=0;i<E;i++) {
				int u = (int)edges[i][0];
				int v = (int)edges[i][1];
				long w = edges[i][2];
				if(ret[u] == MAX_VALUE) continue;
				if(idx == V-1 && ret[v] > ret[u] + w)
					return null;
				ret[v] = Math.min(ret[v], ret[u] + w);
			}
		}
		for(int i=0;i<V;i++)
			ret[i] = (ret[i] >= MAX_VALUE) ? -1 : ret[i];
		
		return ret;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int v = scan.nextInt();
		int e = scan.nextInt();
		long[][] edges = new long[e][3];
		for(int i=0;i<e;i++)
			Arrays.fill(edges[i], MAX_VALUE);
		for(int idx=0;idx<e;idx++) {
			edges[idx][0] = scan.nextLong()-1;
			edges[idx][1] = scan.nextLong()-1;
			edges[idx][2] = scan.nextLong();
		}
		long[] ret = solve(v,e,edges);
		if(ret == null) System.out.println("-1");
		else
			for(int i=1;i<v;i++)
				System.out.println(ret[i]);
		scan.close();
	}
}
