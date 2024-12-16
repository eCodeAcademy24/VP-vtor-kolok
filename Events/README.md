# Event Booking Application

## Опис на проектот

Креирајте нов Spring Boot проект со група mk.ecode и artefactId=events

Дефинирајте пакет mk.ecode.events.model и во него креирајте ја EventBooking класата. Таа треба да содржи:
- String eventName
- String attendeeName
- String attendeeAddress
- Long numberOfTickets

Во mk.ecode.events.model креирајте Event класа која ќе содржи:
- String name
- String description
- double popularityScore

Креирајте класа EventRepository во пакетот mk.ecode.events.repository, во која ќе чувате List<Event> иницијализирана со 10 вредности.
Имплементирајте метод public List<Event> findAll(); кој само ќе ја врати листата.
Имплементирајте метод public List<Event> searchEvents(String text); кој ќе направи пребарување низ листата на настани и ќе ги врати оние во чие име или опис се содржи текстот text кој се праќа како аргумент на методот.

Дефинирајте ги следните интерфејси во mk.ecode.events.service кои ќе ги претставуваат бизнис функционалностите на апликацијата:

```java
public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
}

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
}
```

Имплементирајте ги сервисите (EventService треба да зависи од EventRepository).

Креирајте сервлет EventListServlet во пакетот mk.ecode.events.web и мапирајте го на патеката /. Овој сервлет треба да зависи од EventService и да ги прикаже сите добиени настани од методот listAll().

Овозможете корисникот да избере еден од настаните и за истиот да наведе број на карти што сака да ги нарача. Креирајте по едно радио копче за секој настан каде што вредноста на копчето ќе биде името на настанот, а текстот кој ќе се прикаже ќе биде во форматот:
Name: <event_name>, Description: <event_description>, Rating: <popularity_score>

Прилагодете го фајлот listEvents.html за изгледот на оваа страница.

```html
<html>
    <head>
        <meta charset="utf-8">
        <title>Event Booking page - Welcome and choose an Event</title>
        <style type="text/css">
            body {
                width: 800px;
                margin: auto;
            }
        </style>
    </head>
    <body>
        <header>
            <h1>Welcome to our Event Booking App</h1>
        </header>
        <main>
            <h2>Choose an event:</h2>
            <!-- Display radio buttons for each event,
                    the value should be the event name 
                    and the displayed text should be Name: <event_name>, Description: <event_description>, Rating: <popularity_score> -->
            
            <h2>Choose number of tickets:</h2>
            <input type="number" name="numTickets" min="1" max="10"><br/>
            <br/>
            <input type="submit" value="Submit">
        </main>
    </body>
</html>
```
При избор на настан, треба да ја прикажете резервацијата на корисникот. За оваа цел креирајте сервлет EventBookingServlet мапиран на /eventBooking.

Овој сервлет треба да ја прикажете страната за потврда на резервацијата. Во фолдерот src/main/resources/templates додадете фајл bookingConfirmation.html.

Прилагодете го фајлот bookingConfirmation.html за изгледот на оваа страница.

```html
<html>
    <head>
        <meta charset="utf-8">
        <title>Booking - Confirmation</title>
        <style type="text/css">
            body {
                width: 800px;
                margin: auto;
            }
            table {
                width: 100%;
            }
            table, td, th {
                border: 1px solid black;
                padding: 3px 2px;
            }
        </style>
    </head>
    <body>
        <section>
            <header>
                <h1>Event Booking page - Booking confirmation </h1>
            </header>
            <table>
                <tr>
                    <th colspan="2">
                        Your Booking Status
                    </th>
                </tr>
                <tr>
                    <td><b>Attendee Name </b></td>
                    <td>Petko Petkov</td>
                </tr>
                <tr>
                    <td><b>Client IP Address</b></td>
                    <td>127.0.0.1</td>
                </tr>
                <tr>
                    <td><b>Booking for Event</b></td>
                    <td>Oppenheimer</td>
                </tr>
                <tr>
                    <td><b>Number of tickets</b></td>
                    <td>2</td>
                </tr>
            </table>
        </section>
    </body>
</html>
```

Да се имплементира можност за пребарување на настаните на почетната страна listEvents.html. Треба да се прикажат само настаните кои ги исполнуваат условите од пребарувањето. Пребарувањето треба да се изврши според два параметри:

настани кои го содржат текстот испратен од страна на корисникот во нивното име
настани кои имаат рејтинг поголем или еднаков на внесената вредност од страна на корисникот

# Продолжение I

# Опис на задачата

