package singleton;
/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午9:02:31
 * @TODO
 * 
 */
public class Singleton {
	
    private static Singleton singleton = new Singleton();
    
    private Singleton() {                                 
        System.out.println("生成了一个实例。");
    }
    
    public static Singleton getInstance() {
        return singleton;
    }
}
