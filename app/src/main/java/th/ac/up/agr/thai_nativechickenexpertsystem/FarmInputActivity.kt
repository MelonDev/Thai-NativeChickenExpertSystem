package th.ac.up.agr.thai_nativechickenexpertsystem

import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.mylhyl.circledialog.CircleDialog
import com.mylhyl.circledialog.callback.ConfigButton
import com.mylhyl.circledialog.callback.ConfigDialog
import com.mylhyl.circledialog.callback.ConfigText
import com.mylhyl.circledialog.callback.ConfigTitle
import com.mylhyl.circledialog.params.ButtonParams
import com.mylhyl.circledialog.params.DialogParams
import com.mylhyl.circledialog.params.TextParams
import com.mylhyl.circledialog.params.TitleParams
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_farm_input.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class FarmInputActivity : AppCompatActivity() {

    private val GALLERY = 1000

    private lateinit var dataSQlite :SQLite.SQModel
    private lateinit var sqLiteDatabase: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farm_input)

        val bundle = intent.extras
        val ID = bundle.getInt("ID")

        dataSQlite = SQLite.SQModel()
        val sq = SQLite(this)
        sqLiteDatabase = sq.writableDatabase


        farm_input_back_btn.setOnClickListener {
            finish()
        }

        farm_input_image_choose.setOnClickListener {

            choosePhotoFromGallary()

        }

        if(ID == 1){
            dataSQlite = sq.read()

            Picasso.get().load("file://"+dataSQlite.path).into(farm_input_image)

            farm_input_a1.setText(dataSQlite.farmerName)
            farm_input_a2.setText(dataSQlite.farmName)
            farm_input_a3.setText(dataSQlite.address)

        }

        farm_input_confirm_btn.setOnClickListener {
            Log.e("SAdkd","sdad")
            dataSQlite.farmerName = farm_input_a1.text.toString()
            dataSQlite.farmName = farm_input_a2.text.toString()
            dataSQlite.address = farm_input_a3.text.toString()

            if(dataSQlite.farmerName.isEmpty() && dataSQlite.farmName.isEmpty() && dataSQlite.address.isEmpty() && dataSQlite.path.isEmpty()){
                setErrorDialog("ข้อมูลไม่ครบ")
            }else {
                sq.update(sqLiteDatabase,dataSQlite)
                if(ID == 0){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else {
                    finish()
                }
            }
        }
    }

    fun choosePhotoFromGallary() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            setQuestionDialog(0, "คำอธิบาย", "หากคุณต้องการเปิดคลังภาพให้คุณกด \"ขอสิทธิ์\" แล้วกด \"ยอมรับ\" ตามลำดับ", REQUEST_PERMISSION_GALLERY, "ขอสิทธิ์", "ยกเลิก")
        } else if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            setQuestionDialog(0, "คำอธิบาย", "หากคุณต้องการเปิดหน่วยความจำให้คุณกด \"ขอสิทธิ์\" แล้วกด \"ยอมรับ\" ตามลำดับ", REQUEST_PERMISSION_STORAGE, "ขอสิทธิ์", "ยกเลิก")
        } else {

            val galleryIntent = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            startActivityForResult(galleryIntent, GALLERY)
        }


    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        /* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*/
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data!!.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val path = saveImage(bitmap)
                    //Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
                    //farm_input_image.setImageBitmap(bitmap)
                    dataSQlite.path = path
                    Picasso.get().load("file://"+path).into(farm_input_image)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
                (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.e("fee", wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }

        try {
            Log.e("heel", wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ("thainativeprofile.jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                    arrayOf(f.getPath()),
                    arrayOf("image/jpeg"), null)
            fo.close()
            Log.e("TAG", "File Saved::--->" + f.absolutePath)

            return f.getAbsolutePath()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/thainative"
        const val REQUEST_PERMISSION_GALLERY = 56001
        const val REQUEST_PERMISSION_STORAGE = 56002


    }

    fun getPermision(permission: String, requestCode: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            } else {
                Log.e("PER", "0")
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when (requestCode) {
                REQUEST_PERMISSION_GALLERY -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        choosePhotoFromGallary()
                    } else if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        val showRationale = shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        if (!showRationale) {
                            setQuestionDialog(1, "คำอธิบาย", "เนื่องจากคุณได้ทำการกด \"ไม่ต้องแสดงอีก\" ในหน้าขอสิทธิ์ หากคุณต้่องการจะขอสิทธิ์อีกครั้ง ให้กด \"ไปที่ตั้งค่า\" แล้วกดเลือก \"สิทธิ์ของแอป\" จากนั้นกดเปิดสิทธิ์ที่ต้องการ ", REQUEST_PERMISSION_GALLERY, "ไปที่ตั้งค่า", "ยกเลิก")
                        }
                    } else {
                        setErrorDialog("เกิดข้อผิดพลาด")
                    }

                }
                REQUEST_PERMISSION_STORAGE -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        choosePhotoFromGallary()
                    } else if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        val showRationale = shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        if (!showRationale) {
                            setQuestionDialog(1, "คำอธิบาย", "เนื่องจากคุณได้ทำการกด \"ไม่ต้องแสดงอีก\" ในหน้าขอสิทธิ์ หากคุณต้่องการจะขอสิทธิ์อีกครั้ง ให้กด \"ไปที่ตั้งค่า\" แล้วกดเลือก \"สิทธิ์ของแอป\" จากนั้นกดเปิดสิทธิ์ที่ต้องการ ", REQUEST_PERMISSION_STORAGE, "ไปที่ตั้งค่า", "ยกเลิก")
                        }
                    } else {
                        setErrorDialog("เกิดข้อผิดพลาด")
                    }

                }
            }
        }
    }

    fun setQuestionDialog(ID: Int, title: String, sub: String, requestCode: Int, positive: String, negative: String) {
        CircleDialog.Builder(this
        )
                .configDialog { params -> params.canceledOnTouchOutside = false }
                .setText(sub)
                .configText { params ->
                    params!!.textSize = 50
                    params.textColor = ContextCompat.getColor(this@FarmInputActivity, R.color.colorText)
                    params.padding = intArrayOf(50, 10, 50, 70) //(Left,TOP,Right,Bottom)
                }
                .setTitle(title)
                .configTitle { params ->
                    params!!.textSize = 60
                    params.textColor = ContextCompat.getColor(this@FarmInputActivity, R.color.colorPrimary)
                }
                .setPositive(positive) {
                    if (ID == 0) {
                        when (requestCode) {
                            REQUEST_PERMISSION_GALLERY -> {
                                getPermision(android.Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_PERMISSION_GALLERY)
                            }
                            REQUEST_PERMISSION_STORAGE -> {
                                getPermision(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_STORAGE)
                            }
                        }
                    } else if (ID == 1) {
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        intent.addCategory(Intent.CATEGORY_DEFAULT)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }

                }
                .configPositive { params ->
                    params.textSize = 50
                    params.textColor = ContextCompat.getColor(this@FarmInputActivity, R.color.colorPrimary)
                }
                .setNegative(negative) {
                }
                .configNegative { params ->
                    params.textSize = 50

                    params.textColor = ContextCompat.getColor(this@FarmInputActivity, R.color.colorText)
                }
                .show()


    }

    fun setErrorDialog(string: String) {
        CircleDialog.Builder(this
        )
                .configDialog { params -> params.canceledOnTouchOutside = false }
                .setText(string)
                .configText { params ->
                    params!!.textSize = 60
                    params.textColor = ContextCompat.getColor(this, R.color.colorPrimary)
                    params.padding = intArrayOf(0, 0, 0, 0) //(Bottom,TOP,Right,Left)
                    params.height = 250
                }
                .setPositive("รับทราบ", {
                })
                .configPositive { params ->
                    params.textSize = 50
                    params.textColor = ContextCompat.getColor(this, R.color.colorText)
                }.show()


    }

}
