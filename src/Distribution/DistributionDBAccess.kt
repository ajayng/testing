package Distribution

import com.sun.xml.internal.fastinfoset.util.StringArray
import java.math.BigInteger
import java.sql.DriverManager

class DistributionDBAccess {


    val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
    var EmployeeArray=StringArray()
    var EmployeeIdArray=StringArray()

    fun getEmployeeName() {

    try {
        Class.forName("org.postgresql.Driver")
        val stmt = conn.prepareStatement("Select phone_no,emp_name from emp_details")
        val st=stmt.executeQuery()

        while (st.next()) {
            var data: String = st.getString(2)
            var idData=st.getString(1)
            EmployeeArray.add(data)
            EmployeeIdArray.add(idData)

        }

        /* while (st.next()) {
            println(st.getString(1))
        } */
        System.out.println("Data Saved Successfully")


        conn.close()
    } catch (e: Exception) {
        System.out.println(e)
    }


    var a: Int = 0

    while (a < EmployeeArray.size) {

        println(EmployeeArray.get(a))
        a++
    }
}


    fun getEmployeeID(empName:String):String
    {
        Class.forName("org.postgresql.Driver")
        var id=""
        val stmt = conn.prepareStatement("Select phone_no from emp_details where emp_Name='$empName'")
        val st=stmt.executeQuery()
        while(st.next()) {
             id=st.getString(1)
        }
        return  id
    }

    fun getQuantity(itemname:String,type:String,Field:String):Int
    {

        Class.forName("org.postgresql.Driver")
        var id=0
        val stmt = conn.prepareStatement("Select quantity from $type where $Field='$itemname' ")
        val st=stmt.executeQuery()
        while(st.next()) {
            id=st.getInt(1)

        }
        return  id
    }

    fun getAmount(itemname:String,type:String,Field:String):Double
    {

        Class.forName("org.postgresql.Driver")
        var id=0.0
        val stmt = conn.prepareStatement("Select price from $type where $Field='$itemname' ")
        val st=stmt.executeQuery()
        while(st.next()) {
            id=st.getDouble("price")

        }
        return  id
    }


    fun putDistributionDetails(emp_id:BigInteger,type:String,itemID:Int,quantity:Int,issuedDate:String,empName: String,itemName: String,amount:Double )
    {
        if(type=="Deadstock")
        {

            try {
                Class.forName("org.postgresql.Driver")
                val stmt = conn.prepareStatement("insert into dstock_distribution (emp_id,d_id,quantity,issued_date) values ($emp_id,$itemID,$quantity,'$issuedDate')")
                 stmt.executeQuery()
            }
            catch (e:Exception)
            {
                System.out.print(e)
            }
            conn.close(); //change


        }
        else
        {
            try {
                Class.forName("org.postgresql.Driver")
                val s = conn.createStatement()
                val stmt = " insert into cstock_distribution (c_id,emp_id,quantity,issued_date) values ($itemID,$emp_id,$quantity,'$issuedDate')"
                s.addBatch(stmt)
                val stmt2 = "update consumable set quantity=quantity-$quantity,t_amount=t_amount-$amount where c_id=$itemID"
                s.addBatch(stmt2)
                s.executeBatch()
            }
            catch (e:Exception )
            {

                System.out.println(e)
                conn.close();   //change
            }

        }
    }
    fun getID(itemName:String,type:String,Field: String,ID:String):Int
    {
        Class.forName("org.postgresql.Driver")
        var id=0
        val stmt = conn.prepareStatement("Select $ID from $type where $Field='$itemName' ")
        val st=stmt.executeQuery()
        while(st.next()) {
            id=st.getInt(1)

        }
        return  id
    }

    fun getEmp_name(empid:String):String
    {
        Class.forName("org.postgresql.Driver")
        var id:String=""
        val stmt = conn.prepareStatement("Select emp_name from emp_details where phone_no=$empid ")
        val st=stmt.executeQuery()
        while(st.next()) {
            id=st.getString(1)

        }
        return  id
        conn.close() //change
    }

    fun getItem_name(d_id:Int):String
    {
        Class.forName("org.postgresql.Driver")
        var id:String=""
        val stmt = conn.prepareStatement("Select d_name from deadstock where d_id=$d_id ")
        val st=stmt.executeQuery()
        while(st.next()) {
            id=st.getString(1)

        }
        println(id)
        return  id
        conn.close()   //change

    }

    fun getCItem_name(c_id:Int):String
    {
        Class.forName("org.postgresql.Driver")
        var id:String=""
        val stmt = conn.prepareStatement("Select c_name from consumable where c_id=$c_id ")
        val st=stmt.executeQuery()
        while(st.next()) {
            id=st.getString(1)

        }
        println(id)
        return  id
        conn.close()  //change
    }

    fun getCEmp_name(empid:String):String
    {
        Class.forName("org.postgresql.Driver")
        var id:String=""
        val stmt = conn.prepareStatement("Select emp_name from emp_details where phone_no=$empid ")
        val st=stmt.executeQuery()
        while(st.next()) {
            id=st.getString(1)

        }
        return  id
        conn.close() //change
    }

    fun update(updateddata: String, mobileNo: String, updatingCol:String ) {
        try {
            Class.forName("org.postgresql.Driver")


            conn.prepareStatement("update deadstock set $updatingCol ='$updateddata' where ph_no='$mobileNo'").executeQuery()



            conn.close()
        }

        catch (e: Exception) {
            System.out.println(e)
        }

    }

    fun deleteRecord2(d_id:Int)
    {
        try {

            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            val stmt = conn.prepareStatement("delete from deadstock where d_id=$d_id")
            stmt.executeQuery()
        }
        catch (e:Exception)
        {

        }
        finally {
            conn.close()
        }
    }





}