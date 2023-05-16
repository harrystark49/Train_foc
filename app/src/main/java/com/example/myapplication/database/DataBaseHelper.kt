package com.example.myapplication.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.myapplication.entities.DetailsByAgedc
import com.example.myapplication.entities.Passenger
import com.example.myapplication.entities.Train
import com.example.myapplication.entities.TwoValues

class DataBaseHelper(val context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Passengers(first_name VARCHAR2,last_name VARCHAR2,address VARCHAR2,city VARCAHR2,county VARCHAR2,phone varchar2,SSN int primary key,bdate DATE)")
        db?.execSQL("CREATE TABLE Train(train_number int,train_name VARCHAR2,premium_fair varchar2,general_fair varchar2,source_station varchar2,destination_station varchar2,primary key(train_number,train_name),FOREIGN KEY(train_name) references train_status(Train_name))")
        db?.execSQL("CREATE TABLE train_status(train_date date,Train_name varchar2 primary key, PremiumSeatsAvailable int,GenSeatsAvailable int,PremiumSeatsOccupied varchar2,GenSeatsOccupied varchar2)")
        db?.execSQL("create table booked(passenger_ssn int ,train_number int,ticket_type varchar2,status varchar2,primary key(passenger_ssn,train_number), FOREIGN KEY(passenger_ssn) references Passengers(SSN),FOREIGN KEY(train_number) references Train(train_number))")

        db?.execSQL("create trigger updatelist after delete on booked for each row begin update booked set status=\"Booked\" where passenger_ssn=(select passenger_ssn from booked where status =\"WaitL\" limit 1); end;")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReaderdatabaseeDataBaseeeeee.db"
    }

    @SuppressLint("Range")
    fun getBookedTrains(firstName:String, lastName:String): ArrayList<Train> {
        val db=this.readableDatabase
        var list=ArrayList<Train>()
        var details = arrayOf(lastName, firstName)
       var cursor= db.rawQuery("SELECT *" +
                "FROM Passengers p\n" +
                "INNER JOIN booked b ON p.SSN = b.passenger_ssn\n" +
                "INNER JOIN Train t ON b.train_number = t.train_number\n" +
                "WHERE p.last_name = ? AND p.first_name =?",details)

        while(cursor.moveToNext()){
            var trainName=cursor.getString(cursor.getColumnIndex("train_name")).toString()
            var trainNumber=cursor.getString(cursor.getColumnIndex("train_number")).toString()
            var premium_fair=cursor.getString(cursor.getColumnIndex("premium_fair")).toString()
            var general_fair=cursor.getString(cursor.getColumnIndex("general_fair")).toString()
            var source_station=cursor.getString(cursor.getColumnIndex("source_station")).toString()
            var destination_station=cursor.getString(cursor.getColumnIndex("destination_station")).toString()
            list.add(Train(train_number = trainNumber, train_name = trainName,premium_fair,general_fair,source_station,destination_station))
        }
        return list
    }

    @SuppressLint("Range")
    fun getPassengersbyDate(day:String):ArrayList<Passenger>{
        val db=this.readableDatabase
        var list=ArrayList<Passenger>()
        var details = arrayOf("Booked",day)


        var cursor=db.rawQuery("SELECT *" +
                "FROM Passengers p\n" +
                "INNER JOIN booked b ON p.SSN = b.passenger_ssn\n" +
                "INNER JOIN Train t ON b.train_number = t.train_number\n" +
                "INNER JOIN train_status ts ON t.train_name = ts.Train_name\n" +
                "where  b.status=? and ts.train_date=?"
            ,details)

        while (cursor.moveToNext()){
            var fname=cursor.getString(cursor.getColumnIndex("first_name")).toString()
            var lname=cursor.getString(cursor.getColumnIndex("last_name")).toString()
            var address=cursor.getString(cursor.getColumnIndex("address")).toString()
            var city=cursor.getString(cursor.getColumnIndex("city")).toString()
            var county=cursor.getString(cursor.getColumnIndex("county")).toString()
            var phone=cursor.getString(cursor.getColumnIndex("phone")).toString()
            var SSN=cursor.getString(cursor.getColumnIndex("SSN")).toString()
            var bdate=cursor.getString(cursor.getColumnIndex("bdate")).toString()
            list.add(Passenger(fname,lname,address,city,county, phone, SSN, bdate))
        }
        return list
    }

    @SuppressLint("Range")
    fun getInbetween(age:String):ArrayList<DetailsByAgedc>{

        var list=ArrayList<DetailsByAgedc>()
        var arr= arrayOf("now",age,age,"50","60")
        //select ts.Train_name, count(*) as PassengerCount from train_status ts left join Train t on ts.Train_name=t.train_name join booked b on t.train_number=b.train_number group by ts.Train_name
        val db=this.readableDatabase
        var cursor=db.rawQuery("SELECT Train.Train_Number, Train.train_name, Train.Source_Station, Train.Destination_Station, Passengers.first_name, Passengers.last_name, Passengers.address, Booked.Ticket_Type, Booked.Status \n" +
                "                FROM Booked \n" +
                "                JOIN passengers ON Booked.passenger_ssn = Passengers.SSN \n" +
                "                JOIN Train ON Booked.Train_Number = Train.Train_Number \n" +
                "                WHERE strftime('%Y', ?) - CAST(substr(Passengers.bdate,7,4) as INTEGER)="+ "CAST(? as INTEGER) and (CAST(? as INTEGER) between CAST(? as INTEGER) and CAST(? as INTEGER))",arr
        )


//        var arr= arrayOf("now",age)
//        var cursor=db.rawQuery("SELECT Train.Train_Number, Train.train_name, Train.Source_Station, Train.Destination_Station, Passengers.first_name, Passengers.last_name, Passengers.address, Booked.Ticket_Type, Booked.Status \n" +
//                "                FROM Booked \n" +
//                "                JOIN passengers ON Booked.passenger_ssn = Passengers.SSN \n" +
//                "                JOIN Train ON Booked.Train_Number = Train.Train_Number \n" +
//                "                WHERE strftime('%Y', ?) - CAST(substr(Passengers.bdate,7,4) as INTEGER)="+ "CAST(? as INTEGER) and (CAST(? as INTEGER) between CAST(? as INTEGER) and CAST(? as INTEGER))",arr)


        while (cursor.moveToNext()){
            var fname=cursor.getString(cursor.getColumnIndex("first_name")).toString()
            var lastName=cursor.getString(cursor.getColumnIndex("last_name")).toString()
            var address=cursor.getString(cursor.getColumnIndex("address")).toString()
            var train_number=cursor.getString(cursor.getColumnIndex("train_number")).toString()
            var train_name=cursor.getString(cursor.getColumnIndex("train_name")).toString()
            var source_station=cursor.getString(cursor.getColumnIndex("source_station")).toString()
            var destination_station=cursor.getString(cursor.getColumnIndex("destination_station")).toString()
            var ticket_type=cursor.getString(cursor.getColumnIndex("ticket_type")).toString()
            var status=cursor.getString(cursor.getColumnIndex("status")).toString()
            var name=fname+" "+lastName
            list.add(DetailsByAgedc(train_num = train_number,train_name=train_name,source_station=source_station, dest_station = destination_station, name = name, add = address, ticket_status = status, category = ticket_type))
        }



        return list
    }

    @SuppressLint("Range")
    fun getBookPassengerCount(): ArrayList<TwoValues> {
        var db=this.readableDatabase

        val cursor=db.rawQuery("SELECT t.train_name as TrainName, COUNT(b.passenger_ssn) AS PassengerCount\n" +
                "FROM Train t \n" +
                "LEFT JOIN booked b ON t.train_number = b.train_number\n" +
                "GROUP BY t.train_name\n" +
                "HAVING COUNT(b.passenger_ssn) = 0 union all SELECT t.train_name, COUNT(*) AS PassengerCount from train t INNER JOIN booked b ON t.train_number = b.train_number where b.status='Booked' group by t.train_name order by PassengerCount desc", arrayOf()
        )
        var list=ArrayList<TwoValues>()
        while (cursor.moveToNext()){
            var v1=cursor.getString(cursor.getColumnIndex("TrainName")).toString()
            var v2=cursor.getString(cursor.getColumnIndex("PassengerCount")).toString()
            list.add(TwoValues(v1,v2))
        }
        return list
    }

    @SuppressLint("Range")
    fun getPlistFromTrain(trainName:String): ArrayList<Passenger> {
        var db=this.readableDatabase
        var arr= arrayOf(trainName,"Booked")
        var list=ArrayList<Passenger>()


        val cursor=db.rawQuery("SELECT p.*\n" +
                "FROM Passengers p\n" +
                "JOIN booked b ON p.SSN = b.passenger_ssn\n" +
                "JOIN Train t ON t.train_number = b.train_number\n" +
                "WHERE t.train_name = ?\n" +
                "  AND b.status =?",arr)

        while (cursor.moveToNext()){
            var fname=cursor.getString(cursor.getColumnIndex("first_name")).toString()
            var lname=cursor.getString(cursor.getColumnIndex("last_name")).toString()
            var address=cursor.getString(cursor.getColumnIndex("address")).toString()
            var city=cursor.getString(cursor.getColumnIndex("city")).toString()
            var county=cursor.getString(cursor.getColumnIndex("county")).toString()
            var phone=cursor.getString(cursor.getColumnIndex("phone")).toString()
            var SSN=cursor.getString(cursor.getColumnIndex("SSN")).toString()
            var bdate=cursor.getString(cursor.getColumnIndex("bdate")).toString()
            list.add(Passenger(fname,lname,address,city,county, phone, SSN, bdate))

        }
        return list
    }

    fun deleteTicket(ssn:String,train_num:String){
        var arr= arrayOf(ssn,train_num)
        var db=this.writableDatabase
        db.execSQL("delete from booked where passenger_ssn=? and train_number=?",arr)
    }

}