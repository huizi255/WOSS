package com.briup.woss.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Properties;

import com.briup.woss.bean.BIDR;

public class BackUpImpl implements BackUP{
	private String back_temp;
	private Logger logger;

	@Override
	public void init(Properties properties) {
		back_temp = properties.getProperty("back-temp");
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		try {
			logger = configuration.getLogger();
//			System.out.println(logger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("all")
	@Override
	public Object load(String key, boolean flag){
		System.out.println(back_temp);
		File file = new File("src/"+key);
		try {
			if(file.exists()){
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				Object readObject = ois.readObject();
				ois.close();
				if(flag==true){
					file.delete();
				}
				return readObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}

	@Override
	public void store(String key, Object data, boolean flag) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		String filePath = "src/"+key;  //key: backup.txt
		try {
			fos = new FileOutputStream(filePath,flag);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(data);
				oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fos!=null)fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(oos!=null)oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
