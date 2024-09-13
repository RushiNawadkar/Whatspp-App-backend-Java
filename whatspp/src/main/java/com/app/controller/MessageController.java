package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.Message;
import com.app.Entity.User;
import com.app.Exception.ChatException;
import com.app.Exception.MessageException;
import com.app.Exception.UserException;
import com.app.Sevices.ChatService;
import com.app.Sevices.MessageService;
import com.app.Sevices.UserService;
import com.app.request.SendMessageRequest;
import com.app.respose.ApiResponse;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/send")
	public ResponseEntity<Message> sendMesagetoUser(@RequestBody SendMessageRequest req, @RequestHeader String jwt) throws UserException, ChatException{
		User user=userService.findUserProfile(jwt);
		Message message=messageService.sendMessage(req);
		return new ResponseEntity<Message>(message,HttpStatus.OK);
	}
	
	@GetMapping("/chat/{chatid}")
	public ResponseEntity<List<Message>> getMesagetoUser(@PathVariable Long chatid,@RequestHeader String jwt) throws UserException, ChatException, MessageException{
		User user=userService.findUserProfile(jwt);
		List<Message> message=messageService.getChatMessage(chatid, user);
		return new ResponseEntity<List<Message>>(message,HttpStatus.OK);
	}
	
	@DeleteMapping("/{messageid}")
	public ResponseEntity<ApiResponse> deleteMesagetoUser(@PathVariable Long messageid,@RequestHeader String jwt) throws UserException, ChatException, MessageException{
		User user=userService.findUserProfile(jwt);
		messageService.DeleteMessage(messageid);
		ApiResponse res=new ApiResponse("Message Deleted Successfuly",true);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	
	
	

}
