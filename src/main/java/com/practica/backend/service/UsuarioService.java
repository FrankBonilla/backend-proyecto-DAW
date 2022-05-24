package com.practica.backend.service;

import com.practica.backend.entities.Usuario;
import com.practica.backend.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario  = usuarioRepository.findByUserName(username);

        if(usuario == null){
            logger.error("Error en el loging: no existe el usuario: "+username+" en la base de datos.");
            throw new UsernameNotFoundException("Error en el loging: no existe el usuario: "+username+" en la base de datos.");
        }
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNombreRole()))
                .collect(Collectors.toList());

        return new User(usuario.getUserName(), usuario.getPassword(), true, true, true, true, authorities);
    }
}
