package jacopodemaio.dao;

import jacopodemaio.entities.Evento;
import jacopodemaio.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EventoDAO {
//    questa classe avra metodi che interagiranno con la nostra classe evento
//    come prima cosa dobbiamo passagli come paramentro l'EntityManager

    private EntityManager em;

//    e il costruttore passerà come parametro quest'ultimo


    public EventoDAO(EntityManager em) {
        this.em = em;
    }

//    fatto cio si puo passare ai metodi, per iniziare faremo i piu classici save, findById e delete

    public void save(Evento evento) {
//        dobbiamo chiedere di creare una nuova transizione
        EntityTransaction transaction = em.getTransaction();
//        poi di iniziarla
        transaction.begin();
//        poi dobbiamo passera il nostro parametro "evento" alla persistence
        em.persist(evento);
//        poi dobbiamo fare il commit della nostra transizione
        transaction.commit();
        System.out.println("l'evento " + evento.getTitle() + " è stato correttamente salvato sul database");
    }

    public Evento findById(long eventId) {
//        qui prendiamo un evento qualsiasi e tramite il nostro manager con il metodo find andiamo a prendere quello specifico id
        Evento evento = em.find(Evento.class, eventId);
//        facendo attenzione che il dato tornano no sia null per questo vanno gestite l'eccezioni
        if (evento == null) throw new NotFoundExceptions(eventId);
        return evento;

    }

    public void foundByIdAndDelete(long eventId) {
        Evento foundedEvento = this.findById(eventId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(foundedEvento);
        transaction.commit();
        System.out.println("l'evento " + foundedEvento.getTitle() + " è stato eliminato con successo dal database");

    }
}
