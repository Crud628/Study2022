package prototype;

import prototype.factory.Product;

/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午9:15:06
 * @TODO
 * 
 */
public class MessageBox implements Product {
	private char decochar;

	public MessageBox(char decochar) {
		this.decochar = decochar;
	}

	public void use(String s) {
		int length = s.getBytes().length;
		for (int i = 0; i < length + 4; i++) {
			System.out.print(decochar);
		}
		System.out.println("");
		System.out.println(decochar + " " + s + " " + decochar);
		for (int i = 0; i < length + 4; i++) {
			System.out.print(decochar);
		}
		System.out.println("");
	}

	public Product createClone() {
		Product p = null;
		try {
			p = (Product) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return p;
	}
}
