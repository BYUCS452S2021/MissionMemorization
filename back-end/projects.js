// Placeholder

const express = require("express");
const mongoose = require('mongoose');
const { model } = require("./verses")
// const sqlite3 = require('sqlite3').verbose();

const router = express.Router();

const projectSchema = new mongoose.Schema({
    folder_id: Number,
    user_id: String,
    verse_ids : [{
        type: mongoose.Schema.Types.ObjectId, ref: 'Verse'
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
    })
        .populate('verse_ids')
        .exec();
}

//I'm not sure who will be calling this
function getAllProjectsInFolder(folder_id) {
    return Project.find({
        folder_id: folder_id
    }).exec();
}


//add new project to user
router.post('/:folder_id?', async(req, res) => {

    if (!req.body.user_id || !req.body.verse_ids) {
        console.log("giving 400 error");
        return res.status(400).send({
            message: "user_id and at least one verse is required"
        });
    }

    try {
        const project = new Project({
            folder_id: req.params.folder_id,
            user_id: req.body.user_id,
            attempts: 0,
            corrects: 0,
            verse_ids: req.body.verse_ids
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