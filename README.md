# Product Monitoring Bot

A Telegram bot designed to monitor product prices and availability on the Rozetka e-commerce platform. Built with Kotlin and Spring Boot, the bot provides notifications when a product's price changes or its availability status updates.

## Features

- **Add Products:** Users can easily subscribe to product updates by simply sending a Rozetka product link to the bot.
- **View Subscriptions:** Users can view all the products they are currently tracking and unsubscribe directly from the interface.
- **Automated Monitoring:** The bot periodically checks the status of monitored products in the background.
- **Instant Notifications:** Get notified immediately via Telegram when a product's price changes or its stock status updates (e.g., from "Out of stock" to "Available").

## Technologies Used

- **Language:** Kotlin
- **Framework:** Spring Boot (Web, Data JPA, Scheduling)
- **Database:** PostgreSQL
- **Telegram Integration:** Telegrambots Spring Boot Starter (Webhook approach)
- **Build Tool:** Maven

## Prerequisites

Before running the application, ensure you have the following installed and configured:

- Java 8 or higher
- PostgreSQL database
- A registered Telegram bot and its token (obtained from BotFather)

## Environment Variables

The application relies on specific environment variables for configuration. You must set these before starting the application:

- `TELEGRAM_BOT_TOKEN`: The token provided by BotFather for your Telegram bot.
- `SPRING_DATASOURCE_URL`: The JDBC URL for your PostgreSQL database (e.g., `jdbc:postgresql://localhost:5432/productdb`).
- `SPRING_DATASOURCE_USERNAME`: The username for connecting to the database.
- `SPRING_DATASOURCE_PASSWORD`: The password for connecting to the database.

*Note: Since the bot relies on the webhook approach, it expects the bot to receive updates via POST requests to the root path `/` based on your Telegram Webhook setup. For local development, you might need a service like ngrok to expose your local port.*

## How to Build and Run

1. Clone the repository.
2. Ensure your database is running and environment variables are set.
3. Use the Maven wrapper to build the project:

```bash
./mvnw clean package
```

4. Run the compiled application:

```bash
java -jar target/product-monitoring-bot-0.0.1-SNAPSHOT.war
```

## Deployment

The project includes configurations suitable for Heroku deployment.
- The `pom.xml` uses the `maven-dependency-plugin` to copy the `webapp-runner.jar`.
- The `Procfile` uses `webapp-runner.jar` to launch the application.

To deploy on Heroku:
1. Create a new Heroku application.
2. Set the necessary config variables (`TELEGRAM_BOT_TOKEN`, `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`) in the Heroku dashboard.
3. Push the code to the Heroku remote repository.