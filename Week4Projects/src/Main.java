
public class Main {
    public static void main(String[] args) {

        MyArray<String> st = new MyArray<>();
        st.add("Batuhan");
        st.add("Ali");

        try {
            System.out.println(st.get(4));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}