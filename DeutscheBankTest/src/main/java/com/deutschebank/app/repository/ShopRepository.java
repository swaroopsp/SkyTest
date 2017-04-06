package com.deutschebank.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deutschebank.app.db.model.Shop;

/**
 * Created by swaroop on 27/03/2017.
 */
@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
	public Shop findByShopName(String shopName);

	@Modifying
	@Query("UPDATE Shop s SET s.version = :version WHERE s.shopName = :shopName")
	public void updateShop(@Param("shopName") String shopName, @Param("version") long version);
	
	//possible solution for quicker post code search filtering
	@Query("select u from Shop u where u.postCode like '%:partialpostCode%'")
	List<Shop> findNearestShopBypostCodeLike(@Param("partialpostCode") String partialpostCode);
}
