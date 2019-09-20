import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p13560 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static void countSort(int[] arr) {
		int n = arr.length;
		int[] cnt = new int[n];
		for(int i=0;i<n;i++)
			cnt[arr[i]] += 1;
		int idx=0;
		for(int i=0;i<n;i++) {
			while(cnt[i] --> 0) {
				arr[idx] = i;
				idx += 1;
			}
		}
	}
	
	static void solve(int n, int[] arr) throws Exception {
		countSort(arr);
		int det=0;
		while(n-- > 0) {
			det += n;
			det -= arr[n];
			if(det < 0) {
				bw.write("-1");
				return;
			}
		}
		bw.write(det == 0 ? "1" : "-1");
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
		solve(n,arr);
		bw.flush();
	}
}
