package j.user.domain.user.entities;


import j.user.domain.shared.entities.Entity;
import j.user.domain.shared.exceptions.DomainException;


public class Post extends Entity {

	private String author;
	private String title;
	
	private Post(String id,String author,String title) {
		super(id);
		this.author = author;
		this.title = title;
		this.validate();
	}
	
	public static Post with(String id, String author,String title) {
        return new Post(id, author,title);
    }
	
	public String getId() {
		return  id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
    protected void validate() {
        if (this.getId() == null) {
            throw new DomainException("Id is required");
        }

        if (this.author == null) {		
            throw new DomainException("Author is required");
        }
        if (this.title == null) {		
            throw new DomainException("title is required");
        }
    }
}

