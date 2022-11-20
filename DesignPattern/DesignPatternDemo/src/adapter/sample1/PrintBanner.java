package adapter.sample1;
/**
 * @author Keason
 * @version 创建时间：2022年11月1日 下午10:46:55
 * @TODO
 * 
 */
public class PrintBanner extends Banner implements Print{

	public PrintBanner(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}
    public void printWeak() {
        showWithParen();
    }
    public void printStrong() {
        showWithAster();
    }
}
