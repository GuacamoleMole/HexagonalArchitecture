# Technical Project

This repository contains a Java / Spring Boot project implementing a product sorting service based on sales and stock criteria using a RESTful API. 

## Instructions

1.**Navigate to the project directory**
  ```bash
    cd your-project-directory
  ```
2.**Install Dependencies**  

    Make sure you have Java 17 and Maven installed (I used Apache Maven 3.8.6). This project also uses Spring Boot for the application framework(See the file pom.xml).  

3.**Run the application:**
  ```bash
    mvn spring-boot:run
  ```

  The application should start on http://localhost:8080.

4.**Test the API**
  You can use curl or other tools like Postman to test the API. Example:
  ```bash
    curl -X POST 'http://localhost:8080/sort-products' -H 'Content-Type: application/json' -d '{
      "salesWeight": 0.5,
      "stockWeight": 0.5,
      "productSales": [
        {"productId": "1", "sales": 50000},
        {"productId": "2", "sales": 100000},
        {"productId": "3", "sales": 100000},
        {"productId": "4", "sales": 75000}
      ],
      "productStock": [
        {"productId": "1", "stock": 100000},
        {"productId": "2", "stock": 400000},
        {"productId": "3", "stock": 200000},
        {"productId": "4", "stock": 300000}
      ]
    }'
  ```

  You can also find more examples in the file **examples.md**

## Testing
This project has unit and intregraton test. You can run the test with the following command.
```bash
  mvn test
```

## Endpoint documentation
View the openapi.yml with a tool such as [swagger](https://editor.swagger.io/) to see the endpoint documentation

## Algorithm explanation
The product sorting algorithm orders a list of products based on two configurable weights: sales weight and stock weight. These weights determine the influence of each factor in the sorting priority.

I apply the following formula to calculate the priority of each product, and later sort of highest to lowest:  

$ProductScore=(nªsales×salesWeight)+(nªstock×stockWeight)$