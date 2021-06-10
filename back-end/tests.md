## Curl Commands:

### Create Folder

 curl -X POST -d '{ "user_id": "60c136e579d2ed2fd9a8815e", "folder_name": "folder1"}' -H "Content-Type: application/json" localhost:3001/api/folder/

  response
 ```
{"folder_id":1, "message":"Folder added successfully"} or {"message":"Folder name already exists for this user"}
```

### Retrieve Folders

 curl -X GET -d '{ "user_id": "60c136e579d2ed2fd9a8815e"}' -H "Content-Type: application/json" localhost:3001/api/folder/all

  response
 ```
{"folder_id":1, "message":"Folder added successfully"} or {"message":"Folder name already exists for this user"}
```

### Delete Folder

 curl -X DELETE -d '{}' -H "Content-Type: application/json" localhost:3001/api/folder/60c14167ca002e3489ffa6cc

  response
 ```
{"message":"success"}
```

### Register User

curl -X POST -d '{"first_name": "Joseph", "last_name": "Gillespie", "username": "jgilles", "password": "password", "email": "fun@gmail.com"}' -H "Content-Type: application/json" localhost:3001/api/user/register

  response
 ```
{"message":"success"}

 
```

### Create Project

 curl -X POST -d '{"user_id": "60c136e579d2ed2fd9a8815e", "verse_ids": ["60b9149b611a625f04e17daa", "60b9149b611a625f04e17dc3"]}' -H "Content-Type: application/json" localhost:3001/api/project/60c1557b487f8c38fdfd73e2

  response
 ```
{"message":"success"}
```