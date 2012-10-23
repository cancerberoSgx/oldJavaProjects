//package org.sgx.mywss.client;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Properties;
//
//import org.apache.commons.httpclient.Credentials;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.UsernamePasswordCredentials;
//import org.apache.commons.httpclient.auth.AuthScope;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.sgx.utils.Base64;
//import org.sgx.utils.Utils;
//
//
//
//
//public class MyWssJavaClient {
//	
//    public static final String PROP_SERVERURL = "serverUrl", PROP_JSONURI="jsonUri", 
//    	PROP_USER="user", PROP_PASSWORD="password" ;
//
//	private static final String CLASSNAME_BEEOBJECT = "com.softpoint.portlet.beeblosportlet.service.http.BeeObjectServiceJSON";
//    
//	private static final String defaultConfigResource = "clientConf.properties";
//	public static MyWssJavaClient getDefault() throws IOException{
//		return new MyWssJavaClient(defaultConfigResource);
//	}
//	
//    private Properties props = new Properties();
////	private String propertiesResource;
//
//	private String serverUrl, jsonuri, user, password;
//	
//    public MyWssJavaClient(String propertiesResource) throws IOException{
////    	this.propertiesResource=propertiesResource;
//    	InputStream stream = MyWssJavaClient.class.getResourceAsStream(propertiesResource);
//    	props.load(stream);
//        serverUrl = props.getProperty(PROP_SERVERURL); 
//    	jsonuri = props.getProperty(PROP_JSONURI);
//    	user = props.getProperty(PROP_USER);
//    	password = props.getProperty(PROP_PASSWORD);    
//    }
//    
//    public String buildMessageUrl(String className, String methodName, Object[][]params) {
//    	String  paramNames="", paramValues=""; 
//    	for (int i = 0; i < params.length; i++) {
//			Object[] param = params[i];
//			Object pName=param[0], pValue=param[1];
//			paramNames+=pName;
//			paramValues+="&"+pName+"="+pValue;
//			if(i<params.length-1){
//				paramNames+=",";
//			}
//		}
//        String paramsStr = "?" +
//        		"serviceClassName="+className+"" +
//        		"&serviceMethodName="+methodName+
//        		"&serviceParameters="+paramNames+
//        		paramValues;        
//        String url =  serverUrl + jsonuri + paramsStr;
//        return url;
//    }
//
//		
//	public String makeHttpRequest(String url) throws IOException {
//		return makeHttpRequest(url, user, password);
//	}
//	
//	
//	public String makeHttpRequest(String url, String user, String password) throws IOException {		
//		HttpClient client = new HttpClient();      	      
//		client.getParams().setAuthenticationPreemptive(true);
//        Credentials defaultcreds = new UsernamePasswordCredentials(user, password);
//        client.getState().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM), defaultcreds);        
//        GetMethod get = new GetMethod(url);
//        get.setDoAuthentication( true );        
//        try {
//            int status = client.executeMethod( get );
//            System.out.println(status + "\n" + 
//                    get.getResponseBodyAsString());
//            return get.getResponseBodyAsString();
//        } finally {
//            get.releaseConnection();
//        }
//	}
//		
//	
//
//	/// utilities //
//	private static SimpleDateFormat 
//		DTFOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), 
//		DTFIn = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"), 
//		DFOut = new SimpleDateFormat("yyyy-MM-dd"),			
//		DFIn = new SimpleDateFormat("dd/MM/yyyy");
//
//	/**
//	 * @return null if can't understand input date
//	 */
//	private static String parseAndFormatDate(String date, SimpleDateFormat fin,
//			SimpleDateFormat fout) {
//		try {
//			Date d = fin.parse(date);
//			return fout.format(d);
//		} catch (Exception e) {
//			return null;
//		}
//	}
//	
//	private static String serializeDate(Date d, boolean isDateTime) {
//		if(isDateTime) {
//			return DTFOut.format(d);
//		}else{
//			return DFOut.format(d);
//		}
//	}
//
//	
//	//BEEBLOS API 
//	
//	
//	public int addFile(String repositoryId, String filePath, String fileName,
//			String mimeType, String description, int docClassId, int docSecurity, int virtualDocument,
//			String properties, String fileSource, int parentDocId, byte[] byteArray) throws IOException{
//		String url = buildMessageUrl(CLASSNAME_BEEOBJECT, "addFile", 
//			new Object[][]{
//				{"repositoryId", repositoryId},
//				{"filePath",filePath},
//				{"fileName",fileName},
//				{"mimeType",mimeType},
//				{"description",description},
//				{"docClassId",docClassId},
//				{"docSecurity",docSecurity},
//				{"virtualDocument",virtualDocument},
//				{"properties",properties},
//				{"fileSource",fileSource},
//				{"parentDocId",parentDocId},
//				{"byteArray",new String(Base64.encode(byteArray))}
//			}
//		);
//		String respStr = makeHttpRequest(url);
//		return Integer.parseInt(respStr);
//	}
//	
//    /**
//     * 
//     * @param repositoryId
//     * @param filePath
//     * @param fileName
//     * @param mimeType
//     * @param description
//     * @param docClassId
//     * @param docSecurity
//     * @param virtualDocument
//     * @param properties  something like for example: "1356#2006-03-12|1059#2006-03-12 00:00:00|1256#uno|1456#2006-03-12 00:00:00|"
//     * @param fileSource
//     * @param byteArray
//     * @return
//     * @throws IOException
//     */
//	public int addFile(String repositoryId, String filePath, String fileName,
//			String mimeType, String description, int docClassId, int docSecurity, 
//			int virtualDocument, String properties, String fileSource, byte[] byteArray) throws IOException{
//		
//		String url = buildMessageUrl(CLASSNAME_BEEOBJECT, "addFile", 
//				new Object[][]{
//						{"repositoryId", repositoryId},
//						{"filePath",filePath},
//						{"fileName",fileName},
//						{"mimeType",mimeType},
//						{"description",description},
//						{"docClassId",docClassId},
//						{"docSecurity",docSecurity},
//						{"virtualDocument",virtualDocument},
//						{"properties",properties},
//						{"fileSource",fileSource},
//						{"byteArray",new String(Base64.encode(byteArray))}
//				}
//		);
//		String respStr = makeHttpRequest(url);
//		return Integer.parseInt(respStr);
//	}
//
//	public JSONObject getFileProfile(int docId, String repositoryId) throws JSONException, IOException {
//		String url = buildMessageUrl(CLASSNAME_BEEOBJECT, "getFileProfile", 
//				new Object[][]{
//						{"docId", docId},
//						{"repositoryId",repositoryId}
//				}
//		);
//		String respStr = makeHttpRequest(url);
//		JSONObject jobj = new JSONObject(respStr);		
//		return jobj;
//	}
//	
////	public static void deleteFile(int docId, java.lang.String repositoryId)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.lang.NumberFormatException, java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = 
////				BeeObjectServiceFactory.getService();
////		
////		beeObjectService.deleteFile(docId, repositoryId);
////		
////	}
////
////	public static void deleteFile(int docId, double version,
////		java.lang.String repositoryId)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.lang.NumberFormatException, java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = 
////				BeeObjectServiceFactory.getService();
////		
////		beeObjectService.deleteFile(docId, version, repositoryId);
////		
////	}
////
////	public static java.util.List getChilds(int docId)
////		throws com.beeblos.documentlibrary.NoSuchChildException, 
////			com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = 
////				BeeObjectServiceFactory.getService();
////
////		return beeObjectService.getChilds(docId);
////	}
////
////	public static java.lang.String[] getDirectoryNames(
////		java.lang.String repositoryId, java.lang.String dirName)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = 
////				BeeObjectServiceFactory.getService();
////
////		return beeObjectService.getDirectoryNames(repositoryId, dirName);
////	}
////
////	public static com.beeblos.documentlibrary.util.DirectoryView getDirectoryView(
////		java.lang.String repositoryId)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = 
////				BeeObjectServiceFactory.getService();
////
////		return beeObjectService.getDirectoryView(repositoryId);
////	}
////
////	public static byte[] getFile(
////			int docId, java.lang.String repositoryId)
////	throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = 
////				BeeObjectServiceFactory.getService();
////
////		return beeObjectService.getFile(docId, repositoryId);
////	}
////
////	public static byte[] getFile(
////			int docId, double version, java.lang.String repositoryId)
////	throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = 
////				BeeObjectServiceFactory.getService();
////
////		return beeObjectService.getFile(docId, version, repositoryId);
////	}
////
////	public static com.beeblos.documentlibrary.model.impl.BeeObjectImpl getFileProfile(
////		int docId, java.lang.String repositoryId)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = BeeObjectServiceFactory.getService();
////
////		return beeObjectService.getFileProfile(docId, repositoryId);
////	}
////
////	public static java.util.List getFileProfiles(
////		java.lang.String repositoryId, java.lang.String dirName)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = BeeObjectServiceFactory.getService();
////
////		return beeObjectService.getFileProfiles(repositoryId, dirName);
////	}
////
////	public static int getFileProfilesSize(java.lang.String repositoryId)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = BeeObjectServiceFactory.getService();
////
////		return beeObjectService.getFileProfilesSize(repositoryId);
////	}
////
////	public static void lockFile(int docId, java.lang.String repositoryId)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		BeeObjectService beeObjectService = BeeObjectServiceFactory.getService();
////		beeObjectService.lockFile(docId, repositoryId);
////	}
////
////	@SuppressWarnings("unchecked")
////	public static java.util.Vector<org.apache.lucene.document.Document> search(java.lang.String repositoryId,
////		java.lang.String keywords, java.util.List reqFields)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = BeeObjectServiceFactory.getService();
////
////		return beeObjectService.search(repositoryId, keywords, reqFields);
////	}
////
////	@SuppressWarnings("unchecked")
////	public static java.util.Vector<org.apache.lucene.document.Document> search(java.lang.String[] repositoryIds,
////		java.lang.String keywords, java.util.List reqFields)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = BeeObjectServiceFactory.getService();
////
////		return beeObjectService.search(repositoryIds, keywords, reqFields);
////	}
////
////	public static void unlockFile(int docId)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		BeeObjectService beeObjectService = BeeObjectServiceFactory.getService();
////		beeObjectService.unlockFile(docId);
////	}
////
////	public static void updateFile(int docId, java.lang.String repositoryId,
////		java.lang.String sourceFileName, byte[] byteArray)
////		throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = BeeObjectServiceFactory.getService();
////		
////		beeObjectService.updateFile(docId, repositoryId, sourceFileName,
////			byteArray);
////	}
////
////	public static com.beeblos.documentlibrary.model.BeeObject updateFileProfile(
////		int docId, java.lang.String repositoryId, java.lang.String filePath,
////		java.lang.String fileName, java.lang.String mimeType,
////		java.lang.String description, int docClassId, int docSecurity,
////		int virtualDocument, String properties, String fileSource)
////	throws com.beeblos.documentlibrary.DocumentLibraryException, 
////			com.beeblos.documentlibrary.SystemException, 
////			java.rmi.RemoteException {
////		
////		BeeObjectService beeObjectService = BeeObjectServiceFactory.getService();
////
////		return beeObjectService.updateFileProfile(docId, repositoryId,
////			filePath, fileName, mimeType, description, docClassId, docSecurity,
////			virtualDocument, properties, fileSource);
////	}
//
//
//	
//	
//	
//}