Во оваа вежба ќе продолжиме со работа во рамки на проектот.

1. Во класата `Event` додадете ново својство `private Long id`, кое е уникатно за секој настан. Идентификаторот (`id`) треба автоматски да се генерира за секој настан.

2. Додадете класа `Location` во пакетот `mk.ecode.events.model`. Во оваа класа ќе се чуваат следниве атрибути:
    - `private Long id`
    - `private String name`
    - `private String address`
    - `private String capacity`
    - `private String description`

3. Во класата `Event` додадете врска до класата `Location` како посебно својство:
    - `private Location location`

4. Креирајте класа `LocationRepository` во пакетот `mk.ecode.events.repository` и иницијализирајте листа со 5 локации. Во оваа класа, дефинирајте метод `public List<Location> findAll()`, кој ги враќа сите локации во системот. За секој настан иницијализирајте соодветна локација во атрибутот `location`.

5. Во пакетот `mk.ecode.events.service` креирајте интерфејс `LocationService` и класа `LocationServiceImpl` (во потпакетот `impl`), која го имплементира интерфејсот. Во сервисот дефинирајте метод `public List<Location> findAll()`, кој го повикува методот од `LocationRepository`.

6. Дефинирајте пакет `mk.ecode.events.web.controller` и во него креирајте ја класата `EventController`:
    - Имплементирајте метод `public String getEventsPage(@RequestParam(required = false) String error, Model model)`, 
    - кој го прикажува погледот за сите настани. Нека одговара на mapping `/events`. Погледот за сите настани треба да биде `listEvents.html`, 
    - каде ќе се прикаже името на локацијата, функционалност за избор на настан и број на билети за нарачка. 
    - Додадете копчиња за уредување и бришење на настани.

7. Имплементирајте метод `public String saveEvent()`, кој овозможува додавање на нов настан и прима параметри: `name`, `description`, `popularityScore`,
и `id` на локацијата (корисникот го избира од `<select>` таг). Овој метод треба да одговара на mapping `/events/add` 
и по успешно додавање на настанот да редиректира кон погледот со сите настани.

8. Имплементирајте метод `public String editEvent(@PathVariable Long eventId)`, кој овозможува ажурирање на настани, 
примајќи ги параметрите: `name`, `description`, `popularityScore`, и `id` на локацијата (избрана од `<select>` таг). 
Овој метод треба да одговара на mapping `/events/edit/{eventId}`, каде `eventId` е ID-то на настанот што се уредува. 
По успешно ажурирање редиректира кон погледот со сите настани.

9. Имплементирајте метод `public String deleteEvent(@PathVariable Long id)`, кој одговара на mapping `/events/delete/{id}`. 
По успешно бришење на настанот, повторно ја прикажува листата со настани.

10. Имплементирајте метод `public String getEditEventForm(@PathVariable Long id)`, кој одговара на mapping `/events/edit-form/{id}` 
и ја прикажува страната `add-event.html` за уредување. Доколку ID-то на настанот не постои, редиректира кон листата со настани и прикажува порака за грешка.

11. Имплементирајте метод `public String getAddEventPage()`, кој одговара на mapping `/events/add-form` и ја прикажува страната `add-event.html`.

12. Страната `add-event.html` прикажува форма за додавање или уредување на настан, каде локацијата се избира од `<select>` таг.

13. Функционалноста на `EventBookingServlet` за додавање на нова нарачка заменете ја со `EventBookingController`, 
кој овозможува креирање на резервација и прикажување на потврда.

14. Тестирање:
- Проверете дали листата со настани се прикажува успешно.
- Проверете дали е можно да се креира, едитира и избрише настан.
- Проверете дали функционалностите од претходната вежба (резервација) работат.
- Проверете дали се прикажува резервацијата на моменталниот корисник.

# Продолжение II

## Опис на задачата

### Инструкции за поставување на Spring Boot проект со H2 и PostgreSQL

1. **Додадете ги потребните зависности во `pom.xml`:**

   ```xml
   <dependencies>
       <!-- Spring Boot Data JPA -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-data-jpa</artifactId>
       </dependency>

       <!-- H2 Database -->
       <dependency>
           <groupId>com.h2database</groupId>
           <artifactId>h2</artifactId>
           <scope>runtime</scope>
       </dependency>

       <!-- PostgreSQL Database -->
       <dependency>
           <groupId>org.postgresql</groupId>
           <artifactId>postgresql</artifactId>
           <scope>runtime</scope>
       </dependency>
   </dependencies>
   ```

2. **Инсталирајте PostgreSQL**, доколку сè уште не е инсталиран.

