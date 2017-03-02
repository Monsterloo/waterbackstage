package com.hhh.fund.waterwx.service.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.ResponsibilityDao;
import com.hhh.fund.waterwx.dao.RiverDao;
import com.hhh.fund.waterwx.dao.SwjAnswerDao;
import com.hhh.fund.waterwx.dao.SwjAttachmentDao;
import com.hhh.fund.waterwx.dao.SwjQuestionDao;
import com.hhh.fund.waterwx.dao.SwjQuestiontypeDao;
import com.hhh.fund.waterwx.dao.SwjSignInDao;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjWxsignin;
import com.hhh.fund.waterwx.entity.SwjWxsignin;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjWxsigninBean;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.service.SwjSignInService;
import com.hhh.weixin.util.WeixinSave;
@Component
@Transactional
public class SigninImgDownImpl implements WeixinSave {

	@Autowired
	private SwjSignInService signInService;
	
	
	
	@Override
	public void write(InputStream is) {
		
		ByteArrayOutputStream bos = null;
		SwjAttachment att = new SwjAttachment();
		
		byte[] buffer = new byte[1024];
		HttpURLConnection http = null;
		try {
		int len = 0, tlen = 0, clen = http.getContentLength();
		bos = new ByteArrayOutputStream();
		
			while ((len = is.read(buffer)) != -1 && tlen < clen) {
				bos.write(buffer, 0, len);
				tlen += len;
			}
			bos.close();
			att.setFile(bos.toByteArray());
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		signInService.saveAttachment(att);
		http.disconnect();
	}
	
}
