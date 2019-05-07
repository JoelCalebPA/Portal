package com.domain.portal.auth;

//import com.openkm.cache.WSCacheDAO;
import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.bean.SqlQueryResultColumns;
import com.openkm.sdk4j.bean.SqlQueryResults;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private static Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

//	private WSCacheDAO wsCache;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		log.debug("loadUserByUsername {}", userName);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
//			OKMWebservices ws = wsCache.getOKMWebservices(WSCacheDAO.ADMIN_USER);

			// Get full Name
//			String userFullName = ws.getName(userName);

			// Get roles
//			List<String> roles = ws.getRolesByUser(userName);

			// set roles for spring-security according to those received from OKM ws response
//			Iterator<String> it = roles.iterator();
			String role;
//			while (it.hasNext()) {
//				role = it.next();
//				authorities.add(new SimpleGrantedAuthority(role));
//			}

			// Get Password
			String sql = "SELECT USR_PASSWORD FROM OKM_USER WHERE USR_ID='" + userName + "'";
			InputStream is = new ByteArrayInputStream(sql.getBytes("UTF-8"));
//			SqlQueryResults result = ws.executeSqlQuery(is);

			String password = "";
//			for (SqlQueryResultColumns row : result.getResults()) {
//				password = row.getColumns().get(0);
//			}
			IOUtils.closeQuietly(is);

//			return new CustomUser(userName, password, true, true, true, true, authorities, userFullName);
			return null;
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage(), e);
			return new CustomUser(userName, "", true, true, true, true, authorities, "");
		}
	}
}
