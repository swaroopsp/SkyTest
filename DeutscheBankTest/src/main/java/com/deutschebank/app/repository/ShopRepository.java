package com.deutschebank.app.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.deutschebank.app.db.model.ShopDTO;

@NoRepositoryBean
public interface ShopRepository<ShopDTO> extends Repository<ShopDTO, Long> {
	public ShopDTO save(ShopDTO shop);
	
//	@Query("SELECT t FROM Todo t WHERE " +
//		            "LOWER(t.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
//		            "LOWER(t.description) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
//    Page<Todo> findBySearchTerm(@Param("searchTerm") String searchTerm);

}
