package to.jump.utils;

import javax.persistence.Persistence;

public class CreateTables {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("users");
		Persistence.createEntityManagerFactory("links");
		Persistence.createEntityManagerFactory("clicks");
	}
}
