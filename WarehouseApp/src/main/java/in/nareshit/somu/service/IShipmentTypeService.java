package in.nareshit.somu.service;

import java.util.List;
import java.util.Optional;

import in.nareshit.somu.model.ShipmentType;

public interface IShipmentTypeService {
	Integer saveShipmentType(ShipmentType st);
	List<ShipmentType> getAllShipmentTypes();
	void deleteShipmentType(Integer id);
	boolean isShipmentTypeExist(Integer id);
	Optional<ShipmentType> getOneShipmentType(Integer id);
	void  updateShipmentType(ShipmentType st);
}
