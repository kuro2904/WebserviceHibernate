package com.stu.Model;
// Generated Apr 27, 2023, 6:03:44 AM by Hibernate Tools 4.3.6.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Sinhvien generated by hbm2java
 */
@Entity
@Table(name = "sinhvien", catalog = "qlsv")
public class Sinhvien implements java.io.Serializable {

	@Override
	public String toString() {
		return "Sinhvien [mssv=" + mssv +  ", hoten=" + hoten + ", dtb=" + dtb + "]";
	}
	@Expose
	private Integer mssv;
	@Expose
    @SerializedName("lop")
	private Lop lop;
	@Expose
	private String hoten;
	@Expose
	private Double dtb;

	public Sinhvien() {
	}

	public Sinhvien(Lop lop) {
		this.lop = lop;
	}

	public Sinhvien(Lop lop, String hoten, Double dtb) {
		this.lop = lop;
		this.hoten = hoten;
		this.dtb = dtb;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "mssv", unique = true, nullable = false)
	public Integer getMssv() {
		return this.mssv;
	}

	public void setMssv(Integer mssv) {
		this.mssv = mssv;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "malop", nullable = false)
	public Lop getLop() {
		return this.lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}

	@Column(name = "hoten", length = 50)
	public String getHoten() {
		return this.hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	@Column(name = "dtb", precision = 22, scale = 0)
	public Double getDtb() {
		return this.dtb;
	}

	public void setDtb(Double dtb) {
		this.dtb = dtb;
	}

}
