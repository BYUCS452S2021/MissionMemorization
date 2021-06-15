// Placeholder

const express = require("express");
const mongoose = require('mongoose');
require("./verses");
require("./users");
const folders = require("./folders");
const Folder = folders.model;
// const sqlite3 = require('sqlite3').verbose();

const router = express.Router();

const projectSchema = new mongoose.Schema({

    folder_id: { type: mongoose.Schema.Types.ObjectId, ref: 'Folder'},
    user_id: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
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

function getAllFolders(user_id) {

    return Folder.find({
        user_id: user_id
    }).exec();
}

//I'm not sure who will be calling this
async function getAllProjectsInFolder(folder_id) {
    return Project.find({
        folder_id: folder_id
    })
        .populate('verse_ids')
        .exec(function(err, data) {
            console.log("data is " + data);
            return data;
        });
}

function deleteAllProjectsInFolder(folder_id) {
    Project.deleteMany({ folder_id: folder_id }).then(function(){
        console.log("Folder deleted");
    }).catch(function(error){
        console.log(error);
    });
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
router.put('/:project_id', async(req, res) => {
    try {
        Project.findOneAndUpdate({_id: req.params.project_id},
            {$set:{attempts:req.body.attempts, corrects:req.body.corrects}},
                {new: true}, (err, doc) => {
                    if (err) {
                        return res.sendStatus(500);
                    }
        });

        res.send({});
    }
    catch (error) {
        consolse.log(error);
        return res.sendStatus(500);
    }
});

//delete project
router.delete('/:project_id', async(req, res) => {
   try {
        await Project.deleteOne({
            _id: req.params.project_id
        });

       res.send({});
   }
   catch (error) {
       console.log(error);
       return res.sendStatus(500);
   }
});

module.exports = {
    routes: router,
    Project,
    getProjects: getAllRootProjects,
    getProjectsInFolder: getAllProjectsInFolder,
    deleteAllProjectsInFolder,
    getAllFolders
  };