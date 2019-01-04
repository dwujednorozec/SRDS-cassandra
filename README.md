# SRDS-cassandra
Projekt na zajęcia SRDS z cassandra.

TODO:
- Tabele
- Zakodzić!!!:
    - dodanie ksiazek (przed wystartowaniem trzeba dodac ksiazki do bazy polecal bym zrobic osobny program co na szybko wpisze kilka (z 10 pozycji) dostepnych w kilku egzemparzach (z 100 sztuk?)
    - rezerwacja booka:
        - pytanie czy posiadamy ksiazke
        - sprawdzanie czy jest dostepna
        - daodanie wpisu do request_table
        - sprawdzenie czy przeszlo ??? (rozkminic jak to zrobic)
    -robienie statow zeby podsumowac cos

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
 
