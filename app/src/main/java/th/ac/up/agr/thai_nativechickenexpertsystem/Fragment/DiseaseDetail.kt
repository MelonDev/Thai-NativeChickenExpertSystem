package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_disease_detail.view.*

import th.ac.up.agr.thai_nativechickenexpertsystem.R

class DiseaseDetail : Fragment() {

    companion object {

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_disease_detail, container, false)

        val p0 = arguments!!.getString("P0")
        val p1 = arguments!!.getString("P1")
        val p2 = arguments!!.getString("P2")
        val p3 = arguments!!.getString("P3")
        val p4 = arguments!!.getString("P4")

        view.DiseaseDetailA1.text = p2
        view.DiseaseDetailB1.text = p4
        view.DiseaseDetailC1.text = p1
        view.DiseaseDetailD1.text = p3

        val bitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.chicken_wall)
        //holder.imageView.setImageBitmap(bitmap)

        view.DiseaseDetailImageView.setImageBitmap(bitmap)


        return view
    }


}
