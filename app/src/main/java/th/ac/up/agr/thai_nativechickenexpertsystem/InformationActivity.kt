package th.ac.up.agr.thai_nativechickenexpertsystem

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 56000
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val option = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, option)
        firebaseAuth = FirebaseAuth.getInstance()

        information_back_btn.setOnClickListener {

            signOut()

        }

    }

    private fun signOut() {
        firebaseAuth.signOut()
        googleSignInClient.signOut().addOnCompleteListener(this,
                OnCompleteListener {
                    finish()
                })
    }

    override fun onBackPressed() {
        super.onBackPressed()

        signOut()
    }

}
