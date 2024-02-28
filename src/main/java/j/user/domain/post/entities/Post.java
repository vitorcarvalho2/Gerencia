package j.user.domain.post.entities;

import java.util.Date;
import java.util.UUID;

import j.user.domain.shared.aggregatte.AggregatteRoot;
import j.user.domain.shared.entities.Entity;
import j.user.domain.shared.exceptions.DomainException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class Post extends Entity implements AggregatteRoot{
	@NotBlank
	@Size(max = 50)
	private String title;
	@NotBlank
	@Size(max = 250)
	private String content;
	private String author;
	private Date date;	
	
	private Post(String id,String title,String content,String author,Date date) {
		super(id);
		this.title = title;
		this.content = content;
		this.author = author;
		this.date = date;
		this.validate();
	}
	
	public static Post create(String title,String content,String author,Date date) {
	
        
        
		return new Post(
				UUID.randomUUID().toString(),
				title,
				content,
				author,
				new Date());
	}
	
	public static Post with(String id,String title,String content,String author,Date date) {
		return new Post(
				id,title,content,author,date);
	}
	
	protected void validate() {
		if(this.getId()==null) {
			throw new DomainException("Post id is required");
		}
		if(this.title==null || this.title.isEmpty()) {
			throw new DomainException("Post description is required");
		}
		if(this.title.length() > 50) {
			throw new DomainException("Title can't be more than 50 characters");
		}
		if(this.content == null ||this.content.isEmpty()) {
			throw new DomainException("Content can't be Empty");
		}
		if(this.content.length() > 250) {
			throw new DomainException("Content can't be more than 250 characters");
		}
		if(this.author == null ) {
			throw new DomainException("author can't be Empty");
		}
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}

	public Date getDate() {
		return date;
	}
	
}