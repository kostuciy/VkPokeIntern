package com.example.vkpokeintern.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vkpokeintern.db.converters.Converter
import com.example.vkpokeintern.db.dao.PokemonDao
import com.example.vkpokeintern.db.entity.AbilityEntity
import com.example.vkpokeintern.db.entity.LocationEntity
import com.example.vkpokeintern.db.entity.PokemonEntity
import com.example.vkpokeintern.db.entity.TypeEntity

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