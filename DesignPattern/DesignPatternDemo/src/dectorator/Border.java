package dectorator;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午9:05:37
 * @TODO
 * 
 */
public abstract class Border extends Display {
	protected Display display; // 表示被装饰物

	protected Border(Display display) { // 在生成实例时通过参数指定被装饰物
		this.display = display;
	}
}
