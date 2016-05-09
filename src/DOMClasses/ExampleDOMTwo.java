package DOMClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="ExampleDOMTwo")
public class ExampleDOMTwo {

	@Id
    @Column(name = "objId", unique = true, nullable = false)
    public java.lang.Long objId;
	
	@Column(name = "name")
	public String name;
	
	public ExampleDOMTwo() {
	}
	public ExampleDOMTwo(java.lang.Long objId) {
		this.objId = objId;
	}
	
	public void setObjId(java.lang.Long objId) {
		this.objId = objId;
	}
	public java.lang.Long getObjId() {
		return objId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
