package mediator;

import java.awt.Button;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午10:20:51
 * @TODO
 * 
 */
public class ColleagueButton extends Button implements Colleague {
    private Mediator mediator;
    public ColleagueButton(String caption) {
        super(caption);
    }
    public void setMediator(Mediator mediator) {            // 保存Mediator
        this.mediator = mediator;
    }
    public void setColleagueEnabled(boolean enabled) {      // Mediator下达启用/禁用的指示 
        setEnabled(enabled);
    }
}
