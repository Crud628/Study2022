package adapter.sample2;
/**
 * @author Keason
 * @version 创建时间：2022年11月1日 下午10:46:55
 * @TODO
 * 
 */
public class PrintBanner extends Print{

    private Banner banner;
    public PrintBanner(String string) {
        this.banner = new Banner(string);
    }
    public void printWeak() {
        banner.showWithParen();
    }
    public void printStrong() {
        banner.showWithAster();
    }
}
