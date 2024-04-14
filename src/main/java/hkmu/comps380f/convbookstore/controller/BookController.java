package hkmu.comps380f.convbookstore.controller;

import hkmu.comps380f.convbookstore.dao.BookService;
import hkmu.comps380f.convbookstore.exception.AttachmentNotFound;
import hkmu.comps380f.convbookstore.exception.BookNotFound;
import hkmu.comps380f.convbookstore.model.Attachment;
import hkmu.comps380f.convbookstore.model.Book;
import hkmu.comps380f.convbookstore.view.DownloadingView;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bService;

    // Controller methods, Form-backing object, ...
    @GetMapping(value = {"", "/list"})
    public String list(HttpServletRequest request, HttpSession session, ModelMap model) {

        String action = request.getParameter("action");
        if (action == null)
            action = "browse";

        switch (action) {
            case "addToCart":
                return addToCart(model, request, session);
            case "viewCart":
                return viewCart(model);
            case "emptyCart":
                return emptyCart(session);
            case "browse":
            default:
                return browse(model);
        }
    }
    // Defining other methods ...
    private String viewCart(ModelMap model) {
        model.addAttribute("bookDatabase", bService.getBooks());
        return "viewCart";
    }
    private String browse(ModelMap model) {
        model.addAttribute("bookDatabase", bService.getBooks());
        return "productbooklist";
    }
    private String addToCart(ModelMap model, HttpServletRequest request, HttpSession session){
        // from the browse that .jsp have a <a> ... value="addToCart"
        model.addAttribute("bookDatabase", bService.getBooks());
        int productId;

        Book book = new Book();
        book.setBookName(book.getBookName());

        // if cannot get any productID, that will go back to the book list page
        // else go to cart
        try {
            productId = Integer.parseInt(request.getParameter("productId"));
        } catch (Exception e) {
            return  "redirect:/book/list";
        }

        // if Attribute cart have not been created, create it
        if (session.getAttribute("cart") == null)
            session.setAttribute("cart", new ConcurrentHashMap<>());

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart
                = (Map<Integer, Integer>) session.getAttribute("cart");

        // if the productId = Null
        if (!cart.containsKey(productId))
            cart.put(productId, 0);
        cart.put(productId, cart.get(productId) + 1);

        return "redirect:/book?action=viewCart";
    }
    private String emptyCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/book?action=viewCart";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("add", "bookForm", new Form());
    }
    public static class Form {
        private String bookName;
        private String author;
        private String uploader;
        private Float price;
        private String description;
        private String state;
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

        public String getUploader() {
            return uploader;
        }

        public void setUploader(String uploader) {
            this.uploader = uploader;
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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
                form.getAuthor(), form.getUploader(), form.getPrice(), form.getDescription(), form.getState(), form.getAttachments());
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

    @GetMapping("/edit/{bookId}")
    public ModelAndView showEdit(@PathVariable("bookId") long bookId,
                                 Principal principal, HttpServletRequest request)
            throws BookNotFound {
        Book book = bService.getBook(bookId);
        if (book == null
                || (!request.isUserInRole("ROLE_ADMIN")
                && !principal.getName().equals(book.getUploader()))) {
            return new ModelAndView(new RedirectView("/book/list", true));
        }
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("book", book);
        Form bookForm = new Form();
        bookForm.setBookName(book.getBookName());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setUploader(book.getUploader());
        bookForm.setPrice(book.getPrice());
        bookForm.setDescription(book.getDescription());
        bookForm.setState(book.getState());
        modelAndView.addObject("bookForm", bookForm);
        return modelAndView;
    }
    @PostMapping("/edit/{bookId}")
    public String edit(@PathVariable("bookId") long bookId, Form form,
                       Principal principal, HttpServletRequest request)
            throws IOException, BookNotFound {
        Book book = bService.getBook(bookId);
        if (book == null
                || (!request.isUserInRole("ROLE_ADMIN")
                && !principal.getName().equals(book.getUploader()))) {
            return "redirect:/book/list";
        }
        bService.updateBook(bookId, form.getBookName(), form.getAuthor(), form.getUploader(), form.getPrice(),
                form.getDescription(), form.getState(), form.getAttachments());
        return "redirect:/book/view/" + bookId;
    }


    @ExceptionHandler({BookNotFound.class, AttachmentNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }

}
