package com.netas.avaya.ipps;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;
import com.netas.avaya.AbstractBaseServlet;

@Component("/searchPeople.wml")
public class SearchPeopleServlet extends AbstractBaseServlet {

	static String LdapHost = "LDAP://tge36dbsrv:389/";
    static String LdapBase = "dc=netas,dc=lab,dc=nortel,dc=com";

	@Override
	public String getServletInfo() {
		return "</searchPeople.wml>servlet";
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder();
		response.setContentType("text/vnd.wap.wml");
		
		String wml = readAllText("/peoples-wml.xml");
		String dept = request.getParameter("dept");
		String name = request.getParameter("name");
		if (name != null && name.length() > 0) {
			String[] parts = name.split(" ");
			if (parts.length > 0) {
				name = "";
				for (String s : parts) {
					name += s.substring(0,1).toUpperCase() + s.substring(1) + " ";
				}
				name = name.trim();
			}
		}
		
		try {
			Hashtable<String, Object> env = new Hashtable<String, Object>();
	        env.put(Context.SECURITY_AUTHENTICATION, "anonymous");
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        env.put(Context.PROVIDER_URL, LdapHost);
	        env.put("java.naming.ldap.attributes.binary", "objectSID");
	        
			DirContext dirCtx = new InitialLdapContext(env, null);
			SearchControls searchCtl = new SearchControls();
			searchCtl.setSearchScope(SearchControls.SUBTREE_SCOPE);
			
			String filter = "";
			if (name != null && name.length() > 0)
				filter += "(cn=" + name + ")";
			if (dept != null && dept.length() > 0)
				filter = filter.length() > 0 ? filter + "&(ou=" + dept + ")" : "(ou=" + dept + ")";
			
			NamingEnumeration<SearchResult> results = dirCtx.search(LdapBase, filter, searchCtl);
			
			SearchResult searchRes = null;
			String wmlPeople = "<p>Name:/_NAME_/</p><p><a href=\"wtai://wp/mc;/_TEL_/\">Call /_TEL_/</a></p>";
			String peoples = "";
			
			if (!results.hasMoreElements())
				wml = wml.replace("/_PEOPLES_/", "<p>Not Found...</p>");
			else {
				Attribute cn;
				Attribute telephoneNumber;
				while(results.hasMoreElements()) {
		             searchRes = (SearchResult) results.nextElement();
		             cn = searchRes.getAttributes().get("cn");
		             telephoneNumber = searchRes.getAttributes().get("telephonenumber");
		             
		             if (cn != null && cn.toString().length() > 0) {
		            	 peoples += wmlPeople
			            		 .replace("/_NAME_/", cn.toString().replace("cn:", ""))
			            		 .replace("/_TEL_/", telephoneNumber != null ? telephoneNumber.toString().replace("telephoneNumber:", "").trim() : "")
			            		 ;
		             }
		             
		        }
				wml = wml.replace("/_PEOPLES_/", peoples);
					
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
			wml = wml.replace("/_PEOPLES_/", "<p>An error oocured.</p>");
		}
		
		response.setContentType("text/vnd.wap.wml");
		sb.append(wml);
		
		response.getOutputStream().println(sb.toString());
	}

}
