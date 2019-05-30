package items;

import Dashboard.Controller;
import Distribution.DistributionDBAccess;
import PurchaseOrder.DatabaseAccess;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Currency;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import sun.misc.FloatingDecimal;

import java.util.Date;

public class icontroller  {

    ObservableList<String> CTypeList=FXCollections.observableArrayList("Stationary","Edibles","Cleaners","Soaps","Others");
    ObservableList<String> DepreciationType=FXCollections.observableArrayList("Custom");
    ObservableList<String> Category=FXCollections.observableArrayList("Plan","Non-plan");
    TextField TFrate=new TextField();
    int d_id=0;
    Double c;
    float price;
    float amount;
    float depreciation;
    BigDecimal b1=new BigDecimal("0.00");
    int assetid;


   private  @FXML
    JFXTextField TFPrice;
    @FXML
    JFXTextField CTFPrice;
    @FXML
    JFXTextField TFUnit;
    @FXML
    JFXTextField CTFUnit;
    @FXML
    JFXTextField TFAmount;
    @FXML
    JFXTextField CTFAmount;
    @FXML
    ComboBox CBDepreciation;
    @FXML
    ComboBox CBVendor;
    @FXML
    Button Save_btn;
    @FXML
    Button clearButton;

    @FXML
    JFXTextField TFName;
    @FXML
    TextField CTFName;
    @FXML
    DatePicker TFDate;
    @FXML
    TextField TFId;
    @FXML
    TextField TFDep;
    @FXML
    TextField TFSerial;
    @FXML
    DatePicker CTFDate;
    @FXML
    GridPane DepreciationControl;
    @FXML
    ComboBox CBCategory;
    @FXML
    ComboBox TFPurchaseType;
    @FXML
    ComboBox CBLocation;
@FXML
 private ComboBox CBType;
@FXML private TextField TFVendor;
@FXML
private ComboBox CCBType;
    public void initialize(){   //upright from now i will be broken up but now i do
        CCBType.setItems(CTypeList);
        CBCategory.setItems(Category.sorted());
        setAssetTypeCB();

        initializeLocationCB();
        DatabaseAccess supplierDetails=new DatabaseAccess();
        supplierDetails.getSupplierName();
        ObservableList<String> vendor = FXCollections.observableArrayList();
        vendor.addAll(supplierDetails.getSupplierArray().getArray());
        int s=supplierDetails.getSupplierArray().getSize();
        //CBVendor.setItems(vendor);
        //CBVendor.visibleRowCountProperty().setValue(s);

        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        TFDate.setConverter(converter);

    }


    @FXML
    void fun () {
        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
        insert_data obj1=new insert_data();
        obj1.autocomplete1(CTFName.getText());
        int a=0;
        while(a<obj1.getArray1().getSize()) {
            autoCompletePopup.getSuggestions().add(obj1.getArray1().get(a));
            a++;
        }
        autoCompletePopup.setSelectionHandler(event -> {
            CTFName.setText(event.getObject());
            DistributionDBAccess id=new DistributionDBAccess();
           // Itemid=id.getID(Iname.getText(),Type,FieldName,FieldID);
           // lblItemId.setVisible(true);
            //TFID.setVisible(true);
            //TFID.setText(""+Itemid);
            // you can do other actions here when text completed
        });

        // filtering options
        CTFName.textProperty().addListener(observable -> {
            autoCompletePopup.filter(string -> string.toLowerCase().contains(CTFName.getText().toLowerCase()));
            if (autoCompletePopup.getFilteredSuggestions().isEmpty() || CTFName.getText().isEmpty()) {
                autoCompletePopup.hide();
                // if you remove textField.getText.isEmpty() when text field is empty it suggests all options
                // so you can choose
            } else {
                autoCompletePopup.show(CTFName);
            }
        });
    }

    @FXML
    void updateConsumable()
    {
        int quantity=Integer.parseInt(CTFUnit.getText());
        float amount=Float.parseFloat(CTFAmount.getText());
       insert_data obj1=new insert_data();
       obj1.consumable(CTFName.getText(),quantity,amount);


    }

    @FXML
    void FillDepreciationControl()
    {
        if(CBDepreciation.getSelectionModel().isSelected(0))
        {  Label lblRate=new Label("Depreciation Rate");

           DepreciationControl.add(lblRate,0,1);
           DepreciationControl.add(TFrate,2,1);
        }
    }


