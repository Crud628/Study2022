package mediator;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午10:20:14
 * @TODO
 * 
 */
public interface Colleague {
	public abstract void setMediator(Mediator mediator);

	public abstract void setColleagueEnabled(boolean enabled);
}
