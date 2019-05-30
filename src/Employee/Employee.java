package Employee;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import java.math.BigInteger;

public class Employee {

        private SimpleStringProperty employeeName;
    private SimpleStringProperty designation;
    private SimpleStringProperty department;
         //change
        private SimpleStringProperty place,mobileNo;
     //   private SimpleIntegerProperty unit;
       // private SimpleDoubleProperty amount;

        public Employee(String employeeName, String designation,String department, String place,String mobileNo) {
            this.employeeName = new SimpleStringProperty(employeeName);
            this.designation = new SimpleStringProperty(designation);
            this.place = new SimpleStringProperty(place);
            this.mobileNo = new SimpleStringProperty(mobileNo);
            this.department=new SimpleStringProperty(department);
        }




        public String getEmployeeName() {
            return employeeName.get().toUpperCase();
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = new SimpleStringProperty(employeeName.toLowerCase());
        }

        public String getDesignation() {
            return designation.get();
        }

        public void setDesignation(String designation) {
            this.designation = new SimpleStringProperty(designation.toLowerCase());
        }
        public void setPlace(String Place) {
            this.place = new SimpleStringProperty(Place.toLowerCase());
        }
        public String getPlace() {
            return place.get().toLowerCase();
        }
        public String getDepartment() {
            return department.get().toUpperCase();
        }
        public void setMobileNo(String mobileNo) {
            this.mobileNo = new SimpleStringProperty(mobileNo);
        }
        public String getMobileNo() {
            return mobileNo.get();
        }
        public void setDepartment(String department) {
            this.department = new SimpleStringProperty(department.toLowerCase());
        }



    }


