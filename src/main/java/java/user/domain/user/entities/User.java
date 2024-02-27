package java.user.domain.user.entities;

import java.user.domain.post.entities.Post;
import java.user.domain.shared.aggregatte.AggregatteRoot;
import java.user.domain.shared.entities.Entity;
import java.user.domain.shared.exceptions.DomainException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Email;

public class User extends Entity implements AggregatteRoot {

	private String name;
	@Email
	private String email;
	private String password;
	private String nickname;
	private List<Post> posts;
	
	
	public User(String id,String name, @Email String email, String password, String nickname, final List<Post> posts) {
		super(id);
		this.name = name;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.posts = posts;
		this.validate();
	}
	public static User create(String name, @Email String email, String password, String nickname) {
		return new User(
				UUID.randomUUID().toString(),
				name,
				email,
				password,
				nickname,
				new ArrayList<Post>());
	}
	
	public static User with(String id,String name, @Email String email, String password, String nickname,final List<Post> posts) {
		return new User(id,
				name,
				email,
				password,
				nickname,
				posts);
	}
	
	protected void validate() {
		if(this.getId()==null) {
			throw new DomainException("Post id is required");
		}
		if(this.name==null || this.name.isEmpty()) {
			throw new DomainException("Name is required");
		}
		if(this.email==null || this.email.isEmpty()) {
			throw new DomainException("Email is required");
		}
		if(this.password==null || this.password.isEmpty()) {
			throw new DomainException("Password is required");
		}
		if(this.nickname==null || this.nickname.isEmpty()) {
			throw new DomainException("Nickname is required");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public void addPost(final Post post) {
		this.posts.add(post);
	}
	
	
}