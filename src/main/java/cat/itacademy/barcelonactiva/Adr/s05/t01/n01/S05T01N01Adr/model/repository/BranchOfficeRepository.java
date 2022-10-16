package cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.repository;

import cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.domain.BranchOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchOfficeRepository extends JpaRepository<BranchOffice, Integer> {

}
