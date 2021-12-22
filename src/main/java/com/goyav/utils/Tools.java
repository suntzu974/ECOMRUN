package com.goyav.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.apache.http.client.ClientProtocolException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.goyav.model.configuration.Server;
import com.goyav.model.configuration.X3v12prod;
import com.goyav.model.sqlserver.Tstk;
import com.goyav.model.sqlserver.TstkX3;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;



public class Tools {
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static String OS = System.getProperty("os.name").toLowerCase();
    public static final String ATT_POSTGRES_CONNECTION = "ATTRIBUTE_FOR_POSTGRES";
    public static final String ATT_O4HQ_CONNECTION = "ATTRIBUTE_FOR_O4HQ";
    public static final String ATT_LOCAL_CONNECTION = "ATTRIBUTE_FOR_LOCAL";
    public static final String ATT_INGRES_CONNECTION = "ATTRIBUTE_FOR_INGRES";
    public static final String ATT_MICROSOFT_CONNECTION = "ATTRIBUTE_FOR_MICROSOFT";
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
    private static final String ATT_SERVER_DATA = "ATTRIBUTE_FOR_SERVER_DATA";
    public Tools(Server server) {
		super();
	}
	public Tools() {
		// TODO Auto-generated constructor stub
		super();
	}
	public void storeServer(ServletRequest request, Server information) {
		// TODO Auto-generated method stub
        request.setAttribute(ATT_SERVER_DATA, information);		
	}
    public Server getStoredServer(ServletRequest request) {
        Server server =  (Server) request.getAttribute(ATT_SERVER_DATA);
        return server;
    }

