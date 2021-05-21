const express = require('express');
const bodyParser = require("body-parser");
// const mongoose = require('mongoose');
const sqlite3 = require('sqlite3').verbose();

const DB_FILE_PATH = '../MissionMemorizeRelational.db'

// setup express
const app = express();

// setup body parser middleware to conver to JSON and handle URL encoded forms
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: false
}));


//   firstName: String,
//   lastName: String,
//   username: String,
//   password: String,
//   email: String


let db = new sqlite3.Database(DB_FILE_PATH, (err) => {
    if (err) {
        console.error(err.message);
    }
    console.log('Connected to the Mission Memorize database.');
});

const cookieParser = require("cookie-parser");
app.use(cookieParser());

const cookieSession = require('cookie-session');
app.use(cookieSession({
  name: 'session',
  keys: [
    'secretValue'
  ],
  cookie: {
    maxAge: 24 * 60 * 60 * 1000 // 24 hours
  }
}));

// import the users module and setup its API path
const users = require("./users.js");
app.use("/api/users", users.routes);

const verses = require("./verses.js");
app.use("/api/verses", verses.routes);

const folders = require("./folders.js");
app.use("/api/folders", foldersprojects.routes);

const projects = require("./projects.js");
app.use("/api/projects", foldersprojects.routes);


app.listen(3001, () => console.log('Server listening on port 3001!'));