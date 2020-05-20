package kr.ac.hansung.cse.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Product;

// jdbc -> spring jdbc->hibernate
@Repository
@Transactional // 모든 메소드들이 트랜잭션 수행 aop기능
public class ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);

		return product;
	}

	public List<Product> getProducts() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Product";

		Query<Product> query = session.createQuery(hql, Product.class);
		List<Product> productList = query.getResultList();

		return productList;
	}

	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush(); // 꼭 해야하진 않음 commit 할때 자동적으로 flush가 이루어짐
	}

	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();

	}

	public void updateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}
}
