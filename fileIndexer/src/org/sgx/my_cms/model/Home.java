package org.sgx.my_cms.model;

import java.util.Collection;
import java.util.Set;

import org.sgx.my_cms.model.Repository;
/**
 * responsible of reference all repositories and system operations.
 * @author SGURIN
 *
 */
public interface Home {
	Collection<Repository> getRepositories();

	boolean addRepository(Repository r);

	boolean removeRepository(Repository r);

}