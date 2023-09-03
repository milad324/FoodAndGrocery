package com.shana.foodandgrocery.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shana.foodandgrocery.data.database.entitis.PlannerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlannerDao {
    @Insert
    suspend fun insertPlanner(planner: PlannerEntity)
    @Query("SELECT * FROM PLANNER_TABLE")
    fun readPlanner(): Flow<List<PlannerEntity>>
    @Query("DELETE  FROM PLANNER_TABLE WHERE id=:id")
    suspend fun deletePlanner(id:Long)
}