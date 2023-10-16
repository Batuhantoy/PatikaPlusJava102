import java.io.*;
import java.util.Scanner;

public class Notepad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printNotes();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("notepad.txt",true))) {
            System.out.print("Bir metin girin: ");
            String metin = scanner.nextLine();
            bw.write(metin+"\n\n");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void printNotes(){
        System.out.println("Önceki Notlar :");
        try(BufferedReader fileReader = new BufferedReader(new FileReader("notepad.txt"))) {
            String line;
            while ((line = fileReader.readLine()) != null){
                System.out.println(line);
            }
            System.out.println("---------------------------------");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
