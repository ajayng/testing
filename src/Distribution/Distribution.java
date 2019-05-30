package Distribution;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Distribution {

    private SimpleStringProperty Iname;
    private SimpleIntegerProperty Quantity;
    private SimpleStringProperty Distributee;
    private SimpleStringProperty Date;
    private SimpleIntegerProperty Id;
    private SimpleStringProperty CIname;
    private SimpleIntegerProperty CQuantity;
    private SimpleStringProperty CDistributee;
    private SimpleStringProperty CDate;
    private SimpleIntegerProperty CId;

    //change
    //   private SimpleIntegerProperty unit;
    // private SimpleDoubleProperty amount;

    public Distribution(String iname, int quantity, String distributee,String date,int id) {
        this.Iname = new SimpleStringProperty(iname);
        this.Quantity = new SimpleIntegerProperty(quantity);
        this.Distributee = new SimpleStringProperty(distributee);
        this.Date=new SimpleStringProperty(date);
        this.Id=new SimpleIntegerProperty(id);
    }

    public Distribution(String iname, int quantity, String distributee,String date,int id,String s) {
        this.CIname = new SimpleStringProperty(iname);
        this.CQuantity = new SimpleIntegerProperty(quantity);
        this.CDistributee = new SimpleStringProperty(distributee);
        this.CDate=new SimpleStringProperty(date);
        this.CId=new SimpleIntegerProperty(id);
    }


    public String getIssuedate() {
        return Date.get();
    }

    public void setIssueddate(String date) {
        this.Date = new SimpleStringProperty(date);
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public Integer getId() {
        return Id.get();
    }

    public String getCitemName() {
        return CIname.get();
    }

    public void setCitemName(String IName) {
        this.CIname = new SimpleStringProperty(IName);
    }

    public void setCquantity1(int quantity) {
        this.CQuantity.set(quantity);
    }

    public Integer getCquantity() {
        return CQuantity.get();
    }

    public String getCdistributee() {
        return CDistributee.get();
    }

    public void setcDistributee(String IName) {
        this.CDistributee = new SimpleStringProperty(IName);
    }

    public String getCissuedate() {
        return CDate.get();
    }

    public void setCissueddate(String date) {
        this.CDate = new SimpleStringProperty(date);
    }

    public void setCid(int id) {
        this.CId.set(id);
    }

    public Integer getCid() {
        return CId.get();
    }

    public String getItemName() {
        return Iname.get();
    }

    public void setItemName(String IName) {
        this.Iname = new SimpleStringProperty(IName);
    }

    public void setQuantity1(int quantity) {
        this.Quantity.set(quantity);
    }

    public Integer getQuantity() {
        return Quantity.get();
    }

    public String getDistributee() {
        return Distributee.get();
    }

    public void setDistributee(String IName) {
        this.Distributee = new SimpleStringProperty(IName);
    }

}