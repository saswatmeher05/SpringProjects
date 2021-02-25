package in.nareshit.somu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.somu.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
