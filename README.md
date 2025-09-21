# Seekho Assignment App 🎬

An Android app built with **Jetpack Compose, Retrofit, Room, and Material3**  
It fetches anime data using the [Jikan API](https://jikan.moe/).

## Features
- Fetch top anime list
- View anime details
- Watch trailer (YouTube)
- Offline mode with Room database
- MVVM architecture

## Screens
## List Screen
Displays top/popular anime from the Jikan API
Shows title, number of episodes, rating, and poster image
Supports offline caching of basic anime data

## Detail Screen
Displays detailed anime information including:
Title
Synopsis
Genres
Main cast
Number of episodes
Rating
Plays the trailer if available via YouTube (or fallback to poster image)
Fully scrollable layout
Offline data support
Offline Mode
Stores anime details locally using Room
Fetches updated data when online

## Architecture & Libraries
MVVM architecture with ViewModel and LiveData/StateFlow
Retrofit for API calls
Glide/Coil for image loading
Room for local database
YouTube WebView for trailer playback
Jetpack Compose Material3 for UI

## Screenshots
(Add your app screenshots here)

## Installation
Clone the repository:
git clone https://github.com/surojit1988/SeekhoAssignmentApp.git

