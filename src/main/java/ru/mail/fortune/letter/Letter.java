package ru.mail.fortune.letter;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "LETTERS")
public class Letter {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Column(name = "number")
	@NotEmpty
	@Size(max = 20)
	private String number;

	@Column(name = "letterDate")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date letterDate;

	@Column(name = "theme")
	@NotEmpty
	@Size(max = 250)
	private String theme;

	@Column(name = "published")
	private boolean published;

	@OneToOne(mappedBy = "letter", cascade = CascadeType.ALL)
	@NotNull
	private LetterFile letterFile = new LetterFile(this);

	@Column(name = "note")
	@Size(max = 1000)
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(Date date) {
		this.letterDate = date;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public byte[] getFile() {
		return letterFile.getFile();
	}

	public void setFile(byte[] file) {
		letterFile.setFile(file);
	}

	public String getFileType() {
		return letterFile.getFileType();
	}

	public void setFileType(String fileType) {
		letterFile.setFileType(fileType);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LetterFile getLetterFile() {
		return letterFile;
	}

	public void setLetterFile(LetterFile letterFile) {
		this.letterFile = letterFile;
	}

	public static String PDF_FILE_TYPE_NAME = ".pdf";
	public static String JPEG_FILE_TYPE_NAME = ".jpg";

	public static boolean isCorrectType(String type) {
		return PDF_FILE_TYPE_NAME.equals(type)
				|| JPEG_FILE_TYPE_NAME.equals(type);
	}
}
