

/*
* MyArray<String> st = new MyArray<>();
        st.add("Batuhan");
        st.add("Ali");
        st.add("Özge");
        st.add("Mert");
        st.add("Deniz");

        System.out.println(st.toString());
        st.remove(2); // 3.elamanı sildik
        System.out.println(st.toString());
        *
        System.out.println(st.size());
        System.out.println(st.getCapacity());
        *
        System.out.println("3. eleman : "+st.get(2));
*
* */

public class MyArray <T> {
    private Object[] array;


    public MyArray() {
        array = new Object[10];
    }


    public MyArray(int size) {
        array = new Object[size];
    }

    public T get(int index) {
        if(index >= array.length){
            throw new ArrayIndexOutOfBoundsException("index cant be bigger or equal than array's length!!");
        }
        return (T)array[index];
    }
    public void set(int index,T t) {
        array[index]=t;
    }


    public void add(T data) {
        if(array[array.length-1]!=null) {
            Object[] tempArr=array;
            array = new Object[tempArr.length*2];
            for(int i=0;i<tempArr.length;i++) {
                set(i,(T)tempArr[i]);
                //array[i]=tempArr[i];
            }

        }
        for(int i=0;i<array.length;i++) {
            if(array[i]==null) {
                set(i,data);
                break;
            }
        }
    }
    public T remove(int index){
        if(index<0 || index>= array.length){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds!!!");
        }
        T item = (T) array[index];
        for(int i = index; i < array.length-1;i++){
            array[i] = array[i+1];
        }
        return item;
    }


    public String toString() {

        if(array.length ==0){
            return null;
        }
        StringBuilder b = new StringBuilder();
        b.append('['+array[0].toString());
        for (int i = 1; ; i++) {

            if (array[i] == null)
                return b.append(']').toString();
            else
                b.append(", ");
            b.append(String.valueOf(array[i]));
        }
    }

    public int size() {
        int size=0;
        for(int i=0;i < array.length;i++) {
            if(array[i]!=null) {
                size++;
            }
        }
        return size;
    }

    public int getCapacity() {
        return array.length;
    }

}
