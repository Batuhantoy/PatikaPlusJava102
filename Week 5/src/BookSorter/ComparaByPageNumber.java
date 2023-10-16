package BookSorter;

import java.util.Comparator;

public class ComparaByPageNumber implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
       /*if(o1.getPages() > o2.getPages()){
            return 1;
        }else if(o1.getPages() < o2.getPages()){
            return -1;
        }else {
            return 0;
        }*/
        return o1.getPages() - o2.getPages();
    }
}
