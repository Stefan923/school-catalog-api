package me.stefan923.schoolcatalog.service;

import me.stefan923.schoolcatalog.domain.Role;
import me.stefan923.schoolcatalog.domain.RoleType;
import me.stefan923.schoolcatalog.domain.User;
import me.stefan923.schoolcatalog.dto.UserDTO;
import me.stefan923.schoolcatalog.dto.UserDetailsImpl;
import me.stefan923.schoolcatalog.mapper.UserMapper;
import me.stefan923.schoolcatalog.repository.RoleRepository;
import me.stefan923.schoolcatalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    private Set<Role> mapRoles(Set<String> roles){
        return roles.stream()
                .map(role -> roleRepository.findByTypeEquals(RoleType.valueOf(role))
                        .orElseThrow(NoSuchElementException::new))
                .collect(Collectors.toSet());
    }

    private void verifyDataUnique(User actUser, UserDTO user) {
        if (!actUser.getUsername().equals(user.getUsername()) && userRepository.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if (!actUser.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
    }

    private void getPasswordFromDB(User user) {
        User DBUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Unable to find user"));
        user.setPassword(DBUser.getPassword());
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }


    public UserDTO createUser(UserDTO user) {
        User actUser = userMapper.fromDto(user);
        actUser.setRoles(mapRoles(user.getRoles()));
        if (userRepository.existsByUsername(actUser.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        if (userRepository.existsByEmail(actUser.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        actUser.setPassword(encoder.encode(actUser.getPassword()));
        return userMapper.toDto(userRepository.save(actUser));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public UserDTO editUser(int id, UserDTO user) {
        User actUser = userRepository.findById(id).orElseThrow(()->new EntityExistsException("User not found"));
        actUser.setRoles(mapRoles(user.getRoles()));
        verifyDataUnique(actUser,user);
        actUser.setEmail(user.getEmail());
        actUser.setUsername(user.getUsername());
        if (!user.getPassword().equals("")) {
            actUser.setPassword(encoder.encode(user.getPassword()));
        }
        return userMapper.toDto(userRepository.save(actUser));
    }

    @Autowired
    private void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
