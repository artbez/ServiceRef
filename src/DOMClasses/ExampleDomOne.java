package DOMClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="ExampleDomOne")
public class ExampleDomOne extends ExampleDOMTwo {

	@Id
    @Column(name = "objId", unique = true, nullable = false)
    public java.lang.Long objId;
	
	@Column(name = "myNotKeyField")
	public int myNotKeyField;
	
	public ExampleDomOne() {}
	
	public ExampleDomOne(long objId) {
		super(objId);
		// TODO Auto-generated constructor stub
	}
	
	public void setMyNotKeyField(int myNotKeyField) {
		this.myNotKeyField = myNotKeyField;
	}
	public int getMyNotKeyField() {
		return myNotKeyField;
	}
}
