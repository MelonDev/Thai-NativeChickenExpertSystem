package th.ac.up.agr.thai_nativechickenexpertsystem.Firebase

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenDetailData
import com.google.firebase.database.DataSnapshot
import com.squareup.picasso.Picasso
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.Chicken
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenDetailViewHolder
import th.ac.up.agr.thai_nativechickenexpertsystem.R



/**
 * Created by melondev on 26/3/2018 AD.
 */
class FirebaseLoadDetails(val context: Context,val holder :ChickenDetailViewHolder?) {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    val dataSet = ArrayList<Chicken>()

    var chick = Chicken()


    var posi = 0

    //private var database :DatabaseReference = databaseReference

    fun loadData(arr: ArrayList<String>, sex: String, pos: Int) {
        val arrs = setSnapshot(arr, sex)

        posi = pos

        val data = arrs[arrs.lastIndex]
        setRef(data)

        //setRefC(data)


        //return chick
        //return dataSet[0]
    }

    fun loadKey(arr: ArrayList<String>, sex: String) {
        val arrs = setSnapshot(arr, sex)

        val data = arrs[arrs.lastIndex]
        setRef(data)

        //val data = databaseReference.child("พันธุ์ไก่")
    }

    fun setSnapshot(arr: ArrayList<String>, sex: String): ArrayList<DatabaseReference> {

        val database = databaseReference

        val array = ArrayList<DatabaseReference>()

        array.add(database.child("พันธุ์ไก่"))

        for (i in arr) {
            array.add(array[array.lastIndex].child(i))
        }



        array.add(array[array.lastIndex].child(sex))

        //Toast.makeText(context,array.toString(),Toast.LENGTH_SHORT).show()

        return array
    }

    private fun setRef(data: DatabaseReference) {
        data.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                //getKey(p0)
                getData(p0)
            }
        })
    }


    private fun setRefC(databaseReference: DatabaseReference) {
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                getData(p0)
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                getData(p0)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun getData(dataSnapshot: DataSnapshot?) {
/*
        dataSnapshot!!.children.mapNotNullTo(dataSet) {
            //it.getValue(FirebaseClient.DataBlock::class.java)
            it.getValue(Chicken::class.java)
        }*/

        //ใช้ได้
        /*
        dataSnapshot!!.children.forEach {
            Log.e("TEST", it.value.toString())

            //var item : Chicken = it.value as Chicken

            //Log.e("Age",item.ageOfGiveFirstEgg.toString())
        }
*/

//ใช้ได้

        chick = dataSnapshot!!.getValue(Chicken::class.java)!!
        //Log.e("IMAGE",chick.breed)

        setHolder(posi,chick)

        // Log.e("FG", dataSet.size.toString())

    }

    private fun setHolder(position: Int,slot :Chicken) {
        when (position) {
            0 -> {
                allGone(holder)
                holder?.detailA?.visibility = View.VISIBLE

                holder?.A1?.text = slot.breed
            }
            1 -> {
                allGone(holder)
                holder?.detailB?.visibility = View.VISIBLE

                if (slot.image.isNotEmpty()){
                    Picasso.get().load(slot.image).error(R.drawable.unknown_picture).into(holder?.B1Image)
                }



                //val bitmap = BitmapFactory.decodeResource(context.resources, slot.image, option)
                //holder?.B1Image?.setImageBitmap(bitmap)
            }
            2 -> {
                allGone(holder)
                holder?.detailD?.visibility = View.VISIBLE

                holder?.D1?.text = slot.crestStyle
                holder?.D2?.text = slot.faceColor
                holder?.D3?.text = slot.eyeColor
                holder?.D4?.text = slot.mouthColor
                holder?.D5?.text = slot.earringColor
                holder?.D6?.text = slot.necklaceColor
                holder?.D7?.text = slot.wattleColor
                holder?.D8?.text = slot.spurColor
                holder?.D9?.text = slot.tailStyleAndColor
                holder?.D10?.text = slot.bodyFurColor
                holder?.D11?.text = slot.skinColor
                holder?.D12?.text = slot.shinColor
                holder?.D13?.text = slot.nailColor
            }
            3 -> {
                allGone(holder)
                holder?.detailE?.visibility = View.VISIBLE

                holder?.C1?.text = slot.eggShellColor
                holder?.E1?.text = slot.duration
                holder?.E2?.text = slot.ageOfGiveFirstEgg
                holder?.E3?.text = slot.productivityPerYear
                holder?.E4?.text = slot.weight
                holder?.E5?.text = slot.exchangeRateToFood
                holder?.E6?.text = slot.mortalityRate
                holder?.E7?.text = slot.weightForSold
                holder?.E8?.text = slot.birthWeight
            }
            4 -> {
                allGone(holder)
                holder?.detailF?.visibility = View.VISIBLE

                holder?.F1?.text = slot.weightOfGiveFirstEgg
            }
        }
    }

    private fun allGone(holder: ChickenDetailViewHolder?) {
        holder?.detailA?.visibility = View.GONE
        holder?.detailB?.visibility = View.GONE
        holder?.detailC?.visibility = View.GONE
        holder?.detailD?.visibility = View.GONE
        holder?.detailE?.visibility = View.GONE
        holder?.detailF?.visibility = View.GONE
    }


    fun getKey(dataSnapshot: DataSnapshot?) {
        val dataKey = ArrayList<String>()
        dataSnapshot!!.children.mapNotNullTo(dataKey) {
            it.key
        }
        //Log.e("KEY",dataKey.toString())
    }

}