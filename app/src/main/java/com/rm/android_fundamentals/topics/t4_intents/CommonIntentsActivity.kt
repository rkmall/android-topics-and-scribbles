package com.rm.android_fundamentals.topics.t4_intents

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.AlarmClock
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.rm.android_fundamentals.base.BaseActivity
import com.rm.android_fundamentals.databinding.ActivityCommonIntentsBinding
import com.rm.android_fundamentals.utils.hasPermission
import com.rm.android_fundamentals.utils.toastMessage
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CommonIntentsActivity : BaseActivity() {

    private lateinit var binding: ActivityCommonIntentsBinding
    private lateinit var photoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonIntentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAlarm()

        checkPermissions()

        binding.btnStartCamera.setOnClickListener {
            dispatchTakePicture()
        }
    }

    private fun setAlarm() {
        binding.run {
            btnAlarm.setOnClickListener {
                var message = editTxtMsg.text.toString()
                if (message.isEmpty()) message = DEFAULT_ALARM_MSG

                val hours = editTxtHrs.text.toString()
                val minutes = editTxtMins.text.toString()
                if (hours.isNotEmpty() && minutes.isNotEmpty()) {
                    createAlarm(message, hours.toInt(), minutes.toInt())
                } else {
                    toastMessage(this@CommonIntentsActivity, "Please input time.")
                }
            }
        }
    }

    private fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

        // Alternatively, uses try-catch block
        /*try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            toastMessage(this@CommonIntentsActivity, "App not available")
        }*/
    }

    private fun dispatchTakePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { captureIntent ->
            captureIntent.resolveActivity(packageManager)?.also {
                photoUri = FileProvider.getUriForFile(
                    this,
                    "com.rm.android_fundamentals.FileProvider",
                    createImageFile())
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                cameraLauncher.launch(photoUri)
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String? =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HH:mm:ss"))
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imgFile = File.createTempFile("JPEG_${timeStamp}", ".jpg", storageDir)
        Log.d("mTag", imgFile.absolutePath)
        return imgFile
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { imgSavedToUri ->
        if (imgSavedToUri) {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, photoUri))
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, photoUri)
            }
            Log.d("mTag", "bitmap: $bitmap")
            binding.imgViewCaptured.setImageBitmap(bitmap)
            saveImgToGallery(bitmap)
        } else {
            toastMessage(this@CommonIntentsActivity, "Uri result null")
        }
    }

    private fun saveImgToGallery(bitmap: Bitmap) {
        val timeStamp: String? = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HH:mm:ss"))
        val outputStream: OutputStream?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "$timeStamp.jpg")
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
            }

            val resolver = contentResolver

            val uri: Uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                ?: throw IOException("Failed to create a new MediaStore record")
            outputStream = resolver.openOutputStream(uri)
        } else {
            val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
            val image = File(storageDir, "$timeStamp.jpg")
            outputStream = FileOutputStream(image)
        }

        outputStream?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, it)
        }
    }

    private fun checkPermissions() {
        val requiredPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO)
        } else {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (!hasPermission(this, *requiredPermissions)) {
            ActivityCompat.requestPermissions(this, requiredPermissions, PERMISSION_CODE)
        }
    }

    override fun getTitleToolbar(): String = "Common intents activity"

    companion object {
        const val DEFAULT_ALARM_MSG = "My Alarm"
        private const val PERMISSION_CODE = 1001
    }
}