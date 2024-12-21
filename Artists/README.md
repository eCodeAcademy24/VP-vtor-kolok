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

Креирајте класа ArtistRepository во пакетот mk.ecode.artists.repository, во која ќе чувате List<Artist> иницијализирана
со 5 вредности.

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

# Продолжение I

# Опис на задачата

Во оваа вежба ќе продолжиме со работа во рамки на проектот.

1. Во класата `Song` додадете ново својство `private Long id`, кое е уникатно за секоја песна. Идентификаторот (`id`)
   треба автоматски да се генерира за секоја песна.

2. Додадете класа `Album` во пакетот `mk.ecode.artists.model`. Во оваа класа ќе се чуваат следниве атрибути:
    - `private Long id`
    - `private String name`
    - `private String genre`
    - `private String releaseYear`

3. Во класата `Song` додадете врска до класата `Album` како посебно својство под претпоставка дека една песна може да
   припаѓа само на еден албум, додека еден албум може да содржи повеќе песни
    - `private Album album`

4. Креирајте `AlbumRepository` класа во пакетот `mk.ecode.artists.repository`, и во неа иницијализирајте листа со 5
   различни албуми. Во рамки на класата напишете и метод `public List<Album> findAll()` кој ги враќа сите албуми што
   постојат во системот. За секоја песна иницијализирајте некој од албумите во атрибутот `album`.

5. Во рамки на пакетот `mk.ecode.artists.service` креирајте интерфејс `AlbumService` како и класа која го имплементира
   `AlbumServiceImpl` (во impl потпакетот). Нека овој сервис содржи метод `public List<Album> findAll()` кој го повикува
   соодветниот метод од `AlbumRepository`.

6. Дефинирајте пакет `mk.ecode.artists.web.controller` и во него креирајте ја `SongController` класата.
    - Имплементирајте метод `public String getSongsPage(@RequestParam(required = false) String error, Model model)` кој
      ќе го прикаже погледот на сите песни. Нека одговара на mapping `/songs`. Погледот на сите песни нека биде
      `listSongs.html`, со тоа што во него ќе ги направите потребните промени со приказ на името на албумот за секоја
      песна, заедно со функциите за избор на песна и соодветен артист. Додадете две копчиња за секоја песна: едно кое ќе
      биде линк за пренасочување кон страницата за едитирање на таа песна, и друго кое ќе овозможи бришење на песната.
    - Имплементирајте метод `public String saveSong()` кој ќе овозможи додавање на нова песна и кој како request
      параметри ќе ги прими
      `насловот на песната title, trackId идентификаторот, жанрот genre, годината releaseYear и id-то на албумот кое корисникот ќе го бира од паѓачко мени (<select> таг)`.
      Нека одговара на mapping /songs/add, и при успешно додадена песна нека редиректира кон погледот со сите песни.
    - Имплементирајте метод `public String editSong(@PathVariable Long songId)` кој ќе овозможи ажурирање на песните кој
      како request параметри ќе ги прими прими
      `насловот на песната title, trackId идентификаторот, жанрот genre, годината releaseYear, и id-то на албумот кое корисникот ќе го бира од паѓачко мени (<select> таг)`.
      Како предефинирани вредности на сите полиња треба да се земат од песната што се едитира. Нека одговара на mapping
      `/songs/edit/{songId}` каде `songId е id-то на песната` што се уредува и при успешно ажурирање нека редиректира
      кон погледот со сите песни.
    - Имплементирајте метод `public String deleteSong(@PathVariable Long id)`. Нека одговара на mapping
      `/songs/delete/{id}`, и при успешно избришанa песна од листата повторно нека ја прикажува листата со песни.

7. Во рамки на `listSongs.html`, додадете копчe за бришење на песна и копче за едитирање на песна (во рамки на секој
   item во листата). Дополнително, додадете копче за додавање на новa песна кон листата

