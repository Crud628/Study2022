package abstractFactory;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 下午9:26:17
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
