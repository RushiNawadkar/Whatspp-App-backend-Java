package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.Chat;
import com.app.Entity.User;
import com.app.Exception.ChatException;
import com.app.Exception.UserException;
import com.app.Sevices.ChatService;
import com.app.Sevices.UserService;
import com.app.request.GroupChatRequest;
import com.app.request.SingleChatRequest;
import com.app.respose.ApiResponse;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/single")
	public ResponseEntity<Chat>createChat(@RequestBody SingleChatRequest singlechatrequest,@RequestHeader("Authorization") String jwt) throws UserException{
		User user=userService.findUserProfile(jwt);
		Chat chat=chatService.createChat(user, singlechatrequest.getUserid());
		
		return new ResponseEntity<Chat>(chat,HttpStatus.OK);
		
	}
	
	@PostMapping("/group")
	public ResponseEntity<Chat>createGroupChat(@RequestBody GroupChatRequest groupchatrequest,@RequestHeader("Authorization") String jwt) throws UserException{
		User user=userService.findUserProfile(jwt);
		Chat chat=chatService.createGroup(groupchatrequest, user);
		
		return new ResponseEntity<Chat>(chat,HttpStatus.OK);
		
	}
	
	@GetMapping("/{chatid}")
	public ResponseEntity<Chat> findChatbyid(@PathVariable Long chatid, @RequestHeader("Authorization") String jwt) throws UserException, ChatException{
		
		Chat chat=chatService.findByChatId(chatid);
		
		return new ResponseEntity<Chat>(chat,HttpStatus.OK);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Chat>> findAllChatbyid( @RequestHeader("Authorization") String jwt) throws UserException, ChatException{
		User user=userService.findUserProfile(jwt);
		List<Chat> chat=chatService.findAllChatByUserId(user.getId());
		
		return new ResponseEntity<List<Chat>>(chat,HttpStatus.OK);
		
	}
	
	@PutMapping("/{chatid}/add/{userid}")
	public ResponseEntity<Chat> addToGroup(@PathVariable Long chatid,@PathVariable Long userid,@RequestHeader("Authorization") String jwt) throws UserException, ChatException{
		User user=userService.findUserProfile(jwt);
		Chat chat=chatService.addUserToGroup(userid, chatid, user);
		return new ResponseEntity<>(chat,HttpStatus.OK);
	}
	
	@PutMapping("/{chatid}/remove/{userid}")
	public ResponseEntity<Chat> removeToGroup(@PathVariable Long chatid,@PathVariable Long userid,@RequestHeader("Authorization") String jwt) throws UserException, ChatException{
		User user=userService.findUserProfile(jwt);
		Chat chat=chatService.RemoveFromGroup(userid, chatid, user);
		return new ResponseEntity<>(chat,HttpStatus.OK);
	}
	
	@DeleteMapping("/{chatid}/delete/{userid}")
	public ResponseEntity<ApiResponse> deleteToGroup(@PathVariable Long chatid, @RequestHeader("Authorization") String jwt) throws UserException, ChatException{
		User user=userService.findUserProfile(jwt);
		chatService.deleteChat(chatid, user.getId());
		ApiResponse res=new ApiResponse("Delete User Successfully",true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ApiResponse> renameGroup(@PathVariable Long chatid, @PathVariable String groupName, @RequestHeader("Authorization") String jwt) throws UserException, ChatException{
		User user=userService.findUserProfile(jwt);
		chatService.renameGroup(chatid, groupName, user);
		ApiResponse res=new ApiResponse("Rename Group Successfully",true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	
	
	
	
	
}
