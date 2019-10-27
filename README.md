# iTunesSearchListApp
iTunesSearchListApp is an Android app which displays a list of items obtained from an iTunes Search API and show a detailed view of each item.


## Data to be saved:
  1. Lists of the tracks - This is used to restore the data when the user reopens the app
  2. Track selected - This is used to restore the data when the user reopens the app
  3. Date of previously visited - This is shown in the list header of the list
   
 ## Requirements:
  - Android version 24(Lollipop) - 29 (Android Q)
  
 ## Additional Information:
    1. MVVM Architecture - I used this architecture since this architecture enhances separation of concerns, it allows separating the user interface logic from the business (or the back-end) logic. 
    2. Repository pattern - I used this pattern in the model part since it decouples application from the data sources and testable business logic via Unit Tests
    3. Libraries:
      - RXJava for data holders(https://github.com/ReactiveX/RxJava).
      - Retrofit for http requests(https://square.github.io/retrofit/).
      - moshi for Json library for Android(https://github.com/square/moshi).
      - Glide for loading image into the imageview(https://github.com/bumptech/glide).
