package in.nareshit.somu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.somu.model.OrderMethod;
import in.nareshit.somu.repo.OrderMethodRepository;
import in.nareshit.somu.service.IOrderMethodService;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService{
	@Autowired
	private OrderMethodRepository repo;
	
	@Override
	public Integer saveOrderMethod(OrderMethod om) {
		om=repo.save(om);
		Integer id=om.getId();
		return id;
	}
	
	@Override
	public List<OrderMethod> getAllOrderMethods() {
		List<OrderMethod> list=repo.findAll();
		return list;
	}
	
	@Override
	public void deleteOrderMethod(Integer id) {
		repo.deleteById(id);
		
	}
	
	@Override
	public boolean isOrderMethodExist(Integer id) {
		return repo.existsById(id);
	}
	
	@Override
	public Optional<OrderMethod> getOneOrderMethod(Integer id) {
		Optional<OrderMethod> opt=repo.findById(id);
		return opt;
	}
	
	@Override
	public void updateOrderMethod(OrderMethod om) {
		//update table using repo.save(-) method
		// repo.save(-) method either saves or updates the table data
		repo.save(om);
	}
	
	@Override
	public boolean isOrderMethodExistByCode(String orderCode) {
		/*Integer count = repo.getOrderMethodCountByCode(orderCode);
		boolean exist = count>0? true:false;
		return exist;*/
		return  repo.getOrderMethodCountByCode(orderCode) > 0;
		
	}
	
}
