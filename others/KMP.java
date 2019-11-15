*/

public class Main {
    static int[] pi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bd = new StringBuilder();
        char[] T = br.readLine().toCharArray();
        char[] p = br.readLine().toCharArray();
        pi = new int[p.length];
        int cnt = 0;
        findK(p);
        int j = 0;
        for (int i = 0; i < T.length; i++) {
            while (j > 0 && T[i] != p[j]) {
                j = pi[j - 1];
            }
            if (T[i] == p[j]) {
                if (j == p.length - 1) {
                    cnt++;
                    bd.append((i - j + 1) + " ");
                    j=pi[j];
                } else {
                    ++j;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(bd.toString());
    }


    static void findK(char[] p) {
        int j = 0;
        for (int i = 1; i < p.length; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = pi[j - 1];
            }
            if (p[i] == p[j]) {
                pi[i] = ++j;
            }
        }
    }

}
