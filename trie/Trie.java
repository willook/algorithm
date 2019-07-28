public class Main {

	static class Stree{
		Stree[] next = new Stree[26];
		long[] leaf = new long[26];
		Stree() {
			Arrays.fill(leaf, 0);
		}
		void put(String str, int i) {
			Stree cur = this;
			while(true) {
				if(i == str.length()) return;
				int ch = str.charAt(i) - 'a';
				//System.out.println((char)(ch+'a'));
				cur.leaf[ch] += 1;
				if(cur.next[ch] == null)
					cur.next[ch] = new Stree();
				cur = cur.next[ch];
				i+=1;
			}
		}
		//0~1 : character
		long getValue(String str, int i, int j) {
			Stree cur = this;
			while(true) {
				int ch = str.charAt(i)-'a';
				if(cur.next[ch] == null) return 0;
				if(i+1 == j) return cur.leaf[ch];
				cur = cur.next[ch];
				i+=1;
				if(cur == null) cur = new Stree();
			}
		}
	}
}
