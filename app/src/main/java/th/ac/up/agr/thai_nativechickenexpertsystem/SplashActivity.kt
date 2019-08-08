package th.ac.up.agr.thai_nativechickenexpertsystem

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_splash.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.KotlinDemoAdapter

class SplashActivity : AppCompatActivity() {


    internal lateinit var handler: Handler
    internal lateinit var runnable: Runnable
    internal var delay_time: Long = 0
    internal var time = 1000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler = Handler()

        SQLite(this)

        val sq = SQLite(this)

        runnable = Runnable {
            //val intent = Intent(this, MainActivity::class.java)
            /*if(FirebaseAuth.getInstance().currentUser == null){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            }else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
            */

            //val intent = Intent(this, ModernMainActivity::class.java)


            if(sq.isNull()){
                val intent = Intent(this, FarmInputActivity::class.java)
                intent.putExtra("ID",0)
                startActivity(intent)
            }else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


            //startActivity(intent)

            finish()
        }

    }

    fun test(){
        val adapter = object : KotlinDemoAdapter(){
            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
            }
        }

    }

    public override fun onResume() {
        super.onResume()
        delay_time = time
        handler.postDelayed(runnable, delay_time)
        time = System.currentTimeMillis()
    }

    public override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
        time = delay_time - (System.currentTimeMillis() - time)
    }

}