    @FXML
    void  Calculate_TA(JFXTextField TF1,JFXTextField TF2, JFXTextField TF3)   // Calculates Total amount
    {
        try {


            Float price = Float.parseFloat(TF1.getText());
            TF2.textProperty().addListener((observable, oldValue, newValue) -> {


                Float c = price * Integer.parseInt(newValue);
                TF3.setText("" + new BigDecimal((c * 100) / 100.0).setScale(2,BigDecimal.ROUND_CEILING));
                System.out.println(c);
            });
        }
        catch (Exception E)
        {

        }

    }
    @FXML
    void getDepreciationitems()
    {
        insert_data obj=new insert_data();
        obj.depreciationCalculation();
    }

    @FXML
    void cal_d_amt()
    {
        Calculate_TA(TFPrice,TFUnit,TFAmount);
    }
    @FXML
    void cal_c_amt()
    {
        Calculate_TA(CTFPrice,CTFUnit,CTFAmount);
    }

    @FXML
    void putdata_deadstock()
    {
        try {
            String name = TFName.getText();
            int quantity = Integer.parseInt(TFUnit.getText());
            //BigDecimal result2=new BigDecimal(CTFPrice.getText()).setScale(2,BigDecimal.ROUND_CEILING);
            BigDecimal price = new BigDecimal(TFPrice.getText()).setScale(2, BigDecimal.ROUND_CEILING);
            BigDecimal amount = new BigDecimal(TFAmount.getText()).setScale(2, BigDecimal.ROUND_CEILING);
            String StartDate = TFDate.getValue().toString();

            String Category = CBCategory.getValue().toString();
            String Serial = TFSerial.getText();
            insert_data obj1 = new insert_data();
            obj1.add_ds_data(name.toLowerCase(), quantity, price, amount, Category.toLowerCase(), StartDate, Serial.toLowerCase(),assetid,TFVendor.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data Added: \nItem name: "+name+"\n"+"Quantity: "+quantity+"\n"+"Amount: "+amount , ButtonType.OK);
            alert.showAndWait();
           }
        catch (Exception E)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please check all the fields", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void initializeLocationCB()
    {
        DatabaseAccess shippingDetails=new DatabaseAccess();
        shippingDetails.setShipComboBox();
        ObservableList<String> shipAddress = FXCollections.observableArrayList();
        shipAddress.addAll(shippingDetails.getShipAddressArray().getArray());
        int s1=shippingDetails.getShipAddressArray().getSize();
        CBLocation.setItems(shipAddress);
        CBLocation.visibleRowCountProperty().setValue(s1);
    }


    @FXML
    void  putdata_consumable()
    {
        try {
            insert_data obj2 = new insert_data();
            String name = CTFName.getText();
            int quantity = Integer.parseInt(CTFUnit.getText());
            BigDecimal price = new BigDecimal(CTFPrice.getText()).setScale(2, BigDecimal.ROUND_CEILING);
            BigDecimal amount = new BigDecimal(CTFAmount.getText()).setScale(2, BigDecimal.ROUND_CEILING);
            String Category = CCBType.getSelectionModel().getSelectedItem().toString();
            String Date = CTFDate.getValue().toString();
            System.out.print(Date);
            obj2.add_cs_data(name.toLowerCase(), quantity, price, amount, Category.toLowerCase(), Date);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data Added: \n Item name: "+name+"\n"+"Quantity: "+quantity+"\n"+"Amount: "+amount , ButtonType.OK);
            alert.showAndWait();
            }
        catch (Exception E)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please check all the fields", ButtonType.OK);
            alert.showAndWait();
        }
    }

    //Method to autoupdate current value
     void updateCurValue()
     {
         insert_data update=new insert_data();
         update.updateCurentValue();
     }

     //Method to calculate depreciaiton


    //Method to fill asset type Combo Box

    void setAssetTypeCB()
    {
        insert_data asset=new insert_data();
        asset.getAssetTypeCB();
        ObservableList<String> assets=FXCollections.observableArrayList();
        assets.addAll(asset.getAsset_array().getArray());
        CBType.setItems(assets.sorted());

    }


    @FXML
    void ClearDeadstock()
    {
        TFPrice.clear();
        TFUnit.clear();
        TFAmount.clear();
        TFSerial.clear();
        TFDep.clear();
        CBType.getSelectionModel().clearSelection();
        TFName.clear();
        CBCategory.getSelectionModel().clearSelection();

    }

    @FXML
    void ClearConsumable()
    {
        CTFPrice.clear();
        CTFAmount.clear();
        CTFUnit.clear();
        CTFName.clear();
        CCBType.getSelectionModel().clearSelection();
    }


    @FXML
    void getDepreciationRate()
    {
       insert_data rateobj=new insert_data();
       depreciation=Float.parseFloat(rateobj.getDepreciationRate(CBType.getSelectionModel().getSelectedItem().toString()));
       TFDep.setText(""+depreciation);
       assetid=rateobj.getAssetID(CBType.getSelectionModel().getSelectedItem().toString());


    }

  }


