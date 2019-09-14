package acmicpc3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class p1786 {
	static StringBuilder sb = new StringBuilder();
	
	static int[] preprocess(char[] p) {
		int n = p.length;
		int[] parr = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j>0 && p[i] != p[j]) 
                j = parr[j-1];
            if (p[i] == p[j]) {
            	j += 1;
            	parr[i] = j;
            }
        }
        return parr;
    }
	static int solve(char[] t, char[] p) {
		int n = t.length;
		int m = p.length;
		int[] parr = preprocess(p);
		int ret = 0;
		for(int i=0,j=0;i<n;i++) {
			if(t[i] == p[j])
				j+=1;
			else {
				j = j==0 ? 0 : parr[j-1];
				if(t[i] == p[j])
					j+=1;
			}
			if(j==m) {
				ret+=1;
				sb.append(i-j+2).append(' ');
				j=parr[j-1];
			}
		}
		sb.append('\n');
		return ret;
	
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] t = br.readLine().toCharArray();
		char[] p = br.readLine().toCharArray();
		int ret = solve(t, p);
		bw.write(ret+"\n");
		bw.write(sb.toString());
		bw.flush();
	}
}
