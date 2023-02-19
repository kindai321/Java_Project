import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, Category> categories = new HashMap<Integer, Category>();
    static HashMap<Double, Product> products = new HashMap<Double, Product>();
    static ArrayList<Customer> customers = new ArrayList<Customer>();
    static HashMap<Integer, GiveAwayDeals> giveAwayDeals = new HashMap<Integer, GiveAwayDeals>();


    static HashMap<String , Admin> admins = new HashMap< String ,Admin>();
    public void addAdmins(){
        Admin admin1= new Admin("Beff Jezos", "Password");
        Admin admin2= new Admin("Gill Bates", "Password1");
        admins.put("Beff Jezos", admin1);
        admins.put("Gill Bates", admin2);
    }


//    static HashMap<String, String> admins = new HashMap<String, String>();
//    admins.put("asd", "asd");
//    static ArrayList<Normal> normal = new ArrayList<Normal>();
//    static ArrayList<Elite> elite = new ArrayList<Elite>();
//    static ArrayList<Prime> prime = new ArrayList<Prime>();
//    static ArrayList<Customer*> customers = new ArrayList<Customer*>;

    public static void main(String[] args) {
        System.out.println("Welcome to FutureBuilder:");
        start();
    }

    static void start() {
        while (true) {
//            System.out.println("Welcome to FutureBuilder:");
            System.out.println("1) Enter as Admin");
            System.out.println("2) Explore the Product  Catalog");
            System.out.println("3) Show Available Deals");
            System.out.println("4) Enter as Customer");
            System.out.println("5) Exit the Application");
            int a = Integer.parseInt(sc.nextLine());
            if (a == 1) {
                enterAsAdmin();
            }else if (a == 2) {
                exploreProductCatalog();
            }else if (a == 3) {
                showAvailableDeals();
            }else if (a == 4) {
                enterAsCustomer();
            } else {
                System.out.println("Thanks for using Flipzon!!");
                return;
            }
        }
    }

    static void enterAsAdmin() {
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your Password: ");
        String password = sc.nextLine();
        if(authenticateAdmin(name, password)){
            return;
        }
        while (true) {
//            System.out.println("Choose The mode you want to Enter in:-");
            System.out.println("Welcome "+ name);
            System.out.println(
                    "Please choose any one of the following actions:\n" +
                    "1) Add Category\n" +
                    "2) Delete Category\n" +
                    "3) Add Product\n" +
                    "4) Delete Product\n" +
                    "5) Set Discount on Product\n" +
                    "6) Add Giveaway Deal\n" +
                    "7) Back"
            );
            int ch = Integer.parseInt(sc.nextLine());
            if (ch == 1) {
                addCategory();
            } else if (ch == 2) {
                deleteCategory();
            } else if (ch == 3) {
                addProduct();
            } else if (ch == 4) {
                deleteProduct();
            } else if (ch == 5) {
                setDiscountOnProduct();
            } else if (ch == 6) {
                addGiveawayDeal();
            } else {
                System.out.println("Bye "+ name);
                return;
            }
        }
    }

    static boolean authenticateAdmin(String name, String password){
        if (!admins.containsKey(name)){
            return false;
        }
        else{
            if(admins.get(name).getAdminPassword()!=password){
                return false;
            }
            return true;
        }
    }

    static void addCategory(){
        System.out.println("Enter Category name:");
        String categoryName = sc.nextLine();
        System.out.println("Enter Category Id:");
        int categoryId = Integer.parseInt(sc. nextLine());
        Category category = new Category(categoryId, categoryName);
        if(!categories.containsKey(categoryId)) {
            categories.put(categoryId, category);
            System.out.println("New category created");
            addProduct();
        } else {
            System.out.println("Category Id already being used");
        }
    }
    static void deleteCategory(){
        System.out.println("Enter Category Id:");
        int categoryId = Integer.parseInt(sc.nextLine());
        if(!categories.containsKey(categoryId)) {
            System.out.println("Invalid id");
        } else {
            Category category = categories.get(categoryId);
            category.removeAllProducts();
            categories.remove(categoryId);
            System.out.println("Category deleted");
        }
    }
    static void addProduct(){
        System.out.println("Enter Category Id:");
        int categoryId = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Product Name:");
        String productName = sc.nextLine();
        System.out.println("Enter Product Id:");
        double productId = Double.parseDouble(sc.nextLine());
        System.out.println("Enter Product Description:");
        String description = sc.nextLine();
        System.out.println("Enter Product Price:");
        double productPrice=Double.parseDouble(sc.nextLine());
        System.out.println("Enter Product Stock:");
        int stock= Integer.parseInt(sc.nextLine());
        Product product = new Product(productName, productId, description, productPrice, stock);
        if(products.containsKey(productId)) {
            System.out.println("Product Id already exists");
            return;
        }
        if(!categories.containsKey(categoryId)) {
            System.out.println("Category does not exist");
            addCategory();
        }
        Category category = categories.get(categoryId);
        category.addProduct(product);
        products.put(productId, product);
        System.out.println("Product added!!");
    }
    static void deleteProduct(){
        System.out.println("Enter Product ID:");
        double productId = Double.parseDouble(sc.nextLine());
        if(!products.containsKey(productId)) {
            System.out.println("Invalid product Id");
            return;
        }
        Product product = products.get(productId);
        int categoryId = product.getProductCategory();
        Category category = categories.get(categoryId);
        category.removeProduct(product);
        products.remove(productId);
        System.out.println("Product Deleted");
    }
    static void setDiscountOnProduct(){
        System.out.println("Enter Product ID:");
        double productId = Double.parseDouble(sc.nextLine());
        if(!products.containsKey(productId)) {
            System.out.println("Invalid product Id");
            return;
        }
        Product product = products.get(productId);
        System.out.println("Enter Discount(Normal):");
        double percentageProductDiscountNormal = Double.parseDouble(sc.nextLine());
        product.setProductDiscountNormal(percentageProductDiscountNormal);
        System.out.println("Enter Discount(Prime):");
        double percentageProductDiscountPrime = Double.parseDouble(sc.nextLine());
        product.setProductDiscountPrime(percentageProductDiscountPrime);
        System.out.println("Enter Discount(Elite):");
        double percentageProductDiscountElite = Double.parseDouble(sc.nextLine());
        product.setProductDiscountElite(percentageProductDiscountElite);
    }
    static void addGiveawayDeal(){
        System.out.println("Enter Deal ID:");
        int dealId= Integer.parseInt(sc.nextLine());
        System.out.println("Enter Deal Name:");
        String dealName= sc.nextLine();
        System.out.println("Enter Product 1 ID:");
        double productId1 = Double.parseDouble(sc.nextLine());
        System.out.println("Enter Product 2 ID:");
        double productId2 = Double.parseDouble(sc.nextLine());
        System.out.println("Enter Price(Normal):");
        double priceNormal = Double.parseDouble(sc.nextLine());
        System.out.println("Enter Price(Prime):");
        double pricePrime = Double.parseDouble(sc.nextLine());
        System.out.println("Enter Price(Elite):");
        double priceElite = Double.parseDouble(sc.nextLine());
        GiveAwayDeals deal= new GiveAwayDeals(dealId, dealName, products.get(productId1), products.get(productId2), priceNormal, pricePrime, priceElite);
        if(giveAwayDeals.containsKey(dealId)) {
            System.out.println("Giveaway deal Id already exists");
            return;
        }
        giveAwayDeals.put(dealId, deal);
        System.out.println("Added Giveaway Deals!!");
    }


    static void exploreProductCatalog(){
        if(products.size()==0) {
            System.out.println("There are no products now, Come back later!!");
            return;
        }
        System.out.println("The Product catalog is as follows:");
        for (Map.Entry<Integer, Category> set : categories.entrySet()){
            System.out.println(set.getKey()+" : "+set.getValue().getCategoryName());
            ArrayList<Product> productsList = set.getValue().getProductList();
            for (int i=0; i<productsList.size(); i++) {
                Product tempProduct= productsList.get(i);
                System.out.println("ProductId: "+ tempProduct.getProductId()+ "\nProduct Name: " +tempProduct.getProductName()+"\nProduct Description: "+ tempProduct.getProductDescription() + "\nProduct Price: "+
                tempProduct.getProductPrice()+ "\nProduct Discount(Normal Prime ELite): " + tempProduct.getProductDiscount("Normal") +" " + tempProduct.getProductDiscount("Prime") +" " +tempProduct.getProductDiscount("Elite") +"\n "  );
            }
            System.out.println("");
        }
    }

    static void showAvailableDeals(){
        if(giveAwayDeals.size()==0) {
            System.out.println("There are no deals now, Come back later!!");
            return;
        }
        System.out.println("The Deals catalog is as follows:");
        for (Map.Entry<Integer, GiveAwayDeals> set : giveAwayDeals.entrySet()){
            System.out.println("Giveaway Deal Id: " + set.getKey()+"  Name: "+set.getValue().getDealName());
            Product tempProduct1= set.getValue().getProduct1();
            Product tempProduct2= set.getValue().getProduct2();
            System.out.println("Product1 Id: "+ tempProduct1.getProductId()+ "\nProduct1 Name: " +tempProduct1.getProductName()+"\nProduct1 Description: "+ tempProduct1.getProductDescription() +
                    "\nProduct2 Id: "+ tempProduct2.getProductId()+ "\nProduct2 Name: " +tempProduct2.getProductName()+"\nProduct2 Description: "+ tempProduct2.getProductDescription() +
                    "\nGiveaway Deal Price(Normal Prime ELite): " + set.getValue().getDealPrice("Normal") +" " + set.getValue().getDealPrice("Prime") +" " +set.getValue().getDealPrice("Elite") +"\n "  );
            System.out.println("");
        }
    }

    static void enterAsCustomer(){
        while(true){
            System.out.println("Please choose any one of the following actions:\n" +
                    "1) Sign up\n" +
                    "2) Log in\n" +
                    "3) Back");
            int ch = Integer.parseInt(sc.nextLine());
            if (ch == 1) {
                signUp();
            } else if (ch == 2) {
                logIn();
            } else {
                return;
            }
        }
    }

    static void signUp(){
        System.out.println("Enter your Name:");
        String name = sc.nextLine();
        System.out.println("Enter your Password:");
        String password = sc.nextLine();
        Normal newCustomer = new Normal(name, password);
        customers.add(newCustomer);
        System.out.println("You have successfully signed up for Flipzon!!!");
    }
    static Customer getCustomer(String name, String password) {
        int numberOfCustomers = customers.size();
        for(int i=0;i<numberOfCustomers;i++) {
            if(customers.get(i).authenticate(name, password)) {
                return customers.get(i);
            }
        }
        return null;
    }
    static void logIn(){
        System.out.println("Enter your Name:");
        String name = sc.nextLine();
        System.out.println("Enter your Password:");
        String password = sc.nextLine();
        Customer customer = getCustomer(name, password);
//        Normal customer = getCustomer(name, password);
//        Elite customer = getCustomer(name, password);
//        Prime customer = getCustomer(name, password);
        while (true) {
            System.out.println("Welcome "+name+"!!\n" +
                    "1) browse products\n" +
                    "2) browse deals\n" +
                    "3) add a product to cart\n" +
                    "4) add products in deal to cart\n" +
                    "5) view coupons\n" +
                    "6) check account balance\n" +
                    "7) view cart\n" +
                    "8) empty cart\n" +
                    "9) checkout cart\n" +
                    "10) upgrade customer status\n" +
                    "11) Add amount to wallet\n" +
                    "12) back");
            int ch = Integer.parseInt(sc.nextLine());
            if (ch == 1) {
                browseProducts();
            } else if (ch == 2) {
                browseDeals();
            } else if (ch == 3) {
                addProductToCart(customer);
            } else if (ch == 4) {
                addProductDealToCart(customer);
            } else if (ch == 5) {
                viewCoupons(customer);
            } else if (ch == 6) {
                checkAccountBalance(customer);
            } else if (ch == 7) {
                viewCart(customer);
            } else if (ch == 8) {
                emptyCart(customer);
            } else if (ch == 9) {
                checkoutCart(customer);
            } else if (ch == 10) {
                upgradeCustomerStatus(customer);
            } else if (ch == 11) {
                addAmountToWallet(customer);
            } else {
                System.out.println("Bye "+ name);
                return;
            }
        }
    }

    static void browseDeals(){
        showAvailableDeals();
    }
    static  void browseProducts(){
        exploreProductCatalog();
    }
    static void addProductToCart(Customer customer){
        customer=getCustomer(customer.getName(), customer.getPassword());
        System.out.println("Enter product Id: ");
        double productId = Double.parseDouble(sc.nextLine());
        Product product = products.get(productId);
        System.out.println("Enter product Quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());
        customer.addProductToCart(product, quantity);
    }
    static void addProductDealToCart(Customer customer){
        customer=getCustomer(customer.getName(), customer.getPassword());
        System.out.println("Enter deal Id: ");
        int dealId= Integer.parseInt(sc.nextLine());
        GiveAwayDeals deal= giveAwayDeals.get(dealId);
        System.out.println("Enter deal Quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());
        customer.addDealToCart(deal, quantity);
    }
    static void viewCoupons(Customer customer){
        customer=getCustomer(customer.getName(), customer.getPassword());
        ArrayList<Double> viewingCoupons=customer.getCoupons();
        if(viewingCoupons.size()==0){
            System.out.println("You have no Coupons");
        }
        else{
            System.out.println("You have coupons giving :");
            for(int i=0; i<viewingCoupons.size(); i++){
                System.out.println(viewingCoupons.get(i)+ "%");
            }
        }
    }
    static void checkAccountBalance(Customer customer){
        customer=getCustomer(customer.getName(), customer.getPassword());
        System.out.println("Your Balance is: "+ customer.getBalance());
    }
    static void viewCart(Customer customer){
        customer=getCustomer(customer.getName(), customer.getPassword());
        HashMap<Product, Integer> cart = customer.getCart();
        if(cart.size()==0){
            System.out.println("You have no products in cart");
        }
        else{
            System.out.println("You have following products in cart:");
            for (Map.Entry<Product, Integer> set : cart.entrySet()) {
                System.out.println("Product Name: " + set.getKey().getProductName()+"  Quantity: "+set.getValue() + "\n");
            }
        }
        HashMap<GiveAwayDeals, Integer> dealCart=customer.getDealCart();
        if(dealCart.size()==0){
            System.out.println("You have no deals in cart");
        }
        else{
            System.out.println("You have following deals in cart:");
            for(Map.Entry<GiveAwayDeals, Integer> set : dealCart.entrySet()){
                System.out.println("Product Name: " + set.getKey().getDealName()+"  Quantity: "+set.getValue() + "\n");
            }
        }
    }
    static void emptyCart(Customer customer){
        customer=getCustomer(customer.getName(), customer.getPassword());
        customer.emptyCart();
        System.out.println("The cart has been cleared");
    }

    static void checkoutCart(Customer customer){
//        System.out.println(customers.get(0).getClass());
//        System.out.println(customer.getClass());
        customer=getCustomer(customer.getName(), customer.getPassword());
//        System.out.println(customer.getClass());
//        System.out.println(customer.getBalance());
        double total = customer.totalPrice();
        if(!customer.checkStock()){
            System.out.println("Insufficient Stock of products");
        }
        else if(total==-1){
            System.out.println("Insufficient funds");
        }
        else if(customer.getCart().size()==0 && customer.getDealCart().size()==0){
            System.out.println("Cart is Empty");
        }
        else {
            viewCart(customer);
            customer.decreaseStockForCart();
            System.out.println("The delivery charges are: Rs" + customer.getDeliveryCharges());
            System.out.println("You save: Rs"+ customer.getDiscount());
            System.out.println("The Total Price is: Rs" + total);
            emptyCart(customer);
            if(customer.getCustomerStatus().equals("Normal")){
                System.out.println("Order placed. It will be delivered in 7-10 days.");
            }
            else if(customer.getCustomerStatus().equals("Prime")){
                System.out.println("Order placed. It will be delivered in 3-6 days.");
            }
            else{
                System.out.println("Order placed. It will be delivered within 2 days.");
            }


            if(total>5000 && customer.getCustomerStatus().equals("Prime")){
                double couponVal= (int)(Math.random()*(11)+5);
                customer.addCoupons(couponVal);
                System.out.println("You won one coupon of"+ couponVal+ "%. Congratulations!!!");
            }
            if(total>5000 && customer.getCustomerStatus().equals("Elite")){
                double couponVal1= (int)(Math.random()*(11)+5);
                double couponVal2= (int)(Math.random()*(11)+5);
                customer.addCoupons(couponVal1);
                customer.addCoupons(couponVal2);
                System.out.println("You won two coupons of"+ couponVal1+"% & "+ couponVal2 + "% Discount. Congratulations!!!");
            }
            if(customer.getCustomerStatus().equals("Elite")){
                double prob=Math.random();
//                double prob=0.6;
                if(prob>=0.5){
                    for (Map.Entry<Double, Product> set : products.entrySet()) {
                        if(set.getValue().getStock()>=1){
                            System.out.println("You won a surprise gift of "+ set.getValue().getProductName()+
                                    " worth Rs "+ set.getValue().getProductPrice() + " for free. Congratulations!!!");
                            set.getValue().decreaseStock(1);
                            return;
                        }
                    }
                }
            }
        }
    }


    static void upgradeCustomerStatus(Customer customer){
        System.out.println("Enter the new Customer status: ");
        String status = sc.nextLine();
        String currentStatus = customer.getCustomerStatus();
        if(!currentStatus.equals(status) && status .equals( "Elite")) {
            customers.remove(customer);
            Elite elite = new Elite(customer.getName(), customer.getPassword());
            elite.setCart(customer.getCart());
            elite.setDealCart(customer.getDealCart());
            elite.setCoupons(customer.getCoupons());
            elite.setBalance(customer.getBalance());
            elite.setStatus("Elite");
            customers.add(elite);
            double cost=0;
            if(currentStatus.equals("Prime")) {
                cost=100;
            } else if(currentStatus.equals("Normal")) {
                cost=300;
            }
            if(!elite.decreaseBalance(cost)) {
                System.out.println("Insufficient funds");
            }
//            customer.decreaseBalance(cost);
//            elite.decreaseBalance(cost);
        }
        if(!currentStatus.equals(status) && status.equals("Prime")) {
            customers.remove(customer);
            Prime prime = new Prime(customer.getName(), customer.getPassword());
            prime.setCart(customer.getCart());
            prime.setDealCart(customer.getDealCart());
            prime.setCoupons(customer.getCoupons());
            prime.setBalance(customer.getBalance());
            prime.setStatus("Prime");
            customers.add(prime);
            double cost=0;
            if(currentStatus.equals("Normal")) {
                cost=200;
            }
            if(!prime.decreaseBalance(cost)) {
                System.out.println("Insufficient funds");
            }
//            customer.decreaseBalance(cost);
//            prime.decreaseBalance(cost);
        }
        if(!currentStatus.equals(status) && status.equals("Normal")){
            customers.remove(customer);
            Normal normal = new Normal(customer.getName(), customer.getPassword());
            normal.setCart(customer.getCart());
            normal.setDealCart(customer.getDealCart());
            normal.setCoupons(customer.getCoupons());
            normal.setBalance(customer.getBalance());
            normal.setStatus("Normal");
            customers.add(normal);
        }

        customer.setStatus(status);
        System.out.println("Your new Status is: "+ customer.getCustomerStatus());
//        System.out.println(customers.get(0).getBalance());
//        System.out.println(customers.get(0).getClass());
//        System.out.println(customers.size());
    }

    static void addAmountToWallet(Customer customer){
        customer= getCustomer(customer.getName(), customer.getPassword());
        System.out.println("Enter the amount to be added to the wallet: ");
        double amount = Double.parseDouble(sc.nextLine());
        customer.addBalance(amount);
        System.out.println("Amount added to Wallet Successfully");
    }
}