package org.sgx.my_cms.impl1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.sgx.my_cms.model.Home;
import org.sgx.my_cms.model.Repository;
/**
 * in this impl, system operations support mywss interface.
 * 
 * @author SGURIN
 *
 */
public class Home1 implements Home {
	private static Home instance;

	private Home1() {
		repositories=new HashSet<Repository>();
	}

	public static Home getInstance() {
		if (null == instance) {
			instance = new Home1();
		}
		return instance;
	}
	public Set<Repository> repositories;


	/* (non-Javadoc)
	 * @see org.sgx.my_cms.impl1.Home#getRepositories()
	 */
	public Collection<Repository> getRepositories(){
		return repositories;
	}

	/* (non-Javadoc)
	 * @see org.sgx.my_cms.impl1.Home#addRepository(org.sgx.my_cms.model.Repository)
	 */
	public boolean addRepository(Repository r){
		repositories.add(r);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.sgx.my_cms.impl1.Home#removeRepository(org.sgx.my_cms.model.Repository)
	 */
	public boolean removeRepository(Repository r){
		return true;
	}
	
//	public void addDocument(long repoId, )
	
}
