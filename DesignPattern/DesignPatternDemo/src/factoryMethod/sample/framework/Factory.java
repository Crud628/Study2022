package factoryMethod.sample.framework;
/**
 * @author Keason
 * @version 创建时间：2022年11月13日 下午8:35:43
 * @TODO
 * 
 */
public abstract class Factory {
	
	/**
	 * 自己实现，类似模板
	 * @param owner
	 * @return
	 */
    public final Product create(String owner) {
        Product p = createProduct(owner);
        registerProduct(p);
        return p;
    }
    
    /**
     * 生产
     * @param owner
     * @return
     */
    protected abstract Product createProduct(String owner);
    
    /**
     * 注册
     * @param product
     */
    protected abstract void registerProduct(Product product);
}
