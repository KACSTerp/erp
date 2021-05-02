package deployment;

import hrsystem.Dbs;
import io.ciera.runtime.summit.exceptions.XtumlException;
import sharedtypes.Types;

public class Library {
	private String instance_name;
	private String name;
	public Library() {
		try {
			Dbs.Singleton().ORM().CreateClass("Library");
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Library(String instance_name, String name) {
		this.instance_name = instance_name;
		this.name = name;
		int count = 0;
		try {
			Dbs.Singleton().ORM().CreateClass("Library");
			count++;
			Dbs.Singleton().ORM().CreateInstance("Library", instance_name);
			Dbs.Singleton().ORM().AddAttributes("Library", "name", Types.STRING);
			Dbs.Singleton().ORM().AddInstanceAttributes("Library", instance_name, "name", name);
			
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
