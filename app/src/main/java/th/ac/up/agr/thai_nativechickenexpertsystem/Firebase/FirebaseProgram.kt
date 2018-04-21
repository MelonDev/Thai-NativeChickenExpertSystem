package th.ac.up.agr.thai_nativechickenexpertsystem.Firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DataCard

class FirebaseProgram {

    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    val ID = "melondev_icloud_com"

    fun update(data :DataCard){
        setStabilize()
        databaseReference.child("ผู้ใช้").child(ID).child("ข้อมูล").child(data.status).child(data.cardID).setValue(data)
    }

    private fun setStabilize(){
        var id = "0000-00-00-00-00-00"
        databaseReference.child("ผู้ใช้").child(ID).child("ข้อมูล").child("ACTIVE").child(id).setValue(DataCard())
        databaseReference.child("ผู้ใช้").child(ID).child("ข้อมูล").child("INACTIVE").child(id).setValue(DataCard())
    }

    private fun loadAll(){

    }

}