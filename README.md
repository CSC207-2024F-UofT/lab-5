### Team: ###
- Alina Tiemiertiei, alinaUofT 
- Rhea Sharma, RSharma-png 
- Alice Guo, aliceguo050 
- Nehir Arpat, nehirarpat 
- Wyatt Anderson, Jellyfish1996

### Use Cases ###

***- Color coding for UI Sketches:***
- Blue: Button
- Green: Text field
- Yellow: Text
- Purple: Pop-up screen
###
***Team Use Cases:***
- As a new user, I want to create an account, so that I can use this application.
  - User is shown to the Login/create account screen 
  - User clicks “Create new account” button 
  - User is taken to the New Account screen.
    - On screen, there are text fields for Username, Password, and Re-enter Password. 
  - User fills in all the fields accordingly. 
  - User clicks the “Create New Account” button. 
    - If Username is taken, then an error message (Username is taken) is shown on the same screen, and the user is prompted to try a different username. 
    - If Passwords do not match, then an error message (Passwords are not the same) is shown on the same screen, and the user is prompted to re enter the matching passwords 
    - If no errors, then a confirmation message (Account Created) is shown, and the user is taken to the survey screens. 
      - After successful completion of the survey, take the user to the Home screen.
- UI Sketch: https://www.figma.com/board/jVZQpyrciHAIYiF0vjiLMk/Team-UI-1?node-id=0-1&t=9SqCqI8Xw9p3mCSe-1
###
- As a user, I want to log into my account, so that I can use this application. 
  - User is shown to the Login/create account screen 
  - User enters their username and password. 
  - User clicks “Login” button 
    - If the username is not stored in our database, then an error message (Incorrect Username) is shown on the same screen, and the user is prompted to re-enter a different username or to create a new account 
    - If the password does not match the associated username, then an error message (Incorrect Password) is shown on the same screen, and the user is prompted to re-enter a different password 
    - If no errors, then a confirmation message of successful login is shown, and the user is taken to the Home screen
- UI Sketch: https://www.figma.com/board/gTYJp2yL6EywDpL2ifVKN7/Untitled?node-id=0-1&t=jzokhz9uaNmnUQuC-1
###
- As a user, I want to log out of my account, so that I can exit this application. 
  - User is on the Home screen. 
  - User clicks “Logout” button 
  - User is taken to Login/create account screen
- UI Sketch: https://www.figma.com/board/LA46lp4TAlUzx42Zo1aaRS/Untitled?node-id=0-1&t=30mtHh5VnYnuPXQ3-1
###
- As a user, I want to get recommendations based on movies that I watched previously so that I can find new movies. User is recommended a movie.
  - User is on the Home screen.
  - User clicks on “Recommendation” button
  - User is taken to Recommendation screen, where they can view a list of movies recommended by the program
    - Users can click on the movies to access additional information (See Use case)
    - After clicking, users can click the “I’ve watched this movie” button
- UI Sketch: https://www.figma.com/design/MdgYQnkcPF4Mw59RBqBVyg/Untitled?node-id=0-1&t=RAeYgzQ0hCMjdIcE-1
###
***Wyatt’s Use Cases:***
- As a user, I want to leave a star rating and an optional written review for a specific movie, so that others can see my experience/thoughts on the movie.
  - To leave a review the user will have to first search the movie to access the movie screen boundary.
  - The user will then need to select “I watched this”
  - The “I watched this” window pane will then be updated to the “leave a review” button pane
  - If the user has already added this movie to their watchlist then the “leave a review” button pane will already be there.
  - This will call the controller to run the input boundary for the leave review use case.
  - The leave review use case input boundary will look as follows:
  - The user will be prompted with a text box for their written review (250 character max) (optional)  a star rating between 0 and 5 (required) and a back/home button (that will take them back to the homepage).
  - After completing their review they will hit submit.
  - The use case interactor will check if the star value is an integer between 0 and 5
  - If it is not an integer value between 0 and 5 return an error.
  - Otherwise, the use case interactor will trigger the leave review use case.
  - The leave review use case once called by the interactor will work as follows (we assume the necessary inputs have been provided) :  First check the reviewed object in our online database to see if the movie exists. If it does not exist under review then create a new movie object in our online database under movies and add to it the written review and star rating.
  - If successful, return “review created”.

- As a user, I want to view my past reviews that I have left for movie.
  - To view past reviews the user will click the “my reviews” button from the home screen. 
  - This will open the my reviews screen.
  - The screen will either show one of two things:
  - If the user has not left any review yet it will simply display a textbox stating so.
  - If the user has left reviews then they will be listed in a textbox with scroll enable along with the date posted.
  - Each review will be formatted in the textbox appropriately. 
  
