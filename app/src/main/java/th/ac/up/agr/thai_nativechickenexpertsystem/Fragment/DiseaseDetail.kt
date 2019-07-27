package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


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
import kotlinx.android.synthetic.main.fragment_disease_detail.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.ContainerActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DiseaseData

import th.ac.up.agr.thai_nativechickenexpertsystem.R

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

        FirebaseDatabase.getInstance().reference.child("โรค").child(name).addValueEventListener(object : ValueEventListener{
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

                }
            }
        })



        val bitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.chicken_wall)
        //holder.imageView.setImageBitmap(bitmap)

        view.DiseaseDetailImageView.setImageBitmap(bitmap)


        return view
    }


}
