package com.briup.woss.server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.woss.bean.BIDR;
import com.briup.woss.common.Configuration;
import com.briup.woss.common.ConfigurationImpl;
import com.briup.woss.common.Logger;
import com.briup.woss.common.LoggerImpl;

public class ServerImpl implements Server{
	private static boolean flag = true;
	private int port;
	private Logger logger;
	private DBStore DBstore;

	@Override
	public void init(Properties properties) {
		port = Integer.parseInt(properties.getProperty("port"));
//		System.out.println(port);
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		try {
			logger = configuration.getLogger();
			DBstore =configuration.getDbStore();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("all")
	@Override
	public void receiver() throws Exception {
//		System.out.println(port);
		ServerSocket server = new ServerSocket(port);
		while(flag){
//			logger.debug("服务器开启了");
			Socket client = server.accept();
//			System.out.println("he");
			MyThread thread = new MyThread(client);
			thread.start();
			
		}
	}

	class MyThread extends Thread{
		private Socket client;
		private ObjectInputStream ois;
		
		public MyThread(Socket client) {
			this.client = client;
		}
	@SuppressWarnings("all")	
	@Override
	public void run() {
		try {
			InputStream inputStream = client.getInputStream();
			ois = new ObjectInputStream(inputStream);
			Object readObject = ois.readObject();
			if(ois!=null){
				Collection<BIDR> collection = (Collection<BIDR>)readObject;
//				System.out.println(readObject);
				DBstore.saveToDB(collection);
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}	
	@Override
	public void shutDown() throws Exception {
		flag = false;
	}
	
	public static void main(String[] args) {
		try {
			new ConfigurationImpl().getServer().receiver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
