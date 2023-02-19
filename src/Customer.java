import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer {
    private String name;
    private String password;
    private HashMap<Product, Integer> cart = new HashMap<Product, Integer>();
//    private ArrayList  cartProducts= new ArrayList<Double>();
    private HashMap<GiveAwayDeals, Integer> dealCart = new HashMap<GiveAwayDeals, Integer>();

    private ArrayList<Double> coupons = new ArrayList<Double>();
    private double balance;
    public void setCart(HashMap<Product, Integer> ncart ){cart=ncart;}
    public void setDealCart(HashMap<GiveAwayDeals, Integer> ndeal ){dealCart=ndeal;}

    public void setCoupons( ArrayList<Double> co){coupons=co;}
    private double discount;
    public double getDiscount(){
        return discount;
    }
    private double totalPrice;
    private double totalCheckoutPrice;
    private double deliveryCharges;
    public void setDiscount(double disc){this.discount=disc;}
    public void setDeliveryCharges(double a){
        this.deliveryCharges=a;
    }

    public double getDeliveryCharges(){return deliveryCharges;}
    private String customerStatus;
    public String getName(){
        return name;
    }
    public String getPassword(){ return password;}
    public double getBalance(){return balance;}
    public void setBalance(double a){this.balance=a;}

    public void addBalance(double a){this.balance+=a;}
    public boolean decreaseBalance(double a){
        if(this.balance-a>=0){
            this.balance-=a;
            return true;
        }
        else{
            return false;
        }
    }
    public String getCustomerStatus(){return customerStatus;}
    public void clearCart() {
        cart.clear();
//        cartQuantity.clear();
    }

    public Customer(String n, String p) {
        name = n;
        password = p;
        customerStatus="Normal";
        balance=1000.0f;
        deliveryCharges=0;
        discount=0;
    }

    public void setName(String n){
        this.name=n;
    }
    public void setPassword(String pass){ this.password=pass;}
    public boolean authenticate(String name, String password) {
        return this.name.equals(name) && this.password.equals(password);
    }
    public void addProductToCart(Product product, int quantity) {
        cart.put(product, quantity);
    }
    public void addDealToCart(GiveAwayDeals deal, int quantity) {
        dealCart.put(deal, quantity);
    }
    public HashMap<Product, Integer> getCart() {return this.cart;}
    public HashMap<GiveAwayDeals, Integer> getDealCart() {return this.dealCart;}

    public void emptyCart() {this.cart.clear(); this.dealCart.clear();}
    public void setStatus(String status) {this.customerStatus=status;}
    public double getMaxDiscountCoupon(){
        double max=0;
        for (int i = 0; i < coupons.size(); i++) {
            if (coupons.get(i) > max) {
                max = coupons.get(i);
            }
        }
        return max;
    }
    public void removeCoupon(double coupon) {this.coupons.remove(coupon);}
    public double totalPrice(){return -1;}

    public boolean checkStock(){
        HashMap<Product, Integer> cart = getCart();
        HashMap<GiveAwayDeals, Integer> dealCart = getDealCart();
        for (Map.Entry<GiveAwayDeals, Integer> set : dealCart.entrySet()){
            Product product1= set.getKey().getProduct1();
            Product product2= set.getKey().getProduct2();
            cart.merge(product1, set.getValue(), Integer::sum);
            cart.merge(product2, set.getValue(), Integer::sum);
        }
        for( Map.Entry<Product, Integer> set : cart.entrySet()){
            if(set.getKey().getStock()<set.getValue()){
                return false;
            }
        }
        return true;
    }

    public void decreaseStockForCart(){
        HashMap<Product, Integer> cart = getCart();
        HashMap<GiveAwayDeals, Integer> dealCart = getDealCart();
        for (Map.Entry<GiveAwayDeals, Integer> set : dealCart.entrySet()){
            Product product1= set.getKey().getProduct1();
            Product product2= set.getKey().getProduct2();
            cart.merge(product1, set.getValue(), Integer::sum);
            cart.merge(product2, set.getValue(), Integer::sum);
        }
        for( Map.Entry<Product, Integer> set : cart.entrySet()){
            Product product= set.getKey();
            product.decreaseStock(set.getValue());
        }
    }
    public void addCoupons(double a){this.coupons.add(a);}
    public ArrayList<Double> getCoupons(){return coupons;}
}


