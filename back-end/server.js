const express = require('express');
const bodyParser = require("body-parser");
const mongoose = require('mongoose');


// IMPORTANT:  UPDATED FOR MONGODB

//   firstName: String,
//   lastName: String,
//   username: String,
//   password: String,
//   email: String

// setup express
const app = express();

// setup body parser middleware to conver to JSON and handle URL encoded forms
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: false
}));


// connect to the mongodb database
mongoose.connect('mongodb+srv://mmbackend:kmpLHi86iTZh8f8w@missionmemorize.bcz51.mongodb.net/MissionMemorize?retryWrites=true&w=majority', {
  useUnifiedTopology: true,
  useNewUrlParser: true
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
const user = require("./users.js");
app.use("/api/user", user.routes);

//Import modules for verses, folders, and projects
const verseSource = require("./verses.js");
app.use("/api/verse", verseSource.routes);

const projects = require("./projects.js");
app.use("/api/project", projects.routes);

const folders = require("./folders.js");
app.use("/api/folder", folders.routes);

app.listen(3001, () => console.log('Server listening on port 3001!'));