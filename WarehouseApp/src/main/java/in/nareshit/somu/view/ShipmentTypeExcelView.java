package in.nareshit.somu.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nareshit.somu.model.ShipmentType;
import in.nareshit.somu.util.AppUtil;

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
		//modify fileName using content-disposition
		response.setHeader("Content-Disposition", "attachment;filename=SHIPMENTS-"+AppUtil.getCurrentDateAndTime()+".xlsx");
		
		//read data from ModelAndView Memory
		@SuppressWarnings("unchecked")
		List<ShipmentType> list=(List<ShipmentType>)model.get("list");
		
		//Create sheet using workbook
		Sheet sheet=workbook.createSheet("SHIPMENTS");
		
		setHead(sheet);
		setBody(sheet, list);
	}

	private void setHead(Sheet sheet) {
		/* Not a predefined method
		 * Method to create row heading in excel file */
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("ENABLED");
		row.createCell(4).setCellValue("GRADE");
		row.createCell(5).setCellValue("DESCRIPTION");
	}

	private void setBody(Sheet sheet,List<ShipmentType> list) {
		//Custom method to create body of excel sheet having data
		int rowNum=1;
		for(ShipmentType st:list) {
			//create row using sheet
			Row row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(st.getId());
			row.createCell(1).setCellValue(st.getShipmentMode());
			row.createCell(2).setCellValue(st.getShipmentCode());
			row.createCell(3).setCellValue(st.getEnableShipment());
			row.createCell(4).setCellValue(st.getShipmentGrade());
			row.createCell(5).setCellValue(st.getShipmentDescription());
			
		}
	}
	
}
