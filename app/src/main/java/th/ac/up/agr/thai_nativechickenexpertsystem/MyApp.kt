package th.ac.up.agr.thai_nativechickenexpertsystem

import com.google.firebase.database.FirebaseDatabase

class MyApp : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}