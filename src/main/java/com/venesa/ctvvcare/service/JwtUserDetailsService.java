package com.venesa.ctvvcare.service;

import com.venesa.ctvvcare.dto.UserDTO;
import com.venesa.ctvvcare.entity.User;
import com.venesa.ctvvcare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        List<GrantedAuthority> lst = new ArrayList<>();
        if (user.getRole() != null)
            lst.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), lst);
    }


    public User save(User user) throws Exception {
        User checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser != null) {
            throw new Exception("Username is exists !!");
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<String> getRoleById(int id) {
        return userRepository.getRoleById(id);
    }
//	public Date getTimeToken(String username) throws UsernameNotFoundException {
//		User user = getUserByUsername(username);
//		System.out.println("====user.getTimeToken()====" + user.getTimeToken());
//		return user.getTimeToken();
//	}

    private User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    public void updateTimeTokenByUsername(String username, Date date) throws Exception {
        Integer id = userRepository.updateTimeTokenByUsername(username, date);
        if (id < 0) throw new Exception("loi j a");
    }
}	
