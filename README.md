# Marvel Characters
Android app that displays a list of marvel characters and the details of each character: comics, events, series, and stories. Also you can search for your favourite character.

## Structure
------------
The app is implemented based on MVVM design pattern.

***Models*** represented as data classes that provide the needed data to be displayed in the views from network.

***Repository and ViewModels*** the logic of how retrieve the data from backend or network in our case is [Marvel API](https://developer.marvel.com/).

***Views*** the app UI that contains main activity and three fragments: characters, search, and details. Each one is responsible for dispaly it's data.

## Dependencies
---------------
- Android archituecture components:
  - Paging
  - ViewModel
  - LiveData
- Navigation component
- Hilt dependency injection
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)
- [Glide](https://github.com/bumptech/glide)

## Screenshoots
---------------
<p align="center">
    <img src="screenshots\splash_home.gif" width="200">
    <img src="screenshots\search.gif" width="200">
    <img src="screenshots\details.gif" width="200">
</p>