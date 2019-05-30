package PurchaseOrder;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author jwright
 */
public class Person {
    private SimpleStringProperty itemName, description;
    private SimpleDoubleProperty price; //change
    private Image photo;
    private SimpleIntegerProperty unit;
    private SimpleDoubleProperty amount;

    public Person(String itemName, String description, Double price,Integer unit,Double amount) {
        this.itemName = new SimpleStringProperty(itemName);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price); //change
        this.unit = new SimpleIntegerProperty(unit);
        this.amount=new SimpleDoubleProperty(amount);

    }



    public Image getImage()
    {
        return photo;
    }

    public void setImage(Image newPicture)
    {
        this.photo = newPicture;
    }

    public String getFirstName() {
        return itemName.get();
    }

    public void setFirstName(String itemName) {
        this.itemName = new SimpleStringProperty(itemName);
    }

    public String getLastName() {
        return description.get();
    }

    public void setLastName(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public Double getPrice() {
        return price.get();
    }



    public void setPrice(Double price) {
        this.price =new SimpleDoubleProperty(price); //change
    }
    public Double getAmount() {
        return amount.get();
    }

    protected void setAmount(Double amount) {
        this.amount =new SimpleDoubleProperty(amount); //change
    }

    public Integer getUnit() {
        return unit.get();
    }

    public void setUnit(Integer unit) {
        this.unit =new SimpleIntegerProperty(unit); //change
    }

    public String toString()
    {
        return String.format("%s %s %s", itemName, description,price);
    }
}
