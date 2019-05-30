package PurchaseOrder

import com.sun.xml.internal.fastinfoset.util.StringArray
import javafx.collections.ObservableArray
import org.postgresql.util.PGmoney
import java.math.BigInteger
import java.sql.Date
import java.sql.DriverManager
import java.sql.ResultSet

open class  DatabaseAccess {
    lateinit var  rs:ResultSet
    var SupplierArray = StringArray()
    var ShipAddressArray= StringArray()

    fun database(stmt:String):ResultSet
    {
        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            if (conn == null) {
                print("hello")
            } else
                print("$conn   ")

            val stmt =conn.prepareStatement(stmt)
            rs=stmt.executeQuery()


            /* while (st.next()) {
                println(st.getString(1))
            } */
            System.out.println("Data Saved Successfully")


            conn.close()

        } catch (e: Exception) {
            System.out.println(e)
        }
        return rs
    }
    fun order_details(itemName: String, description: String, quantity: Integer, price: PGmoney, amount: PGmoney, poDate: Date, supp_no: BigInteger, shipId: String) {


        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            if (conn == null) {
                print("hello")
            } else
                print("$conn   ")

            val stmt = conn.prepareStatement("insert into ds_purchases (item_name,description,Orderqty,price,total_amt,poDate,supp_no,ship_id) values ('$itemName','$description',$quantity,$price,$amount,'$poDate',$supp_no,$shipId)")
            stmt.executeQuery()


            /* while (st.next()) {
                println(st.getString(1))
            } */
            System.out.println("Data Saved Successfully")


            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }
    }

    fun getSupplierName() {

       val st=database("Select supp_name from supplier")

            while (st.next()) {
                var data: String = st.getString(1)

                SupplierArray.add(data)


            }
            var a: Int = 0

            while (a < SupplierArray.size) {

                println(SupplierArray.get(a))
                a++
            }




    }


    fun getSupplierDetails(selected: String): String {
        var data: String = ""


            val st = database("Select additional,city,state,pincode,ph_no from supplier where supp_name='$selected'")

            println(st)

            while (st.next()) {


                data = st.getString(1).capitalize() + " ,\n" + st.getString(2).capitalize() + ", " + st.getString(3).capitalize() + "-" + st.getString(4) + "\nPhone No:" + st.getString(5)


            }

            println(data)

           return data

    }

    fun addOfficeAddress( officename:String , contact_person:String ,Zip:Int, state:String , city:String ) {
        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            if (conn == null) {
                print("hello")
            } else
                print("$conn   ")

            val stmt = conn.prepareStatement("Insert into officelocation (officename,contact_person,state,city,Zip ) values ('$officename', '$contact_person','$state','$city' ,$Zip)")
            val st = stmt.executeQuery()


        } catch (e: Exception) {
            System.out.println(e)
        }
    }

    fun setShipComboBox() {
        var data: String = ""

            val st = database("Select OfficeName from officelocation")

            println(st)

            while (st.next()) {
                var data: String = st.getString(1)

                ShipAddressArray.add(data)


            }
            var a: Int = 0
            while (a < ShipAddressArray.size) {
                println(ShipAddressArray.get(a))
                a++
            }


}




}