package Supplier

import com.sun.xml.internal.fastinfoset.util.StringArray
import java.math.BigInteger
import java.sql.DriverManager

open class databaseMethod {
    val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")

    var suppliers = StringArray()
    fun bringsupplierDetails() {

        try {
            Class.forName("org.postgresql.Driver")

            if (conn == null) {
                print("hello")
            } else
                print("$conn   ")

            val stmt = conn.prepareStatement("Select * from supplier ")
            val st = stmt.executeQuery()

            println(st)
            while (st.next()) {
                var i = 0
                while (i < st.fetchSize)
                    suppliers.add(st.getString(i))
                i++


            }
            println(suppliers)


            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }
    }

    fun update(updateddata: String, mobileNo: String, updatingCol:String ) {
        try {
            Class.forName("org.postgresql.Driver")


            conn.prepareStatement("update supplier set $updatingCol ='$updateddata' where ph_no='$mobileNo'").executeQuery()



            conn.close()
        }

        catch (e: Exception) {
            System.out.println(e)
        }

    }
    fun update(updateddata: BigInteger, mobileNo: BigInteger, updatingCol:String ) {
        try {
            Class.forName("org.postgresql.Driver")


            conn.prepareStatement("update supplier set $updatingCol =$updateddata where ph_no=$mobileNo").executeQuery()


            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }

    }
}