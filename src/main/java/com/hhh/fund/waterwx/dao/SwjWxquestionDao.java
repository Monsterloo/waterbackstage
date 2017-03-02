package com.hhh.fund.waterwx.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SwjWxquestion;
import com.hhh.fund.util.SpecificationsRepository;
@Repository
public interface SwjWxquestionDao extends SpecificationsRepository<SwjWxquestion, String> {
}
