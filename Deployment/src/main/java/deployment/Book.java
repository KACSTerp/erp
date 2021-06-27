package deployment;

import hrsystem.Dbs;
import io.ciera.runtime.summit.exceptions.XtumlException;
import sharedtypes.Types;

public class Book {
	private String instance_name;
	private int serial_number;
	private String title;
	private String author;
	
	public Book() {
		try {
			Dbs.Singleton().ORM().CreateClass("Book");
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Book(String instance_name, int serial_number, String title, String author) {
		this.instance_name = instance_name;
		this.serial_number = serial_number;
		this.title = title;
		this.author = author;
		int count = 0;
		try {
			Dbs.Singleton().ORM().CreateClass("Book");
			count++;
			Dbs.Singleton().ORM().CreateInstance("Book", instance_name);
			Dbs.Singleton().ORM().AddAttributes("Book", "serial_number", Types.INTEGER);
			Dbs.Singleton().ORM().AddAttributes("Book", "title", Types.STRING);
			Dbs.Singleton().ORM().AddAttributes("Book", "author", Types.STRING);
			Dbs.Singleton().ORM().AddInstanceAttributes("Book", instance_name, "serial_number", String.valueOf(serial_number));
			Dbs.Singleton().ORM().AddInstanceAttributes("Book", instance_name, "title", title);
			Dbs.Singleton().ORM().AddInstanceAttributes("Book", instance_name, "author", author);
			
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

	public int getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(int serial_number) {
		this.serial_number = serial_number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
