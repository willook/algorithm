package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class p11000 {
	
	static void compact(ArrayList<Integer> arr){
		@SuppressWarnings("unchecked")
		ArrayList<Integer> carr = (ArrayList<Integer>)arr.clone();
		int n = arr.size();
		Collections.sort(carr);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int ni = 0;
		for(int i=0;i<n;i++) {
			int ci = carr.get(i);
			map.put(ci, ni);
			do {
				if(i == n-1 || carr.get(i+1) != ci) break;
				else i += 1;
			}while(true);
			ni += 1;
		}
		
		for(int i=0;i<n;i++) {
			int ci = arr.get(i);
			ni = map.get(ci);
			arr.set(i, ni);
		}
		map = null;
		carr = null;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		
		for(int i = 0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr.add(s);
			arr.add(e);
		}
		compact(arr);
		System.out.println(solve(n,arr));

	}

}
