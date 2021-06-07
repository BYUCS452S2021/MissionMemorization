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


var verseParser = function(req) {  // , res, next
  // var versesRaw = req.query.verses;
  var versesfiltered = req.split(',');  // versesRaw.split(',');
  let verselist = [];

  versesfiltered.forEach((verse, i) => { 
    var index = verse.indexOf('-');
    if (index !== -1) {
      let start = parseInt(verse.substring(0, index));
      let end = parseInt(verse.substring(index + 1));
      if (start > end) {
        [start, end] = [end, start];
      }
      for (let j = start; j < end + 1; ++j) {
        verselist.push(j);
      }
    } else {
      verselist.push(parseInt(verse));
    }
  });
  return verselist;
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
    let verselist = verseParser(req.query.verses)

    let verse = await Verse.find({book_name:req.query.book, chapter:req.query.chapter, verse_num: {$in: verselist} }); //req.query.verse
    return res.send(verse);
  } catch (error) {
    console.log(error);
    return res.sendStatus(500);
  }
});

// get a verse by abreviation
router.get("/abrev", async (req, res) => {
  try {
    let verselist = verseParser(req.query.verses)

    let verse = await Verse.find({book_abrev:req.query.book, chapter:req.query.chapter, verse_num: {$in: verselist} });
    return res.send(verse);
  } catch (error) {
    console.log(error);
    return res.sendStatus(500);
  }
});

// get a verse by url
router.get("/url", async (req, res) => {
  try {
    let verselist = verseParser(req.query.verses)

    let verse = await Verse.find({book_url:req.query.book, chapter:req.query.chapter, verse_num: {$in: verselist} });
    return res.send(verse);
  } catch (error) {
    console.log(error);
    return res.sendStatus(500);
  }
});



module.exports = {
    model: Verse,
    routes: router,
  };


  

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



// var parseVerses = function(versesRaw) {
//   var versesfiltered = versesRaw.split(',');
//   let verselist = [];
//   versesfiltered.forEach((verse, i) => { 
//     var index = verse.indexOf('-');
//     if (index !== -1) {
//       let start = parseInt(verse.substring(0, index));
//       let end = parseInt(verse.substring(index));
//       if (start > end) {
//         [start, end] = [end, start];
//       }
//       for (let j = start; j < end + 1; ++j) {
//         verselist.push(j);
//       }
//     } else {
//       verselist.push(parseInt(verse));
//     }

//     return verselist;
// }
