package com.sns.mars.repository;

import com.sns.mars.entity.MemberDto;

public interface MemberDao {

	MemberDto login(String memberId, String memberPw);
	
	
}
