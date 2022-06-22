package com.sns.mars.vo;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MessageVO {
	private String memberId;
	private String text;
	private String memberAuth;
	private Date time;
	
}
