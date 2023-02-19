import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private int categoryId;
    private String categoryName;
    private ArrayList<Product> products;
    public Category(int c, String cn) {
        categoryId = c;
        categoryName = cn;
        this.products=new ArrayList<Product>();
    }
    public void removeAllProducts() {
        products.clear();
    }
    public boolean productExists(double productId) {
        int numberOfProducts = products.size();
        for(int i=0;i<numberOfProducts;i++) {
            if(products.get(i).getProductId() == productId) {
                return true;
            }
        }
        return false;
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public String getCategoryName(){return this.categoryName;}
    public ArrayList<Product> getProductList() {return this.products;}
}