package th.ac.up.agr.thai_nativechickenexpertsystem.Tools

import android.content.Intent
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainMenuHorizontalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.ChickenDatailActivity

class test(val parent: MainMenuHorizontalAdapter,val position: Int) : ValueEventListener {


    override fun onCancelled(p0: DatabaseError?) {

    }

    override fun onDataChange(p0: DataSnapshot?) {
        parent.onSyncKeys(p0)

        val au = Path().toPath(parent.path,parent.data[position])
        val aus = Path().toPath(au,parent.abc[0])

        //Log.e("abc",abc.toString())

        val intent = Intent(parent.context, ChickenDatailActivity::class.java)
        intent.putExtra("ID", 2)

        if (parent.data[position].contentEquals("ไก่กลาย")){
            intent.putExtra("TITLE", parent.data[position] + " - " + parent.abc[0])
        } else {
            intent.putExtra("TITLE", parent.abc[0])
        }
        intent.putExtra("PATH", aus)
        //Log.e("asa",aus.toString())
        parent.context.startActivity(intent)
    }
}