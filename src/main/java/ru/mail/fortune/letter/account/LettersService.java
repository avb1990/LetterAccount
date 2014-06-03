package ru.mail.fortune.letter.account;

import java.util.List;

public interface LettersService {
	public void addLetter(Letter letter);

	public List<Letter> getAllLetters();

	public Letter getLetter(Integer id);

	public List<Letter> getAllLettersWithoutFiles();
}
