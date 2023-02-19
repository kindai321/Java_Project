public class GiveAwayDeals {
    final int giveAwayDealsId;
    final String dealName;
    final Product product1;
    private Product product2;
    final double dealPriceNormal;
    final double dealPricePrime;
    final double dealPriceElite;
    public Product getProduct1(){
        return this.product1;
    }
    public Product getProduct2(){
        return this.product2;
    }
    public double getDealPrice(String status){
        if(status.equals("Normal")){
            return this.dealPriceNormal;
        } else if(status.equals("Prime")) {
            return this.dealPricePrime;
        } else {return this.dealPriceElite;}
    }

    public String getDealName(){return this.dealName;}

    public GiveAwayDeals(int id, String n, Product p1, Product p2, double pr1, double  pr2, double pr3){
        giveAwayDealsId= id;
        dealName=n;
        product1=p1;
        product2=p2;
        dealPriceNormal=pr1;
        dealPricePrime=pr2;
        dealPriceElite=pr3;
    }
}
