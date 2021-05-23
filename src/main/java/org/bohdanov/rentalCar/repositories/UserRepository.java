package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.roles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE t_user_roles SET roles_id = 3 WHERE user_id_user = :idUser",
            nativeQuery = true)
    void setRoleAdminForUser(@Param("idUser") Long idUser);
}
