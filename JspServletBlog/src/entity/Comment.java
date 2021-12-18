package entity;
// Generated Dec 17, 2021, 3:50:40 PM by Hibernate Tools 5.4.32.Final

import java.util.Date;

/**
 * Comment generated by hbm2java
 */
public class Comment implements java.io.Serializable {

	private long id;
	private Post post;
	private String content;
	private Date publishedAt;

	public Comment() {
	}

	public Comment(long id, Post post) {
		this.id = id;
		this.post = post;
	}

	public Comment(long id, Post post, String content, Date publishedAt) {
		this.id = id;
		this.post = post;
		this.content = content;
		this.publishedAt = publishedAt;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishedAt() {
		return this.publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

}