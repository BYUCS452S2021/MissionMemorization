// Placeholder
const express = require("express");
const mongoose = require('mongoose');
// const sqlite3 = require('sqlite3').verbose();


const router = express.Router();


// const verseSchema = new mongoose.Schema({
//   verse:
// })



// // get one photo
// router.get("/photo/:photoid", async (req, res) => {
//   try {
//     let photo = await Photo.findOne({_id:req.params.photoid}).populate('user').populate('location');
//     return res.send(photo);
//   } catch (error) {
//     console.log(error);
//     return res.sendStatus(500);
//   }
// });

module.exports = {
    // model: Verse,
    routes: router,
  };