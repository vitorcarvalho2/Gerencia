package j.user.domain.user.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import j.user.domain.post.entities.Post;
import j.user.domain.shared.aggregatte.AggregatteRoot;
import j.user.domain.shared.entities.Entity;
import j.user.domain.shared.exceptions.DomainException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class User extends Entity implements AggregatteRoot {
	
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	@NotBlank
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