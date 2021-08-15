package com.example.android.trackmysleepquality.data.room

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.trackmysleepquality.data.sleep.db.SleepDao
import com.example.android.trackmysleepquality.data.sleep.db.SleepyNightEntity
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MyDatabaseTest {
	
	private lateinit var sleepDao: SleepDao
	private lateinit var myDatabase: MyDatabase
	
	@Before
	fun setUp() {
		val context = InstrumentationRegistry.getInstrumentation().targetContext
		myDatabase = Room.inMemoryDatabaseBuilder(context, MyDatabase::class.java)
			.allowMainThreadQueries()
			.build()
		sleepDao = myDatabase.sleepDao
	}
	
	@After
	@Throws(IOException::class)
	fun tearDown() {
		myDatabase.close()
	}
	
	@Test
	@Throws(Exception::class)
	fun insertAndGetNight() {
		val expectedNight = SleepyNightEntity()
		sleepDao.insert(expectedNight)
		// This is because of primary key autoincrement. Expected nightId == 0 by default.
		val actualNight = sleepDao.getTonight()?.copy(nightId = expectedNight.nightId)
		assertEquals(expectedNight, actualNight)
	}
}