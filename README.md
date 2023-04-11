# SWENSON HE Code Challenge

## Running the app

To quickly run the app you don't need any specific task. The project is written in Android Studio
Dolphin | 2021.3.1 Patch 1. Also please read the note section provided at the end of this
documentation to figure out about the temporary solutions provided for testing the app.

## Project structure and architecture

The app is implemented in Clean architecture with MVVM architecture design.  
The main packages are listed below:

- **data**: This package will be responsible to handle data models, data mappers (eliminated to save time), data sources,
  repository implementations and etc. This package can access the domain model to provide data for
  the next layer.
- **domain**: This layer will consist of entities, repository interfaces and usecase implementation
  to provide the data to the presentation layer. This layer is responsible for encapsulating complex
  business logic, or simple business logic that is reused by multiple viewModels.
- **presentation**: This layer is responsible to the UI implmentations. The MVVM architecture is
  implemented here. Presentation layer (Viewmodels) can only communicate with the domain layer (
  Usecases) to get the requested data to the UI components. Other packages consist of:
- **di**: To handle Dependency Injection by Hilt.
- **extension**: Provide some extension functions to make coding easier.
- **utils**: Provide some Helper classes.

## Tech stack - Libraries

- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - A coroutine is a concurrency design
  pattern that you can use on Android to simplify code that executes asynchronously
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
    - Flow is used to pass (send) a stream of data that can be computed asynchronously
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - for
  dependency injection.
- JetPack
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - For reactive
      style programming (from VM to UI).
    - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Used get
      lifecyle event of an activity or fragment and performs some action in response to change
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn't destroyed on UI changes.
    - [Room](https://developer.android.com/topic/libraries/architecture/room) - Used to create room
      db and store the data.
    - [Glide](https://bumptech.github.io/glide/) - Glide is a fast and efficient image loading library
      for Android

## Notes - Temporary solutions

- There are some TODOs in the viewModel that all are related to error handling. As there can be
  different types of error, I've provided the Error Handling basic logic, but to show the proper
  message(I skipped it due to the time saving) we can create a String LiveData, pass the throwable
  to a helper function to fetch the proper message according to the exception type, then UI can
  observe that live data to show the message to the user.
