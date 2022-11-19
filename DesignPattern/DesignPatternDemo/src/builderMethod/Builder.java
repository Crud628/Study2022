package builderMethod;
/**
 * @author Keason
 * @version 创建时间：2022年11月19日 下午9:16:43
 * @TODO
 * 
 */
public abstract class Builder {
    public abstract void makeTitle(String title);
    public abstract void makeString(String str);
    public abstract void makeItems(String[] items);
    public abstract void close();
}
