package prototype.factory;

/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午9:14:04
 * @TODO
 * 
 */
public interface Product extends Cloneable {
	public abstract void use(String s);

	public abstract Product createClone();
}
