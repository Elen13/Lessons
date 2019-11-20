import java.util.Arrays;

public class MyArray {
    public int size = 0;
    public int [] data = new int[7];

    public void add(int element){
        if(data.length == size){
            data = Arrays.copyOf(data, data.length*2);
            data[size] = element;
        }
        else
            data[size] = element;
        size++;
    }
}
