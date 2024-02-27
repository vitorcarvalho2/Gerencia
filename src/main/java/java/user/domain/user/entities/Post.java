package java.user.domain.user.entities;


import java.user.domain.shared.entities.Entity;
import java.user.domain.shared.exceptions.DomainException;


public class Post extends Entity {

	private String author;
	
	private Post(String id,String author) {
		super(id);
		this.author = author;
		this.validate();
	}
	
	public static Post with(String id, String author) {
        return new Post(id, author);
    }
	
	public String getAuthor() {
		return author;
	}
	
	@Override
    protected void validate() {
        if (this.getId() == null) {
            throw new DomainException("Product id is required");
        }

        if (this.author == null) {		
            throw new DomainException("Author is required");
        }
    }
}

