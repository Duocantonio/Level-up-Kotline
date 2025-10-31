package com.example.level_up.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAllUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    @Update
    suspend fun updateUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)

}