import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class problemE {
	static StringBuilder sb = new StringBuilder();

	static class SegmentTree {
		long[] arr;
		long[] tree;

		SegmentTree(long[] arr) {
			this.arr = arr;
			this.tree = new long[arr.length * 4];
		}

		long init(int node, int i, int j) {
			if (i == j) {
				tree[node] = arr[i];
				return tree[node];
			}
			return tree[node] = init(2 * node, i, (i + j) / 2) + init(2 * node + 1, (i + j) / 2 + 1, j);
			// tree[node];
		}

		long sum(int node, int i, int j, int l, int r) {
			if (j < l || r < i)
				return 0;

			if (l <= i && j <= r)
				return tree[node];
			return sum(2 * node, i, (i + j) / 2, l, r) + sum(2 * node + 1, (i + j) / 2 + 1, j, l, r);
		}

		long sum(int l, int r) {
			return sum(1, 0, arr.length - 1, l, r);
		}

		void update(int i, long value) {
			long diff = value - arr[i];
			arr[i] = value;
			// System.out.println("diff: " +diff);
			update(1, 0, arr.length - 1, i, diff);
		}

		void update(int node, int i, int j, int l, long diff) {
			if (l < i || j < l)
				return;
			tree[node] += diff;
			// System.out.println(i+" "+j+" "+l);
			if (i != j) {
				update(2 * node, i, (i + j) / 2, l, diff);
				update(2 * node + 1, (i + j) / 2 + 1, j, l, diff);
			}
		}
	}

	static void solve(int n, long[] arr) {
		long[] tarr = new long[n];
		Arrays.fill(tarr, 1);
		SegmentTree tree = new SegmentTree(tarr);
		tree.init(1, 0, n - 1);
		LinkedHashMap<Long, ArrayList<Integer>> map = new LinkedHashMap<Long, ArrayList<Integer>>();
		//System.out.println(Arrays.toString(arr));
		for (int i = 0; i < n; i++) {
			long cand = arr[i];
			if (!map.containsKey(cand)) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(i);
				map.put(cand, list);
				//System.out.println(map.get(cand).size());
			} else {
				ArrayList<Integer> list = map.get(cand);
				list.add(i);
				//System.out.println(map.get(cand).size());
			}
		}
		//System.out.println(map.get(3L).size());
		//Iterator<Long> iter2 = map.keySet().iterator();
		/*while (!iter2.hasNext()) {
			long key = iter2.next();
			ArrayList<Integer> list = map.get(key);
			System.out.print(key+": ");
			for (int i = 0; i < list.size(); i++) 
				System.out.print(list.get(i)+" ");
			System.out.println();
		}*/
		
		
		long ret = 0;
		while (true) {
			Iterator<Long> iter = map.keySet().iterator();
			if(!iter.hasNext()) break;
			
			long mkey = -1;
			long min = Long.MAX_VALUE;
			while (iter.hasNext()) {
				long key = iter.next();
				
				long det1 = 0;
				long det2 = 0;
				ArrayList<Integer> list = map.get(key);
				if(list.size() == 1) {
					map.remove(key);
				}
					
				//System.out.println("key: "+key);
				for (int i = 0; i < list.size(); i++) {
					//System.out.println(" "+list.get(i));
					det1 += tree.sum(0, list.get(i)) - (i+1);
					det2 += tree.sum(list.get(i), n-1) - (i+1);
				}
				det1 = Math.min(det1, det2);
				//System.out.println(det1+" "+det2);
				if (min > det1) {
					min = det1;
					mkey = key;
				}
			}
			ret += min;
			System.out.println("ret: "+ret);
			System.out.println("mkey "+mkey);
			ArrayList<Integer> list = map.get(mkey);
			for (int i = 0; i < list.size(); i++) {
				tree.update(list.get(i), 0);

			}
			System.out.println(Arrays.toString(tree.arr));
			map.remove(mkey);
		}
		sb.append(ret).append('\n');

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		solve(n, arr);
		System.out.print(sb);
	}
}
