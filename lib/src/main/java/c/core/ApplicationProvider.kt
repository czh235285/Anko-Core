package c.core

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import c.core.ex.DimensionEx
import c.core.widget.status.Gloading
import c.core.widget.status.GlobalAdapter

class ApplicationProvider : ContentProvider() {
    companion object {
        lateinit var ctx: Context
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }


    override fun onCreate(): Boolean {
        context?.let {
            ctx = it
            Gloading.initDefault(GlobalAdapter())
            DimensionEx.init(750, 1334)
        }
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

}