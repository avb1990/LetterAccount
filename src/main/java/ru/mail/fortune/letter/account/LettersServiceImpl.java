package ru.mail.fortune.letter.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LettersServiceImpl implements LettersService {

	@Autowired
	LettersDAO lettersDao;

	@Transactional
	public void addLetter(Letter letter) {
		lettersDao.addLetter(letter);
	}

	@Transactional
	public List<Letter> getAllLetters() {
		// TODO Auto-generated method stub
		return lettersDao.getAllLetters();
	}

	@Transactional
	public Letter getLetter(Integer id) {
		// TODO Auto-generated method stub
		return lettersDao.getLetter(id);
	}

	@Transactional
	public List<Letter> getAllLettersWithoutFiles() {
		return lettersDao.getAllLettersWithoutFiles();
	}

	@Transactional
	public void updateLetter(Letter letter) {
		lettersDao.updateLetter(letter);

	}
}
