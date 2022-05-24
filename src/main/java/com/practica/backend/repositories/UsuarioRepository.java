package com.practica.backend.repositories;

import com.practica.backend.entities.Usuario;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

     Usuario findByUserName(String username);
}
