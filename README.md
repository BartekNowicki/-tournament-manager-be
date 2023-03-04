# project_applause

Instructions

1. Install and configure your local mysql instance with a demo_db database on it
2. Clone the repository into your machine
3. Add your mysql access envvars into your IDE: MYSQL_USERNAME=<your login>;MYSQL_PASSWORD=<your password>
4. Launch your mysql server
5. Use Postman (or a similar tool) to upload the data into your application through a POST request, as separate csv files that will be parsed by the application, like so:

![upload](https://user-images.githubusercontent.com/65188628/222798882-680f29ab-7a6c-46e6-819a-14e241d50785.png)

file location: src/main/java/com/example/demo/csvFilesForInitialUpload

endpoint: http://localhost:8080/api/csv/upload

Use the following order:

testers.scv

devices.csv

tester_device.csv

bugs.csv


Run a test e.g. by sending the following search criteria through a GET request to http://localhost:8080/api/data/match like so:

![response](https://user-images.githubusercontent.com/65188628/222799847-f9b7b444-f223-411d-bf8d-d3df1d8eb330.png)












