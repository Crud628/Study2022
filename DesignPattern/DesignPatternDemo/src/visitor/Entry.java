package visitor;

import java.util.Iterator;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午9:15:27
 * @TODO
 * 
 */
public abstract class Entry implements Element {
    public abstract String getName();                                   // 获取名字
    public abstract int getSize();                                      // 获取大小
    public Entry add(Entry entry) throws FileTreatmentException {       // 增加目录条目
        throw new FileTreatmentException();
    }
    public Iterator iterator() throws FileTreatmentException {    // 生成Iterator
        throw new FileTreatmentException();
    }
    public String toString() {                                          // 显示字符串
        return getName() + " (" + getSize() + ")";
    }
}
