import com.datastax.driver.core.Session;

public class Stats {
    private static Stats INSTANCE;

    private int wypozyczone = 0;
    private int prosb = 0;
    private int niewypozyczone = 0;
    private int zgubiono = 0;

    private void log(int id_user, String msg) {
        System.out.println("[" + id_user + "] " + msg);
    }

    public void rent(int id_user) {
        log(id_user, "Wypozyczylem ksiazke!");
        wypozyczone++;
    }

    public static Stats getInstance(){
        if(INSTANCE==null)
            INSTANCE = new Stats();
        return INSTANCE;
    }


//koment
    public void showStats() {
      /*  System.out.println("happyClients = "+happyClients);
        System.out.println("sadClients = "+sadClients);
        System.out.println("cancelingClients = "+cancelingClients);
        System.out.println("angryClients = "+angryClients);
        System.out.println("------\nHappy Clients:");
        reservations.forEach(r -> System.out.print("" + r + ", "));
        System.out.println("\n------");

        System.out.println("------\nSad Clients:");
        sad.forEach(r -> System.out.print("" + r + ", "));
        System.out.println("\n------");
       */
    /// Staty do poprawienia
    }

}