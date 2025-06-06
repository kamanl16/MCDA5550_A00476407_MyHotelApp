# Hotel App

This is an Android application for a hotel reservation system. The front-end application is developed in Java for Android, while the back-end web service is implemented using Java Spring Boot.

## Features

* **Room Availability Search:** Search for available rooms based on check-in/check-out dates and the number of guests.
* **Dynamic Room Listings:** View a list of available rooms with details like price, occupancy, and photos.
* **Reservation Booking:** Create a new reservation and enter guest information.
* **Reservation Lookup:** Check the details of an existing reservation using a reservation ID.
* **Multi-Language Support:** The app interface is available in both English and Traditional Chinese.

## Prerequisites

Before you begin, ensure you have the following software installed:
* Java JDK 21 or later
* Apache Maven
* Android Studio
* A running MySQL instance

## Project Structure
```
.
├───Backend (back-end web service source code)
└───MyHotelApp (front-end application source code)
```

## Getting Started

To get the application running, you'll need to set up both the backend service and the frontend application.

### Backend (Spring Boot)

1.  **Database Setup**:
    * The database schema and sample data are located in `Backend/data.sql`. Run this script in your MySQL instance to prepare the database.
    * Update the database connection properties in `Backend/SpringBootApp/src/main/resources/application.properties` to point to your MySQL instance.

2.  **Build and Run**:
    * Navigate to the `Backend/SpringBootApp/` directory.
    * Run `mvn clean package` to build the application.
    * Navigate to the `target/` directory.
    * Run the application using `java -jar SpringBootApp-0.0.1-SNAPSHOT.jar`.
    > **Note**: If you are using an encrypted password with JASYPT, run the app with `java -Djasypt.encryptor.password=<secret-password> -jar SpringBootApp-0.0.1-SNAPSHOT.jar`.

### Frontend (Android App)

1.  **Open in Android Studio**:
    * Open the `MyHotelApp` directory as a new project in Android Studio.
    * Allow Gradle to sync and build the project dependencies.

2.  **Update API Connection**:
    * The Android app needs to know the address of your running backend. Update the `BASE_URL` in `MyHotelApp/app/src/main/java/com/example/myhotelapp/data/api/ApiClient.java`.
    * If running the backend on your local machine and using an Android Emulator, set the URL to `http://10.0.2.2:5000/`.

3.  **Run the App**:
    * Select an Android Emulator or a connected physical device.
    * Click the "Run" button in Android Studio to deploy and start the application.

## API Documentation

The backend exposes a REST API for the frontend to consume. For a detailed list of all available endpoints, request/response formats, and functionalities, please see the [Backend API Documentation](./Backend/README.md).