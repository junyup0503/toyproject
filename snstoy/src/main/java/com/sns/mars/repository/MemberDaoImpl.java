package com.sns.mars.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sns.mars.entity.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(String memberEmail, String memberPw) {
		MemberDto memberDto = sqlSession.selectOne("member.one",memberEmail);
		if(memberDto == null) return null;
		boolean success = memberDto.getMemberPw().equals(memberPw);
		if(success) {
			return memberDto;
		}else {
			return null;
		}
	}
}
