package ru.mail.fortune.letter.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "letterFile")
public class LetterFile {

	public LetterFile() {
		// TODO Auto-generated constructor stub
	}

	public LetterFile(Letter letter) {
		this.letter = letter;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "letter"))
	@NotNull
	private Integer id;

	@Column(name = "fileType")
	@NotEmpty
	private String fileType;

	@Column(name = "file")
	@NotEmpty
	@Size(min = 1, max = 134217728)
	private byte[] file;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Letter letter;

	public Letter getLetter() {
		return letter;
	}

	public void setLetter(Letter letter) {
		this.letter = letter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer letterId) {
		this.id = letterId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileName) {
		this.fileType = fileName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
