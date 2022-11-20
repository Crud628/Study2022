package iterator;
/**
 * @author Keason
 * @version 创建时间：2022年11月1日 下午10:20:28
 * @TODO
 * 
 */
public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));
        bookShelf.appendBook(new Book("Daddy-Long-Legs"));
        Iterator it = bookShelf.iterator();
        while (it.hasNext()) {
            Book book = (Book)it.next();
            System.out.println(book.getName());
        }
        // out: 
        // Around the World in 80 Days
        // Bible
        // Cinderella
        // Daddy-Long-Legs
    }
}
