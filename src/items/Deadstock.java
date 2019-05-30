package items;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Deadstock implements Initializable {

    @FXML
    private TableView Tblvw;
    @FXML
    private TextField TFSearch;
    @FXML
    private TableColumn<items, Integer> DidColumn;
    @FXML private TableColumn<items, String> DDateColumn;
    @FXML private TableColumn<items, String> DItemNameColumn;
    @FXML private TableColumn<items,Double> DPriceColumn;
    @FXML private TableColumn<items, Integer> DUnitColumn;
    @FXML private TableColumn<items, String> SerialColumn;
    @FXML private TableColumn<items, String> DVendorColumn;
    @FXML private TableColumn<items,Double> DAmountColumn;
    @FXML private TableColumn<items,Button> ButtonColumn;
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
        ButtonColumn.setCellValueFactory(new PropertyValueFactory<items, Button>("Status"));
        Tblvw.setItems(getPeople("Select * from deadstock order by d_id"));
        DidColumn.setEditable(false);
        Tblvw.setEditable(true);
    }

    ObservableList<items> people;
    public ObservableList<items> getPeople(String query)
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
                insert_data vendorName=new insert_data();
                int d_id = st.getInt("d_id");
                String deadStockName = st.getString("d_name");
                String purchaseDate=st.getString("start_date");
                double price=st.getDouble("price");
                int quantity=st.getInt("quantity");
                double t_amount=st.getDouble("t_amount");
                String category=st.getString("Category");

                int vendorID= st.getInt("vendor_id");//
                String vendor="rakesh";
                float current_amount=st.getFloat("current_amount");

                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");
                people.add(new items(d_id,deadStockName,price,quantity,t_amount,current_amount,purchaseDate,category,vendor,new Button("Status")));
                System.out.println( "d_Id:"+d_id+"\n"+"Dname:"+deadStockName+"\n"+"price:"+price+"\n"+"quantity"+quantity+"\n"+"t_amount"+t_amount+"\n"+"current_amount"+current_amount+"\n"+"purchaseDate"+purchaseDate+"\n"+"Category:"+category+"\n"+"Vendor:"+vendor);

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
        Tblvw.setItems(getPeople("Select * from deadstock where d_name=" + "'" +TFSearch.getText().toLowerCase()+ "'" + ";"));
    }
    @FXML
    void refresh()
    {
        Tblvw.setItems(getPeople("select * from deadstock order by d_id"));
    }

}
