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

# MyHotelApp Backend API

This document provides a reference for the APIs available in the MyHotelApp backend.

### Base URL
The service is hosted at `http://<your-backend-url>`.

### General Controller

This controller handles basic health checks.

* **Check Application Status**
    * **Endpoint:** `GET /`
    * **Description:** A simple endpoint to verify that the application is running.
    * **Success Response:**
        * **Code:** 200 OK
        * **Content:** `"Healthy"`

---

### Image Controller

Handles fetching image-related data.

* **Get All Images**
    * **Endpoint:** `GET /getAllImages`
    * **Description:** Retrieves a list of all image data stored in the database.
    * **Success Response:**
        * **Code:** 200 OK
        * **Content:** A JSON array of `ImageData` objects.

* **Get Images by Category**
    * **Endpoint:** `GET /getImagesByCat`
    * **Description:** Finds and returns a list of images based on a category type and category ID.
    * **Query Parameters:**
        * `catType` (integer): The category type identifier.
        * `catId` (long): The unique ID of the category.
    * **Success Response:**
        * **Code:** 200 OK
        * **Content:** A JSON array of `ImageData` objects matching the criteria.

---

### Room Controller

Handles operations related to hotel rooms. The base path for this controller is `/api/rooms`.

* **Get All Rooms**
    * **Endpoint:** `GET /api/rooms/getRooms`
    * **Description:** Retrieves a list of all rooms without checking for availability.
    * **Success Response:**
        * **Code:** 200 OK
        * **Content:** A JSON array of `Room` objects.

* **Get Available Rooms**
    * **Endpoint:** `GET /api/rooms/getAvailableRooms`
    * **Description:** Finds rooms that are available for a given date range and number of guests.
    * **Query Parameters:**
        * `checkInDate` (date): The check-in date in ISO format (e.g., `YYYY-MM-DD`).
        * `checkOutDate` (date): The check-out date in ISO format (e.g., `YYYY-MM-DD`).
        * `numberOfGuests` (integer): The number of guests.
    * **Success Response:**
        * **Code:** 200 OK
        * **Content:** A JSON array of `RoomDTO` objects, each containing room details and associated images.

---

### Reservation Controller

Handles all operations related to reservations. The base path for this controller is `/api/reservations`.

* **Create a Reservation**
    * **Endpoint:** `POST /api/reservations`
    * **Description:** Creates a new reservation, including guest details. The request body should contain the reservation info and a list of guests.
    * **Request Body:** A `ReservationDTO` JSON object.
    * **Success Response:**
        * **Code:** 201 Created
        * **Content:** The created `Reservation` object.

* **Get Reservation by ID**
    * **Endpoint:** `GET /api/reservations/{reservationId}`
    * **Description:** Retrieves a specific reservation and its associated guest and room details by its unique ID.
    * **Path Variable:**
        * `reservationId` (long): The unique ID of the reservation.
    * **Success Response:**
        * **Code:** 200 OK
        * **Content:** A `ReservationDTO` object.
    * **Error Response:**
        * **Code:** 404 Not Found (if the ID does not exist).

* **Update Reservation Remark**
    * **Endpoint:** `PUT /api/reservations/{id}/remark`
    * **Description:** Updates the remark/note for a specific reservation.
    * **Path Variable:**
        * `id` (long): The unique ID of the reservation.
    * **Request Body:** A plain text string for the remark.
    * **Success Response:**
        * **Code:** 200 OK
        * **Content:** The updated `Reservation` object.

* **Get All Reservations with Guests**
    * **Endpoint:** `GET /api/reservations/getAllReservationDTO`
    * **Description:** Retrieves a list of all reservations, with nested guest information for each.
    * **Success Response:**
        * **Code:** 200 OK
        * **Content:** A JSON array of `ReservationDTO` objects.