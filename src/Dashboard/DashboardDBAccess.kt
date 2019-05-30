package Dashboard

import java.sql.DriverManager
import java.sql.ResultSet

class DashboardDBAccess
{
    lateinit var rs:ResultSet

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
            System.out.println("Data Saved Successfully")
            conn.close()

        } catch (e: Exception) {
            System.out.println(e)
        }
        return rs
    }

    //Getting the Deadstock

    fun getDSqty():String {
        var quantity: String = ""
        val rs = database("select sum(quantity) from deadstock")
        while (rs.next()) {
            quantity = rs.getInt(1).toString()
        }
        return quantity
    }

        fun getCSqty():String{
            var quantity:String=""
            val rs = database("select sum(quantity) from consumable")
            while(rs.next()) {
                quantity=rs.getInt(1).toString()
            }
            return quantity
    }


    fun getAssignedUnit():String

    {
        var quantity:String=""
        var rs=database("select sum(quantity) from dstock_distribution  ")
        while(rs.next())
        {
            quantity=rs.getInt(1).toString()
        }
        return  quantity
    }

    fun getTotalEmployees():String
    {
        var employees:String=""
        var rs=database("select count(*) from emp_details  ")
        while(rs.next())
        {
            employees=rs.getInt(1).toString()
        }
        return  employees
    }

    fun getTotalLocations():String
    {
        var locations:String=""
        var rs=database("select count(*) from officelocation  ")
        while(rs.next())
        {
            locations=rs.getInt(1).toString()
        }
        return  locations
    }

    fun getTotalVendors():String
    {
        var locations:String=""
        var rs=database("select count(*) from supplier  ")
        while(rs.next())
        {
            locations=rs.getInt(1).toString()
        }
        return  locations
    }

    fun getTotalConsumableCost():String
    {
        var cost:String=""
        var rs=database("select sum(t_amount) from consumable")
        while(rs.next())
        {
            cost=rs.getString(1)
        }
        return cost
    }

    fun getTotalDeadstockcost():String
    {
        var cost:String=""
        var rs=database("select sum(t_amount) from deadstock")
        while(rs.next())
        {
            cost=rs.getString(1)
        }
        return cost
    }


}