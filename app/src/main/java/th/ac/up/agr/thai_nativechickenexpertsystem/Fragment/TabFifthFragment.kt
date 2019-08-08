package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_farm_input.*
import kotlinx.android.synthetic.main.fragment_tab_fifth.*
import kotlinx.android.synthetic.main.fragment_tab_fifth.view.*
import kotlinx.android.synthetic.main.fragment_tab_fifth.view.fifth_account_off
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MoreMenuAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.IconSet
import th.ac.up.agr.thai_nativechickenexpertsystem.FarmInputActivity

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.SQLite
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class TabFifthFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_fifth, container, false)
        val arr = ArrayList<IconSet>()
        //arr.add(setIconSet("อาหาร",R.drawable.ic_seed_off,null))
        arr.add(setIconSet("การจัดการแต่ละระยะ",R.drawable.ic_chicken_management_2,null))
        arr.add(setIconSet("การผสมพันธุ์",R.drawable.ic_breeding_2,null))
        arr.add(setIconSet("ลักษณะ",R.drawable.ic_feather_off,null))
        arr.add(setIconSet("โรงเรือน",R.drawable.ic_farm,null))

        arr.add(setIconSet("โภชนาการ",R.drawable.ic_wheat,null))

        arr.add(setIconSet("วัคซีน",R.drawable.ic_vaccine,null))
        //arr.add(setIconSet("ถ่ายพยาธิ",R.drawable.bacteria_off,null))
        arr.add(setIconSet("อ้างอิง",R.drawable.ic_book,null))

        arr.add(setIconSet("ติดต่อเรา",R.drawable.ic_people_off,null))

        //arr.add(setIconSet("ติดต่อเรา",R.drawable.ic_people_off))

        //arr.add(setIconSet("Privacy Policy",R.drawable.ic_seed_off))


        val recyclerView = QuickRecyclerView(view.context
                , view.more_recyclerview
                , "grid"
                , 3
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        recyclerView.adapter = MoreMenuAdapter(view.context,arr)

        view.fifth_account_edit.setOnClickListener {
            val intent = Intent(this.context, FarmInputActivity::class.java)
            intent.putExtra("ID",1)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        val sqlite = SQLite(this.activity!!)
        fifth_account_on.visibility = View.GONE
        fifth_account_off.visibility = View.GONE


        if(sqlite.isNull()){
            fifth_account_off.visibility = View.VISIBLE

            fifth_account_btn.setOnClickListener {
                val intent = Intent(this.context, FarmInputActivity::class.java)
                startActivity(intent)
            }
        }else {
            fifth_account_on.visibility = View.VISIBLE

            val data = sqlite.read()

            fifth_account_farmer.text = data.farmerName
            fifth_account_farm.text = data.farmName

            Picasso.get().load("file://"+data.path).into(fifth_account_image)


        }
    }

    private fun setIconSet(name: String,ID :Int,activity :Activity?) : IconSet{
        return IconSet(name,ID,activity)
    }

}// Required empty public constructor
