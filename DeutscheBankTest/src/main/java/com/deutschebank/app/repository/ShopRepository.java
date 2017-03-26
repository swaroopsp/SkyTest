package com.deutschebank.app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface ShopRepository<ShopDTO> extends Repository<ShopDTO, Long> {
	public ShopDTO save(ShopDTO shop);

	public Boolean existsByShopName(String shopName);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Shop s SET s.date = CURRENT_DATE WHERE s.companyName = :companyName")
	public ShopDTO updateShopDate(@Param("companyName") String companyName);
}
