package adapter.sample1;
/**
 * @author Keason
 * @version 创建时间：2022年11月1日 下午10:46:05
 * @TODO
 * 
 */
public class Banner {
    private String string;
    public Banner(String string) {
        this.string = string;
    }
    public void showWithParen() {
        System.out.println("(" + string + ")");
    }
    public void showWithAster() {
        System.out.println("*" + string + "*");
    }
}
