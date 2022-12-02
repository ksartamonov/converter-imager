# converter-imager

## 1. Description

The repository consists of 2 services(__Converter__ and __Imager__) interacting with each other using __RabbitMQ__. 

### 1.1. Converter
The service reads an RSS file containing news data and passes the read data to __Imager__ via __RabbtMQ__. To work with RSS files the [library](https://mvnrepository.com/artifact/com.apptasticsoftware/rssreader) was used. The user's interaction is carried out only with the use of this service.

### 1.2 Imager 
This service, receiving data from __Converter__ saves them to its own database. Access to this data is carried out only through the __Converter__ service by sending a request through the queue.

## 2. Usage

To use the service, you must complete the following steps:

1. Clone the repository:
```sh
git clone https://github.com/ksartamonov/converter-imager.git
cd converter-imager
```

2. Execute a docker-compose:
```sh
docker-compose up
```

HTTP requests supported by the service are described below:

### 2.1. Read data from the RSS feed and write it to the database:
This POST HTTP-Request reads data from the RSS feed and write it to the database. As a source, it accepts a link to an RSS file (In this request, the url of the RSS file: https://ru.investing.com/rss/302.rss)

```HTTP REQUEST
http://localhost:8082/converter-imager-api/converter/save_items?source=https://ru.investing.com/rss/302.rss
```

The response to this request, in case of successful data saving, will be as follows:
<img width="1012" alt="Снимок экрана 2022-12-02 в 09 10 30" src="https://user-images.githubusercontent.com/65600049/205227860-740c46db-809b-40c8-a6b6-0c1816096b7c.png">

### 2.2. Get all items from the database
This GET-request allows you to get everything that was written to the database that stores the news
```HTTP REQUEST
http://localhost:8082/converter-imager-api/converter/get_all
```

The response:
<img width="1016" alt="Снимок экрана 2022-12-02 в 09 18 25" src="https://user-images.githubusercontent.com/65600049/205228215-3dd0b90b-13b8-49f6-b13c-56b4de79a5f0.png">

### 2.3. Get all the author's news
With this GET requests, you can get all the news in the database with the specified author(In this case, the Path parameter is used):
```HTTP REQUEST
http://localhost:8082/converter-imager-api/converter/get_by_author/Дмитрий Носков
```

The response:
<img width="1015" alt="Снимок экрана 2022-12-02 в 09 21 24" src="https://user-images.githubusercontent.com/65600049/205228683-9478d27f-de03-4b1a-acb7-100054306363.png">

### 2.4 Get news by unique id.
This HTTP request allows you to get the news by its unique UUID identifier(passed as path parameter).
```HTTP REQUEST
http://localhost:8082/converter-imager-api/converter/get_by_id/616b58b1-55c7-4a91-af07-8037859dd192
```

The response:
<img width="1016" alt="Снимок экрана 2022-12-02 в 09 26 15" src="https://user-images.githubusercontent.com/65600049/205229296-efbfa249-c5bd-46b5-93b0-6fa2f4ccf34b.png">

## 3. To do:
* JUnit tests
* UI
* JavaDoc