- As a user, I want to see other people's written reviews for a specific movie and the average star rating for that movie.
  - On the movie screen there will be a button to select “user reviews”
  - This will call the controller to check if the movie exists in our online database
  - If it does not exist then return a message stating no reviews/ratings found.
  - If it does exist, have our use case interactor call the methods on the movie that return its star rating ( should be the average of all star ratings) and also the written reviews (formated appropriately)
  - Switch to display review output boundary and inject both the reviews and star rating provided by the view model into two panes. The first will be a scroll enabled text box with each review written separated by a full line in between each separate review (again this is provided by a method within the movie, if no written reviews exist write “no written reviews found”). The second will be 5 adjacent stars, filled up appropriately to represent the average star rating for the movie returned from the get ratings method which will be defined within the movie class.
  - There will also be a back button to return to the home screen.

- UI Sketches: https://www.figma.com/board/odzhDFeZ9nnXscD8xVAI8O/my-UI?node-id=0-1&t=3caD0awIRqbiKKYf-1
###

***Alice's Use Case***
- As a new user, I want to take an initial survey to establish my preferences so that I can get generated default recommendations.
  - User is shown Survey screen 1
  - User select 3 of their favourite genres by clicking the associated buttons
    - If the user clicks less than 3 genres, they will not be able to submit (the button will be grey). Once the user selects 3 genres, the Submit button will turn green and is click-able.
    - When the user clicks on a genre button, borders will appear around it, suggesting that the box had been clicked
  - Once user is done, user clicks the “Submit” button, and is taken to Survey screen 2
  - On Survey screen 2, the user searches and selects 3 movies that they’ve previously watched.
    - If they search for a movie that does not match any titles in our database, an error message will appear (No movie found), and the user is prompted to re-enter a different movie name
    - If there are no errors, a confirmation message (Movie selected) will appear.
    - If the user selects less than 3 movies, they will not be able to submit (the button will be grey). Once the user selects 3 genres, the Submit button will turn green and is click-able.
  - Once user is done, user clicks the “Submit” button, and is taken to Home screen
  - User is taken to Home screen and is shown a pop-up window (now you can view your recommendations below)
