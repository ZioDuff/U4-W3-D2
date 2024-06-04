package jacopodemaio.entities;

import jacopodemaio.enums.TipoEvento;
import jakarta.persistence.*;

import java.time.LocalDate;

// tramite entity inizializziamo la nostra tabella tramite la classe
@Entity
@Table(name = "eventi") // tramite table andiamo a dare un nome alla nostra tabella
public class Evento {
    @Id
    @GeneratedValue
    // tramite queste 2 annotations gli specifichiamo l'id univoco e seriale per ogni oggetto che andremo a generare
    private long id;

    @Column(name = "Titolo")
    // per ogni colonna che ci servira metteremo la sua annotations specifica con il suo nome e sotto il suo attributo
    private String title;

    @Column(name = "data_evento")
    private LocalDate date;

    @Column(name = "descrizione")
    private String description;

    @Column(name = "tipo_evento")
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    @Column(name = "numero_max_partecipanti")
    private int numeroMaxPartecipanti;

//    una volta stabilito come dovra essere forma la nostra colonna dobbiamo passare al processo di creazione di essa

//    ovviamente per poter richiamare la nostra classe come sempre abbiamo bisogno del costruttore

//    in questo caso pero dobbiamo inizializzare prima di tutto un costruttore vuoto

    public Evento() {
    }

//    dopo di chè posso creare il costruttore vero e proprio

    // il nostro costruttore non avrà come parametro l'id perchè quello lo faremo generare dal db
    public Evento(String title, LocalDate date, String description, TipoEvento tipoEvento, int numeroMaxPartecipanti) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.tipoEvento = tipoEvento;
        this.numeroMaxPartecipanti = numeroMaxPartecipanti;
    }

//    infine la nostra classe avra i classici metodi getter and setter e il tostring


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }

    public void setNumeroMaxPartecipanti(int numeroMaxPartecipanti) {
        this.numeroMaxPartecipanti = numeroMaxPartecipanti;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", tipoEvento=" + tipoEvento +
                ", numeroMaxPartecipanti=" + numeroMaxPartecipanti +
                '}';
    }
}
