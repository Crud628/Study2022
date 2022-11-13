package myTemplate.simple;
/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午8:03:07
 * @TODO 字符的打印
 * 
 */
public class CharDisplay extends AbstractDisplay{

	/**
	 * 需要显示的字符
	 */
    private char ch;
    

    /**
     * 构造方法
     * @param ch
     */
    public CharDisplay(char ch) {
        this.ch = ch;
    }
    
    @Override
    public void open() {   
    	// 显示开始字符"<<"
        System.out.print("<<");                     
    }
    
    @Override
    public void print() { 
    	// 显示保存在字段ch中的字符
        System.out.print(ch);                       
    }
    
    @Override
    public void close() {   
    	// 显示结束字符">>"
        System.out.println(">>");                   
    }

}
