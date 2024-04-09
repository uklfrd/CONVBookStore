package hkmu.comps380f.convbookstore.controller;

import hkmu.comps380f.convbookstore.model.Attachment;
import hkmu.comps380f.convbookstore.model.Book;
import hkmu.comps380f.convbookstore.view.DownloadingView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/book")
public class BookController {
    private volatile long BOOK_ID_SEQUENCE = 1;
    private Map<Long, Book> bookDatabase = new ConcurrentHashMap<>();

    // Controller methods, Form-backing object, ...
    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("bookDatabase", bookDatabase);
        return "list";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("add", "bookForm", new Form());
    }
    public static class Form {
        private String bookName;
        private String author;
        private Float price;
        private String description;
        private List<MultipartFile> attachments;

        // Getters and Setters of customerName, subject, body, attachments

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

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }
    }

    @PostMapping("/create")
    public View create(Form form) throws IOException {
        Book book = new Book();
        book.setId(this.getNextTicketId());
        book.setBookName(form.getBookName());
        book.setAuthor(form.getAuthor());
        book.setPrice(form.getPrice());
        book.setDescription(form.getDescription());
        for (MultipartFile filePart : form.getAttachments()) {
            Attachment attachment = new Attachment();
            attachment.setId(RandomStringUtils.randomAlphanumeric(8));
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null && attachment.getContents().length > 0)
                book.addAttachment(attachment);
        }
        this.bookDatabase.put(book.getId(), book);
        return new RedirectView("/book/view/" + book.getId(), true);
    }
    private synchronized long getNextTicketId() {
        return this.BOOK_ID_SEQUENCE++;
    }

    @GetMapping("/view/{bookId}")
    public String view(@PathVariable("bookId") long bookId,
                       ModelMap model) {
        Book book = this.bookDatabase.get(bookId);
        if (book == null) {
            return "redirect:/book/list";
        }
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        return "view";
    }

    @GetMapping("/{bookId}/attachment/{attachment:.+}")
    public View download(@PathVariable("bookId") long bookId,
                         @PathVariable("attachment") String AttachmentId) {
        Book book = this.bookDatabase.get(bookId);
        if (book != null) {
            Attachment attachment = book.getAttachment(AttachmentId);
            if (attachment != null)
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
        }
        return new RedirectView("/book/list", true);
    }
}