	public void storeConnectionMicrosoft(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_MICROSOFT_CONNECTION, conn);
    }
    public Connection getStoredConnectionMicrosoft(ServletRequest request) {
        Connection conn =  (Connection) request.getAttribute(ATT_MICROSOFT_CONNECTION);
        return conn;
    }


	public void storeConnectionO4Hq(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_O4HQ_CONNECTION, conn);
    }
	public void storeConnectionPostgres(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_POSTGRES_CONNECTION, conn);
    }
	public void storeConnectionLocal(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_LOCAL_CONNECTION, conn);
    }

    public void storeConnectionIngres(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_INGRES_CONNECTION, conn);
    }
    public Connection getStoredConnectionIngres(ServletRequest request) {
        Connection conn =  (Connection) request.getAttribute(ATT_INGRES_CONNECTION);
        return conn;
    }
    public Connection getStoredConnectionPostgres(ServletRequest request) {
        Connection conn =  (Connection) request.getAttribute(ATT_POSTGRES_CONNECTION);
        return conn;
    }
    public Connection getStoredConnectionO4Hq(ServletRequest request) {
        Connection conn =  (Connection) request.getAttribute(ATT_O4HQ_CONNECTION);
        return conn;
    }
    public Connection getStoredConnectionLocal(ServletRequest request) {
        Connection conn =  (Connection) request.getAttribute(ATT_LOCAL_CONNECTION);
        return conn;
    }
 
 
    public String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    // Delete cookie.
    public void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 seconds (This cookie will expire immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
    
    public boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}

	public boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}

	public String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	public String modifyDatabaseAndSchema(X3v12prod microsoft,String request) {
		Map<String,String> map=new HashMap<String,String>();    
		map.put("@database",microsoft.getDatabase());  
		map.put("@schema",microsoft.getSchema());  
		for(Map.Entry m:map.entrySet()){  
			  request = request.replaceAll(m.getKey().toString(),m.getValue().toString());
		  }  
			return request;
	}

	public Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1].replace(" ","%"));
                result.put(pair[0], pair[1].replace("+","%"));
            }else{
                result.put(pair[0], "");
            }
        }
        System.out.println("Result :" + result);
        return result;
    }
    public boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    public boolean isStringAlpha(String aString){
        int charCount=0;
        String alphabet = "%0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(aString.length() == 0) return false;//zero length string ain't alpha
        for(int i=0;i<aString.length();i++){
            for(int j=0;j<alphabet.length();j++){
                if(aString.substring(i,i+1).equals(alphabet.substring(j,j+1))
                        || aString.substring(i,i+1).equals(alphabet.substring(j,j+1).toLowerCase()))
                    charCount++;
            }
            if(charCount != (i+1)){
                System.out.println("\n**Invalid input! Enter alpha values**\n");
                return false;
            }
        }
        return true;
    }
    public String decodeRequest(HttpServletRequest request) {
	       StringBuffer jb = new StringBuffer();
	       String line = null;
	       try {
	         BufferedReader reader = request.getReader();
	         while ((line = reader.readLine()) != null)
	           jb.append(line);
	         
	       } catch (Exception e) { /*report an error*/ }
       return jb.toString();
    }

	public String insertArrayInStatement (String sqlStmt,List<Integer> numdos) {
        //Declare statement as null
        List<Integer> fieldList = new ArrayList<Integer>();
        StringBuffer sb = new StringBuffer();  
        sb.append(sqlStmt);        
        fieldList = numdos;
        for(int i = 0; i < fieldList.size(); i++) {
            if(i == 0) {
                sb.append(" "+fieldList.get(i)+"\n");
            } else {
                sb.append(","+fieldList.get(i)+"\n");
            }
        }
        sb.append(") \n");
        sb.append("");
        return sb.toString();
    }
    
    public List<Integer> splitStringIntoArrrayOfIntegers(String args) {
         String[] stringArray = args.split(",");
         List<Integer> intList = new ArrayList<Integer>();
         int[] intArray = new int[stringArray.length];
         for (int i = 0; i < stringArray.length; i++) {
            String numberAsString = stringArray[i];
            intArray[i] = Integer.parseInt(numberAsString);
         }
         for (int number : intArray) {
            intList.add(number);
         }
         return intList;
    }
    public String sqlFormatedList(String sql,List<Integer> list){
    	 StringBuilder sb = new StringBuilder();
    	 sb.append(sql);
    	 sb.append(" (");
    	 for (Integer i : list){
    	   sb.append(i+",");
    	 }
    	 sb.deleteCharAt(sb.length() -1);
    	 sb.append(")");
    	 
    	 return sb.toString();
    	}
    public String replaceQuote(String st)
    {
    	StringBuffer sb = new StringBuffer();
    	char cArray[] = st.toCharArray();
    	for(int i = 0; i < st.length(); i++)
    	{
    		if(cArray[i] == '\'') // find single quote in String
    		{
    			sb.append('\''); //append the escape character
    		}
    		sb.append(cArray[i]); //append the regular character
    	}
    	return new String(sb);
    }
	public String randomTable() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
		sb.append("jdbc_");
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println("Random Table :" + output);
        return output;
	}
	public void writePid(long pid,String absolutePath) {
		// write the content in file 
		try(FileWriter fileWriter = new FileWriter(absolutePath)) {
		    String fileContent = String.valueOf(pid);
		    fileWriter.write(fileContent);
		} catch (IOException e) {
		    // exception handling
		}
	}
	public void stillAlive(String absolutePath) {
		File f = new File(absolutePath);
		if(f.exists() && !f.isDirectory()) { 
		    // do something
			System.out.println("Process toujours en activite , Something Wrong");
			System.exit(0);
		}
	}
	public void deleteFile(String absolutePath) {
		//relative path
        File file = new File(absolutePath);
        if(file.delete()){
            System.out.println("Processus termine normalement");
        }
	}
	public long getPID() {
	    String processName =
	      java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
	    return Long.parseLong(processName.split("@")[0]);
	}
   public String getCurrentDate(Integer previous) {
		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   	   Calendar cal = Calendar.getInstance();
	   		Date date = new Date();
	   	   cal.setTime(date);
	   	   cal.add(Calendar.DAY_OF_MONTH, - previous);
	   	   return dateFormat.format(cal.getTime());
   }
   public String getCurrentHour() {
		DateFormat dateFormat = new SimpleDateFormat("HHmm");
		Calendar hour = Calendar.getInstance();
		return dateFormat.format(hour.getTime());
	}

   public String getCurrentHour(Integer previous) {
	   	   DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	   	   Calendar hour = Calendar.getInstance();
	   	   hour.add(Calendar.MINUTE, -previous);
	   	   return dateFormat.format(hour.getTime());
   }
   public long DateToLong() {
	   DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
   	   Calendar cal = Calendar.getInstance();
   		Date date = new Date();
   	   cal.setTime(date);
   	   return Long.parseLong(dateFormat.format(cal.getTime()));

  }

  public String LongToDate(Integer longDate) throws ParseException {
	   String pattern = "yyyyMMdd";
	   SimpleDateFormat LD = new SimpleDateFormat(pattern);
	   Date StringDate = LD.parse(longDate.toString());
	   DateFormat SD = new SimpleDateFormat("dd/MM/yyyy");
   	   Calendar cal = Calendar.getInstance();
   	   cal.setTime(StringDate);
   	   return SD.format(cal.getTime());

  }
   public String AddDays(Integer longDate,Integer days) throws ParseException {
   	   
	   String pattern = "yyyyMMdd";
	   SimpleDateFormat LD = new SimpleDateFormat(pattern);
	   Date StringDate = LD.parse(longDate.toString());
	   DateFormat SD = new SimpleDateFormat("dd/MM/yyyy");
   	   Calendar cal = Calendar.getInstance();
   	   cal.setTime(StringDate);
   	   cal.add(Calendar.DAY_OF_MONTH, days);
   	   return SD.format(cal.getTime());

  }
   public Calendar LongToCalender(Integer longDate) throws ParseException {
   	   
	   String pattern = "yyyyMMdd";
	   SimpleDateFormat LD = new SimpleDateFormat(pattern);
	   Date StringDate = LD.parse(longDate.toString());
	   DateFormat SD = new SimpleDateFormat("dd/MM/yyyy");
   	   Calendar cal = Calendar.getInstance();
   	   cal.setTime(StringDate);
   	   return cal;

  }
   public Calendar AddDaysToCalender(Integer longDate,Integer days) throws ParseException {
   	   
	   String pattern = "yyyyMMdd";
	   SimpleDateFormat LD = new SimpleDateFormat(pattern);
	   Date StringDate = LD.parse(longDate.toString());
	   DateFormat SD = new SimpleDateFormat("dd/MM/yyyy");
   	   Calendar cal = Calendar.getInstance();
   	   cal.setTime(StringDate);
   	   cal.add(Calendar.DAY_OF_MONTH, days);
   	   return cal;

  }
   public Long diffDate(Calendar calEnd, Calendar calBegin) throws ParseException {
   	   
 	   return calEnd.getTimeInMillis()-calBegin.getTimeInMillis();
   }

   public Integer ParseI1nbcolis(String orkstring) {

		return Integer.parseInt(orkstring.substring(16,19));
   }
   public String ParseVcrefcli(String orkstring) {

		/* *        this.i1nbcolis = Integer.parseInt(orkstring.substring(16,19));
       this.didatmaj = Integer.parseInt(orkstring.substring(0,8));
       this.orkstring = String.format("%3s:%5s%6s",orkstring.substring(8,11),orkstring.substring(11,16),orkstring.substring(19,25));*/

	   return  String.format("%3s:%5s%6s",orkstring.substring(16,19),orkstring.substring(11,16),orkstring.substring(19,25));
   }
   public String ConvertUtf8 (String rawString) {
       return  rawString.replaceAll("[^\\x00-\\x7F]", "");
   }
   public void FtpSend (String remoteFile) {

	   String user = "ingres";
       String password = "ingres";
       String host = "10.1.0.1";
       int port = 22;

       try {
           JSch jsch = new JSch();
           Session session = jsch.getSession(user, host, port);
           session.setPassword(password);
           session.setConfig("StrictHostKeyChecking", "no");
           System.out.println("Establishing Connection...");
           session.connect();
           System.out.println("Connection established.");
           System.out.println("Crating SFTP Channel.");
           ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
           sftpChannel.connect();
           System.out.println("SFTP Channel created.");

           InputStream inputStream = sftpChannel.get(remoteFile);

           try (Scanner scanner = new Scanner(new InputStreamReader(inputStream))) {
               while (scanner.hasNextLine()) {
                   String line = scanner.nextLine();
                   System.out.println(line);
               }
           }
       		} 
       	catch (JSchException | SftpException e) {
       }
   }
   public boolean sendFTP(){
	   
	   StandardFileSystemManager manager = new StandardFileSystemManager();
	  
	   try {
	  
	    String serverAddress = new String("10.1.0.1") ;//props.getProperty("serverAddress").trim();
	    String userId = new String("ingres") ; //props.getProperty("userId").trim();
	    String password = new String("ingres") ; //props.getProperty("password").trim();
	    String remoteDirectory = new String("home/ingres/JEA/FTP"); //"props.getProperty("remoteDirectory").trim();
	    String localDirectory = new String("C:\\Users\\10373\\Documents\\Projects_C\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SAPOTY\\\\SofaremStock.xlsx").trim();
	  
	    //check if the file exists
	    String filepath = localDirectory ; //+  fileToFTP;
	    File file = new File(filepath);
	    if (!file.exists())
	     throw new RuntimeException("Error. Local file not found");
	  
	    //Initializes the file manager
	    manager.init();
	     
	    //Setup our SFTP configuration
	    FileSystemOptions opts = new FileSystemOptions();
	    SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
	      opts, "no");
	    SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
	    SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);
	     
	    //Create the SFTP URI using the host name, userid, password,  remote path and file name
	    String sftpUri = "sftp://" + userId + ":" + password +  "@" + serverAddress + "/" + 
	      remoteDirectory ;//+ fileToFTP;
	     
	    // Create local file object
	    FileObject localFile = manager.resolveFile(file.getAbsolutePath());
	  
	    // Create remote file object
	    FileObject remoteFile = manager.resolveFile(sftpUri, opts);
	  
	    // Copy local file to sftp server
	    remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
	    System.out.println("File upload successful");
	  
	   }
	   catch (Exception ex) {
	    ex.printStackTrace();
	    return false;
	   }
	   finally {
	    manager.close();
	   }
	  
	   return true;
	  }
	public List<TstkX3> FilterList(List<TstkX3> originalList,List<String> filter) {
	    List<TstkX3> filteredList;
	    Set<String> nameFilterSet = filter.stream().collect(Collectors.toSet());
	 
	    filteredList = originalList.stream()
	      .filter(stock -> nameFilterSet.contains(stock.getChcodi()))
	      .collect(Collectors.toList());
	 
	    return filteredList;
	}
	public List<Tstk> FilterEcomrun(List<Tstk> originalList,List<String> filter) {
	    List<Tstk> filteredList;
	    Set<String> nameFilterSet = filter.stream().collect(Collectors.toSet());
	 
	    filteredList = originalList.stream()
	      .filter(stock -> nameFilterSet.contains(stock.getChcodi()))
	      .collect(Collectors.toList());
	 
	    return filteredList;
	}

}

   
