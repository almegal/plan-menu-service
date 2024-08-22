package ru.aston.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.entity.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByChatId(Long id);
}
