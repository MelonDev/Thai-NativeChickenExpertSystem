package th.ac.up.agr.thai_nativechickenexpertsystem

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_full_image.*

class FullImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)

        val bundle = intent.extras
        val ID = bundle.getString("ID")
        //val title = bundle.getString("TITLE")
        val name = bundle.getString("NAME")

        farm_back_btn.setOnClickListener {
            finish()
        }



        when(ID){
            "FARM" -> {
                val firebase = FirebaseDatabase.getInstance().reference.child("เพิ่มเติม").child("การจัดการแต่ละระยะ").child(name).child("image")
                loadImage(firebase)
            }
            "DISEASE" -> {
                val firebase = FirebaseDatabase.getInstance().reference.child("โรค").child(name).child("image")
                loadImage(firebase)
            }
            "MORE" -> {

                val firebase = FirebaseDatabase.getInstance().reference.child("เพิ่มเติม").child(name).child("image")
                loadImage(firebase)
            }
        }

    }

    private fun loadImage(ref :DatabaseReference){
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("","")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.value != 0){
                    Picasso.get().load(p0.getValue(String::class.java).toString()).into(farm_zoom_view)
                }else {
                    Picasso.get().load(R.drawable.unknown_picture).into(farm_zoom_view)
                }
            }
        })

    }

}
