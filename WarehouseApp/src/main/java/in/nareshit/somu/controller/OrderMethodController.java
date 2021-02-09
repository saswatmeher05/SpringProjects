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
import org.springframework.web.bind.annotation.ResponseBody;

import in.nareshit.somu.model.OrderMethod;
import in.nareshit.somu.service.IOrderMethodService;

@Controller
@RequestMapping("/om")
public class OrderMethodController {
	@Autowired
	private IOrderMethodService service;
	
	//1. Show Registration Page
	@GetMapping("/register")
	public String showOrderMethodReg() {
		return "OrderMethodRegister";
	}
	//2. Save Data
	@PostMapping("/save")
	public String saveOrderMethod(
			@ModelAttribute OrderMethod orderMethod,
			Model model
			) 
	{
		//call service
		Integer id=service.saveOrderMethod(orderMethod);
		//send message to ui using model data
		model.addAttribute("message", "Order Method '"+id+"' Saved");
		
		return "OrderMethodRegister";
	}
	
	//3. Data Display
	@GetMapping("/all")
	public String showAllOrderMethods(Model model) {
		//call service
		List<OrderMethod> list=service.getAllOrderMethods();
		model.addAttribute("list",list);
		return "OrderMethodData";
	}
	
	
	//4. Delete Operation
	@GetMapping("/delete")
	public String doDeleteOrderMethod(
			@RequestParam("id")Integer omId, 
			Model model
			) {
		String message=null;
		//use service
		if(service.isOrderMethodExist(omId)) {
			service.deleteOrderMethod(omId);
			message="Order Method '"+omId+"' Deleted";
		}else {
			message="Order Method '"+omId+"' dont Exist";
		}
		//call service
				List<OrderMethod> list=service.getAllOrderMethods();
				model.addAttribute("message",message);
				model.addAttribute("list", list);
				return "OrderMethodData";
	}
	//5. Show Edit
	@GetMapping("/edit")
	public String showOrderMethodEdit(
			@RequestParam("id")Integer oid,
			Model model
			) 
	{
		String page="null";
		Optional<OrderMethod> opt=service.getOneOrderMethod(oid);
		if(opt.isPresent()) {
			model.addAttribute("orderMethod", opt.get());
			page="OrderMethodEdit";
		}else {
			page="redirect:all";
		}
		
		return page;
	}
	
	
	//6. Do Update
	@PostMapping("/update")
	public String doOrderMethodUpdate(
			@ModelAttribute OrderMethod orderMethod,
			Model model
			)
	{
		service.updateOrderMethod(orderMethod);
		
		List<OrderMethod> list=service.getAllOrderMethods();
		model.addAttribute("message", "Order Method '"+orderMethod.getId()+"' Updated");
		model.addAttribute("list", list);
		
		return "OrderMethodData";
	}
	
	//Ajax validation
	@GetMapping("/validateCode")
	public @ResponseBody String validateOrderCode(
			@RequestParam("code") String orderCode 
			) 
	{
		String message="";
		if(service.isOrderMethodExistByCode(orderCode)) {
			message="Order Method '"+orderCode+"' Exists! Try Different One";
		}
		return message;
	}
}
