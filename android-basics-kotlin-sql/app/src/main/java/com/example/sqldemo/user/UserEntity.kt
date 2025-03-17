package com.example.sqldemo.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")
data class UserEntity(
  @PrimaryKey val id: String = UUID.randomUUID().toString(),
  val name: String,
  val age: Int,
  val email: String,
  val isFavorite: Boolean = false
)

