package com.adamkis.flickr.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File


/**
 * Created by adam on 2018. 01. 13..
 */

object FilePersistenceHelper{

    val HEADER_IMAGE_BITMAP_FILENAME = "header_bmp.png"

    fun writeBitmapToFile(context: Context, bmp: Bitmap){
        try {
            val stream = context.openFileOutput(HEADER_IMAGE_BITMAP_FILENAME, Context.MODE_PRIVATE)
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
            //Cleanup
            stream.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun loadBitmapFromFile(context: Context): Bitmap? {
        var bmp: Bitmap? = null
        try {
            val fis = context.openFileInput(HEADER_IMAGE_BITMAP_FILENAME)
            bmp = BitmapFactory.decodeStream(fis)
            fis.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bmp
    }

}