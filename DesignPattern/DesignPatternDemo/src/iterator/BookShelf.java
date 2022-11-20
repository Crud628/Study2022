package iterator;
/**
 * @author Keason
 * @version 创建时间：2022年11月1日 下午10:21:04
 * @TODO 书架，存放书
 * 
 */
public class BookShelf implements Aggregate{
	/**
	 * 书架的书
	 */
    private Book[] books;
    
	/**
	 * 书架的最后位置
	 */
    private int last = 0;
    
    /**
     * 构造方法
     * @param maxsize 书的最大数量
     */
    public BookShelf(int maxsize) {
        this.books = new Book[maxsize];
    }
    
    /**
     * 按下标差找
     * @param index 下标
     * @return
     */
    public Book getBookAt(int index) {
        return books[index];
    }
    
    /**
     * 尾部添加一本书
     * @param book 书 实体
     */
    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }
    
    /**
     * 当前书架有多少本书
     * @return 最后位置
     */
    public int getLength() {
        return last;
    }
    
    /**
     * 实现Iterator
     */
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
