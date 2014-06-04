package ru.mail.fortune.letter.dao;

import java.util.List;

import ru.mail.fortune.letter.Letter;

public interface LettersDAO {
	public void addLetter(Letter letter);

	public Letter getLetter(Integer id);

	public List<Letter> getAllLetters();

	public List<Letter> getAllLettersWithoutFiles();

	public void updateLetter(Letter letter);
}
