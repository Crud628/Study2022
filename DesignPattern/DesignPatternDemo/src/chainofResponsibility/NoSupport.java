package chainofResponsibility;
/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午10:07:08
 * @TODO
 * 
 */
public class NoSupport extends Support {
    public NoSupport(String name) {
        super(name);
    }
    protected boolean resolve(Trouble trouble) {     // 解决问题的方法
        return false; // 自己什么也不处理
    }
}
