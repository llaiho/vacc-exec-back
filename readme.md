# Vaccine Exercise

Project backend with Spring, React, Maven

location of front code: https://github.com/llaiho/vacc-exec-front


## running backend

In development mode vacc-exec-backend starts local backend.


### http points

http://localhost:8090/testVaccinations
returns JSON array of vaccinations


http://localhost:8090/testOrders
returns JSON array of orders, combined all orders


http://localhost:8090/testProcessed
returns JSON array of combined orders with added "usedOnDate" array


http://localhost:8090/testOrderedOrders
returns JSON array of combined orders with added "usedOnDate" array 
sorted by bottle "arrived" date

