package ru.ifmo.mpi.magichospital.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.rkpunjal.sqlsafe.SqlSafeUtil;

import ru.ifmo.mpi.magichospital.admin.domain.dao.LoginInfo;
import ru.ifmo.mpi.magichospital.admin.domain.repository.LoginInfoRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
	@Autowired
    private LoginInfoRepository loginInfoRepository;
    
	@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		if (!SqlSafeUtil.isSqlInjectionSafe(userName)) {
            throw new UsernameNotFoundException("Possible sql injection attack!");
        }
		
        Optional<LoginInfo> optionalDbUser = loginInfoRepository.findByLogin(userName);
        
        if (optionalDbUser.isPresent()) {
        	LoginInfo dbUser = optionalDbUser.get();
            UserDetails user = User.builder()
                    .username(dbUser.getLogin())
                    .password(dbUser.getPassword())
                    .roles(dbUser.getRole().getCode())
                    .build();
            
            if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            	return user;
            } else {
            	return null;
            }
            
        } else {
        		throw new UsernameNotFoundException("Username: "+userName);
        }
    }
}
