package Reports.Reports;

// Java Program To Call Jasper Report

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

public class report {

    @FXML
   void getreport() {
       //Blank workbook

       //This data needs to be written (Object[])
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Enter File name:");
        Optional<String> path = dialog.showAndWait();

       try
       {
           Class.forName("org.postgresql.Driver");
           java.sql.Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit","postgres", "root");

           PreparedStatement stmt= conn.prepareStatement("select deadstock.d_name,deadstock.quantity,deadstock.t_amount,status.category,status.quantity,status.amount,dep_rates.rate,deadstock.days,deadstock.current_amount from  deadstock  inner join  dep_rates  on dep_rates.id=deadstock.de_id full outer join status on status.d_id=deadstock.d_id where deadstock.category='non-plan';");
           System.out.print(stmt);

           ResultSet st=stmt.executeQuery();
           //Write the workbook in file system
           HSSFWorkbook workbook = new HSSFWorkbook();
           int i=1;
           //Create a blank sheet
           HSSFSheet sheet = workbook.createSheet("Employee Data");
           HSSFRow header=sheet.createRow(0);

           header.createCell(0).setCellValue("S.no");
           header.createCell(1).setCellValue("Name of article");
           header.createCell(2).setCellValue("Quantity");
           header.createCell(3).setCellValue("Total amount");
           header.createCell(4).setCellValue("Category");
           header.createCell(5).setCellValue("Quantity");
           header.createCell(6).setCellValue("amount");
           header.createCell(7).setCellValue("rate");
           header.createCell(8).setCellValue("Days");
           header.createCell(9).setCellValue("Current amount");
           int index=1;
           while (st.next()){
               HSSFRow row=sheet.createRow(index);
               row.createCell(0).setCellValue(i++);
               row.createCell(1).setCellValue(st.getString(1));
               row.createCell(2).setCellValue(st.getString(2));
               row.createCell(3).setCellValue(st.getString(3));
               row.createCell(4).setCellValue(st.getString(4));
               row.createCell(5).setCellValue(st.getString(5));
               row.createCell(6).setCellValue(st.getString(6));
               row.createCell(7).setCellValue(st.getString(7));
               row.createCell(8).setCellValue(st.getString(8));
               row.createCell(9).setCellValue(st.getString(9));
               index++;
           }
           int j=0;


           FileOutputStream out = new FileOutputStream(new File(path.get()+".xlsx"));
           workbook.write(out);
           out.close();
           System.out.println("Plan statement written successfully on disk.");
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "FIle created :"+path.get()+"", ButtonType.OK);
           alert.showAndWait();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }

   }
    @FXML
    void getreport2() {
        //Blank workbook

        //This data needs to be written (Object[])
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Enter File name:");
        Optional<String> path = dialog.showAndWait();

        try
        {
            Class.forName("org.postgresql.Driver");
            java.sql.Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit","postgres", "root");

            PreparedStatement stmt= conn.prepareStatement("select deadstock.d_name,deadstock.quantity,deadstock.t_amount,status.category,status.quantity,status.amount,dep_rates.rate,deadstock.days,deadstock.current_amount from  deadstock  inner join  dep_rates  on dep_rates.id=deadstock.de_id  full outer join status on status.d_id=deadstock.d_id where deadstock.category='plan';");
            System.out.print(stmt);

            ResultSet st=stmt.executeQuery();
            //Write the workbook in file system
            HSSFWorkbook workbook = new HSSFWorkbook();
            int i=1;
            //Create a blank sheet
            HSSFSheet sheet = workbook.createSheet("Employee Data");
            HSSFRow header=sheet.createRow(0);

            header.createCell(0).setCellValue("S.no");
            header.createCell(1).setCellValue("Name of article");
            header.createCell(2).setCellValue("Quantity");
            header.createCell(3).setCellValue("Total amount");
            header.createCell(4).setCellValue("Category");
            header.createCell(5).setCellValue("Quantity");
            header.createCell(6).setCellValue("amount");
            header.createCell(7).setCellValue("rate");
            header.createCell(8).setCellValue("Days");
            header.createCell(9).setCellValue("Current amount");
            int index=1;
            while (st.next()){
                HSSFRow row=sheet.createRow(index);
                row.createCell(0).setCellValue(i++);
                row.createCell(1).setCellValue(st.getString(1));
                row.createCell(2).setCellValue(st.getString(2));
                row.createCell(3).setCellValue(st.getString(3));
                row.createCell(4).setCellValue(st.getString(4));
                row.createCell(5).setCellValue(st.getString(5));
                row.createCell(6).setCellValue(st.getString(6));
                row.createCell(7).setCellValue(st.getString(7));
                row.createCell(8).setCellValue(st.getString(8));
                row.createCell(9).setCellValue(st.getString(9));
                index++;
            }

            FileOutputStream out = new FileOutputStream(new File(path.get()+".xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Plan statement written successfully on disk.");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "FIle created :"+path.get()+"", ButtonType.OK);
            alert.showAndWait();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


   }


