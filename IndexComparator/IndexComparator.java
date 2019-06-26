import java.util.Arrays;
import java.util.Comparator;

public class IndexComparator<T extends Comparable<T>> implements Comparator<Integer>{
	private final T[] array;

    private IndexComparator(T[] array) {
        this.array = array;
    }
    public Integer[] sortIndex() {
        Integer[] indices = new Integer[array.length];
        for (int i = 0; i < array.length; i++){
        	indices[i] = i; 
        }
        Arrays.sort(indices, this);
        return indices;
    }
    public int compare(Integer index1, Integer index2)    {
        return array[index1].compareTo(array[index2]);
    }
    public static void main(String[] args) {
    	//String[] Array = {"France", "Spain", "France", "France", "Italy", "Spain", "Spain", "Italy"};
    	Integer[] Array = {3,6,23,65,73,34,5,};
    	IndexComparator<Integer> aic = new IndexComparator<Integer>(Array);
    	Integer[] Indices = aic.sortIndex();
    	for(Integer a : Indices){
    		System.out.println(a);
    	}
    }
}
