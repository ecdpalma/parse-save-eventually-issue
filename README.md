# parse-save-eventually-issue
Test project to show the issue with saveEventually in Android.

https://developers.facebook.com/bugs/333837070143363

***How to run it:***

Clone the repo.
Set the app and client its in App.java
In Parse, create a Counter class with a column named 'value'

**Success case, i.e., data are saved to Parse:**


Pre-condition: full network access.

1. Run the app.
2. Tap SAVE in the action bar (saveEventually is called for a new item) -> a new row is created in Parse (Counter class, column value incremented for each new row).

**Error case, i.e., data are not saved to Parse**

Pre-condition: device with Wifi enable and connected to some LAN router, but access firewalled (i.e., device cannot access the internet)

1. Run the app.
2. Tap SAVE in the action bar (saveEventually is called for a new item) -> the following will be logged:

V/com.parse.ParseRequest﹕ Request failed. Waiting 1092 milliseconds before attempt #1
V/com.parse.ParseRequest﹕ Request failed. Waiting 2184 milliseconds before attempt #2
V/com.parse.ParseRequest﹕ Request failed. Waiting 4368 milliseconds before attempt #3
V/com.parse.ParseRequest﹕ Request failed. Waiting 8736 milliseconds before attempt #4
V/com.parse.ParseRequest﹕ Request failed. Giving up.

3. Move or unfilter the network so the device can fully access external networks -> there will be no retry. Any following call to saveEventually will result in nothing being save (even with full network access).
4. Disable/enable device's wifi -> same result as 3 outcome.
5. Exit the app main activity using the back button -> same result as 3
6. Exit the app and remove it from recent task list (in some Android versions, this kills the app process).
7. Start the app -> data cached by saveEventually are then sent.

What is expected: that the SDK would not give up retrying to send.

