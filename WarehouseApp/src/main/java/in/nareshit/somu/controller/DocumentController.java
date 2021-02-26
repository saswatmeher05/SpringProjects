package in.nareshit.somu.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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

	// 1.show documents screen
	@GetMapping("/show")
	public String showDocs(Model model) {
		List<Object[]> list = service.getDocIdAndNames();
		model.addAttribute("list", list);
		return "Documents";
	}

	// 2.on click upload button\
	@PostMapping("/upload")
	public String uploadDoc(
			@RequestParam("docId") Integer docId, 
			@RequestParam("docOb") MultipartFile file) {
		if (file != null) {
			// Create Document object
			Document document = new Document();
			// 1. set docId
			document.setDocId(docId);

			// 2. set docName using getOriginalFileName()
			document.setDocName(file.getOriginalFilename());

			// 1. set value/data using getBytes() which returns byte[]
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

	// 3.Download
	@GetMapping("/download")
	public void downloadDoc(@RequestParam("id") Integer docId, HttpServletResponse response) {
		Optional<Document> opt = service.getDocumentById(docId);
		if (opt.isPresent()) {
			// read object
			Document doc = opt.get();
			// add head section
			response.addHeader("Content-Disposition", "attachment;filename=" + doc.getDocName());
			// copy data to output stream from docData
			try {
				FileCopyUtils.copy(doc.getDocData(), response.getOutputStream()); // copy from , to
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}
