package com.example.securenotes.repository;
import com.example.securenotes.model.Notes;
import com.example.securenotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {
    List<Notes> findAllByUser(User user);
    Optional<Notes> findByIdAndUser(Long id, User user);
}