import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println(sumFromFile("src/file.txt"));
    }

    public static int sumFromFile(String fileName){
        int sum = 0;
        try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = fileReader.readLine()) != null){
                sum += Integer.parseInt(line);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return sum;
    }

}
