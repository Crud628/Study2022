package visitor;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午9:14:01
 * @TODO
 * 
 */
public abstract class Visitor {
	public abstract void visit(File file);

	public abstract void visit(Directory directory);
}
