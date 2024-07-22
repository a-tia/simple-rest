package rest.api.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rest.api.task.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Modifying // indication that the query is an update/delete operation
    @Transactional // ensures that the update operation is executed within a transaction
    @Query("UPDATE AppUser u SET u.gender = :gender WHERE u.id = :id") // the JPQL update query
    void updateUserGender(@Param("id") long id, @Param("gender") String gender);

    @Modifying
    @Transactional
    @Query("UPDATE AppUser u SET u.age = :age WHERE u.id = :id")
    void updateUserAge(@Param("id") long id, @Param("age") Integer age);

}