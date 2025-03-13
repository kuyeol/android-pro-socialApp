package com.ung.feedback.database.util

import androidx.room.TypeConverter
import java.time.Instant


class MyTypeConverter {
  @TypeConverter
  fun longToInstant(value: Long?): Instant? =
    value?.let(Instant::ofEpochMilli)

  @TypeConverter
  fun instantToLong(instant: Instant?): Long? =
    instant?.toEpochMilli()
}
