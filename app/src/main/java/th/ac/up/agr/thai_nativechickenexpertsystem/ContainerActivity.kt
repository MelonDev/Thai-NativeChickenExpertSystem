package th.ac.up.agr.thai_nativechickenexpertsystem

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_container.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.DiseaseDetail
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.TabFirstFragment

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        container_back_btn.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        val ID = bundle.getString("ID")
        val title = bundle.getString("TITLE")

        container_title_name.text = title

        val w = window

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            w.navigationBarColor = resources.getColor(R.color.colorBNVT)
        }
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            w.navigationBarColor = resources.getColor(R.color.colorBNV)
        }

        when(ID){
            "DISEASE" -> {
                val DISEASE_CAUSE = bundle.getString("DISEASE_CAUSE")
                val DISEASE_PREVENT = bundle.getString("DISEASE_PREVENT")
                val DISEASE_DETAIL = bundle.getString("DISEASE_DETAIL")
                val DISEASE_SYMPTOM = bundle.getString("DISEASE_SYMPTOM")

                val fragment = DiseaseDetail.newInstance(title,DISEASE_CAUSE,DISEASE_DETAIL,DISEASE_PREVENT,DISEASE_SYMPTOM)

                setFragment(fragment)
            }
        }

        //setFragment(TabFirstFragment())


    }

    private fun setFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().replace(R.id.container_frame,fragment).commit()

}
