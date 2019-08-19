package com.example.android.projet.local_storage

import androidx.room.TypeConverter
import java.sql.Date
import java.sql.Timestamp


class TimestampConverter {

    @TypeConverter
    fun toTimestamp(dateLong: Long?): Timestamp? {
        return if (dateLong == null) null else Timestamp(dateLong)
    }

    @TypeConverter
    fun fromTimestamp(date: Timestamp?): Long? {
        return (if (date == null) null else date!!.getTime())?.toLong()
    }
}