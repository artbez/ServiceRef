package DOMClasses;

public class ExampleDomOne extends ExampleDOMTwo {
	
	public int myNotKeyField;
	
	public ExampleDomOne(long objId, String name) {
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
