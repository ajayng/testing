package items;

import Dashboard.DashboardContents;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.security.PublicKey;

public class items {

       private SimpleIntegerProperty D_id;
    private SimpleIntegerProperty C_id;
        private SimpleStringProperty IName;
    private SimpleStringProperty CName;
        private SimpleDoubleProperty Price;
    private SimpleDoubleProperty CPrice;
        private SimpleIntegerProperty Quanttity;
    private SimpleIntegerProperty CQuanttity;
        private SimpleDoubleProperty Amount;
    private SimpleDoubleProperty CAmount;//change
        private SimpleDoubleProperty CurrentValue;
        Button Status;


        private SimpleStringProperty Date;
        private SimpleStringProperty Category;
    private SimpleStringProperty CCategory;
        private SimpleStringProperty Vendor;
        public items()
        {

        }



        public items(int d_id,String itemName, double price, int quantity, double amount, double currentValue, String date,String category,String vendor,Button status) {
            this.D_id=new SimpleIntegerProperty(d_id);
            this.IName = new SimpleStringProperty(itemName);

            //   this.price = new SimpleDoubleProperty(price); //change
            this.Price= new SimpleDoubleProperty(price);
            this.Quanttity= new SimpleIntegerProperty(quantity);
            this.Amount= new SimpleDoubleProperty(amount);
            this.CurrentValue=new SimpleDoubleProperty(currentValue);
            this.Date=new SimpleStringProperty(date);
            this.Category=new SimpleStringProperty(category);
            this.Vendor=new SimpleStringProperty(vendor);
            this.Status=status;

            Status.setOnAction(event -> {

                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(
                            DashboardContents.class.getResource("../items/status.fxml"));
                    stage.setScene(new Scene(root));
                    stage.setTitle("Status");
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(
                            ((Node) event.getSource()).getScene().getWindow());
                    stage.show();
                }
                catch (Exception e)
                {

                }
                });
            // this.amount=new SimpleDoubleProperty(amount);

        }

    public items(int c_id,String itemName, double price, int quantity, double amount) {
        this.C_id=new SimpleIntegerProperty(c_id);
        this.CName = new SimpleStringProperty(itemName);

        //   this.price = new SimpleDoubleProperty(price); //change
        this.CPrice= new SimpleDoubleProperty(price);
        this.CQuanttity= new SimpleIntegerProperty(quantity);
        this.CAmount= new SimpleDoubleProperty(amount);

         System.out.print("name is:"+CPrice);
        //this.Category=new SimpleStringProperty(category);
      //  this.Vendor=new SimpleStringProperty(vendor);
        // this.amount=new SimpleDoubleProperty(amount);

    }





        public String getCItemName() {
            return CName.get();
        }

        public  void setButton(Button status){
            this.Status=status;
        }

    public Button getStatus() {
        return Status;
    }

    public void setStatus(Button status) {
        Status = status;
    }



        public void setCItemName(String CName) {
            this.CName = new SimpleStringProperty(CName);
        }
    public String getItemName() {
        return IName.get();
    }

    public void setItemName(String IName) {
        this.IName = new SimpleStringProperty(IName);
    }

        public double getPrice() {
            return Price.get();
        }

        public void setPrice(double price) {
            this.Price = new SimpleDoubleProperty(price);
        }
    public Double getPrice1() {
        return CPrice.get();
    }

    public void setPrice1(double price) {
        this.CPrice = new SimpleDoubleProperty(price);
    }

        public Integer getQuantity1() {
            return CQuanttity.get();
        }

        public void setQuantity1(int quantity) {
            this.CQuanttity.set(quantity);
        }
    public Integer getQuantity() {
        return Quanttity.get();
    }

    public void setQuantity(int quantity) {
        this.Quanttity.set(quantity);
    }


    public void setAmount1(double amount) {
        this.CAmount.set(amount);
    }

    public double getAmount1() {  return CAmount.get();
    }
        public void setAmount(double amount) {
            this.Amount.set(amount);
        }

        public double getAmount() {  return Amount.get();
        }


        public void setCategory(String category) {
            this.Category.set(category);
        }

        public String getVendor() {
            return Vendor.get();
        }

        public void setVendor(String vendor) {
            this.Vendor.set(vendor);
        }

        public String getCategory() {
            return Category.get();
        }

        public void setDate(String date)
        {
            this.Date.set(date);
        }

        public String getDate()
        {
            return Date.get();
        }
        public void setD_id(Integer d_id) {  this.D_id.set(d_id); }
        public Integer  getD_id()
        {
            return D_id.get();
        }
        public void setC_id(Integer d_id) {  this.C_id.set(d_id); }
        public Integer  getC_id()
    {
        return C_id.get();
    }

    }


