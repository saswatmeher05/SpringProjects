package in.nareshit.somu.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="order_method_tab")
public class OrderMethod {
	
	@Id
	@GeneratedValue(generator="om_seq")
	@SequenceGenerator(name="om_seq",sequenceName="om_seq_test")
	@Column(name="ord_id_col")
	private Integer id;
	@Column(name="ord_mode_col")
	private String orderMode;
	@Column(name="ord_code_col")
	private String orderCode;
	@Column(name="ord_type_col")
	private String orderType;
	
	@ElementCollection
	@CollectionTable(name="order_acpt_tab",
	joinColumns = @JoinColumn(name="ord_id_col")
	)
	@Column(name="ord_acpt_tab")
	private Set<String> orderAcpt;
	
	@Column(name="ord_desc_col")
	private String orderDesc;
}
