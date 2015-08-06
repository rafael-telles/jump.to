package to.jump.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private String title;
	private String description;
	private String tags;
	private String longUrl;
	private long clicks;

	public long getClicks() {
		return clicks;
	}

	public void setClicks(long clicks) {
		this.clicks = clicks;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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
