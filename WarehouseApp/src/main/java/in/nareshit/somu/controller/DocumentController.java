package in.nareshit.somu.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.somu.model.Document;
import in.nareshit.somu.service.IDocumentService;

@Controller
@RequestMapping("/documents")
public class DocumentController {
	@Autowired
	private IDocumentService service;
	
	//1.show documents screen
	@GetMapping("/show")
	public String showDocs() {
		//logic to fetch uploaded document details
		
		
		return "Documents";
	}
	
	//2.on click upload button\
	@PostMapping("/upload")
	public String uploadDoc(
			@RequestParam("docId")Integer docId, 
			@RequestParam("docOb")MultipartFile file
			) {
		if(file!=null) {
			//Create Document object
			Document document=new Document();
			//1. set docId
			document.setDocId(docId);
			
			//2. set docName using getOriginalFileName()
			document.setDocName(file.getOriginalFilename());
			
			//1. set value/data using getBytes() which returns byte[]
			try {
				document.setDocData(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.saveDocument(document);
		}
		
		return "redirect:show";
	}
}
