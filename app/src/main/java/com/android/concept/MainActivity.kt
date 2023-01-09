package com.android.concept


import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.utils.Utils
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.*
import timber.log.Timber
import java.io.*
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val fileNameInternal = "InternalDemoFile.txt"
    val fileNameExternal = "ExternalDemoFile.txt"
    val externalDirectory = "DemoFolder"

    lateinit var externalfile : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Android Demo"
        Timber.d("Current Thread id : "+Thread.currentThread().id.toString())

        binding.buttonWriteInternal.setOnClickListener{
            writeInInternalStorage()
        }

        binding.buttonReadInternal.setOnClickListener{
            readFromInternalStorage()
        }

        binding.buttonWriteExternal.setOnClickListener{
            writeInExternalStorage()
        }

        binding.buttonReadExternal.setOnClickListener{
            readFromExternalStorage()
        }

        binding.buttonRequestPermission.setOnClickListener{
            requestPermissions()
        }

        binding.buttonSaveImageToPublicStorage.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                saveImageFromUrl()
            }
        }
    }

    private suspend fun saveImageFromUrl() {
        try {
            val url = URL("https://picsum.photos/id/237/200/300")
            val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            saveMediaToStorage(image)
        } catch (e: IOException) {
            println(e)
        }
    }

    suspend fun saveMediaToStorage(bitmap: Bitmap) {
        //Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        //Output stream
        var fos: OutputStream? = null


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)
        {
            //These for devices running on android < Q
            //So I don't think an explanation is needed here
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            //Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            withContext(Dispatchers.Main){
                Utils.showToast("Saved to Photos")
            }
        }
    }

    private fun readFromExternalStorage() {
        externalfile = File(getExternalFilesDir(externalDirectory),fileNameExternal)
        var stringBuffer = StringBuffer()

        try {
            val inputReader = InputStreamReader(FileInputStream(externalfile))

            inputReader.forEachLine {
                stringBuffer.append(it)
            }

            stringBuffer.let {
                binding.textView.text = it.toString()
            }

        }catch(e:Exception){
            e.printStackTrace()
        }
    }

    private fun writeInExternalStorage() {
        val fileOutPutStream : FileOutputStream
        var fileContent = ""

        binding.editText.let {
            fileContent = it.text.toString()
        }

        externalfile = File(getExternalFilesDir(externalDirectory),fileNameExternal)

        try {
            fileOutPutStream = FileOutputStream(externalfile)
            fileOutPutStream.write(fileContent.toByteArray())
            fileOutPutStream.close()
            Utils.showToast("Write Operation Done!")
        }catch ( e: Exception){
            e.printStackTrace()
        }
    }

    private fun readFromInternalStorage() {
        var stringBuffer = StringBuffer()

        try {
            val inputReader = BufferedReader(InputStreamReader(openFileInput(fileNameInternal)))

            inputReader.forEachLine {
                stringBuffer.append(it)
            }

            stringBuffer.let {
                binding.textView.text = it.toString()
            }

        }catch(e:Exception){
            e.printStackTrace()
        }
    }

    private fun writeInInternalStorage() {

        val fileOutPutStream : FileOutputStream
        var fileContent = ""

        binding.editText.let {
            fileContent = it.text.toString()
        }

        try {
            fileOutPutStream = openFileOutput(fileNameInternal,Context.MODE_PRIVATE)
            fileOutPutStream.write(fileContent.toByteArray())
            fileOutPutStream.close()
            Utils.showToast("Write Operation Done!")
        }catch ( e: Exception){
            e.printStackTrace()
        }


    }

    private fun requestPermissions() {
        // below line is use to request permission in the current activity.
        // this method is use to handle error in runtime permissions
        Dexter.withActivity(this)
            // below line is use to request the number of permissions which are required in our app.
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            // after adding permissions we are calling an with listener method.
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    // this method is called when all permissions are granted
                    if (multiplePermissionsReport.areAllPermissionsGranted()) {
                        // do you work now
                        Toast.makeText(this@MainActivity, "All the permissions are granted..", Toast.LENGTH_SHORT).show()
                    }
                    // check for permanent denial of any permission
                    if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied) {
                        // permission is denied permanently, we will show user a dialog message.
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(list: List<PermissionRequest>, permissionToken: PermissionToken) {
                    // this method is called when user grants some permission and denies some of them.
                    permissionToken.continuePermissionRequest()
                }
            }).withErrorListener {
                // we are displaying a toast message for error message.
                Toast.makeText(applicationContext, "Error occurred! ", Toast.LENGTH_SHORT).show()
            }
            // below line is use to run the permissions on same thread and to check the permissions
            .onSameThread().check()
    }



    private fun showSettingsDialog() {
        // we are displaying an alert dialog for permissions
        val builder = AlertDialog.Builder(this@MainActivity)

        // below line is the title for our alert dialog.
        builder.setTitle("Need Permissions")

        // below line is our message for our dialog
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS") { dialog, which ->
            // this method is called on click on positive button and on clicking shit button
            // we are redirecting our user from our app to the settings page of our app.
            dialog.cancel()
            // below is the intent from which we are redirecting our user.
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivityForResult(intent, 101)
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            // this method is called when user click on negative button.
            dialog.cancel()
        }
        // below line is used to display our dialog
        builder.show()
    }

}