# SRDS-cassandra
Projekt na zajęcia SRDS z cassandra.

TODO:
- Tabele ✓
- Zakodzić!!!:
    - dodanie ksiazek (przed odpaleniem trzeba dodac ksiazki do bazy (z 10 pozycji po 100 sztuk?) polecam zrobic to w osobnym programie czy cos)
    - dodanie requesta:
        - sprawdzenie czy posiadamy ksiazke w books_table i czy sa wolne egzemplarze
        - dodanie wpisu do request table (zmniejszyc licznik na ilosci dostepnych w books table - tu albo w nastepnym kroku)
        - sprawdzenie czy approved??? (rozkminic jak to zrobic)
    - oddanie ksiazki:
        - usuniecie wpisu>??? jak to zrobi??
    - jakies ładne statystyki do wywalenia zeby wszyscy byli szczesliwi i podziwiali piekne efekty
- Testy


REQUEST_TABLE:
 - id uuid,
 - id_book int,
 - id_user int, ("RODO!!!")
 - timestamp bigint,
 - PRIMARY KEY(id, id_book);
 
 BOOK_TABLE:
 - id_book int;
 - book_name string,
 - nr_of_free_books int,
 - total_books int,
 - PRIMARY KEY(id_book, book_name);
 
