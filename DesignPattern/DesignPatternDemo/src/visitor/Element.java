package visitor;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午9:12:42
 * @TODO
 * 
 */
public interface Element {
    public abstract void accept(Visitor v);
}
