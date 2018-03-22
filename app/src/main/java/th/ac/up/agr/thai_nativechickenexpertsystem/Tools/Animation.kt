package th.ac.up.agr.thai_nativechickenexpertsystem.Tools

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
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

    fun itemLoadAnimation(view :ImageView){
        AdditiveAnimator.animate(view)
                .setDuration(0)
                .alpha(0f)
                .then()
                .setDuration(400)
                .alpha(1f)
                .start()
    }
    fun itemHideAnimation(view :CardView){
        AdditiveAnimator.animate(view)
                .setDuration(0)
                .alpha(1f)
                .then()
                .setDuration(100)
                .alpha(0f)
                .start()
    }

}
