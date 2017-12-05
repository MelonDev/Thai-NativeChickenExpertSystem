package th.ac.up.agr.thai_nativechickenexpertsystem.Tools

import android.content.Context
import android.content.res.TypedArray
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData

class StringArrayToArrayList(val context: Context) {

    fun mainChicken(resourceID: Int): ArrayList<ChickenBreedData> {
        val titleArray: TypedArray = context.resources.obtainTypedArray(resourceID)
        var array: ArrayList<ChickenBreedData> = ArrayList()
        (0 until titleArray.length()).mapTo(array) {
            ChickenBreedData(titleArray.getString(it)) }
        titleArray.recycle()
        return array
    }

}