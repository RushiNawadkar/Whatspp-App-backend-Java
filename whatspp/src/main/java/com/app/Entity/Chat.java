package com.app.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String chatName;
	
	private String chatImage;
	
	@Column(name="isGroup")
	private boolean isGroup;
	
	@JoinColumn(name="createdBy")
	@ManyToOne
	private User createdBy;
	
	@ManyToMany
	private Set<User> admin=new HashSet(); 
	
	@ManyToMany
	private Set<User>users=new HashSet();
	
	@OneToMany 
	private List<Message> message=new ArrayList();

	public Chat() {
		super();
	}

	public Chat(Long id, String chatName, String chatImage, boolean isGroup, User createdBy, Set<User> admin,
			Set<User> users, List<Message> message) {
		super();
		this.id = id;
		this.chatName = chatName;
		this.chatImage = chatImage;
		this.isGroup = isGroup;
		this.createdBy = createdBy;
		this.admin = admin;
		this.users = users;
		this.message = message;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public String getChatImage() {
		return chatImage;
	}

	public void setChatImage(String chatImage) {
		this.chatImage = chatImage;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

	
	public Set<User> getAdmin() {
		return admin;
	}

	public void setAdmin(Set<User> admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", chatName=" + chatName + ", chatImage=" + chatImage + ", isGroup=" + isGroup
				+ ", createdBy=" + createdBy + ", users=" + users + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(chatImage, chatName, createdBy, id, isGroup, message, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chat other = (Chat) obj;
		return Objects.equals(chatImage, other.chatImage) && Objects.equals(chatName, other.chatName)
				&& Objects.equals(createdBy, other.createdBy) && Objects.equals(id, other.id)
				&& isGroup == other.isGroup && Objects.equals(message, other.message)
				&& Objects.equals(users, other.users);
	}

	


	
	
	
	
}
