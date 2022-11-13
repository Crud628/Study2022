package factoryMethod.sample.IDCard;

import factoryMethod.sample.framework.Product;

/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午8:35:19
 * @TODO
 * 
 */
public class IDCard extends Product {
	
    private String owner;
    
    IDCard(String owner) {
        System.out.println("制作" + owner + "的ID卡。");
        this.owner = owner;
    }
    
    @Override
    public void use() {
        System.out.println("使用" + owner + "的ID卡。");
    }
    
    public String getOwner() {
        return owner;
    }
}
