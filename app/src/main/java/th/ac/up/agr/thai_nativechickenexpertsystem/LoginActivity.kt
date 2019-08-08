package th.ac.up.agr.thai_nativechickenexpertsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mylhyl.circledialog.CircleDialog
import com.mylhyl.circledialog.callback.ConfigButton
import com.mylhyl.circledialog.callback.ConfigDialog
import com.mylhyl.circledialog.callback.ConfigText
import com.mylhyl.circledialog.callback.ConfigTitle
import com.mylhyl.circledialog.params.*

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 56000
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var waitDialog: DialogFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_sign_in_google_btn.setOnClickListener {
            if(FirebaseAuth.getInstance().currentUser == null){
                loginFunction()
            }else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            //finish()
        }

    }

    private fun loginFunction(){
        val option = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, option)

        signIn()

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        setWaitDialog("กำลังดำเนินการ...")
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun setWaitDialog(string: String) {
        waitDialog = CircleDialog.Builder()
                .configDialog { params -> params.canceledOnTouchOutside = false }
                .setProgressText(string)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show(supportFragmentManager)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Log.e("TEST", "TEST")
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                waitDialog.dismiss()
                setErrorDialog("คำขอถูกยกเลิก")
                Log.e("TEST-1",e.localizedMessage.toString())
                Log.e("TEST-2",e.message.toString())

                //Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
                //updateUI(null)
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        //waitDialog.dismiss()

                        startProcess()

                        //Log.e("PROVIDER",firebaseAuth.currentUser!!.providerId)
                        //Log.e("PROVIDER",firebaseAuth.currentUser!!.uid)

                        //val user = firebaseAuth.getCurrentUser()
                        //updateUI(user)
                    } else {
                        waitDialog.dismiss()
                        setErrorDialog("เกิดข้อผิดพลาด")
                        //updateUI(null)
                    }
                })
    }


    fun startProcess() {
        val user = FirebaseAuth.getInstance().currentUser!!
        val firebase = FirebaseDatabase.getInstance().reference.child("ผู้ใช้").child(user.uid).child("farmName")
        firebase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                setErrorDialog("คำขอถูกยกเลิก")
                Log.e("TEST-2","CANCEL")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.value != null) {

                    val info = p0.getValue(String::class.java)!!

                    if(info.isNotEmpty()){
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        waitDialog.dismiss()
                        //setInfo()
                        startActivity(intent)
                        finish()
                    }else {
                        val intent = Intent(this@LoginActivity, InformationActivity::class.java)
                        waitDialog.dismiss()
                        //setInfo()
                        startActivity(intent)
                        finish()
                    }


                    /*val info = p0.getValue(Information::class.java)!!

                    if (info.farmName.isEmpty() || info.username.isEmpty() || info.phoneNumber.isEmpty() || info.farmAddress.isEmpty()) {
                        setQuestionDialogss()
                    } else {
                        val intent = Intent(this@LoginActivity, ProgramMainActivity::class.java)
                        waitDialog.dismiss()
                        //setInfo()
                        startActivity(intent)
                        firebase.removeEventListener(this)
                        finish()
                    }*/

                } else {
                    val intent = Intent(this@LoginActivity, InformationActivity::class.java)
                    waitDialog.dismiss()
                    //setInfo()
                    startActivity(intent)
                    finish()
                    //setInfo()
                }
            }
        })

    }


    fun setQuestionDialogss() {
        CircleDialog.Builder(this
        )
                .configDialog { params -> params.canceledOnTouchOutside = false }
                .setText("บัญชีของคุณยังไม่ได้ใส่ข้อมูลพื้นฐานต่างๆ คุณต้องการไปใส่ข้อมูลตอนนี้เลยไหม?")
                .configText { params ->
                    params!!.textSize = 50
                    params.textColor = ContextCompat.getColor(this@LoginActivity, R.color.colorText)
                    params.padding = intArrayOf(50, 10, 50, 70) //(Left,TOP,Right,Bottom)
                }
                .setTitle("คำอธิบาย")
                .configTitle { params ->
                    params!!.textSize = 60
                    params.textColor = ContextCompat.getColor(this@LoginActivity, R.color.colorPrimary)
                }
                .setPositive("ตกลง", {
                    newStartProcess()
                })
                .configPositive { params ->
                    params.textSize = 50
                    params.textColor = ContextCompat.getColor(this@LoginActivity, R.color.colorPrimary)
                }
                .setNegative("ข้าม", {
                    //val intent = Intent(this@LoginActivity, ProgramMainActivity::class.java)
                    //startActivity(intent)
                    //finish()
                })
                .configNegative { params ->
                    params.textSize = 50

                    params.textColor = ContextCompat.getColor(this@LoginActivity, R.color.colorText)
                }
                .show()


    }

    fun newStartProcess() {
        //val intent = Intent(this, RegisterInfoActivity::class.java)
        //finish()
        intent.putExtra("ID", "0")
        startActivity(intent)
        finish()
    }

    fun setErrorDialog(string: String) {
        CircleDialog.Builder(this
        )
                .configDialog { params -> params.canceledOnTouchOutside = false }
                .setText(string)
                .configText { params ->
                    params!!.textSize = 60
                    params.textColor = ContextCompat.getColor(this@LoginActivity, R.color.colorPrimary)
                    params.padding = intArrayOf(0, 0, 0, 0) //(Bottom,TOP,Right,Left)
                    params.height = 250
                }
                .setPositive("รับทราบ") {
                }
                .configPositive { params ->
                    params.textSize = 50
                    params.textColor = ContextCompat.getColor(this@LoginActivity, R.color.colorText)
                }.show()


    }

}
