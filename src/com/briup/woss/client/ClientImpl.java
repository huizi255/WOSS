package com.briup.woss.client;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.Properties;


import com.briup.woss.bean.BIDR;
import com.briup.woss.common.Configuration;
import com.briup.woss.common.Logger;

public class ClientImpl implements Client{
	private int port;
	private String ip;
	private Logger logger;
	private Client client;

	@Override
	public void init(Properties properties) {
		port = Integer.parseInt(properties.getProperty("port"));
		ip = properties.getProperty("ip");
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		try {
			logger = configuration.getLogger();
//			client = configuration.getClient();
//			System.out.println(client);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("all")
	@Override
	public void send(Collection<BIDR> collection) throws Exception {
		Socket socket = null;
		System.out.println(ip);
		System.out.println(port);
		socket = new Socket("127.0.0.1",8001);
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(outputStream);
		oos.writeObject(collection);
		oos.flush();
		oos.close();
//		PrintWriter pw = new PrintWriter(outputStream);
		/*pw.println(collection);	
		for(BIDR bidr:collection){
			pw.print(bidr);
//			System.out.println(bidr);
		}
		pw.flush();
		pw.close();*/
	}

}
