# CSC207 Project - Recipe Generator

## Project Contributors

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

## Purpose
This project generates a list of recipes from user-inputted ingredients (up to 20 char long). Users can focus on 
ingredients they have and generate recipes that meets their needs, rather than coming up with a specific dish 
that might use those ingredients. If the user had specific dietary needs and a unique ingredient, it may be difficult
to know what to search for. Each recipe will have the option to display its full nutritional information for those
who have specific health requirements. This project allows users to bookmark their favourite recipes as well as 
organize them into folders for easy access. Oftentimes, one may find a recipe but only be missing a couple of 
ingredients, our program can generate a shopping list for the user.

## Table of Contents
1) [Features](#features-of-the-project)
2) [Installation Instructions](#installation-instructions)
3) [Usage](#usage)
4) [License](#license)
5) [Feedback and Contributions](#feedback-and-contributions)


## Features of the Project
### User Stories (that each contributor was mainly responsible for)
*	**William**: The core feature of the program allows users to input ingredients they have on hand and receive 
recipe suggestions. Users can enter ingredients separated by commas, and the system will generate relevant 
recipes. If some ingredients are missing, the system can suggest close alternatives or highlight what else is 
needed. This feature helps users efficiently use the ingredients they already own, potentially reducing food 
waste and unnecessary shopping trips.


*	**Kate**: A key component of the system is the ability for users to filter recipes by diet and cuisine. 
This feature allows users to customize results based on dietary needs—such as vegan, gluten-free, or keto—or 
by preferred cuisines like Italian or Mexican. Users can apply multiple filters simultaneously to find recipes 
that meet both dietary restrictions and taste preferences. Managing these filters effectively ensures that the 
results remain useful even when dietary and cultural preferences overlap.


*	**Hongcheng**: Another feature provides detailed nutritional information for each recipe. Users can click a button 
to access data such as calorie counts, macronutrients (carbs, proteins, fats), and other key nutrients. This 
feature supports those who are tracking their nutrition or following specific dietary goals. It is important 
for the system to present this information clearly, accounting for potential variations in portion sizes and 
ingredient substitutions.


*	**Cindy**: The system will also allow users to bookmark recipes for future reference. This feature enables 
users to save recipes they find interesting or useful and organize them into categories like "Dinner" or 
"Favorites." Additionally, a "Recently Viewed" section can help users retrieve recipes they forgot to bookmark.


*	**Farshad**: Finally, the program offers a shopping list feature for adjacent recipes. When users are missing 
one or two ingredients for a recipe, the system will generate a shopping list with only the required items. This 
function can help users decide what to buy if they want to try new meals without doing extensive shopping. 
Export options, such as saving the list as a PDF or sharing it with other apps, enhance usability. The system 
will also need to distinguish between essential ingredients and common pantry staples to keep the lists concise.

### Software
This project closely follows Clean Architecture, so the program is divided from high-level to low-level layers,
from entities to the database. This program makes use of the Spoonacular API to retrieve recipes and their
details, like ingredients, nutrition, diet, etc. The Recipe Generator is displayed using the Java Swing GUI in
a user-friendly manner with instructions at each step.

![Screenshot of "Welcome" homepage for Recipe Generator](images/Welcome_Homepage.png)

## Installation Instructions
Ensure that you have Java installed on your machine. This program was developed with the version Java 22, so 
ensure you are up-to-date. This program is guaranteed to run on macOS or Windows, 
other OS or hardware has not been tested. Fork the Recipe Generator repository by clicking the 'Fork' button
on GitHub and select 'copy the `main` branch only'. Then, create a local copy by coping the web URL, open your 
preferred IDE, and create a new project from version control by pasting the link.

The `pom.xml` file contains the project configuration. The IDE should have detected this file and run the 
maven commands. If src/main/java isn't 'blue' (or marked as the Sources Root) and src/test/java isn't 'green', 
you may need to right click `pom.xml` and select 'Maven -> Reload project'.

## Usage
To run the application, go to the `src` folder, then open up `main`, click `java`, then `app`, and run `Main.java`.
The app will automatically load, then follow the prompts. Closing the application will close the 
entire program.

## License
This project has the Creative Commons License. See [LICENSE](LICENSE) for more information.

## Feedback and Contributions
Contributions are welcome! If you find any issues or have suggestions for improvement, 
please open an issue.

If you wish to contribute, fork the repository by clicking the `Fork` button on GitHub
and copy the `main` branch only. Then, create a local copy by coping the web URL, open your preferred IDE, 
and create a new project from version control by pasting the link.

To provide feedback or suggestions, go to the `Issues` tab on GitHub to create an issue. Add a title highlighting
your main points and a description of what you think could be changed. Feel free to mention specific files if
you'd like.

