package com.briup.woss.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Properties;

import com.briup.woss.bean.BIDR;
import com.briup.woss.common.Configuration;
import com.briup.woss.common.Logger;
import com.briup.woss.common.LoggerImpl;
import com.briup.woss.util.ConnectionFactory;

public class DBStoreImpl implements DBStore{
	private Logger logger;
	private String batch_size;
	private String driver;
	private String url;
	private String user;
	private String password;

	@Override
	public void init(Properties properties) {
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("username");
		password = properties.getProperty("password");
		batch_size = properties.getProperty("batch-size");
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		try {
			logger = configuration.getLogger();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("all")
	@Override
	public void saveToDB(Collection<BIDR> collection) throws Exception {
		logger = new LoggerImpl();
		Connection conn = null;
		PreparedStatement ps = null;
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);
		logger.info("与数据库建立连接");
		int i = 0;
		int size = Integer.parseInt(batch_size);  //批量处理的大小
		for(BIDR bidr:collection){
			i++;
		    String sql="insert into t_detail_"+bidr.getLogout_date().getDate()+"(aaa_login_name,login_ip,login_date,logout_date,nas_ip,time_duration) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bidr.getAAA_login_name());
			ps.setString(2, bidr.getLogin_ip());
			ps.setTimestamp(3, bidr.getLogin_date());
			ps.setTimestamp(4, bidr.getLogout_date());
			ps.setString(5, bidr.getNAS_ip());
			ps.setInt(6, bidr.getTime_duration());
			ps.addBatch();
			if((i%size)==0){
				ps.executeBatch();
			}
			ps.executeBatch();
			ps.close();
		}
		conn.commit();
		logger.info("插入数据库成功");
		ConnectionFactory.close(null, ps, conn);
	}
}
