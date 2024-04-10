package hkmu.comps380f.convbookstore.dao;

import hkmu.comps380f.convbookstore.model.StoreUser;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserManagementService {
    @Resource
    private StoreUserRepository suRepo;

    @Transactional
    public List<StoreUser> getStoreUsers() {
        return suRepo.findAll();
    }

    @Transactional
    public void delete(String username) {
        StoreUser storeUser = suRepo.findById(username).orElse(null);
        if (storeUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        suRepo.delete(storeUser);
    }

    @Transactional
    public void createStoreUser(String username, String password, String[] roles) {
        StoreUser user = new StoreUser(username, password, roles);
        suRepo.save(user);
    }
}
