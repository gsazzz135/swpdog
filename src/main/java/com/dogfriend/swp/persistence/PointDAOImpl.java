package com.dogfriend.swp.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {

	@Inject
	private SqlSession session;
	
	private static final String NS = "pointMapper";
	private static final String UPDATE_POINT = NS + ".updatePoint";
	
	@Override
	public void updatePoint(String uid, int point) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("point", point);
		
		session.update(UPDATE_POINT, paramMap);

	}

}
