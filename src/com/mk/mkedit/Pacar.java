package com.mk.mkedit;

public class Pacar {

	private long id;
	private String nama_anda;
	private String pacar_anda;
	private String alamat_pacar;

	public Pacar() {

	}

	public Pacar(long id, String nama_anda, String pacar_anda,
			String alamat_pacar) {
		this.id = id;
		this.nama_anda = nama_anda;
		this.pacar_anda = pacar_anda;
		this.alamat_pacar = alamat_pacar;
	}

	public Pacar(String nama_anda, String pacar_anda, String alamat_pacar) {
		this.nama_anda = nama_anda;
		this.pacar_anda = pacar_anda;
		this.alamat_pacar = alamat_pacar;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String get_namaAnda() {
		return nama_anda;
	}

	public void set_namaAnda(String nama_anda) {
		this.nama_anda = nama_anda;
	}

	public String get_pacarAnda() {
		return pacar_anda;
	}

	public void set_pacarAnda(String pacar_anda) {
		this.pacar_anda = pacar_anda;
	}

	public String get_alamatPacar() {
		return alamat_pacar;
	}

	public void set_alamatPacar(String alamat_pacar) {
		this.alamat_pacar = alamat_pacar;
	}

	/*
	 * @Override public String toString() { return nama_anda; }
	 */
}
