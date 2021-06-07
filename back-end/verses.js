// Placeholder
const express = require("express");
const mongoose = require('mongoose');
// const sqlite3 = require('sqlite3').verbose();


const router = express.Router();


const verseSchema = new mongoose.Schema({
  verse_id: Number,
  lang: String,
  book_name: String,
  book_abrev: String,
  volume: String,
  volume_url: String,
  book_url: String,
  chapter: Number,
  verse_num: Number,
  text: String
})


var verses = function(req, res, next) {
  var versesRaw = req.query.verses;
  var versesfiltered = versesRaw.split(',');
  let verselist = [];
  versesfiltered.forEach((verse, i) => { 
    var index = verse.indexOf('-');
    if (index !== -1) {
      let start = verse.substring(0, index);
      let end = verse.substring(index);

// TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  PARSE int INT
// Make a list of numbers between beginning and end!
// add to end of verselist

    } else {

// convert to int
      verselist.push(verse)
    }

// Return verselist
  });

} 


const Verse = mongoose.model('Verse', verseSchema);

// get a verse by id
router.get("/id/:verse_id", async (req, res) => {
  try {
    let verse = await Verse.findOne({verse_id:req.params.verse_id});
    return res.send(verse);
  } catch (error) {
    console.log(error);
    return res.sendStatus(500);
  }
});

// get a verse by full book name
router.get("/full", async (req, res) => {
  try {
    // Build Verse Parser
    let verse = await Verse.find({book_name:req.query.book, chapter:req.query.chapter, verse_num:req.query.verse});
    return res.send(verse);
  } catch (error) {
    console.log(error);
    return res.sendStatus(500);
  }
});

// get a verse by abreviation
router.get("/abrev", async (req, res) => {
  try {
    let verse = await Verse.find({book_abrev:req.body.book, chapter:req.body.chapter, verse_num:req.body.verse});
    return res.send(verse);
  } catch (error) {
    console.log(error);
    return res.sendStatus(500);
  }
});

// get a verse by url
router.get("/url", async (req, res) => {
  try {
    let verse = await Verse.findOne({book_url:req.body.book, chapter:req.body.chapter, verse_num:req.body.verse});
    return res.send(verse);
  } catch (error) {
    console.log(error);
    return res.sendStatus(500);
  }
});



// // get a verse by full book name
// router.get("/full", async (req, res) => {
//   try {
//     let verse = await Verse.findOne({book_name:req.body.book, chapter:req.body.chapter, verse_num:req.body.verse});
//     return res.send(verse);
//   } catch (error) {
//     console.log(error);
//     return res.sendStatus(500);
//   }
// });

// // get a verse by abreviation
// router.get("/abrev", async (req, res) => {
//   try {
//     let verse = await Verse.findOne({book_abrev:req.body.book, chapter:req.body.chapter, verse_num:req.body.verse});
//     return res.send(verse);
//   } catch (error) {
//     console.log(error);
//     return res.sendStatus(500);
//   }
// });

// // get a verse by url
// router.get("/url", async (req, res) => {
//   try {
//     let verse = await Verse.findOne({book_url:req.body.book, chapter:req.body.chapter, verse_num:req.body.verse});
//     return res.send(verse);
//   } catch (error) {
//     console.log(error);
//     return res.sendStatus(500);
//   }
// });



module.exports = {
    model: Verse,
    routes: router,
  };