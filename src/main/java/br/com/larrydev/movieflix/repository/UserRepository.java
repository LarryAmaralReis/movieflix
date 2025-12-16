package br.com.larrydev.movieflix.repository;

import br.com.larrydev.movieflix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
