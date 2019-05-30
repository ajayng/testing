package PurchaseOrder;

public class purdetails {
    String name;
    String desc;
    double price;
    int quantity;
    double amount;
    purdetails(String iname,String desc, double price,int quantity,double amount){
        this.name=iname;
        this.desc=desc;
        this.price=price;
        this.quantity=quantity;
        this.amount=amount;
    }


    void getname(String name)
    {
        this.name=name;
    }
    void getdesc(String desc)
    {
        this.desc=desc;
    }
    void getprice(double price)
    {
        this.price=price;
    }
    void getquantity(int quantity)
    {
        this.quantity=quantity;
    }
    void getamount()
    {
        this.amount=price*quantity;
    }
}
