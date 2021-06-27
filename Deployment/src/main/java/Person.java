import hrsystem.Dbs;
import io.ciera.runtime.summit.exceptions.XtumlException;
import sharedtypes.Types;

public class Person {
	private String instance_name;
	private String name;
	public Person() {
		try {
			Dbs.Singleton().ORM().CreateClass("Person");
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Person(String instance_name, String name) {
		this.instance_name = instance_name;
		this.name = name;
		int count = 0;
		try {
			Dbs.Singleton().ORM().CreateClass("Person");
			count++;
			Dbs.Singleton().ORM().CreateInstance("Person", instance_name);
			Dbs.Singleton().ORM().AddAttributes("Person", "name", Types.STRING);
			Dbs.Singleton().ORM().AddInstanceAttributes("Person", instance_name, "name", name);
			
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getInstance_name() {
		return instance_name;
	}

	public void setInstance_name(String instance_name) {
		this.instance_name = instance_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
