package abstractFactory;
/**
 * @author Keason
 * @version 创建时间：2022年11月19日 下午9:27:32
 * @TODO
 * 
 */
public class PrintBanner extends Banner implements Print {
    public PrintBanner(String string) {
        super(string);
    }
    public void printWeak() {
        showWithParen();
    }
    public void printStrong() {
        showWithAster();
    }
}
