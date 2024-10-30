
# 1ºExample

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

Expected Response:

```json
{"productIds":["2","4","3","1"]}
```

# 2ºExample

```bash
curl -X POST 'http://localhost:8080/sort-products' -H 'Content-Type: application/json' -d '{
  "salesWeight": 0.9,
  "stockWeight": 0.1,
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

Expected Response: 

```json
{"productIds":["2","3","4","1"]}
```

# 3ºExample

```bash
curl -X POST 'http://localhost:8080/sort-products' -H 'Content-Type: application/json' -d '{
  "salesWeight": 0.0,
  "stockWeight": 1,
  "productSales": [
    {"productId": "1", "sales": 50000},
    {"productId": "2", "sales": 100000},
    {"productId": "3", "sales": 100000},
    {"productId": "4", "sales": 75000}
  ],
  "productStock": [
    {"productId": "1", "stock": 1000},
    {"productId": "2", "stock": 400},
    {"productId": "3", "stock": 200},
    {"productId": "4", "stock": 300000}
  ]
}'
```

Expected Response: 

```json
{"productIds":["4","1","2","3"]}
```

# 4ºExample

```bash
curl -X POST 'http://localhost:8080/sort-products' -H 'Content-Type: application/json' -d '{
  "salesWeight": 0.1,
  "stockWeight": 1,
  "productSales": [
    {"productId": "1", "sales": 50000},
    {"productId": "2", "sales": 100000},
    {"productId": "3", "sales": 100000},
    {"productId": "4", "sales": 75000}
  ],
  "productStock": [
    {"productId": "5", "stock": 10000},
    {"productId": "6", "stock": 4000},
    {"productId": "7", "stock": 2000},
    {"productId": "8", "stock": 300000}
  ]
}'
```

Expected Response:

```json
{"productIds":["8","2","5","3","4","1","6","7"]}
```