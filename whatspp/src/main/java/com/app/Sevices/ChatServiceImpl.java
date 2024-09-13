package com.app.Sevices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Entity.Chat;
import com.app.Entity.User;
import com.app.Exception.ChatException;
import com.app.Exception.UserException;
import com.app.Repository.ChatRepository;
import com.app.request.GroupChatRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Chat createChat(User requser, Long userid) throws UserException {
		User user= userService.findUserbyId(userid);
		Chat isExits=chatRepository.findSingleChatByUserIds(user, requser);
		
		if(isExits!=null) {
			return isExits;
		}
		
		Chat chat =new Chat();
		chat.setCreatedBy(requser);
		chat.getUsers().add(user);
		chat.getUsers().add(requser);
		chat.setGroup(false);
		return chat;
	}

	@Override
	public Chat findByChatId(Long chatid) throws ChatException {
		Optional<Chat> chat=chatRepository.findById(chatid);
		
		if(chat.isPresent()) {
			return chat.get();
		}
		throw new ChatException("Chat Not found  with id"+chatid);
		
	}

	@Override
	public List<Chat> findAllChatByUserId(Long userid) throws UserException {
		User user=userService.findUserbyId(userid);
		List<Chat> chat=chatRepository.findChatByUserid(user.getId());
		return chat;
	}

	@Override
	public Chat createGroup(GroupChatRequest req, User requser) throws UserException {
		Chat group=new Chat();
		group.setGroup(true);
		group.setChatName(req.getChaName());
		group.setChatImage(req.getChatImage());
		group.setCreatedBy(requser);
		group.getAdmin().add(requser);
		for(Long userid:req.getUserid()) {
			User user=userService.findUserbyId(userid);
			group.getUsers().add(user);	
			
		}
		
		return group;
	}

	@Override
	public Chat addUserToGroup(Long userid, Long Chatid,User requser) throws UserException ,ChatException{
		User user=userService.findUserbyId(userid);
		Optional<Chat> opt=chatRepository.findById(Chatid);
		if(opt.isPresent()) {
			Chat chat=opt.get();
			if(chat.getAdmin().contains(requser)) {
				chat.getUsers().add(user);
				return chatRepository.save(chat) ;
			}else {
				throw new UserException("Only admin can add the Group");
			}
			
		}
		throw new ChatException("Chat not found with id"+Chatid);
		
	}


	@Override
	public Chat RemoveFromGroup(Long userid, Long chatid, User requser) throws UserException, ChatException {
		User user=userService.findUserbyId(userid);
		Optional<Chat> opt=chatRepository.findById(chatid);
		if(opt.isPresent()) {
			Chat chat=opt.get();
			if(chat.getAdmin().contains(requser) ) {
				chat.getUsers().remove(user);
				return chatRepository.save(chat) ;
			
			}else if(chat.getUsers().contains(requser)){
				if(user.getId().equals(requser.getId())) {
					chat.getUsers().remove(user);
					return chatRepository.save(chat) ;
				}
			}else {
				throw new UserException("You can't remove another user");
			}
			
		}
		throw new ChatException("Chat not found with id"+chatid);
	}

	@Override
	public void deleteChat(Long chatid, Long userid) throws ChatException, UserException {
		Optional<Chat> opt=chatRepository.findById(chatid);
		if(opt.isPresent()) {
			Chat chat=opt.get();
			chatRepository.deleteById(chat.getId());
		}
	}

	@Override
	public Chat renameGroup(Long chatid, String groupName, User requser) throws ChatException, UserException {
		
		Optional<Chat> opt=chatRepository.findById(chatid);
		if(opt.isPresent()) {
			Chat chat=opt.get();
			if(chat.getUsers().contains(requser)) {
				chat.setChatName(groupName);
				return chatRepository.save(chat);
			}
			throw new UserException("You are not member of this group");
		}
		
		throw new ChatException("Chat not found with id"+chatid);
	}

	

}
