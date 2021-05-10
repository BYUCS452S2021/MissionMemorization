Schema
User (user_id*, username, password, email, first_name, last_name)
The User table includes all the users of the app.
Column explanations:
user_id is the primary key for the User table
username is the user´s username
password is the user´s password
email is the user´s email that they register with
first_name is the user´s first name
last_name is the user´s last name

VerseSource (verse_id*, book_id, chapter, verse_num, text)
  Foreign Key book_id references BookInfo
Represents all the verse data except for which book the verse is in.
Column explanations:
verse_id is the primary key for the VerseSource table
book_id is the foreign key referencing the BookInfo table
chapter is an integer representing the chapter the verse is found in
Verse_num is an integer showing the number of the verse
text is the string containing the actual text of the verse

BookInfo (book_id*, book_name, book_abrev, volume, volume_url, book_url)
Represents data on which book and scripture volume verses are in.
Column explanations:
book_id is the primary key for the BookInfo table.
book_name is the full name of a given book.
book_abrev is the abbreviated name of the book.
volume is the volume of scripture the book is a part of.
volume_url is the volume url name used in accessing the scripture online.
book_url is the book url name used in accessing the scripture online.

Project (project_id*, user_id, folder_id, completed, attempts, corrects)
  Foreign Key user_id references User
  Foreign Key folder_id references Folder
Represents a group of verses for a user to memorize that is put into a folder.
Column explanations:
Project_id is the unique identifier in the project table
User_id references the User that owns the project
Folder_id references the Folder that each particular project is in
Completed - a Boolean signifying whether a scripture has been successfully memorized 
Attempts - an integer showing the number of attempts to memorize made by the user
Corrects - an integer showing the number of successful attempts to memorize

Project_Verse (project_id, scripture_id)
  Foreign Key project_id references Project
  Foreign Key verse_id references VerseSource
Join table for many to many relationship between Project and Verse.
Column explanations:
Project_id references the project that is part of this entity.
Scripture_id references the verse to be memorized.

Folder (folder_id*, user_id, folder_name)
  Foreign Key user_id references User
Displays the user-organized verses into what the database refers to as folders.
Column explanations:
folder_id is a unique identifier for folder table.
user_id references the user that owns the folder.
folder_name is the user-given name for the folder.

Evidence of Normalization:
All tables in the schema contain a key.  There is also no unnecessary repetition of data in multiple tables, meaning that there is no duplicate data.
