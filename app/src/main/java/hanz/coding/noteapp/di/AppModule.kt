package hanz.coding.noteapp.di

import android.app.Application
import androidx.room.Room
import hanz.coding.noteapp.data.NoteDatabase
import hanz.coding.noteapp.data.NoteRepositoryImpl
import hanz.coding.noteapp.domain.NoteRepository
import hanz.coding.noteapp.presentation.note.NoteViewModel
import hanz.coding.noteapp.presentation.note_list.NoteListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get<Application>(),
            NoteDatabase::class.java, NoteDatabase.DB_NAME
        ).build()
    }.bind<NoteDatabase>()

    single { get<NoteDatabase>().noteDAO }
    single { NoteRepositoryImpl(get()) }.bind<NoteRepository>()

    viewModelOf(::NoteViewModel)
    viewModelOf(::NoteListViewModel)
}