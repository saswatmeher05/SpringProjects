package in.nareshit.somu.service;

import java.util.List;
import java.util.Optional;

import in.nareshit.somu.model.Document;

public interface IDocumentService {

	public void saveDocument(Document document);
	public List<Object[]> getDocIdAndNames();
	public Optional<Document> getDocumentById(Integer id);
	
}
