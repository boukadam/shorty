# shorty
Shorty - URL Shortener example implementation using [hashids](https://hashids.org/java/).

## Installation
Create a MySQL database 
```
create database shorty; 
```

Add your custom configuration in ``application.properties`` and start the spring boot application : 
```
java -jar target/shorty-0.0.1-SNAPSHOT.jar
```

## Usage
To create a url reduction claim :
```
$ curl --location --request POST 'http://localhost:1337/claims' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "callback": "http://google.fr?q=Lyon"
    }'
```

Example of response : 
```
{
    "token": "4K",
    "redirectionUrl": "http://localhost:1337/4K"
}
```

``redirectionUrl`` is the short URL for ``http://google.fr?q=Lyon``

# Limitations
This application is not secure, access to APIs does not require any authentication. 
For production use, it is your responsibility to implement the appropriate security mechanisms.

# License
Apache 2.0 License. See the ``LICENSE`` file.
