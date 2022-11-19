package AbstractFactoryMethod;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 下午9:27:57
 * @TODO
 * 
 */
public class Main {
	public static void main(String[] args) {
		Print p = new PrintBanner("Hello");
		p.printWeak();
		p.printStrong();
	}
}