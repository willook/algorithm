package acmicpc;

import java.util.Random;
import java.util.Scanner;

class SegmentTree{
	//구간합 트리
	long[] tree;
	final int capacity;
	final int maxHeight;
	final int arrayLength;	//not real with padding
	public SegmentTree(long[] arr) {
		this.capacity = getCapacity(arr.length);
		this.arrayLength = (capacity+1)/2;
		this.tree = new long[capacity];
		this.maxHeight = getHeight(this.capacity-1);
		int base = getBase(maxHeight);
		
		for(int i=0;i<arr.length;i++) 
			tree[i+base] = arr[i]; 
		for(int i=tree.length-1;i>0;i--) {
			int i0 = getParent(i);
			tree[i0] += tree[i];
		}
	}
	private int getParent(int index) {
		return (index-1)/2;
	}
	private int getMSB(int index) {
		if(index == 0) return 0;
		else return getMSB(index/2)+1;
	}
	private int getHeight(int index) {
		return getMSB(index+1)-1;
	}
	private int getCapacity(int size) {
		int msb = getMSB(size-1);
		return (1<<(msb+1)) - 1;
	}
	private int getBase(int height) {
		return (1<<(height)) - 1;
	}

	public void pprint() {
		int h=0;
		int sum=0;
		for(int i=0;i<tree.length;i++) {
			System.out.print(tree[i]+" ");
			if(sum == i) {
				System.out.println();
				h+=1;
				sum += (1<<h);
			}
		}
	}

	public long getSum(int i, int j) {
		return getSum(i,j-1,0,0);
	}
	private long getSum(int i, int j, int node, int height) {
		long ret = 0;
		int[] lr = node2range(node, height);
		//System.out.println("lr "+lr[0]+" "+lr[1]);
		if(i<=lr[0] && lr[1]<=j)
			return this.tree[node];
		else {
			lr = node2range(2*node+1, height+1);
			if(!(lr[1] < i || j<lr[0]))
				ret += getSum(i,j,2*node+1,height+1);
			
			lr = node2range(2*node+2, height+1);
			if(!(lr[1] < i || j<lr[0]))
				ret += getSum(i,j,2*node+2,height+1);
		}
		return ret;
	}
	private int[] node2range(int node, int height) {
		int base = getBase(height);
		int range = this.arrayLength/(1<<height);
		int delta = node-base;
		int[] lr = new int[2];
		lr[0] = delta*range;
		lr[1] = (delta+1)*range-1;
		return lr;
	}

}

public class p2042 {
	static long getSum(long[] arr, int i, int j) {
		long ret=0;
		for(;i<j;i++)
			ret+=arr[i];
		return ret;
	}
	public static void main(String[] args) {
		
	
		Random rand = new Random();
		while(true) {
			int n = Math.abs(rand.nextInt()%100_000);
			if(n==0) n=1;
			int k = Math.abs(rand.nextInt()%100_000);
			long[] arr = new long[n];
			for(int i=0;i<n;i++) 
				arr[i] = rand.nextInt();
			SegmentTree tree = new SegmentTree(arr);
			for(int tc=0;tc<k;tc++) {
				if(tc==4) System.out.println();
				int i = Math.abs(rand.nextInt()%n);
				int j = Math.abs(rand.nextInt()%n);
				if(i>j) {
					i^=j;
					j^=i;
					i^=j;
				}
				long ret1 = tree.getSum(i, j);
				long ret2 = getSum(arr, i, j);
				System.out.println(ret1+" "+ret2);
				
				if(ret1!=ret2) {
					new Scanner(System.in).next();
				}
					
			}
		}
	}
}
