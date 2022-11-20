package dectorator;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午9:05:02
 * @TODO
 * 
 */
public abstract class Display {
	public abstract int getColumns(); // 获取横向字符数

	public abstract int getRows(); // 获取纵向行数

	public abstract String getRowText(int row); // 获取第row行的字符串

	public void show() { // 全部显示
		for (int i = 0; i < getRows(); i++) {
			System.out.println(getRowText(i));
		}
	}
}
