# CSC207 Group Project

* * *

## Group 108 - Translator Program

* * * 

**Team Members:**
- Sean Yin (GitHub Username: PikachuSean)
- Haoxuan Qiu (GitHub Username: Kaguya-kyuu)
- Doran Wang (GitHub Username: Wang20030509)
- Charles Cai (GitHub Username: charles0504)

* * *

**Domain:** Translation generation and management

**Software Specification:**
The program allows users to translate between several languages. Users can create and sign in to accounts, allowing users to bookmark translations and view their translation history. Users can submit feedback on translations they think are inaccurate and can suggest a better translation.

**User Stories:**
1. Bob creates and signs in to an account for the translation app. He translates a sentence from one language to another and receives a translation from the app. (Team story)

2. Bob is a student in a language class. His teacher has created a class account for all the students to use, and he wants to see the translations that his teacher displayed in class. He signs in to the class account, and he views the translation history. (Doran)

3. Bob is a user of the translation app. He translates a sentence from one language to another. He finds the translation inaccurate and decides to submit a suggestion for a better translation. After some time, the suggestion is accepted by the developers of the app and now the app displays the new suggested translation. (Sean)

4. Bob is studying a second language. Sometimes he wants to be able to save his translation result for future review. So he decides to bookmark it so he can see it later by accessing his bookmarked translations. (Ericka)

5. Bob is sick and tired of google translate so he decided to find a better translator. He found this amazing translator called (our app name). He finds out there is a create account/sign in function which is always ignored on translator apps. He decided to create an account and found out there are so many useful functions! He translated a long paragraph that he will be working on for a few days. Unfortunately, he forgot to bookmark it, but he looks through his translation history to find it and bookmarks it from there so he can come back to it within a few clicks. (Charles)

**Proposed Entities for the Domain:**

Translator
- String inputLanguage
- String outputLanguage
- String inputText
- String translatedText

History
- User currentUser
- List<Translator> translationHistory

Bookmark
- User currentUser
- List<Translator> bookmarkedTranslations

User
- String username
- String password

Feedback
- Translator wrongTranslation
- String inputFeedback

**Proposed API to Use:**
https://www.deepl.com/en/docs-api

**Scheduled Meeting Times + Mode of Communication:**

Meeting time outside of lab: Meet on Friday afternoons
Mode of Communication: Discord group chat
