# Game of Cricket

This is a Spring Boot application that simulates a cricket series (T20 or ODI) between two teams. It provides a REST API to manage and simulate entire series, including matches, teams, players, and detailed scoreboards.

A key feature of this project is its ability to dynamically switch its persistence layer between a SQL database (MySQL) and a NoSQL database (MongoDB) at runtime.

## Key Features

* **Cricket Simulation:** Simulates a complete cricket series (T20 or ODI) with a specified number of matches.
* **Ball-by-Ball Logic:** Includes probabilistic logic for ball-by-ball outcomes based on player roles (Batsman, All-rounder, Bowler) and match type.
* **Dual Database Support:** Can seamlessly switch between MySQL and MongoDB persistence layers at runtime using a dedicated API endpoint (`/changeDb/{dbType}`).
* **REST API:** Comprehensive REST APIs to create series, and view details for series, matches, teams, players, and scoreboards.
* **Caching:** Uses Spring Cache with Caffeine to cache database responses for better performance.
* **API Documentation:** Integrated Swagger 2 for live API documentation.
* **Performance Testing:** Includes bulk-call endpoints (e.g., `/bulkNewSeries/{count}`) to simulate concurrent load.

## Technology Stack

* **Framework:** Spring Boot 2.6.4
* **Language:** Java 8
* **Databases:**
    * MySQL (with Spring Data JDBC)
    * MongoDB (with Spring Data MongoDB)
* **Caching:** Spring Cache with Caffeine
* **API Docs:** Swagger 2 (Springfox)
* **Build Tool:** Gradle
* **Utilities:** Lombok

## Database Schema

The SQL schema consists of 6 tables to store all data related to the game:
* `series`: Stores details of a cricket series.
* `team`: Stores team information.
* `player`: Stores player details, linked to a team.
* `match`: Stores details of an individual match, linked to a series.
* `match_scoreboard`: Stores the complete scoreboard for both innings of a match.
* `player_stats`: Stores individual player statistics (runs scored, balls played) for each match.

(See `src/main/java/com/tekion/cricketGame/database-schema.png` for a visual diagram).

## API Endpoints

Once the application is running, API documentation can be accessed at:
`http://localhost:8080/swagger-ui/`

Key endpoints include:

### Series
* `POST /series/newSeries`: Starts a new cricket series.
    **Body:**
    ```json
    {
      "seriesType": "T20",
      "numberOfMatches": 5,
      "team1Name": "India",
      "team2Name": "Australia"
    }
    ```
* `GET /series/seriesDetails/{id}`: Get details for a specific series.

### Match
* `GET /match/matchDetails/{id}`: Get details for a specific match.
* `GET /match/matchList/{id}`: Get all matches played in a series.

### Team & Player
* `GET /team/{id}`: Get details for a team.
* `GET /player/playerInfo/{id}`: Get details for a player.
* `GET /player/playerStat/{playerId}/{matchId}`: Get a player's stats for a specific match.

### Scoreboard
* `GET /scoreBoard/{id}`: Get the detailed scoreboard for a match.

### Database Management
* `GET /showDb`: Shows the currently active database (SQL or MONGO).
* `GET /changeDb/{dbType}`: Switches the active database (e.g., `/changeDb/SQL` or `/changeDb/MONGO`) and restarts the application context to apply the change.

## How to Run

1.  **Prerequisites:**
    * Java 8
    * Gradle
    * MySQL server running
    * MongoDB server running

2.  **Database Setup:**
    * **MySQL:** Create a database named `cricket_stats`.
    * **MongoDB:** No pre-setup is required, but ensure it's running. The application will use a database also named `cricket_stats`.

3.  **Configuration:**
    * Update the database URLs, usernames, and passwords in `src/main/resources/application.properties` to match your local setup.

4.  **Run the Application:**
    ```bash
    ./gradlew bootRun
    ```

5.  **Access the Application:**
    * **API:** `http://localhost:8080`
    * **Swagger UI:** `http://localhost:8080/swagger-ui/`
