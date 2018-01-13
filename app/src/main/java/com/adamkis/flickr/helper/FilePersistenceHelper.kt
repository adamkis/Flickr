package com.adamkis.flickr.helper

import android.content.Context
import android.graphics.Bitmap
import android.content.Context.MODE_PRIVATE
import android.graphics.BitmapFactory
import android.content.Intent.getIntent
import java.io.File
import java.nio.file.Files.delete







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
        // Delete file
        val file = File(HEADER_IMAGE_BITMAP_FILENAME)
        val deleted = file.delete()
        return bmp
    }

}