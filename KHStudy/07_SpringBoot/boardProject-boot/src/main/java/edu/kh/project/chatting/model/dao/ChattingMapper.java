package edu.kh.project.chatting.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.chatting.model.dto.ChattingRoom;
import edu.kh.project.chatting.model.dto.Message;
import edu.kh.project.member.model.dto.Member;

@Mapper
public interface ChattingMapper {
    
    List<ChattingRoom> selectRoomList(int memberNo);

    int checkChattingNo(Map<String, Integer> map);

    int createChattingRoom(Map<String, Integer> map);

    int insertMessage(Message msg);

    int updateReadFlag(Map<String, Object> paramMap);

    List<Message> selectMessageList(int chattingNo);
    
	List<Member> selectTarget(Map<String, Object> map);
}
