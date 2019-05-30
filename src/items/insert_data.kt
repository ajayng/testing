package items
import com.sun.xml.internal.fastinfoset.util.StringArray
import javafx.fxml.FXML
import java.sql.Date
import java.sql.DriverManager
import java.text.SimpleDateFormat
import java.util.*
import Distribution.Dcontroller
import com.jfoenix.controls.JFXTextField
import javafx.scene.input.DataFormat
import sun.util.calendar.BaseCalendar
import java.math.BigDecimal
import java.sql.Connection

open class insert_data
{
    var array1=StringArray()
    var asset_array=StringArray()
    var s:String=""
    val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
    fun getdate()
    {
        Class.forName("org.postgresql.Driver")
        val stmt = conn.prepareStatement("select  date_part('day',current_timestamp - date::timestamp) from depreciation" )
         val st=stmt.executeQuery()
         println("statement is $st")
        while(st.next())
        {



        }


        }
     //Function for saving data in deadstock table
    fun add_ds_data(name:String, quantity:Int, price:BigDecimal, amount:BigDecimal,Category: String,date:String,Serial:String,type:Int,Vendor:String) {


        try {
            Class.forName("org.postgresql.Driver")

            val stmt = conn.prepareStatement( "insert into deadstock (d_name,quantity,price,t_amount,category,serial_no,start_date,de_id,supplier_name) values ('$name',$quantity,$price,$amount,'$Category','$Serial','$date',$type,'$Vendor')")
           // val d_id=nextD_id()+1
            //val stmt2="insert into depreciation (d_id,date,rate) values ($d_id,'$date',$rate)"

             stmt.executeQuery()

            /* while (st.next()) {
                println(st.getString(1))
            } */
            System.out.println("Data Saved Successfully ${nextD_id()}")


            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }
    }


