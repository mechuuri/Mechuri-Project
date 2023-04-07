package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.*;

@Repository
public interface ItemDAO extends JpaRepository<ItemEntity,Integer>{
	@Query(value="select * from hc_item limit 0,30",nativeQuery=true)
	public List<ItemEntity> campItemList();
	
	public ItemEntity findByIno(@Param("ino") Integer ino);
	
	@Query(value="SELECT * FROM hc_item "
			+"WHERE name LIKE CONCAT('%',:name,'%') ORDER BY ino LIMIT :start, 30",nativeQuery = true)
	public List<ItemEntity> itemFindData(@Param("name") String name, @Param("start") Integer start);

	 @Query(value="SELECT CEIL(COUNT(*)/20.0) FROM hc_item "
		       +"WHERE name LIKE CONCAT('%',:name,'%')",nativeQuery = true)
	 public int itemFindTotalPage(String name);
}
