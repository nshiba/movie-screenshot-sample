package net.nshiba.movie_screenshot_sample

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Handler
import android.provider.MediaStore
import android.view.PixelCopy
import android.view.SurfaceView
import android.view.TextureView
import android.view.View
import android.widget.Toast
import androidx.annotation.MainThread

object ScreenshotHelper {

    @MainThread
    fun takeScreenshotForNormalView(context: Context, view: View) {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888).apply {
            view.draw(Canvas(this))
        }
        saveScreenshot(context, bitmap)
    }

    @MainThread
    fun takeScreenshotForSurfaceView(context: Context, view: SurfaceView) {
        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height,
            Bitmap.Config.ARGB_8888
        )
        PixelCopy.request(
            view,
            bitmap,
            { result ->
                if (result == PixelCopy.SUCCESS) {
                    saveScreenshot(context, bitmap)
                } else {
                    Toast.makeText(context, "失敗しました", Toast.LENGTH_SHORT).show()
                }
            },
            Handler()
        )
    }

    @MainThread
    fun takeScreenshotForTextureView(context: Context, view: TextureView) {
        saveScreenshot(context, view.bitmap)
    }

    @SuppressLint("InlinedApi")
    private fun saveScreenshot(context: Context, bitmap: Bitmap) {
        val values = ContentValues().apply {
            val name = "${System.currentTimeMillis()}.jpeg"
            put(MediaStore.Images.Media.DISPLAY_NAME, name)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Screenshots/")
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        context.contentResolver.insert(collection, values)?.let { imageUri ->
            context.contentResolver.openOutputStream(imageUri).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
            }

            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, 0)
            context.contentResolver.update(imageUri, values, null, null)

            Toast.makeText(context, "保存しました", Toast.LENGTH_SHORT).show()
        }
    }
}
