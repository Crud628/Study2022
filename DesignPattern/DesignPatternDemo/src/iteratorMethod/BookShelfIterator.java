package iteratorMethod;
/**
 * @author Keason
 * @version 创建时间：2022年11月1日 下午10:21:41
 * @TODO 书架上书的Iterator
 * 
 */
public class BookShelfIterator implements Iterator{
	
	/**
	 * 书架
	 */
    private BookShelf bookShelf;
    
    /**
     * 下标
     */
    private int index;
    
    /**
     * 构造函数 初始化
     * @param bookShelf 书架
     */
    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }
    
    /**
     * 查看是否有下一个
     */
    public boolean hasNext() {
        if (index < bookShelf.getLength()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 返回当前指的内容，并指向下一个
     */
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
