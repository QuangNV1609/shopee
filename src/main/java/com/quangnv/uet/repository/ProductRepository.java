package com.quangnv.uet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword%")
	public Page<ProductEntity> findByKeyword(String keyword, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND p.category.categoryName IN :categoryNames")
	public Page<ProductEntity> findAllByCategoryNames(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND " + "p.rate >= :rate")
	public Page<ProductEntity> findAllByRate(@Param("keyword") String keyword, @Param("rate") Double rate,
			Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.price >= :priceMin")
	public Page<ProductEntity> findAllByPriceMin(@Param("keyword") String keyword, @Param("priceMin") Double priceMin,
			Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.price <= :priceMax")
	public Page<ProductEntity> findAllByPriceMax(@Param("keyword") String keyword, @Param("priceMax") Double priceMax,
			Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.price <= :priceMax And p.price >= :priceMin")
	public Page<ProductEntity> findAllByPrice(@Param("keyword") String keyword, @Param("priceMax") Double priceMax,
			@Param("priceMin") Double priceMin, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.rate >= :rate")
	public Page<ProductEntity> findAllByCategoryNamesAndRate(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price >= :priceMin")
	public Page<ProductEntity> findAllByCategoryNamesAndPriceMin(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("priceMin") Double priceMin, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price <= :priceMax")
	public Page<ProductEntity> findAllByCategoryNamesAndPriceMax(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("priceMax") Double priceMax, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price >= :priceMin AND p.price <= :priceMax")
	public Page<ProductEntity> findAllByCategoryNamesAndPrice(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("priceMin") Double priceMin,
			@Param("priceMax") Double priceMax, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.rate >= :rate AND " + "p.price <= :priceMax")
	public Page<ProductEntity> findAllByCategoryNamesAndRateAndPriceMax(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate,
			@Param("priceMax") Double priceMax, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.rate >= :rate AND " + "p.price >= :priceMin")
	public Page<ProductEntity> findAllByCategoryNamesAndRateAndPriceMin(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate,
			@Param("priceMin") Double priceMin, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.rate >= :rate AND " + "p.price >= :priceMin")
	public Page<ProductEntity> findAllByRateAndPriceMin(@Param("keyword") String keyword, @Param("rate") Double rate,
			@Param("priceMin") Double priceMin, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.rate >= :rate AND " + "p.price <= :priceMax")
	public Page<ProductEntity> findAllByRateAndPriceMax(@Param("keyword") String keyword, @Param("rate") Double rate,
			@Param("priceMax") Double priceMax, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.rate >= :rate AND " + "p.price >= :priceMin AND p.price <= :priceMax")
	public Page<ProductEntity> findAllByRateAndPrice(@Param("keyword") String keyword, @Param("rate") Double rate,
			@Param("priceMin") Double priceMin, @Param("priceMax") Double priceMax, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price >= :priceMin AND " + "p.rate >= :rate")
	public Page<ProductEntity> findByCategoryNamesAndRateANdPriceMin(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate,
			@Param("priceMin") Double priceMin, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price <= :priceMax AND " + "p.rate >= :rate")
	public Page<ProductEntity> findByCategoryNamesAndRateANdPriceMax(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate,
			@Param("priceMax") Double priceMax, Pageable pageable);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price >= :priceMin AND p.price <= :priceMax AND "
			+ "p.rate >= :rate")
	public Page<ProductEntity> findAll(@Param("keyword") String keyword, @Param("categoryNames") String[] categoryNames,
			@Param("rate") Double rate, @Param("priceMin") Double priceMin, @Param("priceMax") Double priceMax,
			Pageable pageable);

	public Page<ProductEntity> findByCategoryCategoryName(String categoryName, Pageable pageable);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword%")
	public long countByKeyWord(@Param("keyword") String keyword);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND p.category.categoryName IN :categoryNames")
	public long countByCategoryNames(@Param("keyword") String keyword, @Param("categoryNames") String[] categoryNames);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.rate >= :rate")
	public long countByRate(@Param("keyword") String keyword, @Param("rate") Double rate);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.price >= :priceMin")
	public long countByPriceMin(@Param("keyword") String keyword, @Param("priceMin") Double priceMin);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.price <= :priceMax")
	public long countByPriceMax(@Param("keyword") String keyword, @Param("priceMax") Double priceMax);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.price <= :priceMax And p.price >= :priceMin")
	public long countByPrice(@Param("keyword") String keyword, @Param("priceMax") Double priceMax,
			@Param("priceMin") Double priceMin);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.rate >= :rate")
	public long countByCategoryNamesAndRate(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price >= :priceMin")
	public long countByCategoryNamesAndPriceMin(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("priceMin") Double priceMin);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price <= :priceMax")
	public long countByCategoryNamesAndPriceMax(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("priceMax") Double priceMax);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price >= :priceMin AND p.price <= :priceMax")
	public long countByCategoryNamesAndPrice(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("priceMin") Double priceMin,
			@Param("priceMax") Double priceMax);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.rate >= :rate AND " + "p.price >= :priceMin")
	public long countByCategoryNamesAndRateAndPriceMin(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate,
			@Param("priceMin") Double priceMin);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.rate >= :rate AND " + "p.price <= :priceMax")
	public long countByCategoryNamesAndRateAndPriceMax(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate,
			@Param("priceMax") Double priceMax);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.rate >= :rate AND " + "p.price >= :priceMin")
	public long countByRateAndPriceMin(@Param("keyword") String keyword, @Param("rate") Double rate,
			@Param("priceMin") Double priceMin);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.rate >= :rate AND " + "p.price <= :priceMax")
	public long countByRateAndPriceMax(@Param("keyword") String keyword, @Param("rate") Double rate,
			@Param("priceMax") Double priceMax);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.rate >= :rate AND " + "p.price >= :priceMin AND p.price <= :priceMax")
	public long countByRateAndPrice(@Param("keyword") String keyword, @Param("rate") Double rate,
			@Param("priceMin") Double priceMin, @Param("priceMax") Double priceMax);

	@Query(value = "SELECT COUNT(*) FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price >= :priceMin AND p.price <= :priceMax AND "
			+ "p.rate >= :rate")
	public long countByCategoryNamesAndRateAndPrice(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate,
			@Param("priceMin") Double priceMin, @Param("priceMax") Double priceMax);

	public long countByCategoryCategoryName(String categoryName);

	@Query(value = "SELECT p FROM ProductEntity p WHERE CONCAT(p.productName) LIKE %:keyword% AND "
			+ "p.category.categoryName IN :categoryNames And " + "p.price >= :priceMin AND p.price <= :priceMax AND "
			+ "p.rate >= :rate")
	public Page<ProductEntity> test(@Param("keyword") String keyword,
			@Param("categoryNames") String[] categoryNames, @Param("rate") Double rate,
			@Param("priceMin") Double priceMin, @Param("priceMax") Double priceMax, Pageable pageable);

}
