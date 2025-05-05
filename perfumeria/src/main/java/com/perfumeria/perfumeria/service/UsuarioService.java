package com.perfumeria.perfumeria.service;

import com.perfumeria.perfumeria.entity.Usuario;
import com.perfumeria.perfumeria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> autenticar(String correo, String contraseña) {
        return usuarioRepository.findByCorreo(correo)
                .filter(usuario -> usuario.getContraseña().equals(contraseña));
    }
}
