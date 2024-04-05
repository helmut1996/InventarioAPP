package com.example.inventaryapp.di

import android.content.Context
import androidx.room.Room
import com.example.inventaryapp.room.dao.CategoriaDao
import com.example.inventaryapp.room.dao.ProductosDao
import com.example.inventaryapp.room.dao.usuariosDao
import com.example.inventaryapp.room.database.InventarioDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuls {
    @Singleton
    @Provides
    fun providesUsuarioDao(inventarioDatabase: InventarioDatabase): usuariosDao {
        return inventarioDatabase.usuarioDao()
    }

    @Singleton
    @Provides
    fun providesCategoriasDao(inventarioDatabase: InventarioDatabase): CategoriaDao {
        return inventarioDatabase.categoriaDao()
    }

    @Singleton
    @Provides
    fun providesProductosDao(inventarioDatabase: InventarioDatabase): ProductosDao {
        return inventarioDatabase.productoDao()
    }

    @Singleton
    @Provides
    fun  providesCronosDatabase(@ApplicationContext context: Context):InventarioDatabase{
        return Room.databaseBuilder(
            context,
            InventarioDatabase::class.java, "inventario_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}


