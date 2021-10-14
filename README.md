## README

Pre-requisite tools:
* Java 11
* Maven 3.2 or greater
* Functioning web browser (Firefox or Chrome Preferred)

Setup Maven Wrapper (mvnw): ``mvn -N io.takari:maven:wrapper``

Build and Run: ``./mvnw spring-boot:run``

View Application: ``https://localhost:8080``

Read CSV files
```java
import org.springframework.util.ResourceUtils;

File file = ResourceUtils.getFile("classpath:test.csv");
```
Store files in ``resources`` directory

Graph:
Create an ArrayList of HashMap objects

```java
ArrayList<HashMap<String, String>> list = new ArrayList<>();

HashMap<String, String> map = new HashMap<>();
map.put("from", from);
map.put("to", to);
map.put("weight", weight);
map.put("title", title);
list.add(map);
```
where:
* from: course subject and number
* to: course subject and number
* weight: 1
* title: text representing pre-requisite course

```json
"weight": "1",
"from": "MATH 1910",
"to": "CSC 1200",
"title": "Calculus 1"
```