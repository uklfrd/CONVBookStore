package hkmu.comps380f.convbookstore.dao;

import hkmu.comps380f.convbookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
