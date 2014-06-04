package ru.mail.fortune.letter.account;

import java.util.List;

public interface LettersDAO {
	public void addLetter(Letter letter);

	public Letter getLetter(Integer id);

	public List<Letter> getAllLetters();

	public List<Letter> getAllLettersWithoutFiles();

	public void updateLetter(Letter letter);
}
