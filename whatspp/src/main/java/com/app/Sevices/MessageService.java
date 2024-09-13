package com.app.Sevices;

import java.util.List;

import com.app.Entity.Message;
import com.app.Entity.User;
import com.app.Exception.ChatException;
import com.app.Exception.MessageException;
import com.app.Exception.UserException;
import com.app.request.SendMessageRequest;

public interface MessageService {

	public Message sendMessage(SendMessageRequest req) throws UserException,ChatException;
	
	public List<Message> getChatMessage(Long chatid,User user) throws ChatException,UserException;
	
	public Message findMessageid(Long messageid) throws MessageException;
	
	public void DeleteMessage(Long messageid)throws MessageException;
}
