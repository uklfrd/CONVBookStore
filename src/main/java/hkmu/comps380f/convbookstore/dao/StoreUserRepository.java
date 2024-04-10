package hkmu.comps380f.convbookstore.dao;

import hkmu.comps380f.convbookstore.model.StoreUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreUserRepository extends JpaRepository<StoreUser, String> {
}