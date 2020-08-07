//프로그래머스 입국심사
class Solution {
    public boolean isPossible(int n, int[] times, long mid){
        long processed = 0;
        for(int time : times)
            processed += mid / time;
        return n <= processed;
    }
    public long solution(int n, int[] times) {
        long answer = 0;
        long r = 1000000000000000000L;
        long l = 0;
        while(l<r){
            long mid = (l+r)/2;
            boolean det = isPossible(n,times,mid);
            if(det) r = mid;
            else l = mid+1;
        }
        return l;
    }
}
