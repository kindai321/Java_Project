public class Product {
    private int stock;
    private double productId;
    private int categoryId;

    private String productName;
    private String productDescription;
    private double productPrice;
    private double productDiscountNormal;
    private double productDiscountPrime;
    private double productDiscountElite;
    public double getProductDiscount(String status){
        if(status.equals("Normal")) return this.productDiscountNormal;
        else if (status.equals("Prime"))  return this.productDiscountPrime;
        else return this.productDiscountElite;
    }
    public int getStock(){return stock;}
    public void decreaseStock(int a){this.stock-=a;}
    public Product(String n, double id, String des, double price, int quant) {
        productName = n;
        productId = id;
        productDescription= des;
        categoryId= (int)productId;
        productPrice=price;
        stock=quant;
        productDiscountPrime=0.;
        productDiscountNormal=0.;
        productDiscountElite=0.;
    }
    public int getProductCategory(){
        return this.categoryId;
    }
    public double getProductId() {return this.productId;}

    public void setProductDiscountNormal(double discount) {this.productDiscountNormal=discount;}

    public void setProductDiscountPrime(double discount) {this.productDiscountPrime=discount;}

    public void setProductDiscountElite(double discount) {this.productDiscountElite=discount;}
    public double getProductPrice(){return this.productPrice;}
    public String getProductDescription(){return this.productDescription;}
    public String getProductName(){return this.productName;}

}
