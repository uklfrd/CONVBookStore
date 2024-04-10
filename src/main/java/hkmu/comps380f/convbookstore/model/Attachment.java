package hkmu.comps380f.convbookstore.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
public class Attachment {
    @Id
    @GeneratedValue
    @ColumnDefault("random_uuid()")
    private UUID id;
    @Column(name = "filename")
    private String name;
    @Column(name = "content_type")
    private String mimeContentType;
    @Column(name = "content")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] contents;
    @Column(name = "book_id", insertable=false, updatable=false)
    private long nookId;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    // getters and setters of all properties

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeContentType() {
        return mimeContentType;
    }

    public void setMimeContentType(String mimeContentType) {
        this.mimeContentType = mimeContentType;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public long getNookId() {
        return nookId;
    }

    public void setNookId(long nookId) {
        this.nookId = nookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
