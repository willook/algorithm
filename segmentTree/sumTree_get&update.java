import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p1275 {
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static long[] sumTree;
	
	static void updateSum(int node, int s, int i, int j, long diff) {
		if(j<s||i>s) return;
		sumTree[node] += diff;
		if(i!=j) {
			updateSum(node*2+1, s, i, (i+j)/2, diff);
			updateSum(node*2+2, s, (i+j)/2+1, j, diff);
		}
	}
	static long getSum(int node, int s, int e, int i, int j) {
		if(j<s||i>e) return 0L;
		if(s<=i && j<=e) return sumTree[node];
		return getSum(2*node+1, s, e, i ,(i+j)/2) +
				getSum(2*node+2, s, e, (i+j)/2+1 ,j);
	}
	static void solve() {
		sumTree = new long[4*n];
		for(int i=0;i<n;i++) 
			updateSum(0,i,0,n-1,arr[i]);
		
		for(Query q : query) {
			long ret;
			if(q.x > q.y)
				ret = getSum(0,q.y, q.x, 0, n-1);
			else
				ret = getSum(0,q.x, q.y, 0, n-1);
			long diff = q.b-arr[q.a];
			arr[q.a] = q.b;
			updateSum(0,q.a,0,n-1,diff);
			sb.append(ret).append('\n');
		}
	}
	static class Query{
		int x,y,a;
		long b;
		Query(int x, int y, int a, long b){
			this.x = x;
			this.y = y;
			this.a = a;
			this.b = b;
		}
	}
	static Query[] query;
	static long[] arr;
	static int n,m;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new long[n];
		query = new Query[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;	
			int y = Integer.parseInt(st.nextToken())-1;	
			int a = Integer.parseInt(st.nextToken())-1;	
			long b = Long.parseLong(st.nextToken());	
			query[i] = new Query(x,y,a,b);
		}
		solve();
		bw.write(sb.toString());
		bw.close();
	}
}
