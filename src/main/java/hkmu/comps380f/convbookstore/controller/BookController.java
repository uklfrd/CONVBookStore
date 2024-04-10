package hkmu.comps380f.convbookstore.controller;

import hkmu.comps380f.convbookstore.dao.BookService;
import hkmu.comps380f.convbookstore.exception.AttachmentNotFound;
import hkmu.comps380f.convbookstore.exception.BookNotFound;
import hkmu.comps380f.convbookstore.model.Attachment;
import hkmu.comps380f.convbookstore.model.Book;
import hkmu.comps380f.convbookstore.view.DownloadingView;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bService;
    // Controller methods, Form-backing object, ...

    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("bookDatabase", bService.getBooks());
        return "productbooklist";
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
        long bookId = bService.createBook(form.getBookName(),
                form.getAuthor(), form.getPrice(), form.getDescription(), form.getAttachments());
        return new RedirectView("/book/view/" + bookId, true);
    }

    @GetMapping("/view/{bookId}")
    public String view(@PathVariable("bookId") long bookId,
                       ModelMap model)
            throws BookNotFound {
        Book book = bService.getBook(bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        return "book_info_view";
    }

    @GetMapping("/{bookId}/attachment/{attachment:.+}")
    public View download(@PathVariable("bookId") long bookId,
                         @PathVariable("attachment") UUID attachmentId)
            throws BookNotFound, AttachmentNotFound {
        Attachment attachment = bService.getAttachment(bookId, attachmentId);
        return new DownloadingView(attachment.getName(),
                attachment.getMimeContentType(), attachment.getContents());
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") long bookId)
            throws BookNotFound {
        bService.delete(bookId);
        return "redirect:/book/list";
    }

    @GetMapping("/{bookId}/delete/{attachment:.+}")
    public String deleteAttachment(@PathVariable("bookId") long bookId,
                                   @PathVariable("attachment") UUID attachmentId)
            throws BookNotFound, AttachmentNotFound {
        bService.deleteAttachment(bookId, attachmentId);
        return "redirect:/book/view/" + bookId;
    }

    @ExceptionHandler({BookNotFound.class, AttachmentNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }


}
