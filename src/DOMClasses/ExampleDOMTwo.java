package DOMClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="ExampleDomTwo")
public class ExampleDOMTwo {

	@Id
    @Column(name = "objId", unique = true, nullable = false)
    public long objId;
	public String name;
	
	public ExampleDOMTwo(long objId, String name) {
		this.objId = objId;
		this.name = name;
	}
	
	public void setObjId(long objId) {
		this.objId = objId;
	}
	public long getObjId() {
		return objId;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
}
