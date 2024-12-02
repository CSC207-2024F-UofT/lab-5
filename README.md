# CSC207 Course Project- Recipe Management System
# Group 150
# Collaborators: Nora Francis, Adya Gopaul, Zain Malik


## Preamble

Software Specifications: [Adya]
This program allows users to either create a new account for a recipe organization system, or log in to their account using existing user credentials. Users will be able to search for recipes and apply filters to narrow search results and easily obtain recipes that appeal to them. Users can save recipes to their profiles to better organize them and reference them for future use. Additionally, users can review recipes that they have saved by providing a rating from one to five, with one being the lowest score and five being the highest.



Table of Contents
[add hyperlinks to sections below]

Features of the Software [Noura]
User Management:
Sign up: The user can sign up using a username and password
Log in: If the user already has an account, they can log in
Log out
Recipe Search:
Search for recipe: The user can search for recipes by name
Filter search results: The user can filter the search based on calories and nutrients
Recipe Interaction:
Review recipe: The user can review a recipe and save it to their profile
Profile Features:
View saved recipes: The user can view their saved recipes
*insert pictures

Installation Instructions [zain]
Prerequisites:
Java Development Kit (JDK): Amazon Corretto 11 or higher
Apache Maven
Instructions for CMD:
Clone the repository from The Repository
git clone https://github.com/NAF308/Recipe-Management-System.git
cd Recipe-Management-System
Use Maven to compile the project and fetch dependencies
mvn clean install
Run the application
mvn exec:java -Dexec.mainClass="app.Main"

Compatibility:
Both Amazon Corretto and Maven are available on Windows and Mac. The program will work on both and all other platforms that support them.

Usage Guide [zain]

Once the application is started. You will see the Sign up screen.
Choose your username and password.
Repeat the password then press sign up
You will then see the Login screen
Enter your password then press log in
You will then see the Profile Screen
Here you can change your password and go to recipe search and saved recipes
Pressing Recipe Search will take you to the recipe search window
There you can write parameters for the recipe search eg. Recipe Name, and calories range
Note: At Least one parameter needs to be filled and when writing ranges, the minimum can not be more than the maximum.
Pressing search will make an API call and you’ll be taken to the search results window.
In the Profile view if you press the Saved Recipes button, you’ll see a list of all the recipes you saved along with the rating you gave them.

License
[partial license given by Prof Jonathan Calver, we might need to read through and add adjustments]

Section for Feedback [Adya]
Considering the user’s experience with the Recipe Management System is an integral aspect of this project. We, the creators of this project, are open to accepting feedback on your experience using the system, suggest ways in which the system can be improved or recommend features you would like to see in the future. If you wish to do so, the best way to contact us is through the following email addresses.
*insert emails*
Adya Gopaul- adya.gopaul@mail.utoronto.ca


Contributions
This project is not accepting contributions at the moment. Persons interested in working with this software may create a fork of the repository by pressing the “Fork” button located in the top-right corner of the repository. Changes to the code may be made on the individual’s local repository after cloning their fork. Any merge requests made will neither be reviewed nor merged into the project. The individual will be solely responsible for any and all changes made to their fork of the repository.
# Accessibility Report
For each Principle of Universal Design, write 2-3 sentences — or point form notes — explaining which features of your program adhere to that principle. If you do not have any such features, you can either:

(a) Describe features that you could implement in the future that would adhere to the principle or

(b) Explain why the principle does not apply to a program like yours.

# Principles of Universal Design

## Equitable Use
“The design is useful and marketable to people with diverse abilities”
A computer mouse, touchpad or touch-screen device can be used to navigate the application, making it usable to people of different abilities. Individuals who may not have the fine motor skills associated with using a computer mouse will still be able to use the application to its full capabilities using a touch screen.

## Flexibility in Use
“The design accommodate a wide range of individual preferences and abilities”
Currently, the application on its own does not support full flexibility of use, however a user may still be able to utilize accessibility features built into their personal electronic devices to facilitate speech-to-text functionality or manually increase the font size or zoom in.

## Simple and Intuitive Use
“Use of the design is easy to understand, regardless of the user’s experience, knowledge, language skills or current concentration level”
Directions are given in simple, non-technical language. The flow of the application is logical and cohesive and the sequence of views that a user engages with is intuitive to follow. Prompts and text labels are also provided for additional clarity.

## Perceptible Information
“The design communicates necessary information effectively to the user, regardless of ambient conditions or user’s sensory abilities”
Non technical language with appropriate prompts, labels and error messages. While this may be adequate, it can be further improved by using colour to reflect different functionalities (for example, having “back” and “cancel” buttons in red and “search” and “save” buttons in green).

## Tolerance for Error
“The design minimizes hazards and the adverse consequences of accidental or unintended actions”
The back button and cancel buttons
However, once a change is made it cannot be undone. This can be improved by implementing an “undo” or “edit” functionality for features such as reviewing a recipe. Furthermore, a “forgot password” feature can be added so that users will not lose access to their account and saved recipes even if they are unable to remember their login credentials.

## Low Physical Effort
“The design can be used efficiently and comfortably and with a minimum of fatigue”
The application is quite straightforward and does not require excessive button clicks to navigate to different views and features, which reduces the physical effort exerted by the user. However, this principle can be improved upon further by increasing the size of the buttons to make them more easily clickable.

## Size and Space for Approach and Use
“Appropriate size and space is provided for approach, reach, manipulation and use regardless of user’s body size, posture, or mobility”
The main frame of the application can be made larger and depending on the device on which the software is being used, the user may also be able to zoom in on parts of the frame they wish to see better. However, this is mostly dependent on the user’s device rather than the software itself. This principle can be improved upon by implementing a feature that allows the user to enlarge the size of the panels on the frame rather than manually zoom in.


# Intended Users


In the event that this program were to be made available to customers, we would likely market the program towards individuals who are passionate about cooking or baking, whether that be as a profession, hobby, or necessity to provide for oneself and/or one’s family. Such individuals would benefit from having a system which allows them to easily search for new recipes and save them in the same place. Additionally, someone who is health conscious or has a condition that requires strict dietary monitoring (such as diabetes, high blood pressure, or simply a food allergy) may be attracted to the program’s filtering feature as it would make searching for recipes that meet their dietary requirements much easier.

Despite the wide variety of people who would enjoy using this program, it may not be best suited for individuals who do not cook often, whether that be due to a busy schedule, or to a preference for ready-made meals over those they have prepared themselves. An example of people who may fall into this category are healthcare workers, such as doctors and nurses, who work long hours and may not have the time to dedicate to meal planning and preparation. Additionally, since this project is entirely web-based, it may not be used by those who struggle to use or do not have reliable access to technology, for example elderly individuals or people who do not have their own electronic devices. Such groups may have no need for a Recipe Organization System, or may prefer physical cookbooks to online recipes.

People who cook or bake as a hobby, profession etc.
May not be used by elderly persons as they may prefer a physical recipe book.

Write a paragraph (3-6 sentences) about who you would market your program towards, if you were to sell or license your program to customers. This could be a specific category of users, such as "students", or more vague, such as "people who like games". Try to give a bit more detail along with the category.

Write a paragraph about whether or not your program is less likely to be used by certain demographics. Your discussion here should be informed by the content of our embedded ethics modules this term.
