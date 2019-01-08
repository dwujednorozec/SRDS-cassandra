import com.datastax.driver.core.Session;
import java.util.List;

public class Stats {
    private static Stats INSTANCE;

    private int wypozyczone = 0;
    private int prosb = 0;
    private int niewypozyczone = 0;
    private int zgubiono = 0;
    private int oddano = 0;

    private void log(int id_user, String msg) {
        System.out.println("[" + id_user + "] " + msg);
    }

    public void request(int id_user, List<String> book) {
        log(id_user, "Chciałbym ksiazke!{"+book+"}");
        prosb++;
    }

    public void rent(int id_user, List<String> book) {
        log(id_user, "Wypozyczylem ksiazke!{"+book+"}");
        wypozyczone+=book.size();
    }

    public void dontrent(int id_user, List<String> book) {
        log(id_user, "Nie udało się wyporzyczyć!{"+book+"}");
    }
    public void unrent(int id_user, int books) {
        log(id_user, "Oddalem ksiazke! Ilość:{"+books+"}");
        oddano+=books;
    }

    public static Stats getInstance(){
        if(INSTANCE==null)
            INSTANCE = new Stats();
        return INSTANCE;
    }

    public void showStats() {
        int zgubiono = wypozyczone - oddano;
        System.out.println("Wyporzyczone ksiązki = "+wypozyczone);
        System.out.println("Niewypozyczone = "+niewypozyczone);
        System.out.println("Prosby o wyporzyczenie = "+prosb);
        System.out.println("Zgubiono ksiązek = "+zgubiono);
        /*System.out.println("------\nHappy Clients:");
        reservations.forEach(r -> System.out.print("" + r + ", "));
        System.out.println("\n------");

        System.out.println("------\nSad Clients:");
        sad.forEach(r -> System.out.print("" + r + ", "));
        System.out.println("\n------");
       */
    }

}