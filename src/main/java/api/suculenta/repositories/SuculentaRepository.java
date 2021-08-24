package api.suculenta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.suculenta.models.Suculenta;

@Repository
public interface SuculentaRepository extends JpaRepository<Suculenta, Long> {

	public List<Suculenta> findByNameContainingIgnoreCase(String name);
}
