package grdt;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test {

  @Id
  private int id;

  @Basic
  private String data;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getData() {
	return data;
}

public void setData(String data) {
	this.data = data;
}
}