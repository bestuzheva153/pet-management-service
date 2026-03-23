package example.repository;

import example.model.Cat;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CatRepository extends JpaRepository<Cat, Long> {
    Page<Cat> findByColor(String color, Pageable pageable);
}
