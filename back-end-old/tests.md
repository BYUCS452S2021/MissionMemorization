## Curl Commands:

### Create Folder

 curl -X POST -d '{ "user_id": 1, "folder_name": "folder1"}' -H "Content-Type: application/json" localhost:3001/api/folder/

  response
 ```
{"folder_id":1, "message":"Folder added successfully"} or {"message":"Folder name already exists for this user"}
```

### Delete Folder

 curl -X DELETE -d '{}' -H "Content-Type: application/json" localhost:3001/api/folder/1

  response
 ```
{"message":"success"}
```