class Normal extends Customer{
    public Normal(String n, String p) {
        super(n, p);
    }
//    @Override
    public double totalPrice(){
        double total=0.;
        double actualTotal=0.;
        HashMap<Product, Integer> cart = getCart();
        for (Map.Entry<Product, Integer> set : cart.entrySet()) {
            Product product = set.getKey();
            double price=product.getProductPrice();
            double discount = product.getProductDiscount("Normal");
            total+=price*(1-(discount/100))*set.getValue();
            actualTotal=price*set.getValue();
        }
        HashMap<GiveAwayDeals, Integer> dealCart = getDealCart();
        for (Map.Entry<GiveAwayDeals, Integer> set : dealCart.entrySet()){
            double price= set.getKey().getDealPrice("Normal")*set.getValue();
            total+=price*set.getValue();
            actualTotal=price*set.getValue();
        }
        if(this.decreaseBalance(this.getCartCost(total))) {
            setDiscount(actualTotal - this.getCartCost(total));
            return this.getCartCost(total);
        }
        else {
            return -1;
        }
    }

    public double getCartCost(double totalPrice){
        this.setDeliveryCharges(100 + 0.05*totalPrice);
        return totalPrice + 100 + 0.05*totalPrice;
    }

}
class Prime extends Customer{
    public Prime(String n, String p) {
        super(n, p);
    }

    public double getCartCost(double totalPrice){
        this.setDeliveryCharges(100 + 0.02*totalPrice);
        return totalPrice + 100 + 0.02*totalPrice;
    }
    public double totalPrice(){
        double couponDiscount=getMaxDiscountCoupon();
        double total=0.;
        double actualTotal=0.;
        boolean couponUsed = false;
        HashMap<Product, Integer> cart = getCart();
        for (Map.Entry<Product, Integer> set : cart.entrySet()) {
            Product product = set.getKey();
            double price=product.getProductPrice();
            double discount = product.getProductDiscount("Prime");
            if(product.getProductDiscount("Prime")<couponDiscount) {
                discount = couponDiscount;
                couponUsed = true;
            }
            total+=price*(1-(discount/100))*set.getValue();
            actualTotal=price*set.getValue();
        }
        HashMap<GiveAwayDeals, Integer> dealCart = getDealCart();

        for (Map.Entry<GiveAwayDeals, Integer> set : dealCart.entrySet()){
            double price= set.getKey().getDealPrice("Prime")*set.getValue();
            total+=price;
            actualTotal=price*set.getValue();
        }
        if(this.decreaseBalance(this.getCartCost(total))) {
            setDiscount(actualTotal-this.getCartCost(total));
            if (couponUsed) {
                removeCoupon(couponDiscount);
            }
            return  this.getCartCost(total);
        }
        else {
            return -1;
        }
    }
}

class Elite extends Customer{
    public Elite(String n, String p) {
        super(n, p);
    }

    public double getCartCost(double totalPrice){
        this.setDeliveryCharges(100);
        return totalPrice + 100;
    }
    public double totalPrice(){
        double couponDiscount=getMaxDiscountCoupon();
        double total=0.;
        double actualTotal=0.;
        boolean couponUsed = false;
        HashMap<Product, Integer> cart = getCart();
        for (Map.Entry<Product, Integer> set : cart.entrySet()) {
            Product product = set.getKey();
            double price=product.getProductPrice();
            double discount = product.getProductDiscount("Prime");
            if(discount<10){
                discount=10;
            }
            if(product.getProductDiscount("Elite")<couponDiscount) {
                discount = couponDiscount;
                couponUsed = true;
            }
            total+=price*(1-(discount/100))*set.getValue();
            actualTotal=price*set.getValue();
        }
        HashMap<GiveAwayDeals, Integer> dealCart = getDealCart();
        for (Map.Entry<GiveAwayDeals, Integer> set : dealCart.entrySet()){
            double price= set.getKey().getDealPrice("Elite")*set.getValue();
            total+=price;
            actualTotal=price*set.getValue();
        }

        if(this.decreaseBalance(this.getCartCost(total))) {
            setDiscount(actualTotal-this.getCartCost(total));
            if (couponUsed) {
                removeCoupon(couponDiscount);
            }
            return  this.getCartCost(total);
        }
        else {
            return -1;
        }
    }
}
