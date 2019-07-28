package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2188 {
	
	static boolean push(ArrayList<ArrayList<Integer>> map, int[] isThere, int u, boolean[] isVisit) {
		for(int i=0;i<map.get(u).size();i++) {
			int v = map.get(u).get(i);
			if(isVisit[v]) continue;
			if(isThere[v] == -1) {
				isThere[v] = u;
				return true;
			}
		}
		for(int i=0;i<map.get(u).size();i++) {
			int v = map.get(u).get(i);
			if(isVisit[v]) continue;
			isVisit[v] = true;
			int u1 = isThere[v];
			isThere[v] = -1;
			boolean ret = push(map,isThere, u1, isVisit);
			if(ret) {
				isThere[v] = u;
				return true;
			}
			isThere[v] = u1;
		}
		return false;
	}
	static int solve(int n, int m, ArrayList<ArrayList<Integer>> map) {
		int[] isThere = new int[m];
		boolean[] isVisit = new boolean[m];
		Arrays.fill(isThere, -1);
		Arrays.fill(isVisit, false);
		
		for(int u=0;u<n;u++) 
			push(map, isThere, u, isVisit.clone());
		
		int ret = 0;
		for(int i=0;i<m;i++)
			if(isThere[i] != -1) ret += 1;
		return ret;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer> list = new ArrayList<Integer>();
			int k = Integer.parseInt(st.nextToken());
			for(int j=0;j<k;j++)
				list.add(Integer.parseInt(st.nextToken())-1);
			map.add(list);
		}
		System.out.println(solve(n,m,map));
	}
}
