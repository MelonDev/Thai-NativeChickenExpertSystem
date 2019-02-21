package th.ac.up.agr.thai_nativechickenexpertsystem.Firebase

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.database.*
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import android.widget.Toast
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_add_data.view.*


/**
 * Created by melondev on 27/2/2018 AD.
 */
class FirebaseSpinner(val context: Context, val itemView: View) {

    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var path: ArrayList<String> = ArrayList()
    private var dataA: ArrayList<String> = ArrayList()

    private val activity: FirebaseAddDataActivity = FirebaseAddDataActivity(context, itemView)


    fun toSpinner(paths: ArrayList<String>, spinnerA: Spinner, spinnerB: Spinner, spinnerC: Spinner) {

        path.add("พันธุ์ไก่")

        val pathRefA = databaseReference.child(paths[0])

        pathRefA.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {

                val arrA = onSyncKey(p0)
                val arrAS = arrA

                arrAS.add("เพิ่มข้อมูล")

                spinnerA.adapter = toAdapter(arrAS)
                spinnerA.setSelection(arrAS.lastIndex)

                spinnerA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                    //Performing action onItemSelected and onNothing selected
                    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

                        path = resetPath(1, path)

                        if (arrAS[position].contentEquals("เพิ่มข้อมูล")) {
                            spinnerB.visibility = View.GONE
                            spinnerC.visibility = View.GONE

                            activity.showInputZone(true, 0, ArrayList<String>())
                        } else {

                            path.add(arrA[position])

                            val pathRefB = databaseReference.child(path[0]).child(path[1])

                            pathRefB.addListenerForSingleValueEvent(object : ValueEventListener {

                                override fun onCancelled(p0: DatabaseError) {
                                }

                                override fun onDataChange(p0: DataSnapshot) {
                                    spinnerB.visibility = View.VISIBLE
                                    val arrB = onSyncKey(p0)
                                    val arrBS = arrB
                                    arrBS.add("เพิ่มข้อมูล")
                                    val adapter = toAdapter(arrBS)
                                    spinnerB.adapter = adapter
                                    spinnerB.setSelection(arrBS.lastIndex)
                                    spinnerB.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                                        //Performing action onItemSelected and onNothing selected
                                        override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

                                            path = resetPath(2, path)

                                            if (arrBS[position].contentEquals("เพิ่มข้อมูล")) {
                                                spinnerC.visibility = View.GONE

                                                if (path[1].contentEquals("ไก่ชน")) {
                                                    activity.showInputZone(true, 1, path)

                                                } else {
                                                    activity.showInputZone(true, 2, path)
                                                }

                                            } else if (path[1].contentEquals("ไก่ชน")) {

                                                path.add(arrB[position])

                                                //val pathRefC = databaseReference.child(pathCopyB[0]).child(pathCopyB[1]).child(pathCopyB[2])
                                                val pathRefC = databaseReference.child(path[0]).child(path[1]).child(path[2])


                                                pathRefC.addListenerForSingleValueEvent(object : ValueEventListener {

                                                    override fun onCancelled(p0: DatabaseError) {
                                                    }

                                                    override fun onDataChange(p0: DataSnapshot) {
                                                        spinnerC.visibility = View.VISIBLE
                                                        val arrC = onSyncKey(p0)
                                                        val arrCS = arrC
                                                        arrCS.add("เพิ่มข้อมูล")
                                                        val adapter = toAdapter(arrCS)
                                                        spinnerC.adapter = adapter
                                                        spinnerC.setSelection(arrCS.lastIndex)

                                                        spinnerC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                                                            override fun onNothingSelected(parent: AdapterView<*>?) {

                                                            }

                                                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                                                path = resetPath(3, path)

                                                                if (arrCS[position].contentEquals("เพิ่มข้อมูล")) {
                                                                    activity.showInputZone(true, 2, path)

                                                                } else {
                                                                    path.add(arrC[position])
                                                                    activity.showInputZone(true, 3, path)
                                                                }
                                                            }
                                                        }

                                                    }
                                                })
                                            } else {
                                                spinnerC.visibility = View.GONE
                                                path.add(arrB[position])
                                                activity.showInputZone(true, 3, path)
                                            }


                                        }

                                        override fun onNothingSelected(arg0: AdapterView<*>) {
                                            // TODO: Auto-generated method stub


                                        }

                                    }

                                }
                            })
                        }


                    }

                    override fun onNothingSelected(arg0: AdapterView<*>) {
                        // TODO: Auto-generated method stub


                    }

                }

            }
        })

    }

    private fun resetPath(limit: Int, path: ArrayList<String>): ArrayList<String> {
        while (path.size > limit) {
            path.removeAt(path.lastIndex)
        }
        return path
    }

    private fun pathToRef(path: ArrayList<String>): DatabaseReference {
        val ref = databaseReference
        for (i in path) {
            ref.child(i)
        }
        return ref
    }


    private fun onSyncKey(dataSnapshot: DataSnapshot?): ArrayList<String> {
        var arr = ArrayList<String>()
        dataSnapshot!!.children.mapNotNullTo(arr) {
            it.key
        }
        return arr
    }

    private fun functionA() {

    }

    private fun toAdapter(list: ArrayList<String>): ArrayAdapter<String> {
        val adapter = ArrayAdapter<String>(context, R.layout.spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

}
