# Movies Application

## Опис на проектот

Креирајте нов Spring Boot проект со група mk.ecode и artefactId=events

Дефинирајте пакет mk.ecode.movies.model и во него креирајте ја TicketOrder класата. Таа треба да содржи:
- String movieTitle
- String clientName
- String clientAddress
- Long numberOfTickets

Во mk.ecode.movies.model креирајте Movie класа која ќе содржи:
- String title
- String summary
- double rating

Креирајте класа MovieRepository во пакетот mk.ecode.movies.repository, во која ќе чувате List<Movie> иницијализирана со 10 вредности.
Имплементирајте метод public List<Movie> findAll(); кој само ќе ја врати листата.
Имплементирајте метод public List<Movie> searchMovies(String text); кој ќе направи пребарување низ листата на филмови и ќе ги врати оние во чиј наслов или кратка содржина се содржи текстот text кој се праќа како аргумент на методот.
Дефинирајте ги следните интерфејси во mk.ecode.movies.service кои ќе ги претставуваат бизнис функционалностите на апликацијата:

```java
public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
}

public interface TicketOrderService{
    TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets);
}
```

Имплементирајте ги сервисите (MovieService треба да зависи од MovieRepository).
Креирајте сервлет MovieListSevlet во пакетот mk.ecode.movies.web и мапирајте го на патеката /. 
Овој сервлет треба да зависи од MovieService и да ги прикаже сите добиени филмови од методот listAll(). 
Овозможете корисникот да избере еден од филмовите и за истиот да наведе број на карти што сака да ги нарача. 
Креирајте по едно радио копче за секој филм каде што вредноста на копчето ќе биде насловот на филмот, а текстот кој ќе се прикаже ќе биде во форматот: 
Title: <movie_title>, Summary:<movie_summary>, Rating: <movie_rating>

Прилагодете го фајлот listMovies.html за изгледот на оваа страница.
```html
<html>
<head>
    <meta charset="utf-8">
    <title>Movie Ticket Order page - Welcome and choose a Movie</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
        }
    </style>
</head>
<body>
<header>
    <h1>Welcome to our Movie Ticket Shop App</h1>
</header>
<main>
    <h2>Choose movie:</h2>
    <!-- Display radio buttons for each movie,
            the value should be the movie title 
            and the displayed text should be Title: <movie_title>, Summary:<movie_summary>, Rating: <movie_rating> -->

    <h2>Choose number of tickets:</h2>
    <input type="number" name="numTickets" min="1" max="10"><br/>
    <br/>
    <input type="submit" value="Submit">
</main>
</body>
</html>
```
При избор на филм, треба да ја прикажете нарачката на корисникот. За оваа цел креирајте сервлет TicketOrderServlet мапиран на /ticketOrder.

Овој сервлет треба да ја прикажете страната за потврда на нарачката
Во фолдерот src/main/resources/templates додадете фајл orderConfirmation.html.

Прилагодете го фајлот orderConfirmation.html за изгледот на оваа страница.

