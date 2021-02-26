package in.nareshit.somu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.somu.model.Document;
import in.nareshit.somu.repo.DocumentRepository;
import in.nareshit.somu.service.IDocumentService;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentRepository repo;

	@Override
	public void saveDocument(Document document) {
		repo.save(document);

	}

	@Override
	public List<Object[]> getDocIdAndNames() {

		return repo.getDocIdAndNames();
	}

	@Override
	public Optional<Document> getDocumentById(Integer id) {

		return repo.findById(id);
	}

}
