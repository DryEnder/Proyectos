package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.UsuarioEntity;
import mx.unam.aragon.repo.UsuarioRepository;
import mx.unam.aragon.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getEmail()) // o usuario.getNombre()
                .password(usuario.getPassword())
                .roles(usuario.getRol().getNombre().toUpperCase()) // "ADMIN", "CAJERO"
                .build();}
//
//    @Override
//    public UsuarioEntity save(UsuarioEntity usuarioEntity) {
//        return usuarioRepository.save(usuarioEntity);
//    }
//
//    @Override
//    public List<UsuarioEntity> findAll() {
//        return usuarioRepository.findAll();
//    }
//
//    @Override
//    public void deleteById(long id) {
//        usuarioRepository.deleteById(id);
//    }
//

}
