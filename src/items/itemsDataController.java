package items;

import Dashboard.Controller;
import Employee.Employee;
import PurchaseOrder.Person;
import Supplier.supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class itemsDataController extends Controller implements Initializable {
    //Deadstock Table View
    @FXML
    private  TableView Tblvw;
    @FXML private TableColumn<items, Integer> DidColumn;
    @FXML private TableColumn<items, String> DDateColumn;
    @FXML private TableColumn<items, String> DItemNameColumn;
    @FXML private TableColumn<items,Double> DPriceColumn;
    @FXML private TableColumn<items, Integer> DUnitColumn;
    @FXML private TableColumn<items, String> SerialColumn;
    @FXML private TableColumn<items, String> DVendorColumn;
    @FXML private TableColumn<items,Double> DAmountColumn;
    @FXML private TableColumn<items,Button> ButtonColumn;

    //Consumable Table View
    @FXML
    private  TableView Tblvw2;
    @FXML private TableColumn<items, Integer> CidColumn;
    //@FXML private TableColumn<items, String> CDateColumn;
    @FXML private TableColumn<items, String> CItemNameColumn;
    @FXML private TableColumn<items,Double> CPriceColumn;
    @FXML private TableColumn<items, Integer> CUnitColumn;

    @FXML private TableColumn<items, String> CVendorColumn;
    @FXML private TableColumn<items,Double> CAmountColumn;
    @FXML private TextField TFSearch;
    @FXML private TextField CTFSearch;
    @FXML private  Button BtnD;
    @FXML private  Button BtnC;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       DidColumn.setCellValueFactory(new PropertyValueFactory<items, Integer>("D_id"));
        // priceColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("price"));
        DDateColumn.setCellValueFactory(new PropertyValueFactory<items, String>("Date"));
        DItemNameColumn.setCellValueFactory(new PropertyValueFactory<items, String>("ItemName"));
        DPriceColumn.setCellValueFactory(new PropertyValueFactory<items, Double>("price"));
        DUnitColumn.setCellValueFactory(new PropertyValueFactory<items, Integer>("quantity"));
        SerialColumn.setCellValueFactory(new PropertyValueFactory<items, String>("Serial"));
        DVendorColumn.setCellValueFactory(new PropertyValueFactory<items, String>("Vendor"));
        DAmountColumn.setCellValueFactory(new PropertyValueFactory<items, Double>("amount"));
        ButtonColumn.setCellValueFactory(new PropertyValueFactory<items,Button>("Status"));
        Tblvw.setItems(getPeople("Select * from deadstock order by d_id"));
        DidColumn.setEditable(false);
        Tblvw.setEditable(true);
        DidColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        DDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DItemNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        SerialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DUnitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        DVendorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));


        CidColumn.setCellValueFactory(new PropertyValueFactory<items, Integer>("C_id"));
        CItemNameColumn.setCellValueFactory(new PropertyValueFactory<items, String>("CItemName")); //"Citemname" depends on the string after getCitemname
        CPriceColumn.setCellValueFactory(new PropertyValueFactory<items, Double>("price1"));
        CUnitColumn.setCellValueFactory(new PropertyValueFactory<items, Integer>("quantity1"));
      //  SerialColumn.setCellValueFactory(new PropertyValueFactory<items, String>("Serial"));
        //DVendorColumn.setCellValueFactory(new PropertyValueFactory<items, String>("Vendor"));
        CAmountColumn.setCellValueFactory(new PropertyValueFactory<items, Double>("amount1"));

        Tblvw2.setItems(getPeople1("select * from consumable order by c_id"));



        CidColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      //  DDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CItemNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
       // SerialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CUnitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       // DVendorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        Tblvw2.setEditable(true);
        Tblvw.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @FXML
    void searchDeadstock()

    {

            Tblvw.setItems(getPeople("Select * from deadstock where d_name=" + "'" +TFSearch.getText().toLowerCase()+ "'" + ";"));

    }

    @FXML
    void searchConsumable()
    {
        Tblvw2.setItems(getPeople1("Select * from consumable where c_name=" + "'" +CTFSearch.getText().toLowerCase()+ "'" + ";"));
    }

    @FXML
    void showall()
    {
        Tblvw.setItems(getPeople("Select * from deadstock order by d_id"));

    }

    @FXML
            void showallcons()
    {
        Tblvw2.setItems(getPeople1("select * from consumable order by c_id"));
    }
    ObservableList<items> people;
    ObservableList<items> people1;
    /**
     * This method will return an ObservableList of People objects
     */
   public ObservableList<items>  getPeople(String query)
    {
        people = FXCollections.observableArrayList();
        try {
            Class.forName("org.postgresql.Driver");
            java.sql.Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit","postgres", "root");

            PreparedStatement stmt= conn.prepareStatement(query);
            System.out.print(stmt);

            ResultSet st=stmt.executeQuery();


             //select deadstock.d_name ,date from deadstock left join depreciation on deadstock.d_id=depreciation.d_id;

            while(st.next())
            {

                int d_id = st.getInt("d_id");
                String deadStockName = st.getString("d_name");
                String purchaseDate=st.getString("start_date");
                double price=st.getDouble("price");
                int quantity=st.getInt("quantity");
                double t_amount=st.getDouble("t_amount");
                String category=st.getString("Category");

                String Vendor=st.getString("supplier_name"); //

                float current_amount=st.getFloat("current_amount");

                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");
                people.add(new items(d_id,deadStockName,price,quantity,t_amount,current_amount,purchaseDate,category,Vendor,new Button("Status")));
                System.out.println( "d_Id:"+d_id+"\n"+"Dname:"+deadStockName+"\n"+"price:"+price+"\n"+"quantity"+quantity+"\n"+"t_amount"+t_amount+"\n"+"current_amount"+current_amount+"\n"+"purchaseDate"+purchaseDate+"\n"+"Category:"+category+"\n"+"Vendor:"+Vendor);

            }


            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return people;

   }
    public ObservableList<items>  getPeople1(String query)
    {
        people1 = FXCollections.observableArrayList();
        try {
            Class.forName("org.postgresql.Driver");
            java.sql.Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit","postgres", "root");
            Statement s=conn.createStatement();
            PreparedStatement stmt= conn.prepareStatement(query);
            System.out.print(stmt);

            ResultSet st=stmt.executeQuery();


            //select deadstock.d_name ,date from deadstock left join depreciation on deadstock.d_id=depreciation.d_id;
            //c_name | quantity | price  | t_amount |   type    | c_id
            while(st.next())
            {
                insert_data vendorName=new insert_data();
                int c_id = st.getInt("c_id");
                String ConsumableName = st.getString("c_name");
               // String purchaseDate=st.getString("date");
                double price=st.getDouble("price");
                int quantity=st.getInt("quantity");
                double t_amount=st.getDouble("t_amount");
                String category=st.getString("type");



                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");
                people1.add(new items(c_id,ConsumableName,price,quantity,t_amount));
                System.out.println( "dcId:"+c_id+"\n"+"Dname:"+ConsumableName+"\n"+"price:"+price+"\n"+"quantity"+quantity+"\n"+"t_amount"+t_amount+"\n");

            }


            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return people1;

    }

    public void deleteRecord()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Enter ID of deleting record:");
        Optional<String> result = dialog.showAndWait();
        insert_data delete=new insert_data();

        int id=Integer.parseInt(result.get());
        delete.deleteRecord2(id);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Item with id: "+result.get()+ "Successfully deleted", ButtonType.OK);
        alert.showAndWait();
        showall();

    }

    public void deleteRecord2()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Enter ID of deleting record:");
        Optional<String> result = dialog.showAndWait();
        insert_data delete=new insert_data();

        int id=Integer.parseInt(result.get());
        delete.deleteRecord1(id);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Item with id: "+result.get()+ "Successfully deleted", ButtonType.OK);
        alert.showAndWait();
        showall();

    }



    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Entry.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
