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
    let verse = await Verse.findOne({book_name:req.body.book, chapter:req.body.chapter, verse_num:req.body.verse});
    return res.send(verse);
  } catch (error) {
    console.log(error);
    return res.sendStatus(500);
  }
});

// get a verse by abreviation
router.get("/abrev", async (req, res) => {
  try {
    let verse = await Verse.findOne({book_abrev:req.body.book, chapter:req.body.chapter, verse_num:req.body.verse});
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



module.exports = {
    model: Verse,
    routes: router,
  };