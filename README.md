# converter-imager

## 1. Description

The repository consists of 2 services(__Converter__ and __Imager__) interacting with each other using __RabbitMQ__. 

### 1.1. Converter
The service reads an RSS file containing news data and passes the read data to __Imager__ via __RabbtMQ__. The user's interaction is carried out only with the use of this service.

### 1.2 Imager 
This service, receiving data from __Converter__ saves them to its own database. Access to this data is carried out only through the __Converter__ service by sending a request through the queue.

