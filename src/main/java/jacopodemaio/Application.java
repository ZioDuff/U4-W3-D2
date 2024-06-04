package jacopodemaio;

import jacopodemaio.dao.EventoDAO;
import jacopodemaio.entities.Evento;
import jacopodemaio.enums.TipoEvento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Application {
    // questo dato dovra essere uguale a quello presente nel persistence-unit perche ci fa creare il nostro entity manager
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");


    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EventoDAO ev = new EventoDAO(em);


////        proviamo a salvare un nuovo evento
//
//        Evento grandeFesta = new Evento("Grande festa", LocalDate.now(), "festa di laurea", TipoEvento.PRIVATO, 30);
//        Evento evento2 = new Evento("Giovanni baby shower", LocalDate.now(), "Evviva giovanni avra una figlia", TipoEvento.PRIVATO, 120);
//        Evento evento3 = new Evento("Festa Comunità", LocalDate.now(), "venitecia trovare gente", TipoEvento.PUBBLICO, 300);
//
//
//        try {
//            Evento eventoById = ev.findById(4);
//            System.out.println(eventoById.getTitle());
//        } catch (NotFoundExceptions ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        try {
//            ev.foundByIdAndDelete(1);
//        } catch (NotFoundExceptions ex) {
//            System.out.println(ex.getMessage());
//        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Benvenuto utente, qui puoi salvare nuovi eventi, leggere quelli gia presenti oppure eliminarli");
        System.out.println("1 salva nuovo evento");
        System.out.println("2 leggi eventi gia presenti");
        System.out.println("3 elimina evento");
        System.out.println("0 chiudi app");
        int scelta = Integer.parseInt(sc.nextLine());
        switch (scelta) {
            case 1: {
                System.out.println("perfetto per salvare un nuovo evento inserisci:");
                System.out.println("titolo evento");
                String title = sc.nextLine();
                System.out.println("metti la data formato yyyy-mm-dd");
                LocalDate date = LocalDate.parse(sc.nextLine());
                System.out.println("inserisci una descrizione ");
                String description = sc.nextLine();
                System.out.println("scegli se il tuo evento sarà Pubblico o Privato");
                String input = sc.nextLine();
                TipoEvento tipoEvento = null;
                if (input.equals("Pubblico")) {
                    tipoEvento = TipoEvento.PUBBLICO;
                } else if (input.equals("Privato")) {
                    tipoEvento = TipoEvento.PRIVATO;
                } else System.out.println("SBAGLIATOOOOOOOOO");
                System.out.println("decidi un massimo di persone da invitare");
                int numOfPeople = Integer.parseInt(sc.nextLine());
                System.out.println("perfetto ora creo il tuo evento db");

                Evento userEvent = new Evento(title, date, description, tipoEvento, numOfPeople);
                ev.save(userEvent);
                break;
            }
            case 2: {
                System.out.println("questa è la lista degli eventi:");
                List<Evento> listaEVenti = ev.queryForEvents();
                listaEVenti.forEach(System.out::println);


            }
        }
        sc.close();


//        meglio fare sempre un close del nostro entity manager e factory
        em.close();
        emf.close();


    }
}
