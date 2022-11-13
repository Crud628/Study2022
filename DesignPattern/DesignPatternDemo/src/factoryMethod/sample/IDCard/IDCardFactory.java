package factoryMethod.sample.IDCard;

import java.util.ArrayList;
import java.util.List;

import factoryMethod.sample.framework.Factory;
import factoryMethod.sample.framework.Product;

/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午8:35:30
 * @TODO
 * 
 */
public class IDCardFactory extends Factory{
	
    private List owners = new ArrayList();
    
    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }
    
    @Override
    protected void registerProduct(Product product) {
        owners.add(((IDCard)product).getOwner());
    }
    
    public List getOwners() {
        return owners;
    }
}
