package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import th.ac.up.agr.thai_nativechickenexpertsystem.ChickenDatailActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenDetailData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.SubMainActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Animation
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Path
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.test
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder
import java.lang.Exception
import android.os.Environment.getExternalStorageDirectory
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import com.bumptech.glide.request.RequestOptions
import android.R.attr.data
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy


class MainMenuHorizontalAdapter(val context: Context, val data: ArrayList<String>, val mainTitle: String, val path: String) : RecyclerView.Adapter<ChickenBreedViewHolder>() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var arrPath = ArrayList<String>()
    private lateinit var holder: ChickenBreedViewHolder
    private var dataSet = ArrayList<ChickenDetailData>()
    private var dataKey = ArrayList<String>()
    val abc = ArrayList<String>()
    private val abcde = ArrayList<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.menu_icon, parent, false)

        return ChickenBreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChickenBreedViewHolder, position: Int) {

        //this.holder = holder!!

        //Log.e("data",data[position])
        val dataRef = databaseReference.child("พันธุ์ไก่").child(mainTitle).child(data[position])

        if (mainTitle.contentEquals("พันธุ์อื่นๆ")) {
            Log.e("asdla", data.toString())
        } else if (data[position].contentEquals("ไก่ชน") or mainTitle.contentEquals("ไก่ชน")) {
            //getImagePath(0, dataRef)

            dataRef.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError?) {

                }

                override fun onDataChange(p0: DataSnapshot?) {
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
                        override fun onCancelled(p0: DatabaseError?) {
                        }

                        override fun onDataChange(p0: DataSnapshot?) {
                            val url: String = p0?.value.toString()
                            if (url.isNotEmpty() && !url.contentEquals("null")) {
                                //Log.e("PASS","PASS")

                                //holder.itemImageCardView?.visibility = View.GONE
/*
                                Glide.with(context)
                                        .load(url)
                                        .apply { RequestOptions()
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        }
                                        .listener(object : RequestListener<Drawable> {
                                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                                holder.itemImageCardView?.visibility = View.VISIBLE
                                                holder.itemImageView?.visibility = View.GONE
                                                return false
                                            }

                                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                                Animation().itemLoadAnimation(holder.itemImageView)
                                                holder.itemImageView?.visibility = View.VISIBLE
                                                Animation().itemHideAnimation(holder.itemImageCardView)
                                                holder.itemImageCardView?.visibility = View.GONE
                                                return false
                                            }
                                        })

                                        .into(holder.itemImageView!!)
*/
/*
                                Picasso.get().load(url).into(holder?.itemImageView!!,object : com.squareup.picasso.Callback {
                                    override fun onSuccess() {

                                        Animation().itemLoadAnimation(holder.itemImageView)
                                        holder.itemImageView?.visibility = View.VISIBLE
                                        Animation().itemHideAnimation(holder.itemImageCardView)
                                        holder.itemImageCardView?.visibility = View.GONE
                                    }
                                    override fun onError(e: Exception?) {
                                        holder.itemImageCardView?.visibility = View.VISIBLE
                                        holder.itemImageView?.visibility = View.GONE
                                    }
                                })
*/

                                Picasso.get().load(url).fetch(object : Callback{
                                    override fun onSuccess() {
                                        Picasso.get().load(url).into(holder?.itemImageView!!)
                                        Animation().itemLoadAnimation(holder.itemImageView)
                                        holder.itemImageView?.visibility = View.VISIBLE
                                        Animation().itemHideAnimation(holder.itemImageCardView)
                                        holder.itemImageCardView?.visibility = View.GONE
                                        /*
                                        Picasso.get().load(url).into(holder?.itemImageView!!,object : com.squareup.picasso.Callback {
                                            override fun onSuccess() {

                                                Animation().itemLoadAnimation(holder.itemImageView)
                                                holder.itemImageView?.visibility = View.VISIBLE
                                                Animation().itemHideAnimation(holder.itemImageCardView)
                                                holder.itemImageCardView?.visibility = View.GONE
                                            }
                                            override fun onError(e: Exception?) {
                                                holder.itemImageCardView?.visibility = View.VISIBLE
                                                holder.itemImageView?.visibility = View.GONE
                                            }
                                        })
                                        */
                                    }

                                    override fun onError(e: Exception?) {
                                        holder.itemImageCardView?.visibility = View.VISIBLE
                                        holder.itemImageView?.visibility = View.GONE
                                    }
                                })

                                //Picasso.with(context).load(url).into(holder?.itemImageView!!)
                                //Glide.with(context).load(url).centerCrop().placeholder(R.drawable.loading_spinner).into(myImageView)
                            } else {
                                holder?.itemImageCardView?.visibility = View.VISIBLE
                                holder?.itemImageView?.visibility = View.GONE
                            }
                        }
                    })
                }
            })
        } else {
            //getImagePath(1, dataRef)

            val re = dataRef.child("เพศผู้").child("image")
            re.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {
                }

                override fun onDataChange(p0: DataSnapshot?) {
                    val url: String = p0?.value.toString()
                    if (url.isNotEmpty() && !url.contentEquals("null")) {
                        //Log.e("PASS","PASS")
                        holder?.itemImageCardView?.visibility = View.GONE
                        //Glide.with(context).load(url).into(holder?.itemImageView!!)
                        Picasso.get().load(url).into(holder?.itemImageView!!, object : com.squareup.picasso.Callback {
                            override fun onSuccess() {
                                Animation().itemLoadAnimation(holder.itemImageView)
                                holder.itemImageView?.visibility = View.VISIBLE
                                Animation().itemHideAnimation(holder.itemImageCardView)
                                holder.itemImageCardView?.visibility = View.GONE
                            }

                            override fun onError(e: Exception?) {
                                holder.itemImageCardView?.visibility = View.VISIBLE
                                holder.itemImageView?.visibility = View.GONE
                            }
                        })
                        //Picasso.with(context).load(url).into(holder?.itemImageView!!)
                        //Glide.with(myFragment).load(url).centerCrop().placeholder(R.drawable.loading_spinner).into(myImageView)
                    } else {
                        holder?.itemImageCardView?.visibility = View.VISIBLE
                        holder?.itemImageView?.visibility = View.GONE
                    }
                    //Log.e("qwe", url.toString())
                    //val detail = p0!!.getValue(ChickenDetailData::class.java)
                    //URLToImageView(url)
                }
            })
        }

        holder?.mainNameTitle?.text = data[position]


        holder?.mainMenuItem?.setOnClickListener {
            if (mainTitle.contentEquals("พันธุ์อื่นๆ")) {
                val databaseReferences: DatabaseReference = FirebaseDatabase.getInstance().reference
                val p = databaseReferences.child("พันธุ์ไก่").child(data[position])

                //Log.e("PO",data[position])

                p.addListenerForSingleValueEvent(test(this, position))

            } else if (data[position].contentEquals("ไก่ชน") || mainTitle.contentEquals("ไก่ชน")) {
                //Log.e("Pa ${path}",data[position])

                val aa: DatabaseReference = FirebaseDatabase.getInstance().reference
                val ab = aa.child("พันธุ์ไก่").child(path).child(data[position])
                ab.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        onSyncKeysss(p0)
                        if (abcde.size > 1) {
                            val intent = Intent(context, SubMainActivity::class.java)
                            intent.putExtra("ID", 2)
                            intent.putExtra("TITLE", data[position])
                            intent.putExtra("PATH", Path().toPath(path, data[position]))
                            context.startActivity(intent)
                        } else {
                            val paths = Path().toPath(path, data[position])
                            val intent = Intent(context, ChickenDatailActivity::class.java)
                            intent.putExtra("ID", 2)
                            intent.putExtra("TITLE", abcde[0])
                            intent.putExtra("PATH", Path().toPath(paths, abcde[0]))
                            context.startActivity(intent)
                        }
                    }
                })


            } else {
                //Log.e("asa",Path().toPath(path, data[position]))
                val intent = Intent(context, ChickenDatailActivity::class.java)
                intent.putExtra("ID", 2)
                intent.putExtra("TITLE", data[position])
                intent.putExtra("PATH", Path().toPath(path, data[position]))
                context.startActivity(intent)
            }

        }

