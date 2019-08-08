package th.ac.up.agr.thai_nativechickenexpertsystem

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.FragmentActivity
import android.content.ContentValues
import android.database.Cursor


class SQLite(private var context: FragmentActivity) : SQLiteOpenHelper(context, Con.NAME, null, Con.VERSION) {

    var TABLE_NAME = "FARM_DATAS"
    var COL_FARMER_NAME = "FARMER_NAME"
    var COL_FARM_NAME = "FARM_NAME"
    var COL_ADDRESS = "ADDRESS"
    var COL_PATH = "PATH"




    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_FARMER_NAME + " TEXT," + COL_FARM_NAME + " TEXT," + COL_ADDRESS + " TEXT," + COL_PATH + " TEXT);")
        //db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_FARM_NAME +","+") VALUES (" + R.style.MelonTheme_Amber_Material + ");")

        //val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COL_FARMER_NAME, "")
        contentValues.put(COL_FARM_NAME, "")
        contentValues.put(COL_ADDRESS, "")
        contentValues.put(COL_PATH,"")
        db.insert(TABLE_NAME, null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")

        onCreate(db)
    }

    fun update(db: SQLiteDatabase?, sqmodel : SQModel) {
        val cv = ContentValues()

        if(sqmodel.farmerName.isNotEmpty() && sqmodel.farmName.isNotEmpty()){
            cv.put(COL_FARMER_NAME, sqmodel.farmerName)
            cv.put(COL_FARM_NAME,sqmodel.farmName)
            cv.put(COL_ADDRESS,sqmodel.address)
            cv.put(COL_PATH,sqmodel.path)
            db!!.update(TABLE_NAME, cv, "_id=" + 1, null)
        }

    }

    fun read() :SQModel{

        val sqmodel = SQModel()

        var database: SQLite = SQLite(context)
        var sqLiteDatabase: SQLiteDatabase = database.writableDatabase
        var cursor: Cursor = sqLiteDatabase.rawQuery(("SELECT " + database.COL_FARMER_NAME + ", "+ database.COL_FARM_NAME+", "+database.COL_ADDRESS +", "+database.COL_PATH + " FROM " + database.TABLE_NAME), null)
        cursor.moveToFirst()
        sqmodel.farmerName = cursor.getString(cursor.getColumnIndex(database.COL_FARMER_NAME))
        sqmodel.farmName = cursor.getString(cursor.getColumnIndex(database.COL_FARM_NAME))
        sqmodel.address = cursor.getString(cursor.getColumnIndex(database.COL_ADDRESS))
        sqmodel.path = cursor.getString(cursor.getColumnIndex(database.COL_PATH))

        return sqmodel
    }

    fun isNull() :Boolean {
        var database: SQLite = SQLite(context)
        var sqLiteDatabase: SQLiteDatabase = database.writableDatabase
        var cursor: Cursor = sqLiteDatabase.rawQuery(("SELECT " + database.COL_FARMER_NAME + " FROM " + database.TABLE_NAME), null)
        cursor.moveToFirst()
        var x :String = cursor.getString(cursor.getColumnIndex(database.COL_FARMER_NAME))

        return x.isEmpty()
    }

    class Con {
        companion object {
            val NAME = "FARM_DATAS"
            val VERSION = 1
        }
    }

    class SQModel {
        var farmerName :String = ""
        var farmName :String = ""
        var address :String = ""
        var path :String = ""
    }
}