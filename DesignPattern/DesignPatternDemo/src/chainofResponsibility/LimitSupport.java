package chainofResponsibility;
/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午10:06:54
 * @TODO
 * 
 */
public class LimitSupport extends Support {
    private int limit;                              // 可以解决编号小于limit的问题
    public LimitSupport(String name, int limit) {   // 构造函数
        super(name);
        this.limit = limit;
    }
    protected boolean resolve(Trouble trouble) {    // 解决问题的方法
        if (trouble.getNumber() < limit) {
            return true;
        } else {
            return false;
        }
    }
}

