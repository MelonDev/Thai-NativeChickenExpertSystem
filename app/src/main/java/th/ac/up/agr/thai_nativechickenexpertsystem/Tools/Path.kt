package th.ac.up.agr.thai_nativechickenexpertsystem.Tools

import android.util.Log
import com.google.firebase.database.DatabaseReference

/**
 * Created by melondev on 26/2/2018 AD.
 */
class Path (){

    fun toPath(path :String,point :String) : String{

        return if (path.length == 0){
            point
        } else {
            path + ">" + point
        }

    }

    fun fromPathToArray(path: String) :ArrayList<String>{
        var arr :ArrayList<String> = ArrayList<String>()
        var str = ""
        var i = 0
        for(c in path){
            i+=1
            //Log.e("str",str.toString())
            //Log.e("arr",arr.toString())
            //Log.e("c",c.toString())
            when (c) {
                path.get(path.lastIndex) -> {
                    if (i == path.length){
                        str += c.toString()
                        arr.add(str)
                    }else {
                        str += c.toString()
                    }
                }
                '>' -> {
                    arr.add(str)
                    str = ""
                }
                else -> str += c.toString()
            }
        }
        Log.e("ARR ${path}",arr.toString())
        return arr
    }

    fun getChild(ref :DatabaseReference,path :ArrayList<String>) : DatabaseReference{
        return if (path.size == 2){
            ref.child(path[0]).child(path[1])
        } else if(path.size == 3){
            ref.child(path[0]).child(path[1]).child(path[2])
        } else {
            ref
        }
    }

}