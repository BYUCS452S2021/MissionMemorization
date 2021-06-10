const mongoose = require('mongoose');
const express = require("express");
const router = express.Router();
const { deleteAllProjectsInFolder } = require('./projects');
const { getProjectsInFolder } = require('./projects');

const users = require("./users.js");
const User = users.model;
const validUser = users.valid;

const folderSchema = new mongoose.Schema({
  user_id: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
  folder_name: String,
  created: {
    type: Date,
    default: Date.now
  },
});

const Folder = mongoose.model('Folder', folderSchema);


 // make a new folder
 router.post("/", validUser, async (req, res) => {
  try {
      // Make sure that the request includes user_id and folder_name,
      // otherwise return an error.
      if (!req.body.user_id || !req.body.folder_name) {
        return res.status(400).send({
          message: "Must include user_id and folder_name in request"
        });
      }

      var folder = await Folder.findOne({
        user_id: req.body.user_id,
        folder_name: req.body.folder_name
      })

      if(folder != null){
        return res.status(400).send({
          message: "Folder already exists for this user"
        });
      } else {
        let folder = new Folder({
            user_id: req.body.user_id,
            folder_name: req.body.folder_name,
        })

        await folder.save();
        return res.status(200).send({folder_id: folder._id});
      }
  } catch (error) {
      console.log(error);
      return res.status(500).send({
          message: "Error creating folder."
      });
  }
});

// Retrieve all of a users folders
router.get("/all", validUser, async (req, res) => {
  if (!req.body.user_id) {
    return res.status(400).send({
      message: "Must include user_id in request"
    });
  }

  try {
    let folders = await Folder.find({
      user_id: req.body.user_id,
    }).sort({
      created: -1
    }).populate('user');

    let projects = [];
    for (const f of folders){
      var projectsInFolder = await getProjectsInFolder(f._id);
      projects.push(projectsInFolder);
    }

    return res.status(200).send({folders: folders, projects: projects});  
  } catch (error) {
    console.log(error);
    return res.sendStatus(500);
  }
});

// Delete specified folder
router.delete("/:folder_id", validUser, async (req, res) => {
  try{
    let folder = await Folder.findOne({_id:req.params.folder_id});
    if(folder == null){
      return res.status(400).send({
        message: "Folder does not exist"
      })
    }

    await deleteAllProjectsInFolder(folder._id);

    await Folder.deleteOne({_id:folder._id});

    return res.status(200).send({
      message: "Folder deleted successfully"
    });
  } catch {
    console.log(error);
    return res.sendStatus(500);
  }
});


// Add user Validation

module.exports = {
    model: Folder,
    routes: router,
    // valid: validUser
  };

// const DB_FILE_PATH = '../MissionMemorizeRelational.db';






/* API Endpoints */

/* All of these endpoints start with "/" here, but will be configured by the
   module that imports this one to use a complete path, such as "/api/folder" */


// // create a new folder
// // check that user doesn't already have that folder name and add it to Folder table
// router.post('/', async (req, res) => {
//     // Make sure that the request includes user_id and folder_name,
//     // otherwise return an error.
//     if (!req.body.user_id || !req.body.folder_name) {
//       return res.status(400).send({
//         message: "Must include user_id and folder_name in request"
//       });
//     }
  
//     try {
      
//       // open the database
//       console.log(await sqlite.open(DB_FILE_PATH));

//       // Check if folder name already exists for the user
//       let sql = "select folder_id, user_id, folder_name" +
//                   " from Folder where user_id = " + req.body.user_id +
//                   " and folder_name = '" + req.body.folder_name + "'";
       
//       result = await sqlite.all(sql, [])
        
//       if (result.length > 0) {
//         return res.status(400).send({
//           message: "Folder name already exists for this user"
//         });
//       }

//       // Add new folder to db
//       sql = "INSERT INTO Folder (user_id, folder_name) VALUES("
//                   + req.body.user_id + ", '" + req.body.folder_name + "');";

//       r = await sqlite.run(sql);
//       if(r) console.log("A folder has been inserted with rowid " + r.lastID);

//       sqlite.close();

//       return res.status(200).send({
//         folder_id: r.lastID,
//         message: "Folder added successfully"
//       });
        
//     } catch (error) {
//       console.log(error);
//       return res.status(500).send({
//         message: error
//       });
//     }
//   });

// // delete folder from Folder table
// // deleting a folder will cause all of the projects currently within it to be deleted as well
// // folder_id is an integer
// router.delete('/:folder_id', async (req, res) => {
//   try {
    
//     // open the database
//     console.log(await sqlite.open(DB_FILE_PATH));

//     // Delete folder with corresponding folder_id
//     let sql = "DELETE FROM Folder WHERE folder_id = " + req.params.folder_id + ";";
     
//     r = await sqlite.run(sql);
//     if(r) console.log("Folder successfully deleted");

//     sqlite.close();

//     return res.status(200).send({
//       message: "Folder deleted successfully"
//     });
      
//   } catch (error) {
//     console.log(error);
//     return res.status(500).send({
//       message: error
//     });
//   }
// });