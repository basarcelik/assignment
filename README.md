# assignment
Candle Stick Project
## Installation
After git clone run the following commands
```bash
mvn -U compile package
java -jar target/*.jar
```
## Usage
With any desired browser navigate to http://localhost:8080/candles/{symbol}

{symbol} is code of the equity

Time interval for building candles might be changed by changing the value on "application.properties" file.
Please note that value is in milliseconds.

Cache size is configured to 10 MB. Please increase the value when necessary by changing "ehcache.xml"
