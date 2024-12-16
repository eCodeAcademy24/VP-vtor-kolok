# Artists Application

## Опис на проектот

Креирајте нов Spring Boot проект со група mk.ecode и artefactId=artists

Дефинирајте пакет mk.ecode.artists.model и во него креирајте ја Artist класата. Таа треба да содржи:

- Long id
- String firstName
- String lastName
- String bio

Во mk.ecode.artists.model креирајте Song класа која ќе содржи:

- String trackId
- String title
- String genre
- int releaseYear
```java 
List<Artist> performers;
```

Една песна може да има повеќе артисти

Креирајте класа ArtistRepository во пакетот mk.ecode.artists.repository, во која ќе чувате List<Artist> иницијализирана со 5 вредности.

Имплементирајте метод public List<Artist> findAll(); кој само ќе ја врати листата.
Имплементирајте метод public Optional<Artist> findById(Long id); кој ќе го врати артистот со id еднакво на пратената
вредност во параметарот.

Креирајте класа SongRepository во пакетот mk.ecode.artists.repository, во која ќе чувате List<Song> иницијализирана со 5
вредности.

Имплементирајте метод public List<Song> findAll(); кој само ќе ја врати листата.
Имплементирајте метод public Song findByTrackId(String trackId); кој ќе ја врати песната чиј trackId е еднаков на
испратената вредност на trackId во параметарот на функцијата.
Имплементирајте метод Artist addArtistToSong(Artist artist, Song song); кој ќе направи додавање на нов артист во листата
од изведувачи за одредена песна.
Дефинирајте ги следните интерфејси во mk.ecode.artists.service кои ќе ги претставуваат бизнис функционалностите на
апликацијата:

```java
 public interface ArtistService {
    List<Artist> listArtists();

    ArtistfindById(Long id);
}

public interface SongService {
    List<Song> listSongs();

    Song addArtistToSong(Long artistId, String trackId);

    Song findByTrackId(String trackId);
}
```

Имплементирајте ги сервисите ArtistService (треба да зависи од ArtistRepository) и SongService (треба да зависи од
SongRepository и ArtistService).
Целта на вежбата е да се креираат страници од кои ќе се избере песна и артист, а потоа избраниот артист да се додаде во
листата на изведувачи за избраната песна. Следете ги наредните чекори за да го имплементирате ваквото однесување.

Креирајте сервлет SongListServlet во пакетот mk.ecode.artists.web и мапирајте го на патеката /listSongs. Овој сервлет
треба да зависи од SongService и да ги прикаже сите добиени книги од методот listSongs().
Прилагодете го фајлот listSongs.html за изгледот на оваа страница.

```html

<html>
<head>
    <meta charset="utf-8">
    <title>Songs Homepage - Welcome to My Music Store</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
        }
    </style>
</head>
<body>
<header>
    <h1>Welcome to My Music Store</h1>
</header>
<main>
    <h2>Choose a song:</h2>
    <!-- Display radio buttons for each song,
            the value should be the trackId 
            and the displayed text should be Title: <songTitle>, Genre:<genre>, Release Year: <releaseYear> -->
    <input type="radio" name="trackId" value="1"> Song 1 <br/>
    <input type="radio" name="trackId" value="2"> Song 2<br/>
    <input type="radio" name="trackId" value="3"> Song 3 <br/>
    <input type='submit' value='Submit'>
</main>
</body>
</html>
```

Забелешка. Вредноста на секоја ставка во radio листата е trackId-то на песната.

По избор на песна, треба да ја прикажете страница со артисти. За оваа цел креирајте сервлет АrtistServlet мапиран на
/artist.

Овој сервлет треба да ја прикажете страната за избор на aртист за додавање во листата на изведувачи на избраната песна
Во фолдерот src/main/resources/templates додадете фајл artistsList.html.

Прилагодете го фајлот artistsList.html за изгледот на оваа страница.

```html

<html>
<head>
    <meta charset="utf-8">
    <title>Add new Performer to Book</title>
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

        section {
            float: left;
            margin: 0 1.5%;
            width: 63%;
        }

        aside {
            float: right;
            margin: 0 1.5%;
            width: 30%;
        }
    </style>
</head>
<body>
<header>
    <h1>Select the Artist to add to the List of Performers </h1>
</header>
<section>
    <h2>Select artist:</h2>
    <!-- Make changes to this code to dynamically display radio buttons for each artist as in the example -->
    <input type="radio" name="artistId" value="1"> Axl Rose <br/>
    <input type="radio" name="artistId" value="2"> Jon Bon Jovi <br/>
    <input type="radio" name="artistId" value="3"> David Bowie <br/>
    <br/>

    <input type='submit' value='Add artist'>
</section>
<aside>
    <table>
        <tr>
            <!-- change to show selected trackId -->
            <td><b>Track Id</b></td>
            <td>2</td>
        </tr>
    </table>
</aside>
</body>
</html>
```

Забелешка. Вредноста на секоја ставка во radio листата е id-от на артистот.

Креирајте сервлет со име songDetails. Овој сервлет треба да ги прикаже сите детали за песната во која е додаден
селектираниот артист од претходниот чекор. При тоа, треба да ги излистате насловот, жанрот, годината на објавување и
сите изведувачи.
Прилагодете го фајлот songDetails.html за изгледот на оваа страница.

```html

<html>
<head>
    <meta charset="utf-8">
    <title>Song Details</title>

</head>
<body>
<!-- dynamically display data -- >
<header>
   <h1>Song title</h1>
</header>
<section>
    <h2>Genre</h2>
    <h2>Year</h2>
    <h2>Performers:</h2>
    <ul>
        <li>Jon Bon Jovi</li>
        <li>Richie Sambora</li>
    </ul>
</section>

</body>
</html>
```
