package hanz.coding.noteapp

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.FirebaseApp
import hanz.coding.noteapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NoteApplication : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@NoteApplication)
            androidLogger()
            modules(appModule)
        }
    }
}