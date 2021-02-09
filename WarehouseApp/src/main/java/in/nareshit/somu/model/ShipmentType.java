package in.nareshit.somu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="shipment_type_tab")
public class ShipmentType {
	
	
	@Id
	@Column(name="ship_id_col")
	@GeneratedValue(generator="ship_type_seq")
	@SequenceGenerator(name="ship_type_seq",sequenceName="ship_type_test")
	private Integer id;
	
	@Column(name="ship_mode_col")
	private String shipmentMode;
	
	@Column(name="ship_code_col")
	private String shipmentCode;
	
	@Column(name="ship_enbl_col")
	private String enableShipment;
	
	@Column(name="ship_grde_col")
	private String shipmentGrade;
	
	@Column(name="ship_desc_col")
	private String shipmentDescription;
	
}
