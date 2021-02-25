package in.nareshit.somu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="doc_tab")
public class Document {
	//define docId,docName and docData private variables
	
	@Id
	@Column(name="doc_id_col")
	private Integer docId;
	
	@Column(name="doc_name_col")
	private String docName;
	
	@Column(name="doc_data_col")
	@Lob 	//byte[] + @Lob act as BLOB=binary large object
	private byte[] docData;

}
