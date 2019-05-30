package Employee

import com.sun.xml.internal.fastinfoset.util.StringArray
import java.math.BigInteger
import java.sql.DriverManager

open class DatabaseAccess {

     var  dept_id:Int=0
    var DepartmentArray = StringArray()
    var LocationArray = StringArray()
    val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")

    fun addDetails(name: String, emp_dept: Integer, emp_place: String, designation: String, phoneno: String) {

        try {
            Class.forName("org.postgresql.Driver")
            val stmt = conn.prepareStatement("insert into  emp_details(emp_name,emp_dept,emp_place,designation,phone_no ) values ('$name',$emp_dept,'$emp_place','$designation',$phoneno)")
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

    fun update(updateddata: String, mobileNo: String, updatingCol:String ) {
        try {
            Class.forName("org.postgresql.Driver")


             conn.prepareStatement("update emp_details set $updatingCol ='$updateddata' where phone_no='$mobileNo'").executeQuery()







            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }

    }
    fun update(updateddata: BigInteger, mobileNo: BigInteger, updatingCol:String ) {
        try {
            Class.forName("org.postgresql.Driver")


            conn.prepareStatement("update emp_details set $updatingCol =$updateddata where phone_no=$mobileNo").executeQuery()







            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }

    }
    fun getMobileNo(name: String):String
    {
            var phone_no=""
        try {
            Class.forName("org.postgresql.Driver")


            val stmt = conn.prepareStatement("select phone_no from emp_details where emp_name='$name'")
            var rs=stmt.executeQuery()
            while(rs.next())
            {
                phone_no=rs.getString(1)
            }



            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }
        return  phone_no
    }

    //Method for adding department
    fun addDepartment(Department: String) {
        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            if (conn == null) {
                print("hello")
            } else
                print("$conn   ")

            val stmt = conn.prepareStatement("Insert into department (department_name) values ('$Department')")
            stmt.executeQuery()


        } catch (e: Exception) {
            System.out.println(e)
        }
    }

    //Method to fill Deparment combobox from database

    fun getDepartmentName() {

        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            if (conn == null) {
                print("hello")
            } else
                print("$conn   ")

            val stmt = conn.prepareStatement("select department_name from department order by department_name asc")
            val st=stmt.executeQuery()
            while (st.next()) {
                var data: String = st.getString(1)

                DepartmentArray.add(data)


            }
            var a: Int = 0

            while (a < DepartmentArray.size) {

                println(DepartmentArray.get(a))
                a++
            }


        } catch (e: Exception) {
            System.out.println(e)
        }
    }

// Method to get the the department_id

    fun Department_Id(name: String):Int
    {

        try {
            Class.forName("org.postgresql.Driver")


            val stmt = conn.prepareStatement("select department_id from department  where department_name='$name'")
            var rs=stmt.executeQuery()
            while(rs.next())
            {
                dept_id=rs.getInt(1)
            }



            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }
        return  dept_id
    }

    fun getLocations()
    {
        Class.forName("org.postgresql.Driver")


        val stmt = conn.prepareStatement("select officename from officelocation")
        var rs=stmt.executeQuery()
        while(rs.next())


        while (rs.next()) {
            var data: String = rs.getString(1)

            LocationArray.add(data)


        }
        var a: Int = 0

        while (a < LocationArray.size) {

            println(LocationArray.get(a))
            a++
        }


    }

    fun getDepartment_id(dname:String):String
    {
        Class.forName("org.postgresql.Driver")
        var id=""
        val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
        val stmt = conn.prepareStatement("Select * from department where department_id='$dname'")
        val rs=stmt.executeQuery()
        while(rs.next())
        {
          id=rs.getString(1)
        }
        return id

    }


}