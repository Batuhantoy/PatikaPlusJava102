package BookSorter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        HashSet<BookSorter.Book> books = new HashSet<>();
        books.add(new BookSorter.Book("Hayvan Çiftliği",152,"Berat Toy","05/03/2010"));
        books.add(new BookSorter.Book("Koleksiyoncu",304,"Ali Şimşek","05/03/2010"));
        books.add(new BookSorter.Book("Mo'nun Gizemi 1",288,"Berat Toy","05/03/2010"));
        books.add(new BookSorter.Book("Martin eden",496,"Berat Toy","05/03/2010"));
        books.add(new BookSorter.Book("İçimizdeki Şeytan",267,"Berat Toy","05/03/2010"));
        books.add(new BookSorter.Book("İnsan Neyle Yaşar?",104,"Berat Toy","05/03/2010"));


        TreeSet<BookSorter.Book> sortedSet;
        sortedSet = new TreeSet<>(books);
        for (BookSorter.Book b : sortedSet){
            System.out.println("Kitap ismi : "+b.getBookName()+"\nSayfa Sayısı : "+b.getPages());
            System.out.println();
        }

        System.out.println("---------------------------------------");

        sortedSet = new TreeSet<>(new BookSorter.ComparaByPageNumber());
        sortedSet.addAll(books);
        for (BookSorter.Book b : sortedSet){
            System.out.println("Kitap ismi : "+b.getBookName()+"\nSayfa Sayısı : "+b.getPages());
            System.out.println();
        }
    }
}