package hkmu.comps380f.convbookstore.dao;

import hkmu.comps380f.convbookstore.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