/*
        val slot: ChickenBreedData = data[position]

        holder?.mainNameTitle?.text = slot.name

        holder?.mainMenuItem?.setOnClickListener {

            if (position == 0 && po == 0){
                val intent = Intent(context, SubMainActivity::class.java)
                intent.putExtra("ID", 21)
                intent.putExtra("TITLE", slot.name)
                intent.putExtra("POSITION", position)
                context.startActivity(intent)
            } else {
                Toast.makeText(context,"จำกัดการใช้งานอยู่ครับผม :D", Toast.LENGTH_SHORT).show()
            }

        }
*/
    }

    private fun getImagePath(ID: Int, ref: DatabaseReference) {
        if (ID == 0) {
            //arrPath.clear()
            //dataSet.clear()
            //getImagePathKey(ref)
        } else {
            dataSet.clear()
            getImageURL(ref)
        }
    }

    private fun getImagePathKey(dataRef: DatabaseReference) {

        dataRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                onSyncKey(p0)

                //TEST
                //OnSync.Key()

                //val p = arrPath[0]

                //Log.e("ARR",arrPath.toString())
                //val ref = dataRef.child(p)
                //getImagePath(1,ref)
            }
        })
    }

    private fun getImageURL(ref: DatabaseReference) {
        val re = ref.child("เพศผู้").child("image")
        re.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val url: String = p0?.value.toString()
                Log.e("qwe", url.toString())
                //val detail = p0!!.getValue(ChickenDetailData::class.java)
                URLToImageView(url)
            }
        })
    }

    private fun URLToImageView(url: String) {
        if (url.isNotEmpty() && !url.contentEquals("null")) {
            Log.e("PASS", "PASS")
            Glide.with(context).load(url).into(holder.itemImageView!!)
            //Picasso.with(context).load(url).into(holder?.itemImageView!!)
            //Glide.with(myFragment).load(url).centerCrop().placeholder(R.drawable.loading_spinner).into(myImageView)
        }
    }

    fun onSyncKey(dataSnapshot: DataSnapshot?) {
        dataSnapshot!!.children.mapNotNullTo(arrPath) {
            it.key
        }
    }

    fun onSyncKeys(dataSnapshot: DataSnapshot?) {
        abc.clear()
        dataSnapshot!!.children.mapNotNullTo(abc) {
            it.key
        }
    }

    private fun onSyncKeysss(dataSnapshot: DataSnapshot?) {
        abcde.clear()
        dataSnapshot!!.children.mapNotNullTo(abcde) {
            it.key
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }
}
