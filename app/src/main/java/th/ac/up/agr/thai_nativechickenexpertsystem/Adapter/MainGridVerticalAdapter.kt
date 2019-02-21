package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import th.ac.up.agr.thai_nativechickenexpertsystem.ChickenDatailActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.SubMainActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Animation
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Path
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder
import java.lang.Exception

class MainGridVerticalAdapter(val context: Context, val data: ArrayList<String>, val ID: Int, var path: String, val title: String) : RecyclerView.Adapter<ChickenBreedViewHolder>() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var arrPath = ArrayList<String>()
    private val abc = ArrayList<String>()

    private var ars = ArrayList<String>()

    override fun onBindViewHolder(holder: ChickenBreedViewHolder, position: Int) {

        holder.gridItemTitle?.text = data[position]

        if (title.isNotEmpty()) {

            //Log.e("PAT",path)
            val arr = Path().fromPathToArray(path)
            //Log.e("PATH",arr.toString())
            //Log.e("DATA",data.toString())

            if (ID == 100){

            } else {
                val dataRef = databaseReference.child("พันธุ์ไก่").child(arr[0]).child(data[position])

                if (ID == 4) {
                    dataRef.addListenerForSingleValueEvent(object : ValueEventListener {

                        override fun onCancelled(p0: DatabaseError) {

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            //onSyncKey(p0)
                            arrPath.clear()
                            p0!!.children.mapNotNullTo(arrPath) {
                                it.key
                            }
                            val p = arrPath[0]

                            //Log.e("ARR",arrPath.toString())
                            val ref = dataRef.child(p)
                            //getImagePath(1,ref)
                            val re = ref.child("เพศผู้").child("image")
                            re.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onCancelled(p0: DatabaseError) {
                                }

                                override fun onDataChange(p0: DataSnapshot) {
                                    val url: String = p0.value.toString()
                                    if (url.isNotEmpty() && !url.contentEquals("null")) {
                                        //Log.e("PASS","PASS")
                                        holder.itemImageCardView2?.visibility = View.GONE

                                        //Glide.with(context).load(url).into(holder?.itemImageView2!!)
                                        Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(holder.itemImageView2!!,object : com.squareup.picasso.Callback {
                                            override fun onSuccess() {
                                                Animation().itemHideAnimation(holder.itemImageCardView2)
                                                holder.itemImageCardView2?.visibility = View.GONE
                                                Animation().itemLoadAnimation(holder.itemImageView2)
                                                holder.itemImageView2.visibility = View.VISIBLE
                                            }

                                            override fun onError(e: Exception?) {
                                                holder.itemImageCardView2?.visibility = View.VISIBLE
                                                holder.itemImageView2.visibility = View.GONE
                                            }
                                        })
                                        //Picasso.with(context).load(url).into(holder?.itemImageView!!)
                                        //Glide.with(context).load(url).centerCrop().placeholder(R.drawable.loading_spinner).into(myImageView)
                                    } else {
                                        holder.itemImageCardView2?.visibility = View.VISIBLE
                                        holder.itemImageView2?.visibility = View.GONE
                                    }
                                }
                            })
                        }
                    })
                } else if (ID == 5) {

                    val re = dataRef.child("เพศผู้").child("image")
                    re.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            val url: String = p0?.value.toString()
                            if (url.isNotEmpty() && !url.contentEquals("null")) {
                                //Log.e("PASS","PASS")
                                holder.itemImageCardView2?.visibility = View.GONE
                                //Glide.with(context).load(url).into(holder?.itemImageView2!!)
                                Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(holder.itemImageView2!!,object : com.squareup.picasso.Callback {
                                    override fun onSuccess() {
                                        Animation().itemHideAnimation(holder.itemImageCardView2)
                                        holder.itemImageCardView2?.visibility = View.GONE
                                        Animation().itemLoadAnimation(holder.itemImageView2)
                                        holder.itemImageView2.visibility = View.VISIBLE
                                    }
                                    override fun onError(e: Exception?) {
                                        holder.itemImageCardView2?.visibility = View.VISIBLE
                                        holder.itemImageView2.visibility = View.GONE
                                    }
                                })
                                //Picasso.with(context).load(url).into(holder?.itemImageView!!)
                                //Glide.with(myFragment).load(url).centerCrop().placeholder(R.drawable.loading_spinner).into(myImageView)
                            } else {
                                holder.itemImageCardView2?.visibility = View.VISIBLE
                                holder.itemImageView2?.visibility = View.GONE
                            }
                            //Log.e("qwe", url.toString())
                            //val detail = p0!!.getValue(ChickenDetailData::class.java)
                            //URLToImageView(url)
                        }
                    })
                }

                else if (ID == 6){
                    //onSyncKey()

                    //Log.e("PAT",path)

                    val dataRefs = databaseReference.child("พันธุ์ไก่").child(arr[0]).child(arr[1])

                    val re = dataRefs.child(data[position]).child("เพศผู้").child("image")
                    re.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            val url: String = p0.value.toString()

                            Log.e("URL ${data[position]}",url)

                            if (url.isNotEmpty() && !url.contentEquals("null")) {
                                //Log.e("PASS","PASS")
                                holder.itemImageCardView2?.visibility = View.GONE

                                //Glide.with(context).load(url).into(holder?.itemImageView2!!)
                                Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(holder.itemImageView2!!, object : com.squareup.picasso.Callback {
                                    override fun onSuccess() {
                                        Animation().itemHideAnimation(holder.itemImageCardView2)
                                        holder.itemImageCardView2?.visibility = View.GONE
                                        Animation().itemLoadAnimation(holder.itemImageView2)
                                        holder.itemImageView2.visibility = View.VISIBLE
                                    }
                                    override fun onError(e: Exception?) {
                                        holder.itemImageCardView2?.visibility = View.VISIBLE
                                        holder.itemImageView2.visibility = View.GONE
                                    }
                                })
                                //Glide.with(myFragment).load(url).centerCrop().placeholder(R.drawable.loading_spinner).into(myImageView)
                            } else {
                                holder.itemImageCardView2?.visibility = View.VISIBLE
                                holder.itemImageView2?.visibility = View.GONE
                            }

                            //Log.e("qwe", url.toString())
                            //val detail = p0!!.getValue(ChickenDetailData::class.java)
                            //URLToImageView(url)
                        }
                    })
                }
            }

        }



        holder.gridItem?.setOnClickListener {

            when (ID) {
                100 -> {
                    val databaseReferences: DatabaseReference = FirebaseDatabase.getInstance().reference
                    val p = databaseReferences.child("พันธุ์ไก่").child(data[position])

                    Log.e("PO",data[position])

                    p.addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onCancelled(p0: DatabaseError) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            onSyncKeys(p0)

                            val au = Path().toPath(path,data[position])
                            val aus = Path().toPath(au,abc[0])

                            //Log.e("abc",abc.toString())

                            val intent = Intent(context, ChickenDatailActivity::class.java)
                            intent.putExtra("ID", 2)

                            if (data[position].contentEquals("ไก่กลาย")){
                                intent.putExtra("TITLE", data[position] + " - " + abc[0])
                            } else {
                                intent.putExtra("TITLE", abc[0])
                            }
                            intent.putExtra("PATH", aus)
                            //Log.e("asa",aus.toString())
                            context.startActivity(intent)
                        }
                    })
                }
                4 -> {
                    val intent = Intent(context, SubMainActivity::class.java)
                    intent.putExtra("ID", 2)
                    intent.putExtra("TITLE", data[position])
                    intent.putExtra("PATH", Path().toPath(path, data[position]))
                    context.startActivity(intent)
                }
                5 -> {
                    val intent = Intent(context, ChickenDatailActivity::class.java)
                    intent.putExtra("ID", 2)
                    intent.putExtra("TITLE", data[position])
                    intent.putExtra("PATH", Path().toPath(path, data[position]))
                    context.startActivity(intent)
                }
                6 -> {
                    val intent = Intent(context, ChickenDatailActivity::class.java)
                    intent.putExtra("ID", 2)
                    intent.putExtra("TITLE", data[position])
                    intent.putExtra("PATH", Path().toPath(path, data[position]))
                    context.startActivity(intent)
                }
            }

        }

