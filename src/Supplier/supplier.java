package Supplier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import java.math.BigInteger;

public class supplier {

        private SimpleStringProperty supplierName;
        private SimpleStringProperty phoneNo; //change


    private SimpleStringProperty additional;
    private SimpleStringProperty city;
    private SimpleStringProperty state;
    private SimpleIntegerProperty pincode;
    private Button Action;


        public supplier(String itemName, String PhoneNo, String add, String City, String State, Integer Pincode) {
            this.supplierName = new SimpleStringProperty(itemName);

            //   this.price = new SimpleDoubleProperty(price); //change
            this.phoneNo = new SimpleStringProperty(PhoneNo);
            this.additional= new SimpleStringProperty(add);
            this.city= new SimpleStringProperty(City);
            this.state=new SimpleStringProperty(State);
            this.pincode=new SimpleIntegerProperty(Pincode);
            this.Action=new Button("Delete");
            // this.amount=new SimpleDoubleProperty(amount);

        }





        public String getSupplierName() {
            return supplierName.get();
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = new SimpleStringProperty(supplierName);
        }

        public String getAdditional() {
            return additional.get();
        }

        public void setAdditonal(String add) {
            this.additional = new SimpleStringProperty(add);
        }

        public String getPhoneNo() {
            return phoneNo.get();
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo.set(phoneNo);
        }


        public void setPincode(int pincode) {
            this.pincode.set(pincode);
        }

        public Integer getPincode() {  return pincode.get();
        }


         public void setState(String state) {
             this.state.set(state);
         }

    public String getState() {
            return state.get();
    }

         public void setCity(String city) {
             this.city.set(city);
         }

    public String getCity() {
        return city.get();
    }

    public void setButton(Button button)
    {
        this.Action=button;
    }

    public Button getButton()
    {
        return Action;
    }

    }
