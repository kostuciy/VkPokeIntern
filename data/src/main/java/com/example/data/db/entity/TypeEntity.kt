package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "typeentity")
data class TypeEntity(
    @PrimaryKey val typeId: Long,
    val typeName: String,
    val pokemonsType: Map<String, String> // name to url
)