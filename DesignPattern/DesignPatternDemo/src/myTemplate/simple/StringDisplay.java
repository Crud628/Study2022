package myTemplate.simple;

/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午8:05:01
 * @TODO 字符串的打印
 * 
 */
public class StringDisplay extends AbstractDisplay {
	// 需要显示的字符串
	private String string;

	// 以字节为单位计算出的字符串长度
	private int width;

	/**
	 * 构造函数
	 * 
	 * @param string
	 */
	public StringDisplay(String string) {
		this.string = string;
		this.width = string.getBytes().length;
	}

	@Override
	public void open() {
		// 调用该类的printLine方法画线
		printLine();
	}

	@Override
	public void print() {
		// 给保存在字段中的字符串前后分别加上"|"并显示出来
		System.out.println("|" + string + "|");
	}

	@Override
	public void close() {
		// 与open方法一样，调用printLine方法画线
		printLine();
	}

	/**
	 * 被open和close方法调用。 由于可见性是private，因此只能在本类中被调用
	 */
	private void printLine() {
		// 显示表示方框的角的"+"
		System.out.print("+");
		// 显示width个"-" 组成方框的边框
		for (int i = 0; i < width; i++) {
			System.out.print("-");
		}
		// /显示表示方框的角的"+"
		System.out.println("+");
	}
}
