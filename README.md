# Hotel App
This is an Android application for a hotel reservation system. The front-end application is developed in Java for Android, while the back-end web service is implemented using Java Spring Boot.

## Folder Structure
```
.
├───Backend (back-end web service source code)
└───MyHotelApp (front-end application source code)
```

## Back-end Database Setting
1. Schemas and sample data are put in data.sql. Run the SQL to get your database/table/data ready.
2. The connection can be updated in `SpringBootApp/src/main/resources/application.properties`.

## Build and Run the web service
1. Navigate to the `SpringBootApp/` directory
2. Run `mvn clean package`
3. Navigate to the `target/` directory
4. If you are using JASYPT encrypted password, run `java -Djasypt.encryptor.password=<secret-password> -jar SpringBootApp-0.0.1-SNAPSHOT.jar`. Otherwise, just run it without VM option by `java -jar SpringBootApp-0.0.1-SNAPSHOT.jar`

> [!NOTE]
> In my current configuration, my secret-password is my student ID.

## Update the connection from application to web service
1. The connection can be updated in `app\src\main\java\com\example\myhotelapp\data\api\ApiClient.java`