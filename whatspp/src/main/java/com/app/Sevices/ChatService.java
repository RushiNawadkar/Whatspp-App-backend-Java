package com.app.Sevices;
import java.util.List;
import com.app.Entity.Chat;
import com.app.Entity.User;
import com.app.Exception.ChatException;
import com.app.Exception.UserException;
import com.app.request.GroupChatRequest;

public interface ChatService {

	public Chat createChat(User reqUser1, Long requser2) throws UserException;
	
	public Chat findByChatId(Long chatid) throws ChatException;
	
	public List<Chat> findAllChatByUserId(Long userid) throws UserException;
	
	public Chat createGroup(GroupChatRequest req,User requser)throws UserException;
	
	public Chat addUserToGroup(Long userid, Long Chatid,User user)throws UserException,ChatException;
	
	public Chat renameGroup(Long chatid,String groupName,User requser)throws ChatException,UserException;
	
	public Chat RemoveFromGroup(Long userid,Long chatid,User requser)throws UserException,ChatException;
	
	public void deleteChat(Long chatid,Long userid)throws ChatException,UserException;
	
	
	
}