/*
        val slot = data[position]

        holder?.gridItemTitle?.text = slot.name

        holder?.gridItem?.setOnClickListener {

            if (position == 0){
                if (ID == 21) {
                    val intent = Intent(context, SubMainActivity::class.java)
                    intent.putExtra("ID", 21)
                    intent.putExtra("TITLE", slot.name)
                    intent.putExtra("POSITION", position)
                    context.startActivity(intent)
                } else if (ID == 22) {
                    val intent = Intent(context, ChickenDatailActivity::class.java)
                    //intent.putExtra("ID", 22)
                    intent.putExtra("TITLE", slot.name)
                    //intent.putExtra("POSITION", position)
                    context.startActivity(intent)
                }

            }else {
                Toast.makeText(context,"จำกัดการใช้งานอยู่ครับผม :D", Toast.LENGTH_SHORT).show()
            }

        }
*/
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun onSyncKey(dataSnapshot: DataSnapshot?) {
        ars.clear()
        dataSnapshot!!.children.mapNotNullTo(ars) {
            it.key
        }
    }

    private fun onSyncKeys(dataSnapshot: DataSnapshot?) {
        abc.clear()
        dataSnapshot!!.children.mapNotNullTo(abc) {
            it.key
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_icon_2, parent, false)


        return ChickenBreedViewHolder(view)
    }
}