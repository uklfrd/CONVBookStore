package hkmu.comps380f.convbookstore.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Book {
    private long id;
    private String bookName;
    private String author; //subject
    private Float price;
    private String description;//body
    private Map<String, Attachment> attachments = new ConcurrentHashMap<>();

    // Getters and Setters of id, customerName, subject, body (not attachments)

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attachment getAttachment(String name) {
        return this.attachments.get(name);
    }
    public Collection<Attachment> getAttachments() {
        return this.attachments.values();
    }
    public void addAttachment(Attachment attachment) {
        this.attachments.put(attachment.getId(), attachment);
    }
    public int getNumberOfAttachments() {
        return this.attachments.size();
    }
}
