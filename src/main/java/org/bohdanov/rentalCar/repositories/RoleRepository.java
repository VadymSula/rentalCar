package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
}
