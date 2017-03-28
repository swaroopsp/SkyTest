package com.deutschebank.app.repository;

import com.deutschebank.app.db.model.Shop;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
/**
 * Created by swaroop on 27/03/2017.
 */
@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
	public Shop findByShopName(String shopName);

	@Modifying
	@Query("UPDATE Shop s SET s.version = :version WHERE s.shopName = :shopName")
	public void updateShop(@Param("shopName") String shopName, @Param("version") long version);
}
