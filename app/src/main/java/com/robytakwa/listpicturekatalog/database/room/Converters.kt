package com.robytakwa.listpicturekatalog.database.room

import androidx.room.TypeConverter
import java.util.*

object Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun boleanToConvert(date: Boolean): Boolean? {
        return if (date) null else false
    }

}
