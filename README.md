# PizzaYa API

You can access the API documentation [Swagger Docs](http://localhost:8082/pizzaya/api/swagger-ui/index.html).

```sh
.env.production

.env.production.example
```

```sh
docker-compose --env-file .env.production -f docker-compose.prod.yaml up -d

docker-compose --env-file .env.production -f docker-compose.prod.yaml up --build
```

## Development

For development, use the dev profile with local MySQL.

## Production Deployment

To deploy in production using Docker:

1. Ensure Docker and Docker Compose are installed.

2. Configure the environment variables in `.env.production`.

3. Run the following command to build and start the services:

   ```bash
   docker-compose --env-file .env.production -f docker-compose.prod.yaml up --build
   ```

   This will start the Spring Boot application on port 8080 and a private MySQL database accessible only by the API container.

4. The API will be available at `http://localhost:8080/pizzaya/api`.

5. To stop the services:

   ```bash
   docker-compose -f docker-compose.prod.yaml down
   ```

`store_procedure.sql`
