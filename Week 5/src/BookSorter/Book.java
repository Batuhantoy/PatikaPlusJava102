package BookSorter;

public class Book implements Comparable<Book> {

    private String bookName;
    private int pages;
    private String writer;
    private String releaseDate;


    public Book(String bookName, int pages, String writer, String releaseDate) {
        super();
        this.bookName = bookName;
        this.pages = pages;
        this.writer = writer;
        this.releaseDate = releaseDate;
    }


    public String getBookName() {
        return bookName;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public int getPages() {
        return this.pages;
    }


    public void setPages(int pages) {
        this.pages = pages;
    }


    public String getWriter() {
        return writer;
    }


    public void setWriter(String writer) {
        this.writer = writer;
    }


    public String getReleaseDate() {
        return releaseDate;
    }


    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public int compareTo(Book o) {
        return this.getBookName().compareTo(o.getBookName());
    }
}
