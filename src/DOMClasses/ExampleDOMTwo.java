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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
	public int id;
	
	public long objId;
	public String name;
	
	public ExampleDOMTwo(long objId, String name) {
		this.objId = objId;
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setObjId(long objId) {
		this.objId = objId;
	}
	@Column(name = "objId")
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
