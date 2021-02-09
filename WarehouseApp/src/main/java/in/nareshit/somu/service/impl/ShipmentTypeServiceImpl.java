package in.nareshit.somu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.somu.model.ShipmentType;
import in.nareshit.somu.repo.ShipmentTypeRepo;
import in.nareshit.somu.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService{
	
	@Autowired
	private ShipmentTypeRepo repo;
	@Override
	public Integer saveShipmentType(ShipmentType st) {
		st=repo.save(st);
		Integer id=st.getId();
		return id;
	}
	@Override
	public List<ShipmentType> getAllShipmentTypes() {
		List<ShipmentType> list=repo.findAll(); 
		return list;
	}
	
	@Override
	public void deleteShipmentType(Integer id) {
		repo.deleteById(id);
	}
	
	@Override
	public boolean isShipmentTypeExist(Integer id) {
		return repo.existsById(id);
	}
	
	@Override
	public Optional<ShipmentType> getOneShipmentType(Integer id) {
		Optional<ShipmentType> opt=repo.findById(id);
		return opt;
	}
	
	@Override
	public void updateShipmentType(ShipmentType st) {
		repo.save(st);   //save method internally saves or updates
		
	}
	
}
