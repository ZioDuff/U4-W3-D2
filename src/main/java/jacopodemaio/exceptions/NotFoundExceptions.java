package jacopodemaio.exceptions;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(long id) {
        super("L'evento con id " + id + " non è stato trovato");
    }
}
