package ru.mail.fortune.letter.account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	@Column(name = "file")
	@NotNull
	@Size(min = 1)
	private byte[] file;

	@Column(name = "fileType")
	@NotNull
	private String fileType;

	@Column(name = "note")
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
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
