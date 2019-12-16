# MyCodingChallenge

# Objectives:
Create a master-detail application that contains at least one dependency. This application should display a list of items    obtained from a iTunes Search API and show a detailed view of each item.


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
      - [RXJava](https://github.com/ReactiveX/RxJava) for data holders.
      - [Retrofit](https://square.github.io/retrofit/) for http requests.
      - [moshi](https://github.com/square/moshi) for Json library for Android.
      - [Glide](https://github.com/bumptech/glide)for loading image into the imageview.
    
