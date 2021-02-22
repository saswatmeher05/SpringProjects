package in.nareshit.somu.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nareshit.somu.model.ShipmentType;
import in.nareshit.somu.util.AppUtil;

public class ShipmentTypePdfView extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition",
				"attachment;filename=Shipments-" + AppUtil.getCurrentDateAndTime() + ".pdf");
		// font design
		Font f1 = new Font(Font.COURIER, 30, Font.BOLD, Color.RED);
		Font f2 = new Font(Font.COURIER, 8, Font.ITALIC, Color.GREEN);
		// create element
		Paragraph title = new Paragraph("SHIPMENT TYPES", f1);
		title.setAlignment(Element.ALIGN_CENTER);
		// add elements to document
		document.add(title);

		// read data from database
		@SuppressWarnings("unchecked")
		List<ShipmentType> list = (List<ShipmentType>) model.get("list");

		// create table
		PdfPTable table = new PdfPTable(6);
		table.setSpacingBefore(8.0f);
		table.setSpacingAfter(30.0f);
		table.setWidths(new float[] {0.4f,0.6f,1.2f,0.9f,0.7f,1.3f});
		
		
		setHead(table);
		setBody(table, list);

		document.add(table);
		
		document.add(new Paragraph("Auto-generated on: "+AppUtil.getCurrentDateAndTime().toString(),f2));

	}

	private void setHead(PdfPTable table) {
		Font font = new Font(Font.COURIER, 15, Font.BOLD, Color.BLUE);
		table.addCell(new Phrase("ID", font));
		table.addCell(new Phrase("MODE", font));
		table.addCell(new Phrase("CODE", font));
		table.addCell(new Phrase("ENABLED", font));
		table.addCell(new Phrase("GRADE", font));
		table.addCell(new Phrase("DESCRIPTION", font));
	}

	private void setBody(PdfPTable table, List<ShipmentType> list) {
		Font font = new Font(Font.COURIER, 15, Font.BOLD, Color.BLACK);
		for (ShipmentType st : list) {
			table.addCell(new Phrase(st.getId().toString(), font));
			table.addCell(new Phrase(st.getShipmentMode().toString(), font));
			table.addCell(new Phrase(st.getShipmentCode().toString(), font));
			table.addCell(new Phrase(st.getEnableShipment().toString(), font));
			table.addCell(new Phrase(st.getShipmentGrade().toString(), font));
			table.addCell(new Phrase(st.getShipmentDescription().toString(), font));

		}
	}

}
