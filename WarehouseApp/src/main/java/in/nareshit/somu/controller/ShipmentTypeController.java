package in.nareshit.somu.controller;

import java.util.List;
import java.util.Optional;

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
import in.nareshit.somu.view.ShipmentTypeExcelView;

@Controller
@RequestMapping("/st")
public class ShipmentTypeController {
	
	@Autowired
	IShipmentTypeService service;
	
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
	public ModelAndView excelExport() {
		//create ModelAndView object
		ModelAndView m=new ModelAndView();
		
		//Set View(I) implementation class object
		m.setView(new ShipmentTypeExcelView());
		
		//add data to ModelAndView
		
		return m;
	}
	
}
