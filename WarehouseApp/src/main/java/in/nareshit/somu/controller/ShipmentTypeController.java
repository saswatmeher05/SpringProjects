package in.nareshit.somu.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.nareshit.somu.model.ShipmentType;
import in.nareshit.somu.service.IShipmentTypeService;
import in.nareshit.somu.util.ShipmentTypeUtil;
import in.nareshit.somu.view.ShipmentTypeExcelOneView;
import in.nareshit.somu.view.ShipmentTypeExcelView;
import in.nareshit.somu.view.ShipmentTypePdfView;

@Controller
@RequestMapping("/st")
public class ShipmentTypeController {
	
	@Autowired
	private IShipmentTypeService service;
	
	@Autowired
	private ShipmentTypeUtil util;
	
	@Autowired
	private ServletContext sc;
	
	//1.Show register page
	@GetMapping("/register")
	public String showReg() {
		return "ShipmentTypeRegister";
	}
	
	//2. on click submit button read form data
	@PostMapping("/save")
	public String saveShipmentType(@ModelAttribute ShipmentType shipmentType,Model model) {
		//call service
		Integer id=service.saveShipmentType(shipmentType);
		//create message 
		String message="Shipment Type "+id+" Saved";
		//send message to ui
		model.addAttribute("message", message);
		//go back to same page
		return "ShipmentTypeRegister";
	}
	
	//3. Display all rows
	@GetMapping("/all")
	public String showAllShipmentTypes(Model model) {
		//call service
		List<ShipmentType> list = service.getAllShipmentTypes();
		//send data to ui
		model.addAttribute("list",list);
		//go back to ui;
		return "ShipmentTypeData";
	}
	
	//4. Delete by id
	@GetMapping("/delete")
	public String deleteShipmentType(
			@RequestParam("id")Integer sid, Model model
			) 
	{
		if(service.isShipmentTypeExist(sid)) {
		//call service
		service.deleteShipmentType(sid);
		//create message
		String message=new StringBuffer() .append("Shipment Type '")
				.append(sid) .append("' Deleted").toString();
		
		//send data to ui
		model.addAttribute("message",message);
		} else {
			model.addAttribute("message",sid+" Not Found!");
		}
		//latest data
		model.addAttribute("list", service.getAllShipmentTypes());
		return "ShipmentTypeData";
	}
	
	//5. show edit page
	@GetMapping("/edit")
	public String showShipmentTypeEdit(
			@RequestParam("id")Integer sid,
			Model model
			){
		String page="null";
		//call service
		Optional<ShipmentType> opt=service.getOneShipmentType(sid);
		if(opt.isPresent()) {
			model.addAttribute("shipmentType",opt.get());
			page="ShipmentTypeEdit";
		}else {
			page="redirect:all";
		}
		return page;
	}
	
	//6. update ShipmentType
	@PostMapping("/update")
	public String doUpdateShipmentType(
			@ModelAttribute ShipmentType shipmentType,Model model 
			) {
		//call service to update 
		service.saveShipmentType(shipmentType);
		//send message to ui
		model.addAttribute("message","ShipmentType '"+shipmentType.getId() +"' Updated");
		//call service layer for latest data
		List<ShipmentType> list=service.getAllShipmentTypes();
		//send data to ui
		model.addAttribute("list", list);
		//go back to ui page
		return "ShipmentTypeData";
	}
	
	//7.AJAX Validation
	
	//8.Excel Export
	@GetMapping("/excel")
	public ModelAndView showExcelExport() {
		//fetch data(all rows) from database
		List<ShipmentType> list=service.getAllShipmentTypes();
		
		//create ModelAndView object
		ModelAndView m=new ModelAndView();
		m.addObject("list", list);
		m.setView(new ShipmentTypeExcelView());
		
		//return model and view
		return m;
	}
	
	//9.Excel Export One View
		@GetMapping("/excelone")
		public ModelAndView showExcelOneExport(
				@RequestParam("id")Integer sid
				) {
			//fetch data from database
			Optional<ShipmentType> opt=service.getOneShipmentType(sid);
			
			//create ModelAndView object
			ModelAndView m=new ModelAndView();
			m.addObject("st", opt.get());
			m.setView(new ShipmentTypeExcelOneView());
			
			//return model and view
			return m;
		}
	
	//10.pdf export
		@GetMapping("/pdf")
		public ModelAndView exportToPdf() {
			//fetch data from database
			List<ShipmentType> list=service.getAllShipmentTypes();
			
			//create ModelAndView
			ModelAndView m=new ModelAndView();
			m.addObject("list",list);
			m.setView(new ShipmentTypePdfView());
			return m;
		}
	
	//11. Charts generation
		@GetMapping("/charts")
		public String showCharts() {
		// call service for data
		List<Object[]> list =
		service.getShipmentTypeModeCount();
		// dynamic path inside server(runtime location)
		String path = sc.getRealPath("/"); //root location
		System.out.println("Runtime location=>" + path);
		
		// call util method for generation
		util.generatePieChart(path, list);
		util.generateBarChart(path, list);
		
		return "ShipmentTypeCharts.html";
		}
		
		
}
