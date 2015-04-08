# Auth-Server

## Prerequistes

* Running Mysql with HostName=db, username=root, password=password, database = auth-db
 
  * If you have docker you can simply run - docker run -d -e  MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=auth --name auth-db -p 3306:3306 mysql