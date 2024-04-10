package hkmu.comps380f.convbookstore.exception;

public class BookNotFound extends Exception {
    public BookNotFound(long id) {
        super("Ticket " + id + " does not exist.");
    }
}
