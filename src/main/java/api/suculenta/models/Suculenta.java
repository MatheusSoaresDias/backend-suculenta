package api.suculenta.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name="suculenta")
@Data
public class Suculenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "price")
	private BigDecimal price;
	
	@NotNull
	@Column(name = "path")
	private String path;
	
	@NotNull
	@Column(name = "stock")
	private int stock;
	
	
}
