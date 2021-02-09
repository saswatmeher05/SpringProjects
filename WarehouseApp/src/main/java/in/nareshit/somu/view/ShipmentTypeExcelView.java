package in.nareshit.somu.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class ShipmentTypeExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response
			) 
					throws Exception 
	{
		//Create sheet using workbook
		Sheet sheet=workbook.createSheet("SHIPMENTS");
		
		setHead(sheet);
	}

	private void setHead(Sheet sheet) {
		/* Not a predefined method
		 * Method to create row heading in excel file */
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(0).setCellValue("MODE");
		row.createCell(0).setCellValue("CODE");
		row.createCell(0).setCellValue("ENABLED");
		row.createCell(0).setCellValue("GRADE");
		row.createCell(0).setCellValue("DESCRIPTION");
	}

}
