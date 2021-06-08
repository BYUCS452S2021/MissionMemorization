// Placeholder

const express = require("express");
const mongoose = require('mongoose');
const { model } = require("./verses")
// const sqlite3 = require('sqlite3').verbose();

const router = express.Router();

const projectSchema = new mongoose.Schema({
    folder_id: Number,
    user_id: String,
    verses : [{
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
    }],
    attempts: Number,
    corrects: Number
})

const Project = mongoose.model('Project', projectSchema);

// Add user Validation

//get all projects for a user (with or without folder id)
// this is a function because I expect it to be called by users.js login
function getAllRootProjects(user_id) {

    return Project.find({
        user_id: user_id,
        folder_id: null
    }).exec();
}

//I'm not sure who will be calling this
function getAllProjectsInFolder(folder_id) {
    return Project.find({
        folder_id: folder_id
    }).exec();
}


//add new project to user
router.post('/:folder_id?', async(req, res) => {

    if (!req.body.user_id || !req.body.verses) {
        console.log("giving 400 error");
        return res.status(400).send({
            message: "user_id and at least one verse_id is required"
        });
    }

    try {
        const project = new Project({
            folder_id: req.params.folder_id,
            user_id: req.body.user_id,
            attempts: 0,
            corrects: 0,
            // I'm not sure if this will work
            verses: req.body.verses
        });
        await project.save();

        res.send({
           project_id: project._id
        });
    } catch (error) {
        console.log(error);
        return res.sendStatus(500);
    }
});

//update project (once user gets project right or wrong)
// TODO implement this part

//delete project
router.delete('/:project_id', async(req, res) => {
   try {
        await Project.delete({
            _id: req.params.project_id
        });
   }
   catch (error) {
       console.log(error);
       return res.sendStatus(500);
   }
});

module.exports = {
    routes: router,
    getProjects: getAllRootProjects,
    getProjectsInFolder: getAllProjectsInFolder
  };