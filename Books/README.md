# Books Application

## Опис на проектот

Креирајте нов Spring Boot проект со група mk.ecode и artefactId=books

Дефинирајте пакет mk.ecode.books.model и во него креирајте ја Author класата. Таа треба да содржи:

- Long id
- String name
- String surname
- String biography

Во mk.ecode.books.model креирајте Book класа која ќе содржи:

- String isbn
- String title
- String genre
- int year
```java 
List<Author> authors;
```

Една песна може да има повеќе автори

Креирајте класа AuthorRepository во пакетот mk.ecode.books.repository, во која ќе чувате List<Author> иницијализирана со 5 вредности.

Имплементирајте метод public List<Author> findAll(); кој само ќе ја врати листата.
Имплементирајте метод public Optional<Author> findById(Long id); кој ќе го врати авторот со id еднакво на пратената вредност во параметарот.

Креирајте класа BookRepository во пакетот mk.ecode.books.repository, во која ќе чувате List<Book> иницијализирана со 5 вредности.

Имплементирајте метод public List<Book> findAll(); кој само ќе ја врати листата.
Имплементирајте метод public Book findByIsbn(String isbn); кој ќе ја врати книгата чиј isbn е еднаков на испратената вредност на isbn во параметарот на функцијата.
Имплементирајте метод Book addAuthorToBook(Author author, Book book); кој ќе направи додавање на нов автор во листата од автори за одредена книга.

Дефинирајте ги следните интерфејси во mk.ecode.books.service кои ќе ги претставуваат бизнис функционалностите на апликацијата:
```java
public interface AuthorService{
    List<Author> listAuthors();
    Author findById(Long id);
}
public interface BookService{
    List<Book> listBooks();
    Book addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
}
```

Имплементирајте ги сервисите AuthorService (треба да зависи од AuthorRepository) и BookService (треба да зависи од BookRepository и AuthorRepository).
Целта на вежбата е да се креираат страници од кои ќе се избере книга и автор, а потоа избраниот автор да се додаде во листата на автори за избраната книга. 
Следете ги наредните чекори за да го имплементирате ваквото однесување.

Креирајте сервлет BookListServlet во пакетот mk.ecode.books.web и мапирајте го на патеката /listBooks. 
Овој сервлет треба да зависи од BookService и да ги прикаже сите добиени книги од методот listBooks().

Прилагодете го фајлот listBooks.html за изгледот на оваа страница.

```html
<html>
<head>
    <meta charset="utf-8">
    <title>Books Homepage - Welcome to My Book Store</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
        }
    </style>
</head>
<body>
<header>
    <h1>Welcome to My Book Store</h1>
</header>
<main>
    <h2>Choose book:</h2>
    <!-- Display radio buttons for each book,
            the value should be the isbn 
            and the displayed text should be Title: <bookTitle>, Genre:<genre>, Year: <year> -->
    <input type="radio" name="bookIsbn" value="1"> Book 1 <br/>
    <input type="radio" name="bookIsbn" value="2"> Book 2<br/>
    <input type="radio" name="bookIsbn" value="3"> Book 3 <br/>
    <input type='submit' value='Submit'>
</main>
</body>
</html>
```

Забелешка. Вредноста на секоја ставка во radio листата е isbn-от на книгата.

По избор на книга, треба да ја прикажете страница со автори. За оваа цел креирајте сервлет АuthorServlet мапиран на /author.

Овој сервлет треба да ја прикажете страната за избор на автор за додавање на книгата
Во фолдерот src/main/resources/templates додадете фајл authorList.html.

Прилагодете го фајлот authorList.html за изгледот на оваа страница.

```html
<html>
<head>
    <meta charset="utf-8">
    <title>Add new Author to Book</title>
    <style type = "text/css">
        body {
            width: 800px;
            margin: auto;
        }
        table {
            width:100%;
        }
        table, td, th {
            border: 1px solid black;
            padding:3px 2px;
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
    <h1>Select the Author to add to the Book </h1>
</header>
<section>
    <h2>Select author:</h2>
    <!-- Make changes to this code to dynamically display radio buttons for each author as in the example -->
    <input type="radio" name="authorId" value="1"> Петар Петров <br/>
    <input type="radio" name="authorId" value="2"> Миле Милев <br/>
    <input type="radio" name="authorId" value="3"> Ѓорѓи Ѓорѓиев <br/>
    <br/>

    <input type='submit' value='Add author'>
</section>
<aside>
    <table>
        <tr>
            <!-- change to show selected book isbn -->
            <td><b>Book Isbn</b></td>
            <td>2</td>
        </tr>
    </table>
</aside>
</body>
</html>
```

Забелешка. Вредноста на секоја ставка во radio листата е id-от на авторот.

Креирајте сервлет со име bookDetails. Овој сервлет треба да ги прикаже сите детали за книгата во која е додаден селектираниот автор од претходниот чекор. 
При тоа, треба да ги излистате насловот, жанрот, годината на објавување и сите автори на книгата.
Прилагодете го фајлот bookDetails.html за изгледот на оваа страница.

