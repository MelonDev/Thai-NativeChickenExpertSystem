package th.ac.up.agr.thai_nativechickenexpertsystem.Firebase

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_data.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenDetailData
import th.ac.up.agr.thai_nativechickenexpertsystem.R

class FirebaseAddDataActivity(val context: Context,val itemView : View){

    var path = ArrayList<String>()
    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference


    fun startFunction(){

    }

    fun showInputZone(visibility :Boolean,ID: Int,path:ArrayList<String>){

        this.path = path

        //Toast.makeText(context,path.toString(),Toast.LENGTH_SHORT).show()

        clearAll()

        setPathInput(ID)

        //mainControllInputZone()

        itemView.add_confirm_btn.setOnClickListener {


            var upPath = ArrayList<String>(path)

            //Toast.makeText(context,itemView.nameChicken1.text.toString(),Toast.LENGTH_SHORT).show()

            when (ID){
                0 -> {
                    if (itemView.nameChicken1.text.isNotEmpty() && itemView.nameChicken3.text.isNotEmpty()){
                        upPath.add(itemView.nameChicken1.text.toString())
                        upPath.add(itemView.nameChicken3.text.toString())

                        val male = setValue("ตัวผู้",upPath)
                        val female = setValue("ตัวเมีย",upPath)

                        databaseReference.child("พันธุ์ไก่").child(upPath[0]).child(upPath[1]).child("ตัวผู้").setValue(male)
                        databaseReference.child("พันธุ์ไก่").child(upPath[0]).child(upPath[1]).child("ตัวเมีย").setValue(female)

                        clearAll()

                    }
                }
                1 -> {
                    if (itemView.nameChicken2.text.isNotEmpty() && itemView.nameChicken3.text.isNotEmpty()){
                        upPath.add(itemView.nameChicken2.text.toString())
                        upPath.add(itemView.nameChicken3.text.toString())

                        //Toast.makeText(context,upPath.toString(),Toast.LENGTH_SHORT).show()

                        val male = setValue("ตัวผู้",upPath)
                        val female = setValue("ตัวเมีย",upPath)

                        databaseReference.child(upPath[0]).child(upPath[1]).child(upPath[2]).child(upPath[3]).child("เพศผู้").setValue(male)
                        databaseReference.child(upPath[0]).child(upPath[1]).child(upPath[2]).child(upPath[3]).child("เพศเมีย").setValue(female)

                        clearAll()

                    }
                }
                2 -> {
                    if (itemView.nameChicken3.text.isNotEmpty()){
                        upPath.add(itemView.nameChicken3.text.toString())

                        val male = setValue("ตัวผู้",upPath)
                        val female = setValue("ตัวเมีย",upPath)

                        if (upPath.size == 3){
                            databaseReference.child(upPath[0]).child(upPath[1]).child(upPath[2]).child("เพศผู้").setValue(male)
                            databaseReference.child(upPath[0]).child(upPath[1]).child(upPath[2]).child("เพศเมีย").setValue(female)
                        }
                        if (upPath.size == 4){
                            databaseReference.child(upPath[0]).child(upPath[1]).child(upPath[2]).child(upPath[3]).child("เพศผู้").setValue(male)
                            databaseReference.child(upPath[0]).child(upPath[1]).child(upPath[2]).child(upPath[3]).child("เพศเมีย").setValue(female)
                        }



                        clearAll()

                    }
                }
            }

        }


    }


    private fun setValue(sex :String,path: ArrayList<String>) : ChickenDetailData{
        return ChickenDetailData(path[path.lastIndex]
                ,sex
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                , ""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
                ,""
        )
    }


    private fun clearAll(){
        itemView.nameChicken1.text.clear()
        itemView.nameChicken2.text.clear()
        itemView.nameChicken3.text.clear()
    }

    private fun mainControllInputZone(){
        var nolPath: ArrayList<String> = ArrayList()
        nolPath.add("ลักษณะ")

        val outPath = nolPath
        outPath.add("ภายนอก")
        outSpinner(outPath)

    }

    private fun outSpinner(path :ArrayList<String>){
        setSpinner(path,"ลักษณะหงอน",itemView.male_crestStyle)
    }

    private fun setSpinner(curPath :ArrayList<String>,sPath :String,spinner: Spinner){
        val pathRef = databaseReference.child(curPath[0]).child(curPath[1]).child(sPath)

        pathRef.addListenerForSingleValueEvent(object : ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val arr = onSyncKey(p0)
                val adapter = toAdapter(arr)
                spinner.adapter = adapter
            }
        })
    }

    private fun toAdapter(list: ArrayList<String>): ArrayAdapter<String> {
        val adapter = ArrayAdapter<String>(context, R.layout.spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    private fun onSyncKey(dataSnapshot: DataSnapshot?): ArrayList<String> {
        var arr = ArrayList<String>()
        dataSnapshot!!.children.mapNotNullTo(arr) {
            it.key
        }
        return arr
    }

    private fun setPathInput(ID: Int){
        //Toast.makeText(context,ID.toString(),Toast.LENGTH_SHORT).show()

        when(ID){
            0 -> setPathInputVisibility(View.VISIBLE,View.GONE,View.VISIBLE)
            1 -> setPathInputVisibility(View.GONE,View.VISIBLE,View.VISIBLE)
            2 -> setPathInputVisibility(View.GONE,View.GONE,View.VISIBLE)
            3 -> {
                setPathInputVisibility(View.GONE,View.GONE,View.GONE)
            }
        }
    }

    private fun setPathInputVisibility(p0 :Int,p1 :Int,p2 :Int){
        itemView.createChickenLayout1.visibility = p0
        itemView.createChickenLayout2.visibility = p1
        itemView.createChickenLayout3.visibility = p2
    }


}