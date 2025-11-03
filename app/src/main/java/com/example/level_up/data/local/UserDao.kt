package com.example.level_up.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE loggedIn = 1 LIMIT 1")
    suspend fun obtenerUsuarioActivo(): User?

    @Query("UPDATE users SET loggedIn = :estado WHERE correo = :correo")
    suspend fun actualizarEstadoSesion(correo: String, estado: Boolean)

    @Query("SELECT * FROM users WHERE correo = :correo AND clave = :clave LIMIT 1")
    suspend fun verificarCredenciales(correo: String, clave: String): User?




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    @Update
    suspend fun updateUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)

}