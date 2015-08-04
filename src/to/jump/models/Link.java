package to.jump.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import to.jump.Global;

@Entity
@Table(name = "links")
public class Link {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "userId")
	private Long userId;
	
	private String code;
	private String longUrl;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return String.format(Global.URL_PATTERN, code);
	}

	public String getStatisticsUrl() {
		return getShortUrl() + "+";
	}
}