8. До овој момент треба да имате целосна функционалност на прикажување на сите песни во листата, како и бришење на една
   песна од истата. Повторно, потребно е да ја надополните `SongController` класата.
    - Имплементирајте метод `public String getEditSongForm()`, кој одговара на mapping `/songs/edit-form/{id}`.
      Направете ги сите потребни промени во дефиницијата на методот за да го овозможите ова. Овој метод треба да ја
      прикаже `add-song.html` страната. Кога едитираме песна, потребно е во рамки на формата да се прикажуваат нејзините
      моментални податоци. Дополнително, доколку се пристапи патеката `/songs/edit-form/{id}`, со `id` за кое нема песна
      во рамки на листата, нека се направи редирект кон листата со песни, при што ќе се прикаже и порака за грешка.
    - Имплементирајте метод `public String getAddSongPage()`, кој одговара на mapping `/songs/add-form` и ја прикажува
      `add-song.html` страната.

9. Формата која се наоѓа на add-song.html страната, ќе прави POST барање кон SongController, со што ќе овозможите
   креирање на нова песна или пак едитирање на песна.

10. Функционалноста на сервлетот `АrtistServlet` при додавање на артист на песна, заменете ја со контролер
    `(ArtistController)` во кој ќе овозможете додавање на артисти за одредена песна.

11. Тестирање:
- дали успешно ја прикажувате листата со песни
- дали можете да креирате нова песна, да едитирате песна и да избришете песна
- дали работат функционалностите од претходната вежба со тоа што сите сервлети ќе ви бидат заменети со контролери.
- дали успешно ги прикажувате сите детали за одредена песна.


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
   spring.datasource.url=jdbc:postgresql://localhost:5432/music
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

7. **Изменете ја класата `Song` во пакетот `mk.ecode.artists.model`:**
   - Анотирајте ја со `@Entity` за да стане JPA ентитет.
   - Обезбедете уникатно `id` за секој песна, анотирајќи го со `@Id` и `@GeneratedValue`.

8. **Изменете ја класата `Album`:**
   - Анотирајте ја со `@Entity`.
   - Обезбедете уникатно `id` со `@Id` и `@GeneratedValue`.
   - Додадете ја релацијата со `Song` (`@OneToMany`).

9. **Дефинирајте ги релациите меѓу ентитетите `Song` и `Album`:**
   - Во `Song` користете `@ManyToOne` за релацијата со `Album`.
   - Во `Album` додајте листа на `Song` ентитети и користете `@OneToMany(mappedBy = "album")`.

10. **Креирајте табели во базата на податоци за `Song` и `Album` преку Spring Data JPA.**

11. **Во пакетот `mk.ecode.artists.repository`:**
- Креирајте `SongRepository` и `AlbumRepository` интерфејси кои ќе наследуваат од `JpaRepository`.
- Додадете метод во `SongRepository` кој враќа песни според албум:

  ```java
   List<Song> findAllByAlbum_Id(Long albumId);
  ```
  
12. **Во пакетот `mk.ecode.artists.service`:**
- Изменете го `AlbumService` за да ги користи методите од `AlbumRepository`.
- Изменете го `SongService` за да ги користи методите од `SongRepository`.

13. **Изменете ја `SongController`:**
- Додајте поддршка за додавање/ажурирање на песни, притоа перзистирајќи ги податоците во базата.
- Осигурајте се дека при ажурирање, податоците се преземаат од базата и се прикажуваат во формата.
- Изменете го методот `getSongsPage` за да ги презема песните од базата со помош на `SongService`.

14. **Осигурајте се дека `listSongs.html` и `add-form.html` работат со податоците од базата.**

15. **При додавање или ажурирање на песна:**
- Корисникот треба да може да избере албум од листата (пополнета од базата преку `AlbumService`).

16. **Тестирајте ја функционалноста:**
- Додавање нова песна.
- Ажурирање на песна.
- Бришење на песна.
- Прикажување на песните според албум.

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
- По најавата, треба да се отвори `/songs` страната.

Очекувани резултати
Со успешно имплементирање на горенаведените чекори, апликацијата ќе обезбеди основна контрола на пристап,
ограничувајќи ги функционалностите врз основа на статусот на најава на корисникот.
