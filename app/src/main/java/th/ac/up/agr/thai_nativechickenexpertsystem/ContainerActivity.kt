package th.ac.up.agr.thai_nativechickenexpertsystem

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_container.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.TabFirstFragment

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        container_back_btn.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        //val ID = bundle.getInt("ID")
        val title = bundle.getString("TITLE")

        container_title_name.text = title

        //setFragment(TabFirstFragment())


    }

    private fun setFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().replace(R.id.container_frame,fragment).commit()

}
