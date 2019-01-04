# SRDS-cassandra
Projekt na zajęcia SRDS z cassandra.

TODO:
- Tabele
- Zakodzić!!!
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
 
