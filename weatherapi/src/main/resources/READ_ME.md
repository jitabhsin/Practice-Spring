**Weather API - Delhi Weather Data**

**Overview:**
A Spring Boot application providing a REST API to query Delhi weather data stored in MySQL. The dataset (~10,000 records) is loaded from `testset.csv`. At startup, an interactive Y/n prompt allows users to load or append data, saving time and memory.

**Prerequisites:**

* Java 17 (JDK)
* Maven
* MySQL 8+ (running locally)
* Eclipse IDE (optional)
* CSV file: Place `testset.csv` in `src/main/resources` (columns include `datetime_utc`, `conds`, `tempm`, etc.)

**Project Setup:**

1. **Clone/Navigate Project:**
   `C:SECUREMAIN\SECURIN_SOLUTIONS\Assesment_2\weatherapi`

2. **Configure MySQL:**

   * Start server: `mysql -u root -p`
   * Update `src/main/resources/application.properties`:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/weather_db?createDatabaseIfNotExist=true
     spring.datasource.username=root
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
     ```

3. **Build Project:**

   ```bash
   cd C:\Users\ASUS\Documents\SECUREMAIN\SECURIN_SOLUTIONS\Assesment_2\weatherapi
   mvn clean install
   ```

**Running the Application:**

```bash
mvn spring-boot:run
```

* Respond to the prompt:

  * `y` → Load/append CSV
  * `n` → Skip loading

**IMPORTANT FEATURE:**
The Y/n prompt allows users to efficiently update the database:

* Typing `y` loads/appends CSV data into MySQL, updating weather records.
* Typing `n` skips the operation, saving time and memory.

**Application URL:** `http://localhost:8080`

**API Endpoints:**

* **Base URL:** `http://localhost:8080/api/weather`

1. **Get Weather by Month**

   * `GET /month?year={year}&month={month}`
   * Example:

     ```bash
     curl "http://localhost:8080/api/weather/month?year=1996&month=11"
     ```

2. **Get Weather by Date**

   * `GET /date?year={year}&month={month}&day={day}`
   * Example:

     ```bash
     curl "http://localhost:8080/api/weather/date?year=1996&month=11&day=1"
     ```

3. **Get Temperature Stats by Month**

   * `GET /temperature-stats?year={year}&month={month}`
   * Example:

     ```bash
     curl "http://localhost:8080/api/weather/temperature-stats?year=1996&month=11"
     ```
   * Output:

     ```json
     {
       "maxTemperature": 34,
       "minTemperature": 9,
       "medianTemperature": 19.4389
     }
     ```

**Notes:**

* Database after loading: `weather_db.weather` (~10,000 rows)
* CSV loading avoids duplicates based on `datetime_utc`
* Troubleshooting:

  * 404 → Check endpoints
  * Empty response → Load CSV (`y`)
  * MySQL → Verify password & server running
