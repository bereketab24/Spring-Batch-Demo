# Spring Batch CSV to H2 Demo

This project is a demonstration of a robust **ETL (Extract, Transform, Load)** pipeline using **Spring Batch**. It automatically reads customer data from a CSV file, processes it, and persists it into an in-memory **H2 Database**.

##  Features

* **CSV Ingestion**: Reads data efficiently from `src/main/resources` using `FlatFileItemReader`.
* **Database Writer**: Writes data to H2 using chunks of 10 row at a time and `JdbcBatchItemWriter` for high-performance batch inserts.
* **Automatic Scheduling**: The batch job is configured with a Spring Scheduler to run automatically every day at **2:00 AM**.
* **In-Memory Persistence**: Uses H2 database for zero-configuration storage.


## ️ How to Run

1.  Clone the repository.
2.  Open your terminal in the project root.
3.  Run the application using the Gradle wrapper:

```bash
./gradlew bootRun
```
See the DB on:
```bash
http://localhost:8080/h2-console
```