import java.util.Arrays;

public class MyStack {
    private int data [] = new int[70];
    private int size = 0;

    public void push(int value){
        if(data.length == size){
            data = Arrays.copyOf(data, data.length*2);
            data[size] = value;
        }
        else
            data[size] = value;
        size++;
    }

    public int pop(){
        int el = data[size-1];
        data[size-1] = 0;
        size--;
        return el;
    }

    public int getMin(){
        int min = data[0];
        for(int i = 0; i < size; i++){
            if(data[i] < min)
                min = data[i];
        }
        return min;
    }

    public int getMax(){
        int max = data[0];
        for (int i = 0; i < size; i++){
            if (data[i] > max)
                max = data[i];
        }
        return max;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0 ? true : false;
    }

    public void printData(){
        for(int el : data){
            System.out.print(el + " ");
        }
        System.out.println();
    }
}
