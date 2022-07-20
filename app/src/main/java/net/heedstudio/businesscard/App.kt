package net.heedstudio.businesscard

import android.app.Application
import net.heedstudio.businesscard.data.AppDatabase
import net.heedstudio.businesscard.data.BusinessCardRepository

class App : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}