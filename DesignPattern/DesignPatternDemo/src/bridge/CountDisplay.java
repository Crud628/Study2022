package bridge;
/**
 * @author Keason
 * @version 创建时间：2022年11月19日 下午9:31:35
 * @TODO
 * 
 */
public class CountDisplay extends Display{
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }
    public void multiDisplay(int times) {       // 循环显示times次
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
