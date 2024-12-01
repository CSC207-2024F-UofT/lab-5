# CSC207 Project - Recipe Generator

## Team Members

⁠Farshad Haddadi 
* GitHub username: farshad-haddadi
* Email:⁠⁠ farshad.haddadi@mail.utoronto.ca

Hongcheng (Morgan）Huo
* Github username: hhcgoodluck
* Email: hhcgoodluck@outlook.com

Sung-chi (William) Wu
* GitHub username: ⁠⁠wusungch 
* Email:⁠⁠ sungchi2024@outlook.com

Xinyu (Cindy) Zhang
* GitHub username: Cindyzzz616
* Email: cxy.zhang16@gmail.com

Shuxin (Kate) Zhou
* GitHub username: kayzoo8
* Email: katezhou2005@gmail.com

## Table of Contents
1) [Features](#features-of-the-project)
2) [Installation Instructions](#installation-instructions)
3) [Usage](#usage)
4) [License](#license)
5) [Feedback and Contributions](#feedback-and-contributions)


## Features of the Project
### User Stories
*	Member 1: The core feature of the program allows users to input ingredients they have on hand and receive recipe suggestions. Users can enter ingredients separated by commas, and the system will generate relevant recipes. If some ingredients are missing, the system can suggest close alternatives or highlight what else is needed. This feature helps users efficiently use the ingredients they already own, potentially reducing food waste and unnecessary shopping trips.

*	Member 2: A key component of the system is the ability for users to filter recipes by diet and cuisine. This feature allows users to customize results based on dietary needs—such as vegan, gluten-free, or keto—or by preferred cuisines like Italian or Mexican. Users can apply multiple filters simultaneously to find recipes that meet both dietary restrictions and taste preferences. Managing these filters effectively ensures that the results remain useful even when dietary and cultural preferences overlap.

*	Member 3: Another feature provides detailed nutritional information for each recipe. Users can click a button to access data such as calorie counts, macronutrients (carbs, proteins, fats), and other key nutrients. This feature supports those who are tracking their nutrition or following specific dietary goals. It is important for the system to present this information clearly, accounting for potential variations in portion sizes and ingredient substitutions.

*	Member 4: The system will also allow users to bookmark recipes for future reference. This feature enables users to save recipes they find interesting or useful and organize them into categories like "Dinner" or "Favorites." Additionally, a "Recently Viewed" section can help users retrieve recipes they forgot to bookmark. The ability to sync bookmarks across devices ensures easy access whether users are on a phone or computer.

*	Member 5: Finally, the program offers a shopping list feature for adjacent recipes. When users are missing one or two ingredients for a recipe, the system will generate a shopping list with only the required items. This function can help users decide what to buy if they want to try new meals without doing extensive shopping. Export options, such as saving the list as a PDF or sharing it with other apps, enhance usability. The system will also need to distinguish between essential ingredients and common pantry staples to keep the lists concise.

## Installation Instructions
Ensure that you have Java installed on your machine. This program was developed with the version Java 22, so 
ensure you are up-to-date. This program is guaranteed to run on macOS or Windows, 
other OS or hardware has not been tested. Clone the Recipe Generator repository.
*Json and maven instructions?

## Usage
- *gif of running Main once code finalized

To run the application, go to the `src` folder, then open up `main`, click `java`, then `app`, and run `Main.java`.
The app will automatically load, then follow the prompts. Closing the application will close the 
entire program. (*unless we want to fix this)

- *gif of us logging in, searching recipes, filtering, nutrition, bookmarks, shopping list (demo entire program here?)

## License
This project has the Creative Commons License. See [LICENSE](LICENSE) for more information.

## Feedback and Contributions
- ***should we allow contributions?***

Contributions are welcome! If you find any issues or have suggestions for improvement, 
please open an issue.

If you wish to contribute, fork the repository by clicking the `<> Code` button on GitHub
and copy the `main` branch only. Then, create a local copy by coping the web URL, open your preferred IDE, 
and create a new project from version control by pasting the link.

To provide feedback or suggestions, go to the `Issues` tab on GitHub to create an issue. Add a title highlighting
your main points and a description of what you think could be changed. Feel free to mention specific files if
you'd like.

## Notes
- ~~should the bookmarks and recently viewed lists of a user be stored in the user.json file?~~ - implemented
- ~~individual recipe view only displays one ingredient right now~~ - fixed
- add a find recipe by name search function in the main search page
- make ingredients clickable in individual recipe view? Or at least display the amounts
- for the ingredient list in individual recipe view, split it into "used ingredients", "unused ingredients" and "missed ingredients" (right now there's only "missed ingredients")
- ^ might be too complicated to do
- when you double click on the recipes in the search view, you somehow get two copies of the individual recipe view??
- ^ doesn't happen all the time tho
- allow the user to choose how many recipes they want to see? Page flipping feature?
- show random recipes feature?
- allow users to create custom folders???
- allow users to press enter instead of using the search button?
- ~~*add clickable url to individual recipe view*~~
- allow users to select ingredients from individual recipe view and add them to their shopping list?
- ^ would be difficult to implement
- have an ingredient list where users can record the ingredients they have in their fridge
- change background colour?
- *make the recipes in shopping list view clickable*
- display no recipes found in search view
- add clear recently viewed button