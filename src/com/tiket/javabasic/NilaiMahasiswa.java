package com.tiket.javabasic;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NilaiMahasiswa {

	public static void main(String[] args){

        Scanner input = new Scanner(System.in); //object scanner baru
        final String QST = "Masukan ";
        
        System.out.println("|        Data Nilai Pemrogrman JAVA        |");
        System.out.println("============================================");
        
        System.out.print("Masukan jumlah mahasiswa: " );
        int nMahasiswa = Integer.valueOf(input.nextLine()); 
        System.out.println("");
        System.out.println("===============================================");
        
        ArrayList<Mahasiswa> mahasiswaList = new ArrayList<Mahasiswa>();
        int order = 1;
        
        for (int i=0; i<nMahasiswa; i++) { 

        	Mahasiswa mahasiswa = new Mahasiswa();
        	System.out.println("");        	
            
            System.out.print(QST + "NIM \t\t: " );
            mahasiswa.setNim(input.nextLine());
            
            System.out.print(QST + "Nama \t\t: " );
            mahasiswa.setNama(input.nextLine());

            System.out.print(QST + "Nilai Absensi \t: " );
            mahasiswa.setNilaiAbsensi(input.nextLine());

            System.out.print(QST + "Nilai UTS \t: " );
            mahasiswa.setNilaiUTS(input.nextLine());

            System.out.print(QST + "Nilai UAS \t: " );
            mahasiswa.setNilaiUAS(input.nextLine());

            mahasiswa.setOrder(order+++"");
            
            mahasiswaList.add(mahasiswa);
            System.out.println("");        	
        }
        
        
        Nilai nilai = (absen, uts, uas) -> Integer.valueOf(absen)*0.2 + Integer.valueOf(uts)*0.4 + Integer.valueOf(uas)*0.4; 

        Grade gradeMahasiswa = new Grade() {
			public String process(double nilai) {
	        	if (85 <= nilai && nilai <=100 ) { 
	        		return "A";
	        	}
	        	else if (76 <= nilai && nilai <=84) { 
	        		return "B";
	        	} else if (61 <= nilai && nilai <=75) { 
	        		return "C";
	        	} else if (46 <= nilai && nilai <=60) { 
	        		return "D";
	        	} else return "E"; 
			}
		};

		try { 
			PrintStream fileStream = new PrintStream("NilaiMahasiswa.txt");
			System.setOut(fileStream);			
			
			System.out.println("==================================================================");
	        System.out.println("No. | NIM | Nama | Nilai Akhir | Grade");
	        System.out.println("==================================================================");

	        mahasiswaList.forEach((mhs) -> {
	        	System.out.print(mhs.getOrder()+"\t");
	            System.out.print(mhs.getNim()+"\t");
	            System.out.print(mhs.getNama()+"\t");

	            Double hasil = nilai.process(
	            		mhs.getNilaiAbsensi(), 
	            		mhs.getNilaiUTS(), 
	            		mhs.getNilaiUAS()
	            		);
	            System.out.print(hasil.intValue()+"\t");   
	            String grd = gradeMahasiswa.process(hasil);
	            mhs.setGrade(grd);
	            System.out.println(grd+"\t");
	            
	        });

	        System.out.println("Jumlah mahasiswa: " + mahasiswaList.size());

	        int jumlahLulus = 0;
	        for (int i=0; i<mahasiswaList.size(); i++) { 
	        	Mahasiswa mhs = mahasiswaList.get(i);
	            if (mhs.getIsLulus(mhs.getGrade())) { 
	                jumlahLulus = jumlahLulus + 1;            	
	            }
	        }
	        System.out.println("Jumlah mahasiswa lulus: " + jumlahLulus);
	        System.out.println("Jumlah mahasiswa tidak lulus: " + (mahasiswaList.size() - jumlahLulus));

	        fileStream.close();
		} catch (Exception e) {
			System.out.println("Error during reading/writing");
		} finally {
			//
		}        
        
	}

	interface Nilai {
		Double process(String absen, String uts, String uas);
	}
	
	interface Grade {
		String process(double nilai);
	}
	
	static private class Mahasiswa {
		String nim;
		String nama;
		String nilaiUTS;
		String nilaiUAS;
		String nilaiAbsensi;
		String order;
		String grade;

		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
		public String getNim() {
			return nim;
		}
		public void setNim(String nim) {
			this.nim = nim;
		}
		public String getNama() {
			return nama;
		}
		public void setNama(String nama) {
			this.nama = nama;
		}
		public String getNilaiUTS() {
			return nilaiUTS;
		}
		public void setNilaiUTS(String nilaiUTS) {
			this.nilaiUTS = nilaiUTS;
		}
		public String getNilaiUAS() {
			return nilaiUAS;
		}
		public void setNilaiUAS(String nilaiUAS) {
			this.nilaiUAS = nilaiUAS;
		}
		public String getNilaiAbsensi() {
			return nilaiAbsensi;
		}
		public void setNilaiAbsensi(String nilaiAbsensi) {
			this.nilaiAbsensi = nilaiAbsensi;
		}
		
		public boolean getIsLulus(String grade) { 
			if ("ABC".contains(grade)) { 
				return true;
			} else {
				return false;
			}
		}
	}

}
