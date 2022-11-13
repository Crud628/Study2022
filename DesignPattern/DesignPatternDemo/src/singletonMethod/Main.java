package singletonMethod;
/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午9:02:45
 * @TODO
 * 
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Start.");
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        if (obj1 == obj2) {
            System.out.println("obj1与obj2是相同的实例。");
        } else {
            System.out.println("obj1与obj2是不同的实例。");
        }
        System.out.println("End.");
    }
}
