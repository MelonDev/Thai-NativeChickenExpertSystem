package th.ac.up.agr.thai_nativechickenexpertsystem

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

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
