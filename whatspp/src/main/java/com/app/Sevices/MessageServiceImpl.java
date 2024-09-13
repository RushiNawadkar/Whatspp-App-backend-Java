package com.app.Sevices;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.app.Entity.Chat;
import com.app.Entity.Message;
import com.app.Entity.User;
import com.app.Exception.ChatException;
import com.app.Exception.MessageException;
import com.app.Exception.UserException;
import com.app.Repository.MessageRepository;
import com.app.request.SendMessageRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;

	@Override
	public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
		 User user= userService.findUserbyId(req.getUserid());
		 Chat chat=chatService.findByChatId(req.getChatdid());
		 
		 Message message=new Message();
		 message.setChat(chat);
		 message.setUser(user);
		 message.setContent(req.getContent());
		 message.setTimestamp(LocalDateTime.now());
		 
		return message;
	}

	@Override
	public List<Message> getChatMessage(Long chatid,User user) throws ChatException, UserException {
		Chat chat=chatService.findByChatId(chatid);
		if(!chat.getUsers().contains(user)) {
			throw new UserException("You are not releted to this chat  "+chatid);
		}
		List<Message>message=messageRepository.findByChatId(chatid);
		return message;
	}

	@Override
	public Message findMessageid(Long messageid) throws MessageException {
		
		return messageRepository.findById(messageid).
				orElseThrow(()->new MessageException("Message Not Found with message id"+messageid));
	}

	@Override
	public void DeleteMessage(Long messageid) throws MessageException {
		Message message=findMessageid(messageid);
		messageRepository.deleteById(messageid);
		
	}

}
