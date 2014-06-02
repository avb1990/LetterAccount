package ru.mail.fortune.letter.account;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LettersDAOImpl implements LettersDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addLetter(Letter letter) {
		getSession().save(letter);
	}

	public List<Letter> getAllLetters() {

		return (List<Letter>) getSession().createQuery("from Letter").list();
	}

	public Letter getLetter(Integer id) {
		return id != null ? (Letter) getSession().get(Letter.class, id) : null;
	}
}
