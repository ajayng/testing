package PurchaseOrder;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Location {

    private SimpleIntegerProperty Id;
    private SimpleStringProperty OfficeName; //change


    private SimpleStringProperty Contact;
    private SimpleStringProperty city;
    private SimpleStringProperty state;
    private SimpleIntegerProperty Zip;



    public Location(Integer ID, String OfficeName, String Contact, String City, String State, Integer Pincode) {
        this.Id = new SimpleIntegerProperty(ID);

        //   this.price = new SimpleDoubleProperty(price); //change
        this.Contact = new SimpleStringProperty(Contact);
        this.OfficeName= new SimpleStringProperty(OfficeName);
        this.city= new SimpleStringProperty(City);
        this.state=new SimpleStringProperty(State);
        this.Zip=new SimpleIntegerProperty(Pincode);

        // this.amount=new SimpleDoubleProperty(amount);

    }


    public void setEId(int id) {
        this.Id.set(id);
    }

    public Integer getEId() {  return Id.get();
    }


    public String getOfficename() {
        return OfficeName.get();
    }

    public void setOfficename(String name) {
        this.OfficeName = new SimpleStringProperty(name);
    }


    public String getContact() {
        return Contact.get();
    }

    public void setContact(String email) {
        this.Contact=new SimpleStringProperty(email);
    }


    public void setZip(int pincode) {
        this.Zip.set(pincode);
    }

    public Integer getZip() {  return Zip.get();
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

}