    var suppliers=StringArray()
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
            while(st.next())
            {
                var j:Int=0
                     while(j<st.fetchSize)
                    suppliers.add(st.getString(j))




            }
            var i:Int=0
            while (i<suppliers.size) {
            println(suppliers.get(i))
            i++                      }


            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }
    }
    //Method for adding data in consumable table
    fun add_cs_data(name:String, quantity:Int, price:BigDecimal, amount:BigDecimal,Category:String,Date:String) {


        try {
            Class.forName("org.postgresql.Driver")
            val stmt = conn.prepareStatement("insert into consumable (c_name,quantity,price,t_amount,type,date) values ('$name',$quantity,$price,$amount,'$Category','$Date')")
            stmt.executeQuery()
            conn.close()
        } catch (e: Exception) {

        }
    }
    //Method for autocompletion of item name text field
       fun autocomplete(txt:String,Fieldname:String,Type:String)
    {

        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            val stmt = conn.prepareStatement("Select $Fieldname from  $Type where $Fieldname like '$txt%' ")
            val st = stmt.executeQuery()

            println(st)
            while(st.next())
            {
              var data:String=st.getString(1)

                array1.add(data)


            }
            var a:Int=0
            while(a<array1.size)
            {
                println(array1.get(a))
                a++
            }



            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }
    }

    fun autocomplete1(txt:String)
    {

        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            val stmt = conn.prepareStatement("Select c_name from  consumable where c_name like '$txt%' ")
            val st = stmt.executeQuery()

            println(st)
            while(st.next())
            {
                var data:String=st.getString(1)

                array1.add(data)


            }
            var a:Int=0
            while(a<array1.size)
            {
                println(array1.get(a))
                a++
            }



            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }
    }





        fun Depreciation_days (p:String,c:String)
        {
            val myFormat = SimpleDateFormat("yyyy-mm-dd")
            val dateBeforeString = p
            val dateAfterString =  c

            try {
                val dateBefore = myFormat.parse(dateBeforeString)
                val dateAfter = myFormat.parse(dateAfterString)
                val difference = dateAfter.time - dateBefore.time
                val daysBetween = (difference / (1000 * 60 * 60 * 24)).toFloat()
                /* You can also convert the milliseconds to days using this method
           * float daysBetween =
           *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
           */
                println("Number of Days between dates: $daysBetween")
                println()
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    //Function to save supplier details
    fun  Supplier_details(Ph:String,name:String,city:String,state:String,pincode:Number,house:String) {


        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            if (conn == null) {
                print("hello")
            } else
                print("$conn   ")

                val stmt = conn.prepareStatement("insert into supplier (supp_name,ph_no,additional,pincode,state,city) values ('$name','$Ph','$house',$pincode,'$state','$city')")
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
    //updating consumable
    fun consumable(name:String,quantity:Int,amount:Float)
    {
        Class.forName("org.postgresql.Driver")
        val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
        val stmt = conn.prepareStatement("update consumable set quantity=quantity+$quantity,t_amount=t_amount+$amount where c_name='$name'")
        stmt.executeQuery()

    }

    //Method to update current value
    fun updateCurentValue()
    {     var i:Int=1

        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            val stmt = conn.prepareStatement("select * from deadstock left join depreciation on deadstock.d_id=depreciation.d_id")
            val rs=stmt.executeQuery()
            val size=rs.fetchSize

            println(size)


            while(rs.next())
            {


                if(rs.getInt(6)==rs.getInt(13))  {//6,13
                    println(rs.getFloat(6))
                var amount=rs.getFloat(4)
                var dep_amt=rs.getFloat(12)
                    var d_id=rs.getInt(6)
               // println("amount=$amount, dept_amt=$dep_amt,${rs.getString(1)}")
                 //   val stmt=conn.prepareStatement("update deadstock set current_amount=$amount-$dep_amt where d_id=${rs.getInt(6)}")

                   //       stmt.addBatch()
                   update(amount,dep_amt,d_id)

            }     }
            stmt.executeBatch()

            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }

    }

    fun update(amount:Float,dep_amt:Float,d_id:Int)  {

        try {
            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            val stmt = conn.prepareStatement("update deadstock set current_amount=$amount-$dep_amt where d_id=$d_id")
            println("updating d_id=$d_id")
            stmt.executeQuery()

        }
        catch (e:Exception)
        {
            println("")

        }
    }
    fun nextD_id():Int
    {


            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            val stmt = conn.prepareStatement("select max(d_id) from deadstock ")

            val rs=stmt.executeQuery()
           var lastD_id=rs.getInt(1)
           return lastD_id

    }
    fun getVendorName(vendorID:Int):String
    {
        Class.forName("org.postgresql.Driver")
        val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
        val stmt = conn.prepareStatement("select supp_name  from supplier where ph_no=$vendorID")

        val rs=stmt.executeQuery()
        return rs.getString(1)

    }

    fun setCurrentAmount()
    {

        try {
            Class.forName("org.postgresql.Driver")
          //  val s=conn.createStatement()
            val updatedays =conn.prepareStatement("update deadstock set days=abs((EXTRACT(epoch from age(now(), start_date)) / 86400))::int where start_date is not null")
          //  val updatecurrentAmount="update deadstock set current_amount=t_amount * rate from dep_rates * days where deadstock.de_id=(select id from dep_rates)"
            updatedays.executeQuery()
            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }

    }


    fun getAssetTypeCB()
    {
        Class.forName("org.postgresql.Driver")


        val stmt = conn.prepareStatement("select asset_name from dep_rates")
        var rs=stmt.executeQuery()
        while(rs.next())


            while (rs.next()) {
                var data: String = rs.getString(1)

                asset_array.add(data)


            }
        var a: Int = 0

        while (a < asset_array.size) {

            println(asset_array.get(a))
            a++
        }


    }


    fun getDepreciationRate(assetname:String):String
    {
        Class.forName("org.postgresql.Driver")
        var rate=""
        val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
        val stmt = conn.prepareStatement("select rate from dep_rates where asset_name='$assetname'")
        val rs=stmt.executeQuery()
        while (rs.next()) {
            rate=rs.getString(1)
        }
        return rate
    }

    fun getAssetID(assetname:String):Int
    {
        Class.forName("org.postgresql.Driver")
        var id:Int=0
        val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
        val stmt = conn.prepareStatement("select id from dep_rates where asset_name='$assetname'")
        val rs=stmt.executeQuery()
        while (rs.next()) {
            id=rs.getInt(1)
        }
        return id
    }




    fun setDays()
    {

        try {
            Class.forName("org.postgresql.Driver")
            //  val s=conn.createStatement()
            val updatedays =conn.prepareStatement("update deadstock set days=abs((EXTRACT(epoch from age(now(), start_date)) / 86400))::int where start_date is not null")
            //val updatecurrentAmount=conn.prepareStatement("update deadstock set current_amount=t_amount * rate from dep_rates * days where deadstock.de_id=(select id from dep_rates)")
            updatedays.executeQuery()
            conn.close()
        } catch (e: Exception) {
            System.out.println(e)
        }

    }

    fun setCurrentAmt(t_amount:String,rate:String,d_id:Int,days:Int)
    {
        try {
            Class.forName("org.postgresql.Driver")
            //  val s=conn.createStatement()

            val updatecurrentAmount=conn.prepareStatement("update deadstock set current_amount=$t_amount-$t_amount * $rate * $days/365 where d_id=$d_id")
            updatecurrentAmount.executeQuery()
            println("updating $d_id")
        } catch (e: Exception) {
            System.out.println(e)
        }

    }



    fun deleteRecord2(d_id:Int)
    {

        try {

              Class.forName("org.postgresql.Driver")
              val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
              val stmt = conn.prepareStatement("delete from  deadstock where d_id=$d_id")
              stmt.executeQuery()
          }
          catch (e:Exception)
          {

          }
          finally {
              conn.close()
          }
    }

    fun deleteRecord1(c_id:Int)
    {

        try {

            Class.forName("org.postgresql.Driver")
            val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit", "postgres", "root")
            val stmt = conn.prepareStatement("delete from  consumable where c_id=$c_id")
            stmt.executeQuery()
        }
        catch (e:Exception)
        {

        }
        finally {
            conn.close()
        }
    }

    fun depreciationCalculation()
    {
        try {
            Class.forName("org.postgresql.Driver")
            //  val s=conn.createStatement()

            val updatecurrentAmount = conn.prepareStatement("select deadstock.d_name,deadstock.days,deadstock.t_amount,deadstock.d_id,dep_rates.asset_name,deadstock.de_id,dep_rates.rate,dep_rates.id from deadstock inner join dep_rates on deadstock.de_id=dep_rates.id")
            val rs = updatecurrentAmount.executeQuery()
            while (rs.next()) {

                if(rs.getInt("id")==rs.getInt("de_id")) {

                    setCurrentAmt(rs.getString("t_amount"),rs.getString("rate"),rs.getInt("d_id"),rs.getInt("days"))

                }

            }
        }

         catch (e: Exception) {
            System.out.println(e)
        }
    }

    //Method to update the current value if item price is lesser than 5000 and  usage is greater than 366
     fun updateDeadstockvalue(){

         try {
             Class.forName("org.postgresql.Driver")
             //  val s=conn.createStatement()

             val updatecurrentAmount=conn.prepareStatement("update deadstock set current_amount=0 where (t_amount/quantity)<5000 AND days>365")
             updatecurrentAmount.executeQuery()

         } catch (e: Exception) {
             System.out.println(e)
         }
         finally{
         conn.close()
     }    }


    //Put data in status id
    fun status(d_id: Int,quantity: Int,category:String,amount: BigDecimal,date:String){

        try {
            Class.forName("org.postgresql.Driver")
            //  val s=conn.createStatement()

            val stat=conn.prepareStatement("insert into status (d_id,quantity,category,amount,date) values ($d_id,$quantity,'$category',$amount,'$date')")
            declineQuantity(quantity,d_id)
            stat.executeQuery()

        } catch (e: Exception) {
            System.out.println(e)
        }
        finally{

        }    }

      fun declineQuantity(quantity:Int,d_id:Int)
      {
          try {
              Class.forName("org.postgresql.Driver")
              //  val s=conn.createStatement()

              val stat=conn.prepareStatement("update deadstock set quantity=quantity-$quantity where d_id=$d_id")
              stat.executeQuery()

          } catch (e: Exception) {
              System.out.println(e)
          }

          }


      }





    //Retrieving supplier details


