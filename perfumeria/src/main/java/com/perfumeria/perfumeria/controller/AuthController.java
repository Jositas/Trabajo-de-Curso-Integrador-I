package com.perfumeria.perfumeria.controller;

import com.perfumeria.perfumeria.entity.Usuario;
import com.perfumeria.perfumeria.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/") // Asegúrate de que esta ruta exista si es tu página de inicio principal
    public String mostrarIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                 @RequestParam String contraseña,
                                 Model model,
                                 HttpSession session) {

        Optional<Usuario> usuarioOpt = usuarioService.autenticar(correo, contraseña);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            session.setAttribute("usuario", usuario);
            String rol = usuario.getRol().getNombre();
            if ("Cliente".equalsIgnoreCase(rol)) {
                return "redirect:/cliente/inicio";
            } else {
                return "redirect:/admin/dashboard";
            }
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/cliente/inicio")
    public String inicioCliente() {
        return "cliente/inicio";
    }

    @GetMapping("/admin/dashboard")
    public String dashboardAdmin() {
        return "admin/dashboard";
    }

    @GetMapping("/productos")
    public String mostrarProductos() {
        return "productos";
    }
}
