package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1929 {
	static StringBuilder sb = new StringBuilder();
	static void solve(int m, int n) {
		boolean[] isPrime = eras(n);	
		for(int i=m;i<=n;i++)
			if(isPrime[i])
				sb.append(i).append('\n');
	}
	static boolean[] eras(int n) {
		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		for(int i=2;i<=n;i++) {
			if(!isPrime[i]) continue;
			for(int j=2*i;j<=n;j+=i)
				isPrime[j] = false;
		}
		return isPrime;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		solve(m, n);
		System.out.print(sb);
		
	}
}
