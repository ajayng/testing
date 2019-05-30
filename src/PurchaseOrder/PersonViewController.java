package PurchaseOrder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class PersonViewController  implements Initializable {

    private Person selectedPerson;
    
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;    
    @FXML private Label birthdayLabel;
    @FXML private Label unitLabel;
    @FXML private Label amountLabel;
    @FXML private ImageView photo;
    
    /**
     * This method accepts a person to initialize the view
     * @param person 
     */
    public void initData(Person person)
    {
        selectedPerson = person;
        firstNameLabel.setText(selectedPerson.getFirstName());
        lastNameLabel.setText(selectedPerson.getLastName());
        birthdayLabel.setText(Double.toString(selectedPerson.getPrice()));  //change with method
        unitLabel.setText(Integer.toString(selectedPerson.getUnit()));
        amountLabel.setText(Double.toString(selectedPerson.getAmount()));

       // ageLabel.setText(Integer.toString(selectedPerson.getAge()));
      //  photo.setImage(selectedPerson.getImage());
    }
    
    
        /**
     * When this method is called, it will change the Scene to 
     * a TableView example
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Location.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
