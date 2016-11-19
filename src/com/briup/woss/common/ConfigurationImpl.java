package com.briup.woss.common;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.woss.bean.BIDR;
import com.briup.woss.bean.ConfigurationAWare;
import com.briup.woss.bean.WossModuleInit;
import com.briup.woss.client.Client;
import com.briup.woss.client.Gather;
import com.briup.woss.server.DBStore;
import com.briup.woss.server.Server;
import com.briup.woss.server.ServerImpl;

public class ConfigurationImpl implements Configuration{
	Map< String,Object> map = new TreeMap<>();
	private Properties properties;
	
	@SuppressWarnings("all")
	public ConfigurationImpl() {
		properties = new Properties();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read("src/com/briup/woss/common/config.xml");
			Element rootElement = document.getRootElement();
			List<Element> elements = rootElement.elements();
			for(Element e1:elements){
//				System.out.println(e1.getName());  //server,dbstore....
				
				List<Attribute> attributes = e1.attributes();
				for(Attribute attr:attributes){
						Class c = Class.forName(attr.getValue());
//						System.out.println(attr.getValue());
						Object obj = c.newInstance();
//						System.out.println(e1.getName()+"--"+obj);
						map.put(e1.getName(), obj);
//						System.out.println(obj);   //可以拿到6个不同的对象
						WossModuleInit wossModuleInit = (WossModuleInit)obj;
					List<Element> elements2 = e1.elements();
					for(Element e2:elements2){
//						System.out.println(e2.getName()+" "+e2.getText());   //port  8001,ip ..
						properties.put(e2.getName(), e2.getText());
					}
					wossModuleInit.init(properties);
					if(!(obj instanceof Logger)){
						ConfigurationAWare aWare = (ConfigurationAWare) obj;
						aWare.setConfiguration(this);
					}
				}
			}
//			System.out.println(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BackUP getBackup() throws Exception {
		for(Object key:map.keySet()){
			if(key.equals("backup")){
				BackUP backup = (BackUP) map.get(key);
				return backup;
			}
		}
		return null;
	}

	@Override
	public Logger getLogger() throws Exception {
		for(Object key:map.keySet()){
			if(key.equals("logger")){
				Logger logger = (Logger) map.get(key);
				return logger;
			}
		}
		return null;
	}

	@Override
	public Server getServer() throws Exception {
		for(Object key:map.keySet()){
			if(key.equals("server")){
				Server server = (Server) map.get(key);
				return server;
			}
		}
		return null;
	}

	@Override
	public DBStore getDbStore() throws Exception {
		for(Object key:map.keySet()){
			if(key.equals("dbstore")){
				DBStore dbstore = (DBStore) map.get(key);
				return dbstore;
			}
		}
		return null;
	}

	@Override
	public Client getClient() throws Exception {
		for(Object key:map.keySet()){
			if(key.equals("client")){
				Client client = (Client) map.get(key);
				return client;
			}
		}
		return null;
	}

	@Override
	public Gather getGather() throws Exception {
		for(Object key:map.keySet()){
			if(key.equals("gather")){
				Gather gather =  (Gather) map.get(key);
				return gather;
			}
		}
		return null;
	}
}
