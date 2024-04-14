package hkmu.comps380f.convbookstore.dao;

import hkmu.comps380f.convbookstore.exception.BookNotFound;
import hkmu.comps380f.convbookstore.model.Attachment;
import hkmu.comps380f.convbookstore.model.Book;
import hkmu.comps380f.convbookstore.model.StoreUser;
import jakarta.annotation.Resource;
import org.apache.catalina.Store;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserManagementService {
@Resource
private StoreUserRepository suRepo;

@Transactional
public List<StoreUser> getStoreUser() {
    return suRepo.findAll();
}

    @Transactional
    public StoreUser getStoreUser(String username)
            throws UsernameNotFoundException {
        StoreUser storeUser = suRepo.findById(username).orElse(null);
        if (username == null) {
            throw new UsernameNotFoundException(username);
        }
        return storeUser;
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
    public void createStoreUser(String username, String password, String fullname, String email, String address,String[] roles) {
        StoreUser user = new StoreUser(username, password, fullname, email, address ,roles);
        suRepo.save(user);
    }

    @Transactional(rollbackFor = UsernameNotFoundException.class)
    public void updateUser(String username,String password,String fullname,
                           String email, String address) {
        StoreUser updateUser = suRepo.findById(username).orElse(null);
        if (updateUser == null) {
            throw new UsernameNotFoundException(username);
        }
        updateUser.setUsername(username);
        updateUser.setPassword(password);
        updateUser.setFullname(fullname);
        updateUser.setEmail(email);
        updateUser.setAddress(address);
        suRepo.save(updateUser);
    }

}
