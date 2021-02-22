package in.nareshit.somu.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nareshit.somu.model.ShipmentType;
import in.nareshit.somu.util.AppUtil;

public class ShipmentTypeExcelOneView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {
		//change filename for download purposes
		response.addHeader("Content-Disposition", "attachment;filename=Shipment"+AppUtil.getCurrentDateAndTime()+".xlsx");
		
		//read data from model
		ShipmentType st=(ShipmentType) model.get("st");
		// create new sheet
		Sheet sheet = workbook.createSheet("Shipment Type");
		// create row
		createRows(sheet,0,"ID",st.getId().toString());
		createRows(sheet,1,"MODE",st.getShipmentMode());
		createRows(sheet,2,"CODE",st.getShipmentCode());
		createRows(sheet,3,"ENABLED",st.getEnableShipment());
		createRows(sheet,4,"GRADE",st.getShipmentGrade());
		createRows(sheet,5,"DESCRIPTION",st.getShipmentDescription());
		
		createRows(sheet,9,"Generated date and time",AppUtil.getCurrentDateAndTime());
		createRows(sheet,10,"Module Name","Shipment Type");
		
		
		
	}

	private void createRows(Sheet sheet, int rowNum, String label, String value) {
		Row row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue(label);
		row.createCell(1).setCellValue(value);

	}
	
	
}
