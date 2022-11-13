package templateMethod.simple;
/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午8:01:10
 * @TODO 抽象类，定义子类要实现的方法模板
 * 
 */
public abstract class AbstractDisplay {
	
	/**
	 * 打开
	 */
    public abstract void open();
    
    /**
     * 打印
     */
    public abstract void print();
    
    /**
     * 关闭
     */
    public abstract void close();
    
    /**
     * 模板方法
     */
    public final void display() {
    	// 首先打开…
        open();
        // 循环调用5次print        
        for (int i = 0; i < 5; i++) {   
            print();                    
        }
        // …最后关闭。这就是display方法所实现的功能
        close();                        
    }
}
