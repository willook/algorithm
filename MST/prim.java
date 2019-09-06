package acmicpc2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p4386 {
	static StringBuilder sb = new StringBuilder();
	static class Point{
		double x,y;
		Point(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	static class Edge implements Comparable<Edge>{
		int u,v;
		double dist;
		Edge(int u, int v, double dist){
			this.u = u;
			this.v = v;
			this.dist = dist;
		}
		public int compareTo(Edge e) {
			if(this.dist > e.dist) return 1;
			if(this.dist < e.dist) return -1;
			return 0;
		}
	}
	static void solve(int n, Point[] parr) {
		PriorityQueue<Edge> que = new PriorityQueue<Edge>();
		boolean[] isVisit = new boolean[n];
		for(int v=1;v<n;v++) 
			que.add(new Edge(0,v,getDist(parr,0,v)));
		isVisit[0] = true;
		ArrayList<Double> retArr = new ArrayList<Double>();
		int cnt = 1;
		while(cnt < n) {
			Edge e = que.poll();
			if(isVisit[e.u] && isVisit[e.v]) continue;
			cnt += 1;
			retArr.add(e.dist);
			int u = isVisit[e.u]? e.v:e.u;
			isVisit[u] = true;
			for(int v=0;v<n;v++) {
				if(isVisit[v]) continue;
				que.add(new Edge(u,v,getDist(parr,u,v)));
			}			
		}
		double ret=0;
		for(int i=0;i<retArr.size();i++)
			ret += Math.sqrt(retArr.get(i));
		sb.append(ret).append('\n');
	}
	static double getDist(Point[] parr, int u, int v) {
		double dx = parr[u].x - parr[v].x;
		double dy = parr[u].y - parr[v].y;
		return dx*dx + dy*dy;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Point[] parr = new Point[n];
		for(int i = 0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			parr[i] = new Point(x,y);
		}
		solve(n,parr);
		System.out.print(sb);
	}
}
