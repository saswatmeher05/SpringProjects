package in.nareshit.somu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.somu.model.ShipmentType;

public interface ShipmentTypeRepo extends JpaRepository<ShipmentType, Integer> {
	
}
