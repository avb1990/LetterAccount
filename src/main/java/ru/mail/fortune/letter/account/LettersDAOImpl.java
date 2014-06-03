package ru.mail.fortune.letter.account;

import java.util.ArrayList;
import java.util.Date;
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

		return (List<Letter>) getSession().createQuery("from letter").list();
	}

	public Letter getLetter(Integer id) {
		return id != null ? (Letter) getSession().get(Letter.class, id) : null;
	}

	public List<Letter> getAllLettersWithoutFiles() {

		List<Object[]> letterVals = getSession()
				.createQuery(
						"select l.id, l.number,l.letterDate, l.theme,l.published,l.note from Letter l")
				.list();
		List<Letter> letters = new ArrayList<Letter>(letterVals.size());
		for (Object[] objects : letterVals) {
			Letter letter = new Letter();
			letter.setId((Integer) objects[0]);
			letter.setNumber((String) objects[1]);
			letter.setLetterDate((Date) objects[2]);
			letter.setTheme((String) objects[3]);
			letter.setPublished((Boolean) objects[4]);
			letter.setNote((String) objects[5]);
			letters.add(letter);
		}
		return letters;
	}
}
