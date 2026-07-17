## Android Room Example

A simple task management app that demonstrates local data storage using Android Room. Create, edit, and delete tasks — all stored on your device.

![Build Status](https://github.com/yoesuv/android-room-example/actions/workflows/github-actions.yml/badge.svg)

### Features

- Add new tasks with a title and content
- Edit existing tasks
- Delete individual tasks or clear all at once
- Tasks are saved locally and persist after closing the app

### Screenshots

| ![](https://i.imgur.com/muj97TJ.png) | ![](https://i.imgur.com/wlkrJgq.png) | ![](https://i.imgur.com/N95oBo6.png) | ![](https://i.imgur.com/ny1fb2H.png) |
| :----------------------------------: | :----------------------------------: | :----------------------------------: | :----------------------------------: |

### Requirements

- Android 7.0 (API 24) or higher

### Running UI Tests

```
./gradlew connectedForTestDebugAndroidTest
```

### Tech Stack

- Kotlin
- Room (local database)
- MVVM architecture
- Material Design 3
