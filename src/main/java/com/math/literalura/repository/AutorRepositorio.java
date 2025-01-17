package com.math.literalura.repository;

import com.math.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    List<Autor> findByNacimientoGreaterThanEqual(int nacimientoMinimo);
}
