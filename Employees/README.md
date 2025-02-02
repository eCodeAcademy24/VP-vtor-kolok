# Испитна задача

Потребно е да развиете апликација за менаџирање на вработени која ќе овозможи прегледување, додавање, уредување и бришење на вработени.

## Функционални барања

- Потребно е на патеките `/` и `/employees` да прикажете листа од сите креирани вработени со користење на темплејтот `list.html`.
  - Имплементацијата на оваа функционалност може да ја проверите со тестот `test_list_20pt`.

- Потребно е да се  имплементира додавање на вработен. При клик на копчето **Add** од темплејтот `list.html`, 
потребно е да се прикаже темплејтот `form.html` на патеката `/employees/add`, каде при клик на **Submit** ќе се креира и запише нов ентитет 
во базата на податоци. Потоа треба да се прикаже страната `/employees`.
  - Имплементацијата на оваа функционалност може да ја проверите со тестовите `test_create_10pt` и `test_create_mvc_10pt`.

- Потребно е да се  имплементира бришење на вработен. При клик на копчето **Delete** од темплејтот `list.html`, потребно е да 
се избрише вработениот од базата на податоци. Потоа треба да се прикаже страната `/employees`.
  - Имплементацијата на оваа функционалност може да ја проверите со тестовите `test_delete_5pt` и `test_delete_mvc_5pt`.

- Потребно е да се  имплементира променување на вработен. При клик на копчето **Edit** од темплејтот `list.html`, 
потребно е да се прикаже темплејтот `form.html` на патеката `/employees/[id]/edit`, при што во `<input>` елементите ќе се прикажат 
вредностите за ентитетот кој се променува. При клик на **Submit** треба да се запише промената на ентитетот во базата на податоци. 
Потоа треба да се прикаже страната `/employees`.
  - Имплементацијата на оваа функционалност може да ја проверите со тестовите `test_edit_10pt` и `test_edit_mvc_10pt`.

- Потребно е да конфигурирате најава на `/login` и одјава на `/logout`. Притоа, јавна треба да биде само страницата `/`, 
    додека сите останати страници треба да се видливи само за `ROLE_ADMIN`. Дополнително, кај`list.html` копчињата 
    **Edit**, **Delete** и **Add** треба да се видливи само за `ROLE_ADMIN`.
    - Најавата треба да биде со email и password својствата од вработените во базата. Лозинките треба да се чуваат енкриптирани.
     Улогата треба да се постави да биде типот на вработениот со префикс `ROLE_`
    - Имплементацијата на оваа функционалност може да ја проверите со тестовите `test_security_urls_10pt` и `test_security_buttons_10pt` 

- Потребно е да овозможите пребарување на вработени според вештниа и минимален стаж 
(време поминато од вработувањето) преку формата со `id="filter-form"` во темплејтот `list.html`. 
Резултатите од пребарувањето треба да се прикажат на истата страница, при што ќе се прикажат само вработените според бараниот услов. 
Филтрирањето се извршува само според внесените полиња (ако се празни, се игнорира филтрирањето по тој критериум).
  - Имплементацијата на оваа функционалност може да ја проверите со тестовите `test_filter_5pt` и `test2_filter_service_5pt`.

**ВАЖНО:** Сите споменати тестови се наоѓаат во класата `SeleniumScenarioTest`.

**НЕ Е ДОЗВОЛЕНО МЕНУВАЊЕ НА ТЕСТОВИТЕ**

## Техничко упатство:
- Во пакетот `mk.ecode.employees` веќе се креирани класите кои го репрезентираат моделот.
  Потребно е да извршите нивно мапирање со соодветните JPA анотации за моделот успешно да се сними во базата на податоци.
  Притоа важат следните услови:
  - Еден вработен може да има повеќе вештини, додека пак на една вештина може да ја поседуваат повеќе вработени.
  - Идентификаторите за `Employee` и `Skill` треба да бидат генерирани.
- Во пакетот `service` се веќе дефинирани интерфејсите за сервисната логика. За секој од методите имате опис што треба 
  да биде имплементирано. Потребно е да се имплементираат овие интерфејси во соодветните класи во пакетот `service.impl`. 
  Во коментарите на методите се објаснети дополнителни услови (доколку ги има) кои треба да ги исполни методот.
- Класите од пакетот `repository` треба да ги дополните со потребните методи кои ви се потребни за да ја овозможите 
  функционалноста на имплементацијата на сервисниот слој. Тие треба да се изведат од класата `JpaRepository` од Spring Data.
- Потребно е да ја анотирате класата `DataInitializer` и нејзините соодветни методи, така што при стартување на апликацијата ќе се изврши методот `initData`.
- Во класата `EmployeesController` се дефинирани сите методи кои се потребни за да се имплементира менаџирањето со вработените.
  За секој од методите имате опис што треба да биде имплементирано. Потребно е овие handler методи да ги мапирате со користење само на HTTP GET и POST барања.
- Дополнете ги темплејтите со соодветните **Thymeleaf** атрибути за да се постигнат бараните функционалности.
  Притоа, ако недостасуваат одредени елементи и атрибути, може да ги додадете, но **НЕ СМЕЕ** да ги менувате `id` и `class` својствата на тековните елементи.
  Во коментари се дадени описи за елементите кои треба да се повторуваат, како и кои методи од контролерот треба да се повикаат.
- Потребно е да конфигурирате најава и одјава на корисници со Spring Security во класата `SecurityConfig`.
  Во самата класа има опис што треба да биде имплементирано.