# CSC207 Course Project- Recipe Management System
# Group 150
# Collaborators: Noura Francis, Adya Gopaul, Zain Malik

# Table of Contents
- [Features of the Software](#features-of-the-software)
- [Installation Instructions](#installation-instructions)
- [Usage Guide](#usage-guide)
- [License](#license)
- [Feedback](#feedback)

## Software Specifications
This program allows users to either create a new account for a recipe organization system, or log in to their account
using existing user credentials. Users will be able to search for recipes and apply filters to narrow search results and
easily obtain recipes that appeal to them. Users save recipes to their profiles to better organize them and reference 
them for future use. Additionally, users may choose to review recipes that they have saved by providing a rating from
one to five, with one being the lowest and five being the highest, and an optional comment.


## Features of Software
### User Management
- **Sign Up**: The user can create an account using a username and password.
- **Log In**: Existing users can log in to their accounts.
- **Log Out**: Users can securely log out of the application.




### Recipe Search
- **Search for Recipes**: Users can search for recipes by name.
- **Filter Search Results**: Filters include options for:
  - Calories
  - Nutrients (Protein, Carbs, Fat)


 <img src="./images/search_example.png" alt="Search Example" width="400">


### Recipe Interaction
- **Review Recipe**: Users can add reviews to a recipe.
- **Save Recipe**: Users can save recipes to their profiles for easy access later.




### Profile Features
- **View Saved Recipes**: Users can view a personalized list of saved recipes.


---


## Installation Instructions


## Prerequisites
- **Java Development Kit (JDK)**: Amazon Corretto 11 or higher
- **Apache Maven**


## Instructions for CMD:
- **Clone the repository from**: [The Repository](https://github.com/NAF308/Recipe-Management-System)
- git clone https://github.com/NAF308/Recipe-Management-System.git
- cd Recipe-Management-System 


- **Use Maven to compile the project and fetch dependencies**
- mvn clean install 


- **Run the application**
- mvn exec:java -Dexec.mainClass="app.Main"


---


## Usage Guide
- Once the application is started. You will see the Sign up screen.
- Choose your username and password.
- Repeat the password then press sign up
- You will then see the Login screen
- Enter your password then press log in
- You will then see the Profile Screen
- Here you can change your password and go to recipe search and saved recipes
- Pressing Recipe Search will take you to the recipe search window
- There you can write parameters for the recipe search eg. Recipe Name, and calories range
- Note: At Least one parameter needs to be filled and when writing ranges, the minimum can not be more than the maximum.
- Pressing search will make an API call and you’ll be taken to the search results window.
- In the Profile view if you press the Saved Recipes button, you’ll see a list of all the recipes you saved along with the rating you gave them.

## Feedback
Considering the user’s experience with the Recipe Management System is an integral aspect of this project. We, the creators of this project, are open to accepting feedback on your experience using the system, suggest ways in which the system can be improved or recommend features you would like to see in the future. If you wish to do so, the best way to contact us is through the following email addresses.
Noura Francis: noura.francis@mail.utoronto.ca
Adya Gopaul: adya.gopaul@mail.utoronto.ca
Zain Malik:

## Contributions
This project is not accepting contributions at the moment. Persons interested in working with this software may create a fork of the repository by pressing the “Fork” button located in the top-right corner of the repository. Changes to the code may be made on the individual’s local repository after cloning their fork. Any merge requests made will neither be reviewed nor merged into the project. The individual will be solely responsible for any and all changes made to their fork of the repository.


* * *

