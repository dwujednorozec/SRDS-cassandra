# SRDS-cassandra
Projekt na zajęcia SRDS z bazy danych nosql cassandra.
---
**Wykonanie:** Mateusz Łaczkowski, Patryk Jankowski  

**Cel projektu:**  
Napisanie w języku Java implementacji pomysłu zarządzania wirtualną wypożyczalnią książek w której klient może wypożyczyć książkę, bądź zbiór książek i je zwrócić.

**Założenia:**  
Klienci mogą wypożyczyć ksiązki z dostępnej z góry narzuconej puli.  
Klienci którzy chcieli wypożyczyć książki lecz conajmniej jedna nie jest dostępna nie dostają żadnej.  
Na koniec cyklu książki sa przez klientów oddawane.  
Zebranie danych w czasie trwania cyklu i przedstawienie ich.  

**Przykłady wykorzystywanych tabel:**
```
REQUEST_TABLE:  
  id uuid,  
  id_book int,  
  id_user int,   
  req_books int,
  returned boolean,
  timestamp bigint,  
  PRIMARY KEY(id_book, id);  
```
```
 BOOK_TABLE:  
  id_book int,  
  book_name text,  
  nr_of_free_books int,  
  total_books int,  
  PRIMARY KEY(book_name, id_book);
```  
  
**Wnioski:**  
Baza nosql posiada swoje zalety które sa uwiodcznione w projekcie,  
