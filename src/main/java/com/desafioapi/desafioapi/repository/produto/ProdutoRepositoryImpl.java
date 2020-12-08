/*package com.desafioapi.desafioapi.repository.produto;

import com.desafioapi.desafioapi.model.Produto;
import com.desafioapi.desafioapi.repository.filter.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;


    @Override
    public Page<Produto> filter(ProdutoFilter produtoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
        Root<Produto> root = criteria.from(Produto.class);
        Predicate[] predicates = createRestrictions(produtoFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<Produto> query = manager.createQuery(criteria);
        addRestrictionsOfPagination(query, pageable);
    }

    private void addRestrictionsOfPagination(TypedQuery<Produto> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRegisterPerPage = pageable.getPageSize();
        int firstRegisterOfPage = currentPage * totalRegisterPerPage;

        query.setFirstResult(firstRegisterOfPage);
        query.setMaxResults(totalRegisterPerPage);
    }

    private Predicate[] createRestrictions(ProdutoFilter produtoFilter, CriteriaBuilder builder, Root<Produto> root) {
        CriteriaBuilder build = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria  = builder.createQuery(Long.class);
        Root<Produto> root = criteria.from(Produto.class);

        Predicate[] predicates = createRestrictions(produtoFilter, build, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
*/