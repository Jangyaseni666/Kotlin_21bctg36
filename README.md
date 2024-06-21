# Lazy Loading:
Lazy loading is a design pattern commonly used in software development to defer the initialization of an object until it is needed. This can improve the performance of applications, especially in scenarios where loading all resources upfront would be resource-intensive and unnecessary. Lazy loading is particularly useful in web development, mobile applications, and data processing.
Example: Loading images, videos, and other multimedia content only when they are about to be viewed by the user.

## Benefits:
- Performance Improvement
- Reduced Memory Usage
- Improved User Experience

In Jetpack compose we use ```LazyRow```, ```LazyColumn```, ```LazyVerticalGrid``` and ```LazyHorizontalGrid``` to implement lazy loading.
 
# App LifeCycle:

The life cycle entails opening the app, performing a task and killing the app. It is also called as activity lifecycle.
The lifecycle methods are:  onCreate, onStart, onResume, onPause, onStop, onDestroy
- When app is first launched, onCreate function is called. One time setup is written inside this function
- onStart is called when the activity is visible to the user.
- onResume is called when the user is interacting with the activity.
- onPause is called when the user is about to leave the activity. Ex: When we get a notification from another app and we click on the notification.
- onStop is called when system puts the activity in the background, and another activity is about to start
- onDestroy is called to kill the activity

# Screenshot of Assignment:
<img src='https://github.com/Jangyaseni666/Kotlin_21bctg36/assets/96827920/77ed80b1-ea57-45cc-aeea-2a3003225502' height=800 width=400>
