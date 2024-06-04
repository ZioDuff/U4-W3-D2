package jacopodemaio;

import jacopodemaio.dao.EventoDAO;
import jacopodemaio.entities.Evento;
import jacopodemaio.enums.TipoEvento;
import jacopodemaio.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    // questo dato dovra essere uguale a quello presente nel persistence-unit perche ci fa creare il nostro entity manager
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");


    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EventoDAO ev = new EventoDAO(em);

//        proviamo a salvare un nuovo evento

        Evento grandeFesta = new Evento("Grande festa", LocalDate.now(), "festa di laurea", TipoEvento.PRIVATO, 30);
        Evento evento2 = new Evento("Giovanni baby shower", LocalDate.now(), "Evviva giovanni avra una figlia", TipoEvento.PRIVATO, 120);
        Evento evento3 = new Evento("Festa Comunità", LocalDate.now(), "venitecia trovare gente", TipoEvento.PUBBLICO, 300);


        try {
            Evento eventoById = ev.findById(4);
            System.out.println(eventoById.getTitle());
        } catch (NotFoundExceptions ex) {
            System.out.println(ex.getMessage());
        }

        try {
            ev.foundByIdAndDelete(1);
        } catch (NotFoundExceptions ex) {
            System.out.println(ex.getMessage());
        }


//        meglio fare sempre un close del nostro entity manager e factory
        em.close();
        emf.close();


    }
}
