# Note

A simple notes app for Android, written in Kotlin with **Room** for local storage.

- Create, edit and delete notes, listed in a RecyclerView
- Notes persist locally through a Room database (`Note`, `NoteDao`, `NoteDatabase`)
- A repository sits between the DAO and a `NoteViewModel`, which the UI observes
- Kotlin Coroutines for database work off the main thread

**Stack:** Kotlin · Room · ViewModel + LiveData · Coroutines · RecyclerView · XML layouts

## Getting started

Clone, open in Android Studio and run the `app` configuration. No backend or API key is needed.

## Status

An early practice project from learning Room and the MVVM pattern. Kept as a record of that work.