```html
</head>
<body>
<!-- dynamically display data -- >
<header>
   <h1>Book title</h1>
</header>
<section>
    <h2>Genre</h2>
    <h2>Year</h2>
    <h2>Authors:</h2>
    <ul>
        <li>Петар Петров</li>
        <li>Јован Јованов</li>
    </ul>
</section>

</body>
```

# Продолжение

## Класата `Book`
1. Во класата `Book` додадете уште едно својство, `private Long id`, кое е уникатно за секоја книга.
`id` генерирајте го за секоја книга посебно.

## Класата `BookStore`
1. Додадете класа `BookStore` во рамки на пакетот `mk.ecode.books.model`.
2. Во истата класа додајте:
    - `private Long id`
    - `private String name`
    - `private String city`
    - `private String address`
3. Во класата `Book` додадете врска до класата `BookStore` како посебно својство под претпоставка дека една книга може да 
ја има само во една книжарница, а една книжарница може да има повеќе книги:
    - `private BookStore bookStore`

## Класата `BookStoreRepository`
1. Креирајте класа `BookStoreRepository` во пакетот `mk.ecode.books.repository`.
2. Во неа иницијализирајте листа во која ќе има 5 различни книжарници.
3. Во рамки на класата напишете метод `public List<BookStore> findAll()` кој ги враќа сите книжарници кои постојат во системот.
4. За секоја од книгите иницијализирајте некоја од книжарниците во атрибутот `bookStore`.

## Класата `BookStoreService`
1. Во пакетот `mk.ecode.books.service` креирајте интерфејс `BookStoreService`.
2. Имплементирајте го интерфејсот во класата `BookStoreServiceImpl` (во подпакетот `impl`).
3. Во сервисот креирајте метод `public List<BookStore> findAll()` кој го повикува соодветниот метод од `BookStoreRepository`.

## Класата `BookController`
1. Дефинирајте пакет `mk.ecode.books.web.controller` и во него креирајте ја класата `BookController`.
2. Имплементирајте метод `public String getBooksPage(@RequestParam(required = false) String error, Model model)` кој само треба да го прикаже погледот на сите книги.
3. Нека методот одговара на `/books`.
4. Погледот на сите книги нека биде `listBooks.html`, со тоа што во него ќе ги направите потребните промени за приказ 
на името на книжарницата каде што може да се најде книгата.
5. Додадете функционалност за избирање на книга и соодветен автор од минатата вежба.
6. До секоја книга додадете две копчиња: едно кое ќе преставува линк за пренасочување кон страницата за едитирање на таа книга и второто кое ќе овозможи бришење на книгата.

## Додадени методи во `BookController`
1. `public String saveBook()`: Овој метод овозможува додавање на нова книга. Како request параметри ќе ги прими:
    - Името на книгата (`title`),
    - ISBN идентификаторот,
    - Жанрот на книгата (`genre`),
    - Годината на издавање (`year`),
    - id-то на книжарницата кое корисникот ќе го избира од паѓачко мени (select компонента).
      Нека одговара на `/books/add`, и при успешно додавање нека редиректира кон погледот со сите книги.

2. `public String editBook(@PathVariable Long bookId)`: Овој метод овозможува ажурирање на книгата. Како request параметри ќе ги прими:
    - Името на книгата (`title`),
    - ISBN идентификаторот,
    - Жанрот на книгата (`genre`),
    - Годината на издавање (`year`),
    - id-то на книжарницата кое корисникот ќе го избира од паѓачко мени.
      Предефинираните вредности за сите полиња ќе бидат земени од книгата која се уредува. Нека одговара на `/books/edit/{bookId}`, 
   каде `bookId` е id-то на книгата што се уредува и при успешно ажурирање нека редиректира кон погледот со сите книги.

3. `public String deleteBook(@PathVariable Long id)`: Овој метод овозможува бришење на книга. Нека одговара на `/books/delete/{id}`, 
и при успешно избришаната книга, повторно нека ја прикажува листата со книги.

## Дополнителни методи
1. `public String getEditBookForm()`: Овој метод одговара на `/books/edit-form/{id}` и ја прикажува формата за едитирање на книга. 
Ако не постои книга со даденото id, се прави редирекција на списокот со книги со порака за грешка.

2. `public String getAddBookPage()`: Овој метод одговара на `/books/add-form` и ја прикажува формата за додавање на нова книга.

## Страна `add-book.html`
1. Страната треба да прикажува форма за додавање на нова книга. Истата форма ќе се користи и за едитирање на книга.
2. Формата ќе прави POST барање кон `BookController` за креирање на нова книга или едитирање на постоечка книга.
3. Формата за книжарницата ќе има `<select>` елемент за избор на книжарница од сите книжарници во системот.

## Страна `listBooks.html`
1. Страната ќе прикажува листа на сите книги.
2. За секоја книга ќе има копче за бришење и копче за едитирање на книгата.
3. Дополнително, ќе има копче за додавање на нова книга.

## Тестирање на функционалностите
1. Проверете дали успешно ја прикажувате листата со книги.
2. Проверете дали можете да креирате нова книга, да едитирате книга и да избришете книга.
3. Проверете дали работат функционалностите од претходната вежба.
4. Проверете дали ги прикажувате сите детали за одредена книга.

