package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_disease_detail.*
import kotlinx.android.synthetic.main.fragment_disease_detail.view.*
import kotlinx.android.synthetic.main.fragment_disease_detail.view.DiseaseDetailB
import th.ac.up.agr.thai_nativechickenexpertsystem.ContainerActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DiseaseData
import th.ac.up.agr.thai_nativechickenexpertsystem.FullImageActivity

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Animation
import java.lang.Exception

class DiseaseDetail : Fragment() {

    companion object {

        /*
        fun newInstance(p0 :String,p1 :String,p2 :String,p3 :String,p4 :String): DiseaseDetail {
            val args = Bundle()

            args.putString("P0",p0)
            args.putString("P1",p1)
            args.putString("P2",p2)
            args.putString("P3",p3)
            args.putString("P4",p4)

            val fragment = DiseaseDetail()
            fragment.arguments = args
            return fragment
        }
        */

        fun newInstance(name: String): DiseaseDetail {
            val args = Bundle()
            args.putString("NAME",name)
            val fragment = DiseaseDetail()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_disease_detail, container, false)

        //val p0 = arguments!!.getString("P0")
        //val p1 = arguments!!.getString("P1")
        //val p2 = arguments!!.getString("P2")
        //val p3 = arguments!!.getString("P3")
        //val p4 = arguments!!.getString("P4")
        val name = arguments!!.getString("NAME")
        //Log.e("TE",name)

        var names = ""

        if(name.indexOf("ธิ")>=0){
            names = "พยา$name"
        }else {
            names = name
        }

        view.DiseaseDetailImageView.setOnClickListener {
            val intent = Intent(context!!, FullImageActivity::class.java)

            intent.putExtra("ID","DISEASE")
            intent.putExtra("NAME",name)
            //intent.putExtra("POSITION",position)
            context!!.startActivity(intent)
        }


        FirebaseDatabase.getInstance().reference.child("โรค").child(names).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("","")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.value != null){
                    val detail = p0.getValue(DiseaseData::class.java)!!

                    view.DiseaseDetailA1.text = detail.detail

                    view.DiseaseDetailB1.text = detail.symptom
                    view.DiseaseDetailC1.text = detail.cause
                    view.DiseaseDetailD1.text = detail.prevent

                    Log.e("TES",detail.name)

                    if(detail.image.isNotEmpty())Picasso.get().load(detail.image).error(R.drawable.unknown_picture).into(view.DiseaseDetailImageView!!, object : com.squareup.picasso.Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {

                        }
                    })

                    view.DiseaseDetailB.visibility = View.GONE
                    view.DiseaseDetailC.visibility = View.GONE
                    view.DiseaseDetailD.visibility = View.GONE

                    if(detail.symptom.isNotEmpty()){
                        view.DiseaseDetailB.visibility = View.VISIBLE
                    }
                    if(detail.cause.isNotEmpty()){
                        view.DiseaseDetailC.visibility = View.VISIBLE
                    }
                    if(detail.prevent.isNotEmpty()){
                        view.DiseaseDetailD.visibility = View.VISIBLE
                    }





                }
            }
        })



        val bitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.chicken_wall)
        //holder.imageView.setImageBitmap(bitmap)

        view.DiseaseDetailImageView.setImageBitmap(bitmap)


        return view
    }


}