3. **Креирајте два Spring профили:**
   - `h2` (за in-memory база)
   - `prod` (за PostgreSQL база).

   Активниот профил нека биде тој кој користи PostgreSQL.

4. **Доколку користите Docker:**
   - Креирајте `docker-compose.yml` за конфигурација и работа со PostgreSQL.
   - Доколку не, осигурајте се дека правилно ги конфигурирате `username` и `password` параметрите при инсталацијата на PostgreSQL.

5. **Поврзете го IntelliJ IDEA со PostgreSQL базата:**
   - Осигурајте се дека користите правилни податоци за `port`, `username`, `password` и името на базата.
   - Името на базата нека биде `events`.

6. **Во `application-prod.properties` осигурајте се дека:**

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/events
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

7. **Изменете ја класата `Event` во пакетот `mk.ecode.events.model`:**
   - Анотирајте ја со `@Entity` за да стане JPA ентитет.
   - Обезбедете уникатно `id` за секој настан, анотирајќи го со `@Id` и `@GeneratedValue`.

8. **Изменете ја класата `Location`:**
   - Анотирајте ја со `@Entity`.
   - Обезбедете уникатно `id` со `@Id` и `@GeneratedValue`.
   - Додадете ја релацијата со `Event` (`@OneToMany`).

9. **Дефинирајте ги релациите меѓу ентитетите `Event` и `Location`:**
   - Во `Event` користете `@ManyToOne` за релацијата со `Location`.
   - Во `Location` додајте листа на `Event` ентитети и користете `@OneToMany(mappedBy = "location")`.

10. **Креирајте табели во базата на податоци за `Event` и `Location` преку Spring Data JPA.**
   - Осигурајте се дека имињата на табелите се уникатни, особено за ентитетите како `User` (на пример, преименувајте ја табелата за `User` со користење на `@Table(name = "users")`).

11. **Во пакетот `mk.ecode.events.repository`:**
   - Креирајте `EventRepository` и `LocationRepository` интерфејси кои ќе наследуваат од `JpaRepository`.
   - Додадете метод во `EventRepository` кој враќа настани според локација:

     ```java
     List<Event> findAllByLocation_Id(Long locationId);
     ```

12. **Во пакетот `mk.ecode.events.service`:**
   - Изменете го `LocationService` за да ги користи методите од `LocationRepository`.
   - Изменете го `EventService` за да ги користи методите од `EventRepository`.

13. **Изменете ја `EventController`:**
   - Додајте поддршка за додавање/ажурирање на настани, притоа перзистирајќи ги податоците во базата.
   - Осигурајте се дека при ажурирање, податоците се преземаат од базата и се прикажуваат во формата.
   - Изменете го методот `getEventsPage` за да ги презема настаните од базата со помош на `EventService`.

14. **Осигурајте се дека `listEvents.html` и `add-event.html` работат со податоците од базата.**

15. **При додавање или ажурирање на настан:**
   - Корисникот треба да може да избере локација од листата (пополнета од базата преку `LocationService`).

16. **Тестирајте ја функционалноста:**
   - Додавање нов настан.
   - Ажурирање на настан.
   - Бришење на настан.
   - Прикажување на настаните според локација.

# Продолжение III

## Опис на задачата

### Имплементација на контрола на пристап со Spring Security

1. Додавање зависности
Најпрво е потребно да ги додадете зависностите за **Spring Security** кои се поставени во `pom.xml`.

```xml
    <dependencies>
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<dependency>
			<groupId>org.thymeleaf.extras</groupId>
			<artifactId>thymeleaf-extras-springsecurity6</artifactId>
			<version>3.1.1.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
        </dependency>
    </dependencies>
```

2. Конфигурација на Spring Security
Потребно е да конфигурирате **Spring Security**, при што ќе се овозможи најава на корисници.

- Користете `inMemoryAuthentication` и креирајте предефиниран **admin** корисник.

3. Контрола на пристап
 Кога нема најавено корисник:
- Се гледаат сите страни **освен** оние за додавање и уредување на податоци.
- Не треба да се гледаат копчињата за додавање, бришење и уредување (`edit`) на податоци.

Кога е најавен **admin** корисникот:
- Треба да има пристап до **сите функционалности** на апликацијата.
- По најавата, треба да се отвори `/events` страната.

Очекувани резултати
Со успешно имплементирање на горенаведените чекори, апликацијата ќе обезбеди основна контрола на пристап,
ограничувајќи ги функционалностите врз основа на статусот на најава на корисникот.
