package com.briup.woss.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import com.briup.woss.bean.BIDR;
import com.briup.woss.common.BackUP;
import com.briup.woss.common.BackUpImpl;
import com.briup.woss.common.Configuration;
import com.briup.woss.common.Logger;

public class GatherImpl implements Gather{
	private String src_file;
	private String nas_ip;
	private Logger logger;
	private BackUP backup;

	@Override
	public void init(Properties properties) {
		src_file = properties.getProperty("src-file");
//		System.out.println(src_file);
		nas_ip = properties.getProperty("nas-ip");
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		try {
			System.out.println("程序开始");
			backup=configuration.getBackup();
			System.out.println("backup"+backup);
			logger = configuration.getLogger();
			gather();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("all")
	@Override
	public Collection<BIDR> gather() throws Exception {
		/*
		 * 1.读取radwtmp_test文件，读取数据
		 * 以 | 为分割，第一列用户名（#后面没有，表示下线，有表示正在登录，第三列是7正在登录，8表示下线）
		 * 是7的时候，创建对象，放入map中，key:ip,value:对象
		 * 第四列表示时间（上线或下线），第五列表示IP，确定是哪个用户
		 * 把map的values以对象放入list
		 * 调用BackuUp模块的store方法，讲map写入文件
		 * 将已经读取的字节数调用store写入文件
		 * */
//		System.out.println("backup1"+backup);
//		System.out.println(src_file);
		BufferedReader br = new BufferedReader(new FileReader("src/com/briup/woss/bean/radwtmp_test"));
		Map<String, BIDR> map = new HashMap<>();
		
		Object load1 = new BackUpImpl().load("backup.txt", BackUP.LOAD_REMOVE);
		if(load1!=null){
			Map<String,BIDR> map2 = (Map)load1; 
			map.putAll(map2);
		}
		Object load2 = new BackUpImpl().load("byteNum.txt", BackUP.LOAD_REMOVE);
		if(load2!=null){
			int lo = (int)load2;
			br.skip(lo);
		}
		List<BIDR> list = new ArrayList<>();
		String data = null;
		int skip=0;
		while((data=br.readLine())!=null){
			skip+=data.length()+2;
			String line = br.readLine();
//				System.out.println(line);
			String[] split = line.split("\\|");
//				System.out.println(split[2]);
//				System.out.println(split[0]);
			if(Integer.parseInt(split[2])==7){
				BIDR bidr = new BIDR();
				bidr.setAAA_login_name(split[0].substring(1, split[0].length()));
				Timestamp timestamp = new Timestamp(new java.sql.Date(Long.parseLong(split[3])*1000).getTime());
				bidr.setLogin_date((timestamp));
				bidr.setLogin_ip(split[4]);
				bidr.setNAS_ip(nas_ip);
//					System.out.println(bidr);
//					System.out.println(bidr.getLogin_ip());
				map.put(bidr.getLogin_ip(), bidr);
			}
			if(Integer.parseInt(split[2])==8){
				BIDR bidr = map.get(split[4]);
				if(map.containsKey(split[4])){
					Date date = new Date(Long.parseLong(split[3]));
					Timestamp timestamp = new Timestamp(new java.sql.Date(Long.parseLong(split[3])*1000).getTime());
					bidr.setLogout_date(timestamp);
					long login_date = bidr.getLogin_date().getTime();
					long logout_date = bidr.getLogout_date().getTime();
					bidr.setTime_duration((int)(logout_date-login_date)/1000);
					list.add(bidr);
					map.remove(bidr);
				}
			}
//				map.remove(map.get(split[4]));    //移除map所有的值
		}
		
		new BackUpImpl().store("backup.txt",map, BackUP.STORE_APPEND);  //把map写入文件中，追加
		new BackUpImpl().store("byteNum.txt", skip, BackUP.STORE_OVERRIDE);
//			new ClientImpl().send(list);
			
//			System.out.println(skip);   //skip  : 字节数
//			System.out.println(map2);
			
			/*for(String m:map.keySet()){        //输出所有map的值
				System.out.println(m+"--"+map.get(m));
			}*/
			/*for(BIDR b:list){
				System.out.println(b);   		//返回list集合
			}*/
		return list;
	}

}
