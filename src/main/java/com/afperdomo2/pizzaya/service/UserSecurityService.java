package com.afperdomo2.pizzaya.service;

import com.afperdomo2.pizzaya.persistence.entity.UserEntity;
import com.afperdomo2.pizzaya.persistence.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        System.out.println(user.toString());

        String[] roles = user.getRoles().stream()
                .map(r -> r.getId().getRole())
                .toArray(String[]::new);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(this.grantedAuthorities(roles))
                .accountLocked(user.getIsLocked())
                .disabled(!user.getIsActive())
                .build();
    }

    /**
     * Convierte un arreglo de roles en una lista de autoridades concedidas.
     * Para cada rol, agrega la autoridad "ROLE_" + rol y las autoridades adicionales
     * obtenidas del método getAuthoritiesFromRole.
     *
     * @param roles arreglo de cadenas con los roles del usuario
     * @return lista de GrantedAuthority con las autoridades del usuario
     */
    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority : getAuthoritiesFromRole(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }

    /**
     * Obtiene las autoridades asociadas a un rol específico.
     * Para los roles "ADMIN" o "CUSTOMER", devuelve "random_order".
     * Para otros roles, devuelve un arreglo vacío.
     *
     * @param role el rol del usuario
     * @return un arreglo de cadenas con las autoridades
     */
    private String[] getAuthoritiesFromRole(String role) {
        // Cualquiera de estos roles puede pedir una orden aleatoria
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
            return new String[]{"RANDOM_ORDER"};
        }
        return new String[]{};
    }
}
