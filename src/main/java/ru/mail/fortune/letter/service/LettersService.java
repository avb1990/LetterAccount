package ru.mail.fortune.letter.service;

import java.util.List;

import ru.mail.fortune.letter.Letter;

public interface LettersService {
	public void addLetter(Letter letter);

	public List<Letter> getAllLetters();

	public Letter getLetter(Integer id);

	public List<Letter> getAllLettersWithoutFiles();

	public void updateLetter(Letter letter);
}
