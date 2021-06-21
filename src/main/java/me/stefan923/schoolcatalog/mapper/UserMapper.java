package me.stefan923.schoolcatalog.mapper;

import me.stefan923.schoolcatalog.domain.Role;
import me.stefan923.schoolcatalog.domain.RoleType;
import me.stefan923.schoolcatalog.domain.User;
import me.stefan923.schoolcatalog.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roles", target = "roles", qualifiedByName = "RoleToString")
    UserDTO toDto(User user);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "StringToRole")
    User fromDto(UserDTO user);

    @Named("StringToRole")
    static Set<Role> mapStringToRole(Set<String> value) {
        return value.stream()
                .map(roleType -> {
                    Role role = new Role();
                    role.setType(RoleType.valueOf(roleType));
                    return role;
                }).collect(Collectors.toSet());
    }

    @Named("RoleToString")
    static Set<String> mapRoleToString(Set<Role> value) {
        new Role();
        return value.stream()
                .map(role -> role.getType().toString())
                .collect(Collectors.toSet());
    }

}
