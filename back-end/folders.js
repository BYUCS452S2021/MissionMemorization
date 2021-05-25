const express = require("express");
// const mongoose = require('mongoose');
const sqlite = require("aa-sqlite");


const router = express.Router();


// Add user Validation

module.exports = {
    routes: router,
    // valid: validUser
  };

const DB_FILE_PATH = '../MissionMemorizeRelational.db';




/* API Endpoints */

/* All of these endpoints start with "/" here, but will be configured by the
   module that imports this one to use a complete path, such as "/api/folder" */


// create a new folder
// check that user doesn't already have that folder name and add it to Folder table
router.post('/', async (req, res) => {
    // Make sure that the request includes user_id and folder_name,
    // otherwise return an error.
    if (!req.body.user_id || !req.body.folder_name) {
      return res.status(400).send({
        message: "Must include user_id and folder_name in request"
      });
    }
  
    try {
      
      // open the database
      console.log(await sqlite.open(DB_FILE_PATH));

      // Check if folder name already exists for the user
      let sql = "select folder_id, user_id, folder_name" +
                  " from Folder where user_id = " + req.body.user_id +
                  " and folder_name = '" + req.body.folder_name + "'";
       
      result = await sqlite.all(sql, [])
        
      if (result.length > 0) {
        return res.status(400).send({
          message: "Folder name already exists for this user"
        });
      }

      // Add new folder to db
      sql = "INSERT INTO Folder (user_id, folder_name) VALUES("
                  + req.body.user_id + ", '" + req.body.folder_name + "');";

      r = await sqlite.run(sql);
      if(r) console.log("A folder has been inserted with rowid " + r.lastID);

      sqlite.close();

      return res.status(200).send({
        folder_id: r.lastID,
        message: "Folder added successfully"
      });
        
    } catch (error) {
      console.log(error);
      return res.status(500).send({
        message: error
      });
    }
  });