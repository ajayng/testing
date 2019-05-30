package items;

import Dashboard.DashboardContents;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Consumable implements Initializable {

    @FXML
    private TableView Tblvw2;
    @FXML private TableColumn<items, Integer> CidColumn;
    //@FXML private TableColumn<items, String> CDateColumn;
    @FXML private TableColumn<items, String> CItemNameColumn;
    @FXML private TableColumn<items,Double> CPriceColumn;
    @FXML private TableColumn<items, Integer> CUnitColumn;

    @FXML private TableColumn<items, String> CVendorColumn;
    @FXML private TableColumn<items,Double> CAmountColumn;
     @FXML private TextField CTFSearch;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CidColumn.setCellValueFactory(new PropertyValueFactory<items, Integer>("C_id"));
        CItemNameColumn.setCellValueFactory(new PropertyValueFactory<items, String>("CItemName")); //"Citemname" depends on the string after getCitemname
        CPriceColumn.setCellValueFactory(new PropertyValueFactory<items,Double>("price1"));
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

    }

    ObservableList<items> people;
    public ObservableList<items> getPeople1(String query)
    {
        people = FXCollections.observableArrayList();
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
                people.add(new items(c_id,ConsumableName,price,quantity,t_amount));
                System.out.println( "dcId:"+c_id+"\n"+"Dname:"+ConsumableName+"\n"+"price:"+price+"\n"+"quantity"+quantity+"\n"+"t_amount"+t_amount+"\n");

            }


            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return people;

    }

    @FXML
     void searchConsumable()
    {
        Tblvw2.setItems(getPeople1("Select * from consumable where c_name=" + "'" +CTFSearch.getText().toLowerCase()+ "'" + ";"));
    }

    @FXML
    void refresh()
    {
        Tblvw2.setItems(getPeople1("select * from consumable order by c_id"));
    }
}


