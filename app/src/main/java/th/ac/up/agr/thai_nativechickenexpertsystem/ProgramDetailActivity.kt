package th.ac.up.agr.thai_nativechickenexpertsystem

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_program_detail.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DataCard
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.DP_one
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.DP_three
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.DP_two
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.ConvertCard

class ProgramDetailActivity : AppCompatActivity() {

    private lateinit var status: String
    private lateinit var cardID: String
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val userID = "melondev_icloud_com"

    private var dataCard: DataCard = DataCard()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_detail)

        detail_program_back_btn.setOnClickListener { finish() }

        status = intent.extras.getString("STATUS")

        cardID = intent.extras.getString("CARDID")

        //var arr = intent.extras.get("DATA") as ArrayList<DataCard>


        if (status.contentEquals("ACTIVE")) {
            pd_edit_icon.setOnClickListener {
                val intent = Intent(this, AddProgramActivity::class.java)
                intent.putExtra("ID", "1")
                intent.putExtra("CARDID", dataCard.cardID)
                startActivity(intent)

            }

            val ref = databaseReference.child("ผู้ใช้").child(userID).child("ข้อมูล").child("ACTIVE").child(cardID)
            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot?) {
                    dataCard = p0?.getValue(DataCard::class.java) ?: DataCard()
                    if (dataCard.cardID.isNotEmpty()) {
                        setData()
                    }

                }
            })
        } else {
            val ref = databaseReference.child("ผู้ใช้").child(userID).child("ข้อมูล").child("INACTIVE").child(cardID)
            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot?) {
                    dataCard = p0?.getValue(DataCard::class.java) ?: DataCard()
                    if (dataCard.cardID.isNotEmpty()) {
                        setData()
                    }
                }
            })
        }

    }

    private fun setData() {
        pd_title_name.text = dataCard.cardID
        pd_text_a.text = "${dataCard.dateDay} ${ConvertCard().getMonth(dataCard.dateMonth)} ${dataCard.dateYear.toInt() + 543}"
        pd_text_b.text = "${dataCard.amountMale.toInt() + dataCard.amountFemale.toInt()} ตัว"
        pd_text_c.text = "${dataCard.amountMale} ตัว"
        pd_text_d.text = "${dataCard.amountFemale} ตัว"
        pd_text_e.text = "${dataCard.ageDay} วัน ${dataCard.ageWeek} สัปดาห์"
        pd_text_f.text = ConvertCard().getSystem(dataCard.systemFarm)
        pd_text_g.text = ConvertCard().getObjective(dataCard.userObjective)
    }


}
