package com.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, String>{
	public UserEntity findByUsername(String username); 
	
	public UserEntity findByUserid(String userid);

	public UserEntity findByPhoneNum(String username);
	
	@Modifying
	@Transactional
	@Query(value = "update userrole set role_roleid=1 where user_userid =:id",nativeQuery = true)
	public void updateRole(@Param("id")String id);

	@Query(value = "update userrole set role_roleid=2 where user_userid =:id",nativeQuery = true)
	public void updateRoleAdmin(@Param("id")String id);
}
