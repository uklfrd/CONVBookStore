package hkmu.comps380f.convbookstore.dao;

import hkmu.comps380f.convbookstore.model.StoreUser;
import hkmu.comps380f.convbookstore.model.UserRole;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreUserService implements UserDetailsService {
    @Resource
    StoreUserRepository storeUserRepo;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        StoreUser storeUser = storeUserRepo.findById(username).orElse(null);
        if (storeUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : storeUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new User(storeUser.getUsername(), storeUser.getPassword(), authorities);
    }
}
