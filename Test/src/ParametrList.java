import java.util.Arrays;
import java.util.Objects;

public class ParametrList<E> {
    private int size = 0;
    private Object data[];

    public ParametrList() {
        this.size = 0;
        this.data = new Object[70];
    }

    public int size(){
        return this.size;
    }

    public void add(E element){
        data[size] = element;
        size++;
    }

    public E get(int index){
        for(int i = 0; i < size; i++){
            if(i == index)
                return (E) data[i];
        }
        return null;
    }

    public void set(int index, E value){
        data[index] = value;
    }

    public void insert(int index, E value){
        size++;
        for(int i = size; i >= index; i--){
            data[i] = data[i-1];
        }
        set(index,value);
    }

    public void remove(int index){
        for(int i = index; i < size; i++){
            data[i] = data[i+1];
        }
        size--;
    }

    public int find(E value){
        for(int i = 0; i < size; i++){
            if(data[i] == value)
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < size; i++)
            s.append(data[i]).append(" ");
        return s.toString();
    }
}
