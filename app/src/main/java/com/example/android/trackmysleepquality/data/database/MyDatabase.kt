/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.trackmysleepquality.data.sleep.db.SleepDao
import com.example.android.trackmysleepquality.data.sleep.db.SleepyNightEntity

@Database(entities = [SleepyNightEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
	
	abstract val sleepDao: SleepDao
	
	companion object {
		@Volatile
		private var INSTANCE: MyDatabase? = null
		
		fun getInstance(context: Context): MyDatabase {
			synchronized(this) {
				var instance = INSTANCE
				if (instance == null) {
					instance = Room.databaseBuilder(
						context,
						MyDatabase::class.java,
						"sleep_history_database"
					).fallbackToDestructiveMigration()
						.build()
					INSTANCE = instance
				}
				return instance
			}
		}
	}
}