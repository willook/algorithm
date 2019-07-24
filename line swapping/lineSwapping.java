package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2170 {
	static class line implements Comparable<line>{
		int s,e;
		line(int s, int e){
			this.s = s;
			this.e = e;
		}
		public int compareTo(line l) {
			return this.s - l.s;
		}
	}
	static int solve(int n, line[] arr) {
		Arrays.sort(arr);
		int ret = 0;
		int s = arr[0].s;
		int e = arr[0].e;
		for(int i=1;i<n;i++) {
			if(arr[i].s <= e) 
				e = Math.max(e, arr[i].e);
			else {
				//System.out.println(s+" "+e);
				ret += (e-s);
				s = arr[i].s;
				e = arr[i].e;
			}
		}
		ret += (e-s);
		return ret;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		line[] arr = new line[n];
		for(int i = 0;i<n;i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[i] = new line(s,e);
		}
		System.out.println(solve(n,arr));

	}

}
