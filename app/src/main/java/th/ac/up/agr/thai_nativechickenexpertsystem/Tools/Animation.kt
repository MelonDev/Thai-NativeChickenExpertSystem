package th.ac.up.agr.thai_nativechickenexpertsystem.Tools

import android.view.View
import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator

class Animation {

    fun animation(view: View) {
        AdditiveAnimator.animate(view)
                .setDuration(0)
                .alpha(0f)
                .then()
                .setDuration(400)
                .alpha(1f)
                .start()

    }

}