```html
<html>
<head>
    <meta charset="utf-8">
    <title>Ordered Ticket - Confirmation</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
        }
        table {
            width:100%;
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
        <h1>Movie Order page - Order confirmation </h1>
    </header>
    <table>
        <tr>
            <th colspan="2">
                Your Order Status
            </th>
        </tr>
        <tr>
            <td><b>Client Name </b></td>
            <td>Petko Petkov</td>
        </tr>
        <tr>
            <td><b>Client IP Address</b></td>
            <td>127.0.0.1</td>
        </tr>
        <tr>
            <td><b>Ticket for Movie</b></td>
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

Да се имплементира можност за пребарување на филмовите на почетната страна listMovies.html. Треба да се прикажат само филмовите кои ги исполнуваат условите од пребарувањето. Пребарувањето треба да се изврши според два параметри:
филмови кои го содржи текстот испратен од страна на корисникот во нивниот наслов
филмови кои имаат рејтинг поголем или еднаков на внесената вредност од страна на корисникот


# Продолжение

## Опис на барања

Во рамки на овој проект ќе креирате систем за управување со филмови и нивните продукциски куќи. Ќе се користат следниве компоненти:

### Класата `Movie`

Во класата `Movie` ќе додадете ново својство:
- `private Long id`, кое ќе биде уникатно за секој филм.

Овој `id` ќе биде генериран автоматски..

### Класата `Production`

Додадете класа `Production` во пакетот `mk.ecode.movies.model`, која ќе има следниве својства:
- `private Long id`
- `private String name`
- `private String country`
- `private String address`

### Врска помеѓу `Movie` и `Production`

Во класата `Movie`, додадете врска до класата `Production` како посебно својство:
- `private Production production`

### Класата `ProductionRepository`

Во пакетот `mk.ecode.movies.repository`, креирајте класа `ProductionRepository`. Во неа иницијализирајте листа која ќе содржи 5 филмски продукции. Напишете метод:
- `public List<Production> findAll()` кој ќе враќа сите продукции кои постојат во системот.

За секој од филмовите иницијализирајте некоја од продукциите во атрибутот `production`.

### Класата `ProductionService`

Во пакетот `mk.ecode.movies.service`, креирајте интерфејс `ProductionService` и имплементирајте го во класа `ProductionServiceImpl` во подпакетот `impl`. Во овој сервис, создадете метод:
- `public List<Production> findAll()` кој го повикува методот `findAll()` од `ProductionRepository`.

### Класата `MovieController`

Во пакетот `mk.ecode.movies.web.controller`, креирајте ја класа `MovieController`. Имплементирајте методи:
- `public String getMoviesPage(@RequestParam(required = false) String error, Model model)` кој ќе прикажува сите филмови. Овој метод ќе одговара на мапингот `/movies` и ќе го прикажува погледот `listMovies.html`. Погледот ќе ги прикажува сите филмови и ќе овозможи избор на филм за нарачка.
- `public String saveMovie()` за додавање на нов филм. Овој метод ќе прима параметри како што се: име на филмот (`movieTitle`), опис на филмот (`summary`), рејтинг (`rating`) и id на продукцијата. Овој метод ќе одговара на мапингот `/movies/add`, и при успешно додавање на филм, ќе редиректира кон погледот со сите филмови.
- `public String editMovie(@PathVariable Long movieId)` за уредување на филм. Овој метод ќе прима параметри: `movieTitle`, `summary`, `rating` и `productionId` и ќе се прикажува на мапингот `/movies/edit/{movieId}`.
- `public String deleteMovie(@PathVariable Long id)` кој ќе дозволи бришење на филм и повторно ќе го прикаже погледот со сите филмови.

### Страна `add-movie.html`

Создајте HTML форма за додавање нов филм, која ќе се користи и за едитирање на филм. Формата ќе содржи:
- Поле за избор на продукција преку `<select>` таг.

### Страна `listMovies.html`

На оваа страница прикажете ја листата со филмови. За секој филм додајте две копчиња:
- Едно за уредување на филмот.
- Друго за бришење на филмот.

Дополнително, додадете копче за додавање на нов филм во листата.

### Методите на `MovieController`

Имплементирајте методи во `MovieController` за:
- Прикажување на форма за додавање нов филм: `public String getAddMoviePage()`, кој ќе одговара на мапингот `/movies/add-form`.
- Прикажување на форма за едитирање на филм: `public String getEditMovieForm(@PathVariable Long id)`, кој ќе одговара на мапингот `/movies/edit-form/{id}`. Во оваа форма треба да се прикажуваат податоците на филмот што се уредува.

### Тестирање

При тестирање, проверете:
- Дали успешно се прикажуваат сите филмови.
- Дали може да се креира нов филм, да се едитира и да се избрише филм.
- Дали работат функционалностите за нарачка, односно дали може да се стигне до потврда за нарачката.
- Дали успешно се прикажува нарачката на моменталниот корисник.
