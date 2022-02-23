package com.krukovska.task3.repository;

import com.krukovska.task3.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b inner join b.authors a where a.id= :id ")
    List<Book> findAuthorBook(Long id);

    @Query("select distinct b from Book b inner join b.authors a where a.id in (:ids) group by b.id " +
            "having count(b.id) >= (:authorsAmount) ")
    List<Book> findGroupAuthorsBook(@Param("ids") List<Long> authorsId, long authorsAmount);

    @Query("select distinct b from Book b inner join b.authors a where a.id in(:ids)")
    List<Book> findByAtLeastOneGroupAuthor(@Param("ids") List<Long> ids);

    @Query("select b from Book b where b.price >= :priceFrom and b.price <= :priceTo")
    List<Book> findByPrice(BigDecimal priceFrom, BigDecimal priceTo);

    @Query("select b from Book b where b.price = :price")
    List<Book> findByPrice(BigDecimal price);

    @Query("select b from Book b where b.publisher = :publisher")
    List<Book> findByPublisher(String publisher);

    @Query("select b from Book b inner join b.genres g where g.id= :id ")
    List<Book> findAllByGenre(@Param("id") Long genreId);

    @Query("select b from Book b where b.pageAmount = :pageAmount")
    List<Book> findByPageAmount(long pageAmount);

    @Query("select b from Book b where b.pageAmount >= :pageAmountFrom and b.pageAmount <= :pageAmountTo")
    List<Book> findByPageAmountRange(long pageAmountFrom, long pageAmountTo);

    @Query("select distinct b from Book b inner join b.genres g where g.id in (:genreIds) group by b.id " +
            "having count(b.id) >= (:genreAmount) ")
    List<Book> findAllByMultipleGenres(List<Long> genreIds, long genreAmount);

    @Query("select distinct b from Book b inner join b.genres g where g.id in(:genreIds)")
    List<Book> findAllByAtLeastOneGenre(List<Long> genreIds);

    @Query("select distinct b from Book b inner join b.authors a where" +
            " lower(b.title) like concat('%', :input, '%') " +
            "or lower(a.fullName) like concat('%', :input, '%') " +
            "or lower(b.description) like concat('%', :input, '%') " +
            "or lower(b.publisher) like concat('%', :input, '%') ")
    List<Book> findByText(String input);

}