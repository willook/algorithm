package acmicpc3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class p1786 {
	static StringBuilder sb = new StringBuilder();
	static int[] preprocess(String p) {
		int n = p.length();
		int[] parr = new int[n];
		boolean isSame = true;
		for(int i=1;i<n;i++) {
			int j = parr[i-1];
			if(p.charAt(i) == p.charAt(j)) {
				parr[i] = j+1;
				if(j!=0 && i < n && p.charAt(i) != p.charAt(i-1))
					isSame = false;
			}
			else {
				if(isSame && p.charAt(i) == p.charAt(i-1)) parr[i] = j;
				else if(p.charAt(i) == p.charAt(0))
					parr[i] = 1;
				else
					parr[i] = 0;
				isSame = true;
			}
			//System.out.println(p.charAt(i)+" "+i+" "+parr[i]+" "+isSame);
			
		}
		return parr;
	}
	static int solve(String t, String p) {
		int n = t.length();
		int m = p.length();
		int[] parr = preprocess(p);
		//System.out.println(Arrays.toString(parr));
		int ret = 0;
		for(int i=0,j=0;i<n;i++) {
			if(t.charAt(i) == p.charAt(j))
				j+=1;
			else {
				j = j==0 ? 0 : parr[j-1];
				if(t.charAt(i) == p.charAt(j))
					j+=1;
			}
			if(j==m) {
				ret+=1;
				sb.append(i-j+2).append(' ');
				j=parr[j-1];
			}
			//System.out.println(t.charAt(i)+" "+i+" "+j);
		}
		sb.append('\n');
		return ret;
	
	}
	public static void main(String[] args) throws Exception {
		/*
		AAABAAAABAAAA
		AAABAAAA
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String t = br.readLine();
		String p = br.readLine();
		int ret = solve(t, p);
		System.out.println(ret);
		System.out.print(sb);
	}
}
