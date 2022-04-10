package com.krukovska.task3.service.impl;

import com.krukovska.task3.model.Author;
import com.krukovska.task3.model.Book;
import com.krukovska.task3.model.Condition;
import com.krukovska.task3.model.Genre;
import com.krukovska.task3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

@Service
public class BookCriteriaServiceImpl implements BookService {

    public static final String AND_LABEL = "and";
    private final EntityManager entityManager;

    @Autowired
    public BookCriteriaServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> findAllByAuthor(Long authorId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        Root<Author> author = criteriaQuery.from(Author.class);
        Path<Book> bookPath = author.get("books");

        CriteriaQuery<Book> authorBooksCriteriaQuery = criteriaQuery.select(bookPath)
                .where(criteriaBuilder.equal(author.get("id"), authorId));

        return entityManager.createQuery(authorBooksCriteriaQuery).getResultList();
    }

    @Override
    public List<Book> findAllByAuthorGroup(List<Long> authorsId) {
        return new LinkedList<>();
    }

    @Override
    public List<Book> findAllByAtLeastOneAuthor(List<Long> authorsId) {
        return new LinkedList<>();
    }


    @Override
    public List<Book> findByPrice(BigDecimal price) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        CriteriaQuery<Book> selectBooksByPrice = criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("price"), price));

        return entityManager.createQuery(selectBooksByPrice).getResultList();
    }

    @Override
    public List<Book> findByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        CriteriaQuery<Book> priceRangeCriteriaQuery = criteriaQuery.select(root)
                .where(criteriaBuilder.between(root.get("price"), priceFrom, priceTo));

        return entityManager.createQuery(priceRangeCriteriaQuery).getResultList();
    }

    @Override
    public List<Book> findByPublisher(String publisher) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        CriteriaQuery<Book> selectBooksByPrice = criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("publisher"), publisher));

        return entityManager.createQuery(selectBooksByPrice).getResultList();
    }

    @Override
    public List<Book> findAllByGenre(Long genreId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        Root<Genre> genre = criteriaQuery.from(Genre.class);
        Path<Book> bookPath = genre.get("books");

        CriteriaQuery<Book> genreBooksCriteriaQuery = criteriaQuery.select(bookPath)
                .where(criteriaBuilder.equal(genre.get("id"), genreId));

        return entityManager.createQuery(genreBooksCriteriaQuery).getResultList();
    }

    @Override
    public List<Book> findAllByMultipleGenres(List<Long> genreIds) {
        return new LinkedList<>();
    }

    @Override
    public List<Book> findAllByAtLeastOneGenre(List<Long> genreIds) {
        return new LinkedList<>();
    }

    @Override
    public List<Book> findByPageAmount(long pageAmount) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        CriteriaQuery<Book> booksByPageAmountCriteriaQuery = criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("pageAmount"), pageAmount));

        return entityManager.createQuery(booksByPageAmountCriteriaQuery).getResultList();
    }

    @Override
    public List<Book> findByPageAmountRange(long pageAmountFrom, long pageAmountTo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        CriteriaQuery<Book> pageAmountRangeCriteriaQuery = criteriaQuery.select(root)
                .where(criteriaBuilder.between(root.get("pageAmount"), pageAmountFrom, pageAmountTo));

        return entityManager.createQuery(pageAmountRangeCriteriaQuery).getResultList();
    }

    @Override
    public List<Book> findByText(String input) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);

        String inputPattern = "%" + input.toLowerCase() + "%";
        Predicate titlePredicate = builder.like(builder.lower(bookRoot.get("title")), inputPattern);
        Predicate fullNamePredicate = builder.like(builder.lower(authorRoot.get("fullName")), inputPattern);
        Predicate descriptionPredicate = builder.like(builder.lower(bookRoot.get("description")), inputPattern);
        Predicate publisherPredicate = builder.like(builder.lower(bookRoot.get("publisher")), inputPattern);

        CriteriaQuery<Book> textSearchCriteriaQuery = criteriaQuery.select(bookRoot).where(builder
                .or(titlePredicate, fullNamePredicate, descriptionPredicate, publisherPredicate)).distinct(true);

        return entityManager.createQuery(textSearchCriteriaQuery).getResultList();
    }

    @Override
    public List<Book> findByParameters(List<Condition> conditions) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        List<Predicate> andPredicates = new LinkedList<>();
        List<Predicate> orPredicates = new LinkedList<>();

        for (Condition condition : conditions) {
            Predicate predicate = cb.equal(root.get(condition.getFieldName()), condition.getValue());
            if (AND_LABEL.equals(condition.getOperator())) {
                andPredicates.add(predicate);
            } else {
                orPredicates.add(predicate);
            }
        }

        if (!andPredicates.isEmpty() && orPredicates.isEmpty()) {
            query.where(andPredicates.toArray(new Predicate[0]));
        } else if (andPredicates.isEmpty() && !orPredicates.isEmpty()) {
            query.where(cb.or(orPredicates.toArray(new Predicate[0])));
        } else {
            Predicate and = cb.and(andPredicates.toArray(new Predicate[0]));
            Predicate or = cb.or(orPredicates.toArray(new Predicate[0]));
            query.where(and, or);
        }

        return entityManager.createQuery(query).getResultList();

    }

}