- UI Sketch: https://www.figma.com/design/Xb7jVHywI8K1tLwIAfp7dm/Untitled?node-id=0-1&t=BxHCOusQxYNt7SSz-1
###
***Nehir's Use Case***
- As a user, I want to create additional watchlists and add specific movies (e.g. category, to watch, favorites, etc.), so that I can categorize movies to my preferences.
  - User is on the Home Screen
  - User clicks on the “My Watchlists” button
  - User is taken to the My Watchlists screen
    - On the screen, the user can see all watchlists created by the user including the “Previously Watched” list generated as default and a “Create a New List” button.
    - If there aren't any custom watchlists created there will only be the “Previously Watched” list.
  - User clicks on the “Create a New List” button.
    - A page pops up with a text field for “List Name” (default is “List #1”, increments accordingly) and a “Create List” button
  - User fills text field with desired list name or leaves it empty (default will be assigned)
    - 100 characters max for list name
    - Error message is shown if the list name exceeds 100 characters (Exceeds 100 characters!) and “Create a New List” button is un-clickable.
  - User clicks Create List 
    - An empty list screen with the list name on top and an “Add Movie” button opens
  - User clicks “Add movie” button
    - A new screen with a search bar (text field) and Search button opens
  - User types in the title of the desired movie into the search bar (exact title)
  - User clicks Search button
    - Movie screen is shown
    - Movie information, “I watched this” button, “Add to list” button, “User Reviews” button
  - User clicks on the “Add to list” button
  - List Screen pops up
    - List names are shown
  - User clicks on the list they want to add the movie to
    - If a movie is already on the list, the list will appear with borders
    - If the bordered list is clicked, the movie is removed from the list through a confirmation message.
  - The movie is added onto the list
    - A confirmation message pops us and list screen is closed
      - Message and “Close” button
    - When “Close” is clicked, user returns to Movie Screen. From there, they can add the movie to another list or go back to the search screen by clicking “Back” button
- UI Sketch: https://www.canva.com/design/DAGWADUDt98/efepPEZTM00gCa3Ee74mHg/edit?utm_content=DAGWADUDt98&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
###
***Alina's Use Case***
- As a user, I want to add previously watched movies to my previously watched list (PWL), so that the application doesn’t recommend movies that I’ve already seen and to keep track of the movies I have watched.
  - User is on the Home Screen
  - User clicks on the “My Watchlists” button
  - User is taken to the My Watchlists screen
  - User is taken to a screen with only the empty “Previously Watched” list generated as default.
  - User can add watched movies by clicking on the “Previously Watched” screen.
  - Screen displays an empty list screen with the list name on top and an “Add Movie” button if the user skipped step 2 of the survey when creating their account.
  - Screen displays
  - User add movies to the list as described in Nehir’s use cases.
  - User clicks on the “Recommendations” button on the home screen and is taken to the Recommendations screen with updated movie recommendations that are not in the “Previously Watched” list.
- UI Sketch: https://www.figma.com/design/IQVo41Hr9JiNmATohpKy2H/Alice-UI-sketches?node-id=0-1&t=Qwu64L8FEirIUzwV-1
###
- As a user, I want to access and manage  all watchlists (e.g. PWL, to watch, recommended, etc.), so that I can view the movies that I’ve already seen and want to see.
  - On the homescreen user clicks “My watchlists” button
  - User is taken to the watchlists page. The page has a “Home” button top left, “Create a watchlist” button below “Home” in the middle and a list of watchlists below “Create a watchlist”, that starts with the PWL and then has other watchlists in the alphabetic order. Names of the watchlists are buttons. Every playlist, except for PWL, has a “rename” button, followed by the “delete” button to the right of its name.
  - To go back to home screen:
    - User click the “Home” button
    - User is taken to the homescreen
  - To delete a playlist:
    - User clicks a “delete” button corresponding to the playlist they want to delete.
    - A window pops up with text “Are you sure you want to delete playlist_name playlist?” and buttons “Yes” and “No” below it.
      - If the user clicks “No”:
        - The pop up window disappears
        - Watchlists window is unchanged.
      - If the user clicks “Yes”:
        - The playlist is deleted
        - The pop up window disappears
        - The watchlists page is updated to no longer have that watchlist on the list.
  - To rename the watchlist:
    - User clicks a “rename” button corresponding to the playlist they want to rename
    - A window pops up with text “Enter new name:”, a text field below it and buttons ”confirm” and “cancel” below the text field.
      - If user clicks the “cancel” button:
        - The pop up window disappears, watchlists page is unchanged
      - If user enters a new name in the text field and clicks the “confirm” button
        - If a valid name for a playlist was entered:
          - The pop up window disappears
          - The watchlist name is updated for this watchlist
          - The watchlists page is updated to have the new name for this playlist
        - Otherwise:
          - A red message appears below the text field “Enter a valid playlist name”
  - To see the content of the watchlist:
    - User clicks on the name of the watchlist
    - User is taken to the page that corresponds to that watchlist. The page has a “Home” button top left, “Add a movie” button below “Home” in the middle and a list of movies (in the order reverse of the order they were added in) below the “Add a movie”. Names of the movies are buttons. Every movie has a “remove” button to the right of its name.
  - To remove a movie from the watchlist:
    - User clicks a “remove” button
    - A window pops up with text “Are you sure you want to remove movie_name from the playlist?” and buttons “Yes” and “No” below it.
      - If the user clicks “No”:
        - The pop-up window disappears
        - Watchlist window is unchanged.
      - If the user clicks “Yes”:
        - The movie is deleted from the playlist
        - The pop-up window disappears
        - The watchlist page is updated to no longer have that movie on the list.
  - To view the info about the movie on the watchlist:
    - User clicks on the name of the movie
    - User is taken to the page containing info about that movie
- UI Sketch: https://www.figma.com/board/aeVSsGygPZisKgZesNCwGN/Alina-Tiemiertiei?node-id=28-247&t=mRigDak78WMw11ms-1
###
***Rhea's Use Case***
- As a user, I want to access info about the movies (description, review, rating, movie details, etc), so that I can better understand if I want to see the movie.
  - User searches for a movie.
  - User is on the Home screen. 
  - User clicks on search text field, enters the name of the movie, and presses the search button 
    - If there is no movie title found in the database, then a error message is shown (No Movie Found), and the user is prompted to re enter a different Title
    - If there are no errors, then the user is take to the Search Results screen
  - User is on the Search Results screen, and can select a provided movie that match the search results 
  - User clicks on the movie icon and is taken to the associated Movie screen. 
  - User is on the associated Movie screen and can read through the available information (e.g. ratings, summary, actors, etc.) 
- UI Sketch: https://www.figma.com/board/5lEKZW5BJ2hcl2Zel8PnN3/Rhea's-Use-Cases?node-id=0-1&t=xZLEaDxes4PWcUHE-1
###

