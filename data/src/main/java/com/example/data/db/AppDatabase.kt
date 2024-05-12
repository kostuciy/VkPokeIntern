package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.db.converters.Converter
import com.example.data.db.dao.PokemonDao
import com.example.data.db.entity.AbilityEntity
import com.example.data.db.entity.LocationEntity
import com.example.data.db.entity.PokemonEntity
import com.example.data.db.entity.TypeEntity

@Database(
    entities = [
        PokemonEntity::class,
        TypeEntity::class,
        LocationEntity::class,
        AbilityEntity::class
               ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): PokemonDao
}