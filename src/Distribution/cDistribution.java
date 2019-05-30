package Distribution;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class cDistribution {



        private SimpleStringProperty Iname;
        private SimpleIntegerProperty Quantity;
        private SimpleStringProperty Distributee;
        private SimpleStringProperty Date;
        private SimpleIntegerProperty Id;
        //change
        //   private SimpleIntegerProperty unit;
        // private SimpleDoubleProperty amount;

        public cDistribution(String iname, int quantity, String distributee,String date,int id) {
            this.Iname = new SimpleStringProperty(iname);
            this.Quantity = new SimpleIntegerProperty(quantity);
            this.Distributee = new SimpleStringProperty(distributee);
            this.Date=new SimpleStringProperty(date);
            this.Id=new SimpleIntegerProperty(id);
        }


        public String getIssuedate() {
            return Date.get();
        }

        public void setIssueddate(String date) {
            this.Date = new SimpleStringProperty(date);
        }

        public void setId(int id) {
            this.Id.set(id);
        }

        public Integer getId() {
            return Id.get();
        }

        public String getItemName() {
            return Iname.get();
        }

        public void setItemName(String IName) {
            this.Iname = new SimpleStringProperty(IName);
        }

        public void setQuantity1(int quantity) {
            this.Quantity.set(quantity);
        }

        public Integer getQuantity() {
            return Quantity.get();
        }

        public String getDistributee() {
            return Distributee.get();
        }

        public void setDistributee(String IName) {
            this.Distributee = new SimpleStringProperty(IName);
        }

    }

