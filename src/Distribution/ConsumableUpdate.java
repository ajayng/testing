package Distribution;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ConsumableUpdate implements Initializable {

    @FXML
    private TextField TFname;
    @FXML
    private TextField TFQ;
    @FXML
    TextField TFename;
    @FXML
    TextField TFid;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
         Fillform();


    }



    void Fillform() {
        try {
            Class.forName("org.postgresql.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root");
            java.sql.PreparedStatement stmt = conn.prepareStatement("select * from cstock_distribution where Issue_id=11");
            System.out.print(stmt);
            ResultSet st = stmt.executeQuery();


            while (st.next()) {
                DistributionDBAccess obj1 = new DistributionDBAccess();
                TFname.setText(obj1.getCItem_name(st.getInt(4)));
                TFQ.setText(""+st.getInt(2));
                TFename.setText(obj1.getCEmp_name(st.getString(1)));


                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");

            }
        } catch (Exception e) {
            System.out.print(e);
        }

    }
}
