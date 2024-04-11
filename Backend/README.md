# Java Spring Boot Assignment
This is a Java Spring Boot application which is connected to a remote MySQL database and provides REST API to access the data.

# Database Setting
1. Schemas and sample data are put int data.sql. Run the SQL to get your database/table/data ready.
2. The connection can be updated in `SpringBootApp/src/main/resources/application.properties`.

# Build and Run the application
1. Navigate to the `SpringBootApp/` directory
2. Run `mvn clean package`
3. Navigate to the `target/` directory
4. If you are using JASYPT encrypted password, run `java -Djasypt.encryptor.password=<secret-password> -jar SpringBootApp-0.0.1-SNAPSHOT.jar`. Otherwise, just run it without VM option by `java -jar SpringBootApp-0.0.1-SNAPSHOT.jar`

> [!NOTE]
> In my current configuration, my secret-password is my student ID.

# Accessing the REST API
## GET API
- Get a list of available hotels
- URL: localhost:8080/hotels
![alt text](https://github.com/A00476407/java_spring_boot_assignment/blob/main/Screenshots/GET.png?raw=true)

## POST API
- Add a hotel entry with JSON request body
- URL: localhost:8080/hotels
```JSON
{
    "name": "ABC Hotel",
    "ranking": 5,
    "location": "Halifax"
}
```
![alt text](https://github.com/A00476407/java_spring_boot_assignment/blob/main/Screenshots/POST.png?raw=true)
![alt text](https://github.com/A00476407/java_spring_boot_assignment/blob/main/Screenshots/POST_result.png?raw=true)

## PUT API
- Update a hotel data with JSON request body
- URL: localhost:8080/hotels/id
```JSON
{
    "name": "ABC Hotel",
    "ranking": 5,
    "location": "Halifax"
}
```
![alt text](https://github.com/A00476407/java_spring_boot_assignment/blob/main/Screenshots/PUT.png?raw=true)
![alt text](https://github.com/A00476407/java_spring_boot_assignment/blob/main/Screenshots/PUT_result.png?raw=true)

# DELETE API
- Delete a hotel data
- URL: localhost:8080/hotels/id
![alt text](https://github.com/A00476407/java_spring_boot_assignment/blob/main/Screenshots/DELETE.png?raw=true)
![alt text](https://github.com/A00476407/java_spring_boot_assignment/blob/main/Screenshots/DELETE_result.png?raw=